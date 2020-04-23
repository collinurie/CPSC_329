package application;

import java.io.IOException;

import javafx.concurrent.Task;

/**
 * @author ak5158
 *
 */
public class SystemController {
	private ServerComm comm_;
	private GUIController handler_;

	public SystemController(GUIController handle, String host, int port) {
		//System.out.println("SystemController");
		handler_ = handle;
		comm_ = new ServerComm(host,port);
	}

	public void getKnockKnock() {

		KnockKnockTask task = new KnockKnockTask();
		task.setOnSucceeded(e -> {
			try {
				String joke = task.getValue();
				handler_.displayText(joke);
			} catch ( Exception e1 ) {
				e1.printStackTrace();
			}
		});
	
		Thread t = new Thread(task);
		t.start();


	}

	public void getRiddle() {
		RiddleTask task = new RiddleTask();
		task.setOnSucceeded(e -> {
			try {
				String riddle = task.getValue();
				handler_.displayText(riddle);
			} catch ( Exception e1 ) {
				e1.printStackTrace();
			}
		});
	
		Thread t = new Thread(task);
		t.start();
	}

	public void answerRiddle(String guess) {
		String answer = "";
		try {
			comm_.writeLine(guess);
			answer = answer+comm_.readLine();
			if(answer.equals("incorrect")) {
				answer = answer + "\n"+comm_.readLine();
			}
			handler_.displayText(answer);
			comm_.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void shutdownServer() {
		ShutdownTask task = new ShutdownTask();
		task.setOnSucceeded(e -> {
			try {
				System.exit(0);
			} catch ( Exception e1 ) {
				e1.printStackTrace();
			}
		});
		Thread t = new Thread(task);
		t.start();
	}

	class ShutdownTask extends Task<String>{
		@Override
		protected String call () throws Exception {
			comm_.connect();
			comm_.writeLine("shutdown");
			System.out.println("Shutdown the server");
			//comm_.disconnect();
			return null;
		}  
		
	}
	
	class RiddleTask extends Task<String>{

		@Override
		protected String call () throws Exception {
			comm_.connect();
			String riddle = "";
			comm_.writeLine("riddle");
			// go through riddle protocol 
			riddle = riddle+comm_.readLine()+"\n\n";
			return riddle;
		}  
		
	}

	
	class KnockKnockTask extends Task<String>{

		@Override
		protected String call () throws Exception {
			comm_.connect();
			String wholeJoke = "";
			comm_.writeLine("knockknock");
			// go through knock knock protocol 
			wholeJoke = wholeJoke+comm_.readLine()+"\n";
			comm_.writeLine("who's there");
			wholeJoke = wholeJoke+ "whose there?\n";
			String thing = comm_.readLine();
			comm_.writeLine(thing+" who?");
			wholeJoke = wholeJoke + thing+ "\n";
			wholeJoke = wholeJoke+ thing+" who?\n";
			wholeJoke = wholeJoke+comm_.readLine();
			comm_.disconnect();
			return wholeJoke;
		}  
		
	}
	
	
}
