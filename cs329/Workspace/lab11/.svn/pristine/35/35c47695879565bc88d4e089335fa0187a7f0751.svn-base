
/**
 * The movement controller for a person. A person repeatedly calls an elevator,
 * goes to a new floor, and completes some business on that floor until the
 * final destination floor is reached.
 */
public class PersonSim implements Runnable {

	private Person person_; // the person being controlled
	private Building building_; // the building the person is in

	/**
	 * Create a movement controller for the specified person and building.
	 * 
	 * @param person
	 *            the person
	 * @param building
	 *            the building the person is in
	 */
	public PersonSim(Person person, Building building) {
		super();
		person_ = person;
		building_ = building;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		int curfloor = person_.nextDest(); // starting floor

		for (; person_.hasNextDest();) { // as long as passenger has somewhere
											// to go...
			int destfloor = person_.nextDest(); // next destination
			Elevator elevator = null; // passenger's elevator

			// acquire an elevator
			for (; true;) {
				// passenger summons elevator to current floor and waits for one to arrive
				System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + ": person "
						+ person_.getID() + " is waiting on floor " + curfloor);
				elevator = building_.summon(curfloor);
				// if there's room, passenger can get on
				if (elevator != null && elevator.hasRoom()) {
					break;
				}
				System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName()
						+ ": an elevator arrived but is unavailable");
				// TODO wait before summoning again - give elevator a chance to leave
				synchronized (person_) {
					try {
						person_.wait();
					} catch ( InterruptedException e ) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(SimConstants.RESUMMON_DELAY);
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}

			}

			// [elevator present, with doors open and room]
			// passenger boards elevator
			elevator.enter(person_);

			// [passenger is now on the elevator]
			// passenger pushes button for destination
			elevator.pushButton(destfloor);

			// passenger waits until doors open on the desired floor (in a loop since a
			// passenger is notified each time the elevator arrives at a floor)
			for (; elevator.getFloor() != destfloor;) {
				// TODO wait on person_
				try {
					Thread.sleep(SimConstants.VISIT_TIME);
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}

			}

			// [elevator is now at destination with doors open]
			// passenger exits elevator
			elevator.exit(person_);

			// [passenger has arrived on desired floor]
			curfloor = destfloor;
			// TODO person does business on the floor

		}
		
		System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName()
				+ ": person "+person_.getID()+" terminating");
	}
}
