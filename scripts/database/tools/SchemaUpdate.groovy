import groovy.sql.Sql
import java.util.concurrent.CountDownLatch
import org.apache.commons.dbcp.BasicDataSource
import java.io.*
import org.apache.commons.io.FilenameUtils


class PropertiesHolder
{
  static def properties
}

class WildcardFilter implements FileFilter {

        private String wildcard;

        public WildcardFilter(String wildcard) {
            this.wildcard = wildcard;
        }

        public boolean accept(File pathname) {
            return FilenameUtils.wildcardMatch(pathname.getName(), wildcard);
        }

    }

class FileUtils 
{

	def static listFiles(File directory, FileFilter[] filters) {

			boolean recurse = filters.length > 1;
			if (recurse) {
				FileFilter[] subFilters = new FileFilter[filters.length - 1];
				System.arraycopy(filters, 1, subFilters, 0, filters.length - 1);
				List<File> files = new ArrayList<File>();
				for (File file : directory.listFiles(filters[0])) {
					if (file.isDirectory()) {
						files.addAll(Arrays.asList(listFiles(file.getAbsoluteFile(), subFilters)));
					}
				}
				return files.toArray(new File[files.size()]);
			} else {
				return directory.getAbsoluteFile().listFiles(filters[0]);
			}
		}
		
    def static  listFiles(String directory, String wildcard) {
        return listFiles(new File(directory), wildcard);
    }

    
    def static  listFiles(File directory, String wildcard) {
        List<FileFilter> filters = new ArrayList<FileFilter>();
        String finalWildCard = wildcard.replace('\\', '/');
        List<String> wildcards = Arrays.asList(finalWildCard.split("/"));
        for (String split : wildcards) {
            filters.add(new WildcardFilter(split));
        }
        if (directory.exists()) {
            return listFiles(directory.getAbsoluteFile(), filters.toArray(new FileFilter[filters.size()]));
        } else {
            throw new IllegalArgumentException("Unexisting directory " + directory.getAbsolutePath());
        }
    }
}

class PrintStreamInterceptor extends PrintStream
{
  def outputStream
  def interceptedOutpuStream
	
  def PrintStreamInterceptor(def outputStream) {
    super(outputStream)
    this.outputStream = outputStream
    this.interceptedOutpuStream = new ByteArrayOutputStream()
  }
	
  @Override
  public void write(byte[] buffer, int offset, int length) {
    outputStream.write(buffer, offset, length)
    interceptedOutpuStream.write(buffer, offset, length)
  }
	
  def getOutput() {
    return interceptedOutpuStream
  }
}


def runAndWaitProc(def proc, def inputs) {
  def interceptedSysOut = new PrintStreamInterceptor(System.out)
  def interceptedSysErr = new PrintStreamInterceptor(System.err)
  Thread.start { interceptedSysOut << proc.in  }
  Thread.start { interceptedSysErr << proc.err }
  inputs.each{ it -> println "piping in ${it}"; proc << it +"\n" ;println "${it} is executed"}
  proc.out.close()
  proc.waitForOrKill(0)
  failIfAnyOracleErrors(interceptedSysOut.output, interceptedSysErr.output)
}

def runAndWait(def args, def inputs) {
  println "Running " + args.join(" ");
  runAndWaitProc(args.execute(),inputs);
}

def runAndWait(String script, File where, def inputs) {
  def envs = []
  System.getenv().each{ key, value -> envs << key +"=" + value; }

  println "Running [$script] in [$where] with input [$inputs]"

  runAndWaitProc(script.execute(envs,where),inputs);
}


def runAndWait(List args, List inputs, int processes) {
  println "Running " + args.join(" ") + " with " + processes
  CountDownLatch doneSignal = new CountDownLatch(processes);
  allInputs = [];
  for(threadNo in 0..<processes) { allInputs << [] }
  int i = 0;
  inputs.each{ it -> allInputs[ i++ % processes ] << it }
  
  for(threadNo in 0..<processes) {
    final number = threadNo;
        Thread.start{
        println number + " " + allInputs[number].join(" ");
        runAndWait(args, allInputs[number]);
        doneSignal.countDown();
        }
  } 
  doneSignal.await();
}


