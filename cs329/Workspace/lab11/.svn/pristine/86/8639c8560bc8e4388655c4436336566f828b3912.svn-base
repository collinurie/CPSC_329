import java.util.ArrayList;
import java.util.List;

/**
 * A building, which has some number of floors and elevators which move between
 * the floors.
 */
public class Building {

	private List<Elevator> elevators_; // the building's elevators
	private Floor[] floors_; // the building's floors

	/**
	 * Create a building with the specified number of floors, elevators, and maximum
	 * capacity per elevator.
	 * 
	 * @param numfloors
	 *            number of floors (numfloors > 0)
	 * @param numelev
	 *            number of elevators (numelev > 0)
	 * @param capacity
	 *            maximum capacity (people) per elevator (capacity > 0)
	 */
	public Building(int numfloors, int numelev, int capacity) {
		floors_ = new Floor[numfloors];
		for (int ctr = 0; ctr < numfloors; ctr++) {
			floors_[ctr] = new Floor(ctr);
		}
		elevators_ = new ArrayList<Elevator>();
		for (int ctr = 0; ctr < numelev; ctr++) {
			elevators_.add(new Elevator(ctr, numfloors, capacity));
		}
	}

	/**
	 * Get the number of floors in the building.
	 * 
	 * @return the number of floors
	 */
	public int numFloors() {
		return floors_.length;
	}

	/**
	 * Call an elevator to the specified floor if there is not already one there.
	 * The ground floor is floor 0. Note: this method does not return until an
	 * elevator has arrived at the specified floor.
	 * 
	 * @param floor
	 *            the floor (0 <= floor < numFloors())
	 * @return the elevator that has arrived
	 */
	public Elevator summon(int floor){
		System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName()
				+ ": [building] summoning elevator to floor " + floor);
		Elevator elevator = getElevator(floor);
		if (elevator != null) {
			// there's already an elevator here!
			return elevator;
		}

		// [there's not an elevator at the desired floor]
		// choose an elevator that services the floor (pick randomly from the building's
		// elevators)
		elevator = elevators_.get((int) (Math.random() * elevators_.size()));

		// push the button to summon the elevator
		elevator.pushButton(floor);

		// TODO wait for an elevator to arrive at this floor
			synchronized( floors_[floor]) {
				try {
					floors_[floor].wait();
				} catch ( InterruptedException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
		// return an available elevator (i.e. on the floor, with doors open) - note that
		// this may not be the one summoned, since other elevators are arriving as well
		return getElevator(floor);
	}

	/**
	 * Tell the building an elevator has arrived at the specified floor. The people
	 * waiting for an elevator are notified.
	 * 
	 * @param floor
	 *            the floor an elevator has arrived at
	 */
	public void arrive(int floor) {
		System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName()
				+ ": [building] an elevator has arrived at floor " + floor);
		
		synchronized(floors_[floor]){
			floors_[floor].notifyAll();
		}

	}

	/**
	 * Get an available elevator on the specified floor. "Available" means the
	 * elevator is at the floor with its doors open.
	 * 
	 * @param floor
	 *            the floor
	 * @return an elevator with its doors open on the specified floor, or null if
	 *         there is no such elevator
	 */
	public Elevator getElevator(int floor) {
		for (Elevator elevator : elevators_) {
			if (elevator.getFloor() == floor && elevator.doorsOpen()) {
				return elevator;
			}
		}
		return null;
	}

	/**
	 * Get the building's elevators.
	 * 
	 * @return an iterable collection of the building's elevators
	 */
	public Iterable<Elevator> getElevators() {
		return elevators_;
	}
}
