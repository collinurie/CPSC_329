import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;
import java.net.*;

/**
 * To use this program you must have worker programs running
 * then enter the IP addresses of those workers. When you have finished 
 * entering the IP addresses you will then hit enter and the connections will be made. 
 * After the connections are made the workers will search for the first prime number
 * after the randomly generated one that the supervisor creates.  The size of the number 
 * is set to 2048 bits, but that could be changed by changing the constant BITS.
 * @author Collin Urie
 */
public class BigPrimeServer {
	
	private static final int PORT = 8888;
	private static final int BITS = 2048;
	private static final int CERTAINTY = 100;
	private static final Random RAND = new Random();
	private static BigInteger startNum;
	public static String answer = "";
	public static ArrayList<Socket> socketList;
	
	public static void main(String[] args) {
		ArrayList<String> iplist = new ArrayList<String>();
		socketList = new ArrayList<Socket>();
		Scanner s = new Scanner(System.in);
		// gathers ip addresses to use for workers.
		while(true) {
			System.out.print("Enter ip (hit enter when finished): ");
			String ip = s.nextLine().trim();
			if(ip.length()>1)
			iplist.add(ip);
			else
				break;
		}// end loop
		
		startNum = randomBigInteger(BITS);
	
		for(int i = 0; i < iplist.size(); i++) {
			BigInteger temp = startNum.add(new BigInteger(Integer.toString(i*2)));// allows each worker to start at a different num
			try {
				Socket sock = new Socket(iplist.get(i), PORT);
				System.out.println("Connected to client: "+iplist.get(i));
				new ConnectionHandler(sock, temp.toString(), iplist.size());// offset is iplist.size() so the worker knows how to increment search
				socketList.add(sock);
				
			}catch(Exception e) {
				
			}
			
		}// end loop
		
	}// end main
	
	/*********************************************************************************
	 * Makes a random big integer that is odd and is the specified 
	 * amount of bits
	 * @param bits
	 * @return
	 ********************************************************************************/
	private static BigInteger randomBigInteger(int bits) {
		BigInteger big = new BigInteger(bits,RAND);
		big = big.setBit(0);
		big = big.setBit(BITS-1);
		
		return big;
	}// end randomBigInteger
	
	
	/*********************************************************************************
	 * handles the connection with the workers. Sends and receives messages to workers.
	 * @param s
	 * @param bigNum
	 * @param offset
	 ********************************************************************************/
	private static void handleConnection(Socket s, String bigNum, int offset) {
		DataInputStream in = null;
		DataOutputStream out = null;
		
		try {
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			
			out.writeUTF(bigNum+"$"+offset);
			//System.out.println("sent data");
		}
		catch(IOException e) {
	 			System.out.println("Error: "+e);
		}
		boolean found = false;
		String clientMessage;
		while(true) {
			if(answer.length() > 0)
				break;
			clientMessage = "";
			try {
				//System.out.println(in.readUTF());
				clientMessage = in.readUTF();
			
				if(clientMessage.length()>20) {
					
					System.out.println("Found prime:\n\t"+clientMessage);
					found = true;
					answer = clientMessage;
			
					System.out.println("\n\n");
					
					// close sockets running on threads
					for(Socket sock : socketList) {
						String name = sock.getInetAddress().toString();
						sock.close();
						System.out.println("Connection closed with: "+ name);
					}
					
					//close main socket
					in.close();
					out.close();
					s.close();
					
					break;				
					
				}// end if
				
			}// end try
			catch(Exception e) {
				if(!found)
					System.out.println("Error:: "+e);
				
	 			break;
			}
		}
	}// end handleConnection
	
	/******************************************************************************
	 * This is the thread that handles connections with each worker 
	 *
	 *******************************************************************************/
	private static class ConnectionHandler extends Thread{
		Socket sock;
		String bigNum;
		int offset;
		ConnectionHandler(Socket s, String bigNum, int offset) {
			sock = s;
			this.bigNum = bigNum;
			this.offset = offset;
			start();
		}// end constructor
		
		
		/******************************************************************************
		 * This is what runs when the thread is created and starts.
		 *
		 *******************************************************************************/
		public void run() {
			 try {
		            String clientAddress = sock.getInetAddress().toString();
	                //System.out.println("Connection made with: "+ clientAddress);
	                handleConnection(sock, bigNum, offset);
	            }
	            catch (Exception e) {
		 			System.out.println("Error: "+e);
	            }
		
		}
		
	}// end class
		
	

}
