package com.eaglesoft.socket.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EchoClient {
	
    private static final Logger logger = LogManager.getLogger(EchoClient.class);
    
	private PrintWriter out;
	private BufferedReader in;
	private Socket connection;
	private BufferedReader console;
	
	
	
	public EchoClient(String connectto,int port) throws UnknownHostException, IOException{
		  if (connectto.equals("localhost")) {
              connection = new Socket(InetAddress.getLocalHost(), port);
          }
          else {
              connection = new Socket(InetAddress.getByName(connectto), port);
          }
	}
	
	public EchoClient(InetAddress address) throws UnknownHostException, IOException{
		this.connection = new Socket(address, 2536);
	}
	
	
	public static EchoClient createSocketClient(String connectto,int port) throws UnknownHostException, IOException{
		
		EchoClient client  = new EchoClient(connectto,port);
		return client;
	}
	
	
	

     public static void main(String args[]) {
         try {
        	 EchoClient client=null;
        	 boolean done =false;
        	 String infor ="";
        	 //int port  = (int) (3000+1000*Math.random());
        	 try {
        		 client=  EchoClient.createSocketClient("localhost",2536);
        		 while (!done) {
                     String sInput = client.getConsole().readLine();
                     infor = client.send(sInput);
                     client.showInfor(infor);
                     if (sInput.equalsIgnoreCase("bye"))
                         done = true;

                 }
        	 }finally{
        		 if(client!=null)client.close();
        	 }
        	
        	 
        	 
         }
         catch (SecurityException e) {
             logger.error("SecurityException when connecting Server!");
         }
         catch (IOException e) {
             logger.error("IOException when connecting Server!");
         }
     }

     
     public void showInfor(String infor){
         logger.info(infor);  
     }
	 public void close()
	{
		try
		{
			this.connection.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public PrintWriter getOut()
	{
		if (this.out != null)
		{
			return this.out;
		} else
		{

			try
			{
				this.out = new PrintWriter(connection.getOutputStream(),true );
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			return this.out;
		}
	
	}

	public void setOut(PrintWriter out)
	{
		this.out = out;
	}

	public BufferedReader getIn() throws IOException
	{

		if (in != null)
		{
			return this.in;
		} else
		{
			this.in = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
			return this.in;

		}
	
	}

	public void setIn(BufferedReader in)
	{
		this.in = in;
	}

	public Socket getConnection()
	{
		return connection;
	}

	public void setConnection(Socket connection)
	{
		this.connection = connection;
	}
	
	
	public String send(String message) throws IOException{
		this.getOut().println(message);
		this.getOut().println("End");
		String result ="";
		while(true){
			String line = this.getIn().readLine();
			if("End".equalsIgnoreCase(line)) break;
			else result += line+"\r\n";
		}
		return result;
	}

	public BufferedReader getConsole()
	{
		if (this.console != null)
		{
			return this.console;
		} else
		{
			this.console = new BufferedReader(new InputStreamReader(System.in));
			return this.console;

		}
	}
	
	
	
	

}
