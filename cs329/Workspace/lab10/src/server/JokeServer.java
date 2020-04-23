package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A knock knock joke / riddle server.
 */
public class JokeServer {

	/**
	 * Set up knock knock jokes.
	 */
	private static List<KnockKnockJoke> setupKnockKnocks() {
		List<KnockKnockJoke> knockknocks = new ArrayList<KnockKnockJoke>();

		knockknocks.add(new KnockKnockJoke("orange", "Orange you glad you asked?"));
		knockknocks.add(new KnockKnockJoke("cash", "No thanks, but I would like a peanut instead."));
		knockknocks.add(new KnockKnockJoke("rhino", "Rhino every knock knock joke there is."));

		return knockknocks;
	}

	/**
	 * Set up riddles.
	 */
	private static List<Riddle> setupRiddles() {
		List<Riddle> riddles = new ArrayList<Riddle>();

		riddles.add(new Riddle("What has roots as nobody sees, " + "is taller than trees, " + "up, up it goes, "
				+ "and yet never grows?", "a mountain"));
		riddles.add(new Riddle("Thirty white horses on a red hill, " + "first they champ, " + "then they stamp, "
				+ "then they stand still.", "teeth"));
		riddles.add(new Riddle(
				"Voiceless it cries, " + "wingless flutters, " + "toothless bites, " + "mouthless mutters.", "wind"));
		riddles.add(new Riddle("An eye in a blue face saw an eye in a green face.  "
				+ "\"That eye is like to this eye\" said the first eye, " + "\"But in low place, not in high place.\"",
				"the sun"));
		riddles.add(new Riddle("It cannot be seen, cannot be felt, " + "cannot be heard, cannot be smelt.  "
				+ "It lies behind stars and under hills, and empty holes it fills.  "
				+ "It comes first and follows after, ends life, kills laughter.", "dark"));
		riddles.add(new Riddle("A box without hinges, key, or lid, " + "yet golden treasure inside is hid.", "an egg"));
		riddles.add(new Riddle("Alive without breath, as cold as death; "
				+ "never thirsty, ever drinking, all in mail never clinking.", "fish"));
		riddles.add(new Riddle(
				"This thing all things devours: " + "Birds, beasts, trees, flowers; " + "gnaws iron, bites steel; "
						+ "grinds hard stones to meal; " + "slays king, ruins town, " + "and beats high mountain down.",
				"time"));

		return riddles;
	}

	/**
	 * Main server.
	 */
	public static void main(String[] args) {

		// set up jokes and riddles
		List<KnockKnockJoke> knockknocks = setupKnockKnocks();
		List<Riddle> riddles = setupRiddles();

		Random random = new Random();

		// TODO create a ServerSocket on port 9000, listen for an incoming request, pick
		// a random knock knock joke, and tell the joke
		try {
			ServerSocket socket = new ServerSocket(9000);

			for (; true ;) {
				System.out.println("waiting for connection");
				Socket connection = socket.accept();
				System.out.println("connected");

				PrintWriter writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				try {
						String command = reader.readLine();
						System.out.println("client said: " + command);

						if (command.equals("knockknock")) {
							KnockKnockJoke joke = knockknocks.get(random.nextInt(knockknocks.size()));

							writer.println("knock knock");
							writer.flush();
							System.out.println("wrote knock knock");

							String response1 = reader.readLine();
							System.out.println("client said: " + response1);

							writer.println(joke.getThing());
							writer.flush();
							System.out.println("wrote " + joke.getThing());

							String response2 = reader.readLine();
							System.out.println("client said: " + response2);

							writer.println(joke.getPunchline());
							writer.flush();
							System.out.println("wrote " + joke.getPunchline());

						} else if (command.equals("riddle")) {
							Riddle riddle = riddles.get(random.nextInt(riddles.size()));

							writer.println(riddle.getRiddle());
							writer.flush();
							System.out.println("wrote " + riddle.getRiddle());

							String answer = reader.readLine();
							System.out.println("client said: " + answer);

							if (answer.equals(riddle.getAnswer())) {
								writer.println("correct");
								writer.flush();
								System.out.println("wrote correct");
							} else {
								writer.println("incorrect");
								writer.println(riddle.getAnswer());
								writer.flush();
								System.out.println("wrote incorrect");
								System.out.println(riddle.getAnswer());
							}
						} else if ( command.equals("shutdown") ) {
							System.exit(0);
						}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				connection.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
