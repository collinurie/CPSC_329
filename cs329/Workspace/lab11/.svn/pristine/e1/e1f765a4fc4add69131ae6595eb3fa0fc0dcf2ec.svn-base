
/**
 * The elevator movement controller. Elevators move one floor at at time,
 * continuing in the same direction (up or down) until the top or bottom floor
 * is reached. Elevators only stop on a floor if the button has been pressed
 * (indicating that a passenger wants to get off on that floor or there's a
 * person waiting for an elevator on that floor). If no button is pushed, the
 * elevator waits where it is.
 */
public class ElevatorSim implements Runnable {

	// TODO add support for shutting down the thread running this code
  private boolean shutdown_;
	private Elevator elevator_; // the elevator being controlled
	private Building building_; // the building the elevator is in

	/**
	 * Create a movement controller for the specified elevator and building.
	 * 
	 * @param elevator
	 *            the elevator to control
	 * @param building
	 *            the building the elevator is in
	 */
	public ElevatorSim(Elevator elevator, Building building) {
		super();
		elevator_ = elevator;
		building_ = building;
		shutdown_ = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// start with the elevator's doors open
		elevator_.open();

		for (; !shutdown_;) {
			// if no buttons are pressed, wait until summoned
			if (elevator_.idle()) {
				System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + ": elevator "
						+ elevator_.getID() + " idle");
				// TODO elevator waits until summoned
				synchronized(elevator_) {
					try {
						elevator_.wait();
					} catch ( InterruptedException e ) {
						e.printStackTrace();
					}
				}
				System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + ": elevator "
						+ elevator_.getID() + " continuing");
			}

			// [the elevator has a destination to go to]
			// if doors are open, close doors
			if (elevator_.doorsOpen()) {
				elevator_.close();
			}

			// [doors are closed]

			// TODO the elevator takes some time to move between floors
			try {
				Thread.sleep(SimConstants.FLOOR_CHANGE_TIME);
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
			// move one floor - continue in current direction if possible, but switch
			// directions if the top or bottom floor has been reached
			if (elevator_.goingUp()) {
				if (elevator_.getFloor() < building_.numFloors() - 1) {
					elevator_.up();
				} else {
					elevator_.down();
				}
			} else if (elevator_.getFloor() > 0) {
				elevator_.down();
			} else {
				elevator_.up();
			}

			// [elevator is at the new floor]
			// if button has been pushed for the arrival floor, open doors and
			// notify of arrival
			int floor = elevator_.getFloor();
			if (elevator_.buttonPushed(floor)) {
				// tell the elevator it has arrived
				elevator_.arrive();
				// tell the building that an elevator has arrived at the floor
				building_.arrive(floor);
			}

			// TODO the elevator waits for a while so passengers can board
			try {
				Thread.sleep(SimConstants.DOORS_OPEN_TIME);
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
			
		}

		// TODO uncomment this once the loop exits
		System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + ": elevator "
				+ elevator_.getID() + " terminating");
	}
	
	public void shutdown() {
		shutdown_ = true;
	}
	
}
