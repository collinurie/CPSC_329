

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientServicing implements Runnable {
	private  final int PORT_ = 9999;
	private  BufferedReader in;
	private  PrintWriter out;
	private  List<KnockKnockJoke> knockknocks;
	private  List<Riddle> riddles;
	private Socket connection;
	
	public ClientServicing(Socket s,List<KnockKnockJoke> jokeList,List<Riddle> riddleList ) {
		connection = s;
		knockknocks = jokeList;
		riddles = riddleList;
	}

	
	@Override
	public void run() {
		
		try {

//			while(true) {
				System.out.println("connection made with: "+ connection.getLocalAddress());
			// i/o streams
			in = new BufferedReader(new InputStreamReader(connection
			    .getInputStream()));
			out =
			    new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));

			String protocol = in.readLine();
			if ( protocol.contentEquals("knock knock") ) {
					handleKnockKnock();
			}
			else if( protocol.contentEquals("riddle") ){
				handleRiddle();
			}
			else if( protocol.contentEquals("shutdown")) {
				System.out.println("Shutting down...");
				connection.close();
				
				JokeServer.shutdown();
				
				
				//break;
			}
			
			connection.close();
		//}

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public  void handleRiddle() {
		//while ( true ) {
			Riddle joke =
			    riddles.get((int) (Math.random() * riddles.size()));
			try {
				
				// send message to client
				String sentMessage = joke.getRiddle();
				out.println(sentMessage);
				out.flush();
//				System.out.println("sent: " + sentMessage);
				System.out.println(sentMessage);

				// listen for response
				String clientMessage = in.readLine();
				System.out.println("client said: " + clientMessage);

				// send message to client
				sentMessage = joke.getAnswer();
				out.println(sentMessage);
				out.flush();
//				System.out.println("sent: " + sentMessage);
				System.out.println(sentMessage);
				connection.close();
				// wait for a message to go again 
//				clientMessage = in.readLine();
//				System.out.println("client said: " + clientMessage);
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		//}	
	}

	public  void handleKnockKnock () {
		//while ( true ) {
			KnockKnockJoke joke =
			    knockknocks.get((int) (Math.random() * knockknocks.size()));
			try {
				// send message to client
				String sentMessage = "knock knock";
				out.println(sentMessage);
				out.flush();
				System.out.println(sentMessage);

				// listen for response
				String clientMessage = in.readLine();
				System.out.println("client said: " + clientMessage);

				// send message to client
				sentMessage = joke.getThing();
				out.println(sentMessage);
				out.flush();
//				System.out.println("sent: " + sentMessage);
				System.out.println(sentMessage);

				// listen for response
				clientMessage = in.readLine();
				System.out.println("client said: " + clientMessage);

				// send message to client
				sentMessage = joke.getPunchline();
				out.println(sentMessage);
				out.flush();
//				System.out.println("sent: " + sentMessage);
				System.out.println(sentMessage);
				
				// wait for a message to go again 
				clientMessage = in.readLine();
				System.out.println("client said: " + clientMessage);
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		//}
	}
	
	
	
	

}
