import java.util.ArrayList;

/**
 * Elevator simulation.
 */
public class Main {

	private static final int NUMFLOORS = 10; // number of floors in the building
	private static final int NUMELEVATORS = 1; // number of elevators
	private static final int CAPACITY = 4; // max number of people an elevator can hold
	private static final int NUMPEOPLE = 2; // number of people

	public static void main(String[] args) {
		// create building
		Building building = new Building(NUMFLOORS, NUMELEVATORS, CAPACITY);

		ArrayList<ElevatorSim> elevatorSims = new ArrayList<ElevatorSim>();
		ArrayList<Thread> elevatorThreads = new ArrayList<Thread>();

		// create and start threads for each elevator
		for (Elevator elevator : building.getElevators()) {
			// TODO create and start a thread for an ElevatorSim for 'elevator'
			ElevatorSim sim = new ElevatorSim(elevator,building);
			Thread t = new Thread(sim);
			t.start();
			elevatorThreads.add(t);
			elevatorSims.add(sim);
		}

		ArrayList<Thread> personThreads = new ArrayList<Thread>();
		// create and start threads for each person
		for (int ctr = 0; ctr < NUMPEOPLE; ctr++) {
			Person person = new Person(ctr, getDestinationString(building.numFloors()));
			// TODO create and start a thread for a PersonSim for 'person'
			PersonSim sim = new PersonSim(person,building);
			Thread th = new Thread(sim);
			th.start();
			personThreads.add(th);
		}

		// TODO wait until all of the person threads have completed, then shut down
		// the elevator threads
		for(Thread t: personThreads) {
			while(t.isAlive()) {
				try {
					t.join();
				} catch ( InterruptedException e ) {}
			}
		}
		
		for(ElevatorSim sim: elevatorSims) {
			sim.shutdown();
		}

		// TODO wait until all of the elevator threads have completed before printing
		// the "simulation complete" message
		for(Thread t: elevatorThreads) {
			while(t.isAlive()) {
				try {
					t.join();
				} catch ( InterruptedException e ) {}
			}
		} 	

		System.out
				.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + ": simulation complete");
	}

	/**
	 * Generate a string of random destination floors for a passenger, starting and
	 * ending at floor 0.
	 * 
	 * @param numfloors
	 *            number of floors to visit (before returning to floor 0)
	 * @return the generated destination string
	 */
	private static String getDestinationString(int numfloors) {
		String str = "";
		for (int ctr = 0; ctr < 3; ctr++) {
			str += "," + ((int) (Math.random() * numfloors));
		}
		return "0" + str + ",0";
	}
}
