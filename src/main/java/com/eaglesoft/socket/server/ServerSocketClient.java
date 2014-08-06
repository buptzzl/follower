package com.eaglesoft.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ServerSocketClient implements Runnable
{
	
    private static final Logger logger = LogManager.getLogger(ServerSocketClient.class);
    
	private BufferedReader in;

	private PrintWriter out;

	private Socket socket;
	
	private EchoServer server;


    public ServerSocketClient(EchoServer server,Socket socket){
    	this.socket = socket;
    	this.server = server;
    }


	public BufferedReader getIn()
	{
		if (in != null)
		{
			return this.in;
		} else
		{
			try
			{
				Socket incoming = getSocket();
				this.in = new BufferedReader(new InputStreamReader(incoming
						.getInputStream()));
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return this.in;

		}
	}

	public void setIn(BufferedReader in)
	{
		this.in = in;
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
				this.out = new PrintWriter(getSocket().getOutputStream(),	true);
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

	public Socket getSocket()
	{
		return this.socket;
	}

	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}

	public void run()
	{
		boolean done = false;
		String line;
		try
		{
			while (!done)
			{
				line = this.recieve();
				if (line != null)
				{
					if(line.trim().equalsIgnoreCase("Eagle")){
						this.getOut().println("Echo: " +"Eagle");
						this.getOut().println("Echo: " +"in his 30's");
						this.getOut().println("Echo: " +"has one child named junxi");
						this.sendMessageOver();
					}else{
						this.sendln("Echo: " + line);
						if (line.trim().equalsIgnoreCase("BYE")){
							done = true;
						}
							
							
					}
					
				}
			}
			
			logger.info("done normally");
		} catch (IOException e)
		{
		    logger.error("done with exception");
			e.printStackTrace();
			this.sendMessageOver();
		}
		finally{
			this.close();
		}
		
	}

	private void close()
	{
		
		try
		{
			this.getOut().close();
			this.getIn().close();
			this.getSocket().close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void sendMessageOver(){
		this.getOut().println("End");
	}
	
	public void sendln(String message){
		this.getOut().println(message);
		this.getOut().println("End");
	}
	
	public String recieve() throws IOException{
		String result ="";
		while(true){
			String line ="";
			line =this.getIn().readLine();
			if("End".equalsIgnoreCase(line)) {
				break;
			}
			else result += line+"\r\n";
		}
		return result;
	}
	
}