boolean isAnOracleErrorToIgnore(def logLine) {
  def oracleErrorsToIgnore = ['ORA-14074']
  for (oracleError in oracleErrorsToIgnore) {
    if (logLine =~ /$oracleError/) return true
  }
  return false
}

boolean hasAnyOracleErrors(def log) {
  def oracleErrorsInLog = []
  log.toString().eachLine() { logLine ->
    if (logLine =~ /ORA-/ && !isAnOracleErrorToIgnore(logLine)) {
      oracleErrorsInLog << logLine
    }
  }
  def hasErrors = oracleErrorsInLog.size > 0
  if (hasErrors) reportOracleErrors(oracleErrorsInLog)
  return hasErrors
}


def failIfAnyOracleErrors(def sysOut, def sysErr) {
  def shouldOracleErrorsFailTheUpdate = 'y'.equalsIgnoreCase(PropertiesHolder.properties['fail-schema-update-on-error'])
  if (shouldOracleErrorsFailTheUpdate) {
    print "\n\nChecking oracle errors in logs ... "
    if (hasAnyOracleErrors(sysOut) || hasAnyOracleErrors(sysErr)) {
      System.exit(1)
    } else {
      println "no errors"
    }
  }
}

def mvnUpdate(def project, boolean fullImport) {
    println "Updating Schema with fullImport "+fullImport
    project.properties['db-full-import'] = ( fullImport )? "Y" : "N"
    mvnUpdate(project)
}


def mvnUpdate(def project) {
       
	//Condition used to select which schema (oracle or sqlserver) to use
    dbType = project.properties['db-type']
    if (dbType != null ){
        println "\nschema type: $dbType"
    }
    else {
        println "DB type choice not recognised...defaulting to 'oracle'"
        dbType = 'oracle'
    }            
    println "db schema file: " + project.properties['db-schema-file']
    println "user: " + project.properties["$dbType-user"]
    println "url: " + project.properties["$dbType-url"]
    println "rootDirectory: " + project.properties['db-root-dir']
    println "fullImport: " + project.properties['db-full-import']
    println "fail-schema-update-on-error: " + project.properties['fail-schema-update-on-error']
		
    File schemaFile = new File(new File(project.properties['db-schema-file']).absolutePath);
    def props = evaluate(schemaFile);
    props.put("user", project.properties["$dbType-user"])
    props.put("password", project.properties["$dbType-password"])
    props.put("url", project.properties["$dbType-url"])
    props.put("user_suffix", project.properties["$dbType-user-suffix"])
    props.put("full-import", project.properties['db-full-import'])
    //redirect root
    props.put("root", project.properties['db-root-dir'])
    props.put("fail-schema-update-on-error", project.properties['fail-schema-update-on-error'])
    update(props)
}

def update(Map props) {
		println "STARTING SchemaUpdate"
        PropertiesHolder.properties = props
        int startTime = System.currentTimeMillis();
		
		//get the sid
        def urls = props.url.split(":");
        def sid = urls[urls.length - 1];
		
		println "url : " + props.url + ", user: " + props.user + ", sid: " + sid
		def parallel = props['parallel'] == null ? 1 : props['parallel'];
		
		//root dir
        def rootDir = new File((String)props.root).absoluteFile.absolutePath;
        def oracleId= "${props.user}@${sid}.world/${props.password}"
		
		//performing the full import by default it is true
        def fullImport = (props['full-import'] == "Y");
		
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName('oracle.jdbc.driver.OracleDriver');
        dataSource.setUrl(props.url);
        dataSource.setUsername(props.user);
        dataSource.setPassword(props.password);
		
		db = new Sql(dataSource)
		 
		  if (fullImport) {
                dropObjectsForFullImport(props,db);
        }
		
		  //Scripts are organized in script set ( dir => directory, wildcards => list of wildcards ) 
			props.scripts.each{ scriptSet ->
			  if (scriptSet.commands != null) {
				runAndWait(["sqlplus" , oracleId], scriptSet.commands); 
			  } else {
				def localDir = rootDir + File.separator + scriptSet.dir;
				def sqlInputs = [ ];
				scriptSet.wildcards.each{ wildcard -> 
						//Run scripts  one by one individually
						FileUtils.listFiles(localDir, wildcard).each {
						  match -> def scriptToRun = "@" + ( match.absolutePath.substring(localDir.length() + 1));
					  print "Running $scriptToRun from $localDir\n";
					  sqlInputs << scriptToRun
						  if (scriptSet.inputs != null) {
						scriptSet.inputs.each { it -> inputs <<  evaluate("${it}") } ;
						  }
						}; //FileUtils.listFiles
				} //scriptSet.wildcards
				println "running the following"
				println sqlInputs;
				runAndWait("sqlplus $oracleId", new File(localDir), sqlInputs);    
			  }
			}
			
	
		int elapsedTime = System.currentTimeMillis() - startTime;
        println "Elapsed time " + elapsedTime;
}

