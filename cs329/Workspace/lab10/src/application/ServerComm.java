package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author ak5158
 *
 */
public class ServerComm {
	private Socket socket_;
	private BufferedReader in_;
	private PrintWriter out_;
	private String host_;
	private int port_;
	
	public ServerComm(String host, int port) {
		//System.out.println("ServerComm");
		host_ = host;
		port_ = port;
	}
	
	public void connect() throws UnknownHostException, IOException {
		socket_ = new Socket(host_,port_);
		//System.out.println(socket_.isConnected());
		in_ = new BufferedReader(new InputStreamReader(socket_
		                                             			    .getInputStream()));
		out_ =
		    new PrintWriter(new OutputStreamWriter(socket_.getOutputStream()));

	}
	
	public void disconnect() throws IOException {
		socket_.close();
		socket_ = null;
	}
	
	public void writeLine(String message) {
		out_.println(message);
		out_.flush();
	}
	
	public String readLine() throws IOException {
		return in_.readLine();
	}
	
}
