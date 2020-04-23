import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * This program is to be run on a separate device than the supervisor
 * when the supervisor connects to it and sends it a task it will then search 
 * for a prime number  based on the number that is received and the offset. 
 * @author collinurie
 *
 */
public class BigPrimeWorker {
	
	private static final int  PORT = 8888;
	static Socket con;
	//static InputStream in;
	//static OutputStream out;
	public static void main(String[] args) {
		System.out.println("Running Server");
		boolean found = false; // to see if the prime is found
		try {
			ServerSocket ss = new ServerSocket(PORT);
			//System.out.println(ss.getInetAddress().toString());
			con = ss.accept();
			System.out.println("Connected!!!!");
			
			DataOutputStream out = new DataOutputStream(con.getOutputStream()); 
			DataInputStream in = new DataInputStream(con.getInputStream());

			
			int i = 0;
			String task = "";
			while(true) {
				out.writeUTF("");
				task = in.readUTF();
				if(task.length()> 0) {
					//System.out.println("Task Received: "+task);
					out.writeUTF("Task Received");
					String bigNum = task.substring(0,task.indexOf('$'));
					int offset = Integer.parseInt(task.substring(task.indexOf('$') +1));
					
					String foundPrime = findPrime(bigNum,offset).toString();
					found = true; // this will bypass any exceptions thrown when closing connection
					
					//System.out.println(foundPrime);
					
					out.writeUTF(foundPrime);
					
//				    try {
//				        out.close();
//				        in.close();
//				        con.close();
//				    } catch (IOException ex) {
//				        System.out.println("Error closing the socket and streams");
//				    }
					
				}
				if(i == -1)
					break;
				
				i++;
		
			}
			
			System.out.println("data sent");
			//con.close();
			
		}// end try
		catch (Exception e) {
			if(!found)
				e.printStackTrace();
		}// end catch
		
	}// end main
	
	
	/***********************************************************************************
	 * takes a BigInteger and finds the next prime number...
	 * This does not find the next one, it uses an offset to allow all 
	 * of the workers to check separate numbers.
	 * @param num
	 * @param offset
	 * @return
	 **********************************************************************************/
	public static BigInteger findPrime(String num, int offset) {
		BigInteger bigInt = new BigInteger(num);
		while(true) {
			if(bigInt.isProbablePrime(100)) {
				break;
			}
			else {
				bigInt = bigInt.add(new BigInteger(Integer.toString(2*offset)));
			}
		}
		
		return bigInt;
		
	}// end findPrime

	
}// end class






