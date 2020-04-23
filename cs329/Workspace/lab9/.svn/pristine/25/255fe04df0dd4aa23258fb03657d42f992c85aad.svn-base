import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ShutdownClient {
	
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
			out.println("shutdown");
 			out.flush();
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}


}
