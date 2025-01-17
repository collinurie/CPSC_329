import java.net.*;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
///Users/collinurie/Desktop/


public class FileServer {
	
    private static final int THREAD_POOL_SIZE = 5;

    private static final int QUEUE_CAPACITY = 10;

	public static final int LISTENING_PORT = 8000;
	
	
	public static File directory;	
    private static ArrayBlockingQueue<Socket> connectionQueue;

    
    
	
	public static void main(String[] args) {
	  
		// set up directory information for file server
		String directoryName =  System.getProperty("user.home");
		if(args.length > 0) {
			directoryName = args[0];
		} 
		directory = new File(directoryName);
	
		if(!directory.isDirectory())
			System.out.println("Error: \""+directoryName+"\" is not a directory");
		
		//create connection with client 
		ServerSocket clientListener;
	    Socket connection;      
		
		try {// try to make connection with a client
			clientListener  = new ServerSocket(LISTENING_PORT);
			connectionQueue = new ArrayBlockingQueue<Socket>(QUEUE_CAPACITY);
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                new ConnectionHandler();  // Create the thread; it starts itself.
            }
            
            System.out.println("Listening on port " + LISTENING_PORT);
            while (true) {
                    // Accept next connection request and put it in the queue.
                connection = clientListener.accept();
                try {
                    connectionQueue.put(connection); // Blocks if queue is full.
                }
                catch (InterruptedException e) {
                }
            }
            
		}// end try 
		catch(IOException e) {
			System.out.println("Cannot open listening socket on port "+LISTENING_PORT);
			System.out.println("Error: "+ e);
			return;
		}// endcatch
		
	}// end main
	
	
	private static class ConnectionHandler extends Thread{
		
	    ConnectionHandler() {
	        setDaemon(true);
	        start();
	    }
	    public void run() {
	        while (true) {
	            Socket client;
	            try {
	                client = connectionQueue.take();
		            String clientAddress = client.getInetAddress().toString();
	                System.out.println("Connection made with: "+ clientAddress);
	                handleConnection(client);
	            }
	            catch (InterruptedException e) {
	                continue; // (If interrupted, just go back to start of while loop.)
	            }
	        
	            
	        }
	    }
		
	}// end class
	
	public static String getMimeType(String m) {
		//String m = "";
		String type;
//		Pattern pattern = Pattern.compile("(\\..{3})");
//		Matcher matcher = pattern.matcher(ma);
//	    if(matcher.matches()) {
//	    	m = matcher.group(1);
//	    }
//	    m = m.trim();
	    //m = m.substring(m.indexOf('.'));
		//m = m.trim();
		System.out.println("*************"+m+"********************");
		
		
		if(m.matches(".*\\.html"))
		{
			type = "text/html";
		}
		else if(m.matches(".*\\.txt"))
		{
			type = "text/plain";
		}
		else if(m.matches(".*\\.css"))
		{
			type = "text/css";
		}
		else if(m.matches(".*\\.js"))
		{
			type = "text/javascript";
		}
		else if(m.matches(".*\\.java"))
		{
			type = "text/x-java";
		}
		else if(m.matches(".*\\.c"))
		{
			type = "text/x-csrc";
		}
		else if(m.matches(".*\\.jpg") || m.equals(".*\\.jpeg"))
		{
			type = "image/jpeg";
		}
		else if(m.matches(".*\\.png"))
		{
			type = "image/png";
		}
		else if(m.matches(".*\\.gif"))
		{
			type = "image/gif";
		}
		else {
			type = "text/plain";
		}
		return type;
	}
	
	
	/**
	 * Takes the socket that is being used to connect to the client and handles all of the communication
	 * between the server and the client. This method handles all exceptions. 
	 * @param client
	 */
	private static void handleConnection(Socket client) {
			//System.out.println("in handleConnection");
		 try {
 			//InetAddress clientAddress = client.getInetAddress();
 			//System.out.println("Accepted connection from IP address: "+ client.getInetAddress());
 			
 			// bufferedReader will read the input stream coming from the client
 			BufferedReader in = new BufferedReader(
 					new InputStreamReader(client.getInputStream()));
 			
 			OutputStream out = client.getOutputStream();
 			String command = in.readLine();
 			
 			if(command == null)
 				throw new IOException("No data was sent from the client.");
 			if(command.equals("LIST")) {
 				//NetUtil.writeASCII("You have made a LIST request!!!!!\n", out);
 				NetUtil.writeASCII("OK", out);
 				NetUtil.writeCRLF(out);
 				File[] files = directory.listFiles();
 				for (File f : files) {
 					if (f.isFile() && f.canRead()) {
 						NetUtil.writeASCII( NetUtil.encodeURL(f.getName()), out );
 						NetUtil.writeCRLF(out);
 					}					
 				}
 				System.out.println("Sent directory listing.");
 			}// end list 
 			else if(!command.contains("HTTP/1.1")) {
 				NetUtil.writeASCII("ERROR", out);
 				NetUtil.writeCRLF(out);
 				NetUtil.writeASCII("HTTP/1.1 505 Version Not Suported ", out);
 				NetUtil.writeCRLF(out);
 				System.out.println("version not supported error request: "+command);
 			}
 			else if(command.startsWith("GET")) {
 				String fn = command.substring(4);
 				fn = fn.substring(0,fn.indexOf(' '));
 				String fileName = URLDecoder.decode(fn, "UTF-8");
 				File file = new File(directory, fileName);
 				if(file.isFile() && file.canRead()) { // if the filename given by the user is valid
 					String mimeType;
	    			long contentLen = file.length();
 					NetUtil.writeASCII("HTTP/1.1 200 OK", out);// confirm file is valid
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("Connection: close", out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("Content-Type: "+getMimeType(fn), out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("Content-Length: "+contentLen, out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeCRLF(out);
 					InputStream fileIn = new FileInputStream(file);
 					NetUtil.copyStream(fileIn, out);
 					System.out.println("File has been sent.");
 					fileIn.close();
 				}
 				else if(file.isFile() && !file.canRead()) {
 					//NetUtil.writeASCII("ERROR", out);
 					NetUtil.writeASCII("HTTP/1.1 403 Forbidden ", out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("Connection: close", out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("Content-Type: "+getMimeType(""), out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("403 Forbidden ", out);
 					NetUtil.writeCRLF(out);
 					System.out.println("Forbidden file error request: "+command);
 				}// forbidden 403
 				else if(!file.isFile()) {
 					NetUtil.writeASCII("HTTP/1.1 404 Not Found ", out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("Connection: close", out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("Content-Type: "+getMimeType(""), out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeCRLF(out);
 					NetUtil.writeASCII("404 Not Found ", out);
 					NetUtil.writeCRLF(out);
 					System.out.println("Unknown file error request: "+command);
 				}// not found error 
 			}// end GET command check
 			else if(command.startsWith("POST") || command.startsWith("HEAD")) {
 				NetUtil.writeASCII("HTTP/1.1 501 Not Implemented ", out);
 				NetUtil.writeCRLF(out);
 				NetUtil.writeASCII("Connection: close", out);
				NetUtil.writeCRLF(out);
				NetUtil.writeASCII("Content-Type: "+getMimeType(""), out);
				NetUtil.writeCRLF(out);
				NetUtil.writeCRLF(out);
 				NetUtil.writeASCII("501 Not Implemented ", out);
 				
 			}
 			else {
 				NetUtil.writeASCII("HTTP/1.1 400 Bad request ", out);
 				NetUtil.writeCRLF(out);
 				NetUtil.writeASCII("Connection: close", out);
				NetUtil.writeCRLF(out);
				NetUtil.writeASCII("Content-Type: "+getMimeType(""), out);
				NetUtil.writeCRLF(out);
				NetUtil.writeCRLF(out);
 				NetUtil.writeASCII("400 Bad request ", out);
 				NetUtil.writeCRLF(out);
 				System.out.println("Bad Request error request: "+command);
 			}
 	
 		}
 		catch(IOException e) {
 			return;
 		}// end try/catch
 		catch(Exception e) {
 			/**
 			 * TODO: find out how to send a HTTP/1.1 500 Internal Server Error
 			 * 		 can't send outside of try catch
 			 */
 		}
 		
 		finally {
 			try {
 				client.close();
 			}
 			catch(Exception e) {/** do nothing*/}
 		}// end finally 
	}
	
	
	
	
	
}
