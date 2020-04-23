import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author cu5988
 *
 */
public class KnockKnockClient {
	private static Scanner s;
	public static void main(String[] args) {
		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		s = new Scanner(System.in);
		handleConnection(ip,port);
	}
	
	public static void handleConnection(String ip, int port) {
		try {
			Socket connection = new Socket(ip, port);
			
			// create i/o
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out =  new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));			
			out.println("knock knock");
 			out.flush();
			while(true) {
	 			// get message from server 
	 			String receivedMessage = in.readLine();
	 			System.out.println("server said: "+ receivedMessage);
	 			
	 			// send message back
	 			String sentMessage = s.nextLine();
	 			out.println(sentMessage);
	 			out.flush();

	 			//System.out.println(sentMessage);
 			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}

}