def dropObjectsForFullImport(Map props, Sql db){
				//get the sid
				def urls = props.url.split(":");
				def sid = urls[urls.length - 1];
                def oracleId= "${props.user}@${sid}.world/${props.password}"
				def parallel = props['parallel'] == null ? 1 : props['parallel'];
				def statements = [];
                println "Dropping views"
                db.eachRow('SELECT VIEW_NAME FROM USER_VIEWS ORDER BY VIEW_NAME ASC') {
                  row -> statements << "DROP VIEW ${row.VIEW_NAME};\n"; 
                }
				println "statements:$statements" 
				if(statements) {
					runAndWait(["sqlplus", oracleId ], statements, parallel);
				} else {
					println "No view to drop"
				}
                
                statements = [];
                
                println "Dropping sequences"
                db.eachRow('SELECT SEQUENCE_NAME FROM USER_SEQUENCES ORDER BY SEQUENCE_NAME ASC') {
                  row -> statements << "DROP SEQUENCE ${row.SEQUENCE_NAME};";
                }
				
				if(statements) {
					runAndWait(["sqlplus", oracleId ], statements, 1);
				} else {
					println "No sequence to drop"
				}
                
                
                statements = [];
                println "Dropping foreign key constraints"
                db.eachRow("select table_name, constraint_name from user_cons_columns where constraint_name like '%FK%'") {
                  row -> statements << "ALTER TABLE ${row.table_name} DROP CONSTRAINT ${row.constraint_name};";
                }
				
				if(statements) {
					runAndWait(["sqlplus", oracleId ], statements, parallel)
				} else {
					println "No foreign key constraint to drop"
				}
                
                
                statements = [];
                println "Dropping tables"
                db.eachRow('SELECT TABLE_NAME FROM USER_TABLES ORDER BY TABLE_NAME ASC') {
                  row -> statements << "DROP TABLE ${row.TABLE_NAME} PURGE;";
                }
 
				if(statements) {
					runAndWait(["sqlplus", oracleId ], statements, parallel)
				} else {
					println "No table to drop"
				}
                
                statements = [];
                if (props['drop-procedures']) {
                  println "Dropping procedures"
                  dropUserObject("PROCEDURE", parallel, oracleId)
                }
                
                if (props['drop-functions']) {
                  println "Dropping functions"
                  dropUserObject("FUNCTION", parallel, oracleId)
                }
                
                if (props['drop-packages']) {
                  println "Dropping packages"
                  dropUserObject("PACKAGE", parallel, oracleId)
                }
                
                if (props['drop-links']) {
                  println "Dropping database links"
                  dropUserObject("DATABASE LINK", parallel, oracleId)
                }
                
                // Importing dump
                if ( props.schemaFile != null ) {
                        def schemaFile = new File(rootDir + "/" + props.schemaFile).absolutePath
                        runAndWait(["imp", "${props.user}@${sid}.world", "file=$schemaFile", "full=Y", "STATISTICS=NONE", "buffer=100000"], ["${props.password}"])
                }
}

