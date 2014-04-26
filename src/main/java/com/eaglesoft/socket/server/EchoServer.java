package com.eaglesoft.socket.server;


import java.io.*;
import java.net.*;

public class EchoServer implements Runnable
{
	private ServerSocket server ;

	private boolean done =false;
	
	public EchoServer() throws IOException{
		this.server = new ServerSocket(2536);
	}
	
	public ServerSocket getServer()
	{
		return server;
	}

	public void setServer(ServerSocket server)
	{
		this.server = server;
	}
	
	public void execute(){
		
		while(!isDone()){
			try
			{
				Socket socket= this.server.accept();
				if(!isDone())new Thread(new ServerSocketClient(this,socket)).start();
			} catch (SocketException e){
				System.out.println("perhaps time over");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
     public static void main(String[] args)
     {
    	 
    	 try
		{
    		 EchoServer echoserver=  new EchoServer();
    		 echoserver.execute();
    		 echoserver.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
     }

	private void close()
	{
		try
		{
			this.server.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	public synchronized boolean isDone()
	{
		return done;
	}

    @Override
    public void run() {
        try
        {
             this.execute();
             this.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }


}
