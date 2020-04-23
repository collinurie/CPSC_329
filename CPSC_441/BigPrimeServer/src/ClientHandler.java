import java.io.*;

import java.net.Socket;

public class ClientHandler extends Thread {
	
	//int PORT;
	Socket clientConn;
	InputStream in;
	OutputStream out;
	
	public ClientHandler(Socket s){
		//PORT = port;
		clientConn = s;
	}
	@Override
	public void run() {
		try {
			System.out.println("HELLO FROM THREAD");

			in = clientConn.getInputStream();
			out = clientConn.getOutputStream();
			
			NetUtil.writeASCII("INSERT BIGPRIME INFO HERE", out);
		}
		catch(IOException e) {
	 			System.out.println("Error: "+e);
		}
		
		String clientMessage;
		while(true) {
			try {
			clientMessage = NetUtil.readLine(in);
			System.out.println(clientMessage);
				if(clientMessage.startsWith("found")) {
					System.out.println(clientMessage);
					/**
					 * TODO: come up with way to finish protocol
					 */
				}
			}
			catch(IOException e) {
	 			System.out.println("Error: "+e);
	 			break;
			}
		}
			
		
		
		
	}

}
