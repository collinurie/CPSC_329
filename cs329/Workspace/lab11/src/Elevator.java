import java.util.HashSet;
import java.util.Set;

/**
 * An elevator.
 */
public class Elevator {

	private int capacity_; // max people on the elevator at once
	private int id_; // unique ID for the elevator

	private volatile boolean open_; // true if the elevator's doors are open
	private int floor_; // which floor the elevator is on
	private boolean up_; // true if the elevator is currently going up

	private boolean[] buttons_; // true if the button for a specified floor has
	                            // been pushed since the elevator
	// last visited that floor
	private Set<Person> passengers_; // people currently on the elevator

	/**
	 * Create an elevator with the specified ID, servicing the specified number of
	 * floors, and with the specified maximum capacity.
	 * 
	 * @param id
	 *          unique ID for the elevator
	 * @param numfloors
	 *          the number of floors the elevator can service (the elevator will
	 *          visit floors 0 to numfloors-1)
	 * @param capacity
	 *          maximum number of passengers that can be on the elevator at one
	 *          time
	 */
	public Elevator ( int id, int numfloors, int capacity ) {
		id_ = id;
		capacity_ = capacity;
		floor_ = 0;
		open_ = false;
		up_ = true;
		buttons_ = new boolean[numfloors];
		for ( int floor = 0 ; floor < numfloors ; floor++ ) {
			buttons_[floor] = false;
		}
		passengers_ = new HashSet<Person>();
	}

	/**
	 * Move the elevator up one floor. The elevator's doors must be closed and it
	 * cannot already be at the top floor.
	 */
	public void up () {
		if ( open_ ) {
			throw new IllegalStateException("doors are open");
		}
		if ( floor_ == buttons_.length - 1 ) {
			throw new IllegalStateException("elevator cannot move past the last floor");
		}
		floor_++;
		up_ = true;
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] elevator " + id_
		    + " going up - arriving at floor " + floor_);
	}

	/**
	 * Move the elevator up one floor. The elevator's doors must be closed and it
	 * cannot already be at the ground floor.
	 */
	public void down () {
		if ( open_ ) {
			throw new IllegalStateException("doors are open");
		}
		if ( floor_ == 0 ) {
			throw new IllegalStateException("elevator cannot move below the ground floor");
		}
		floor_--;
		up_ = false;
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] elevator " + id_
		    + " going down - arriving at floor " + floor_);
	}

	/**
	 * Open the elevator's doors.
	 */
	public void open () {
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] elevator " + id_
		    + " opening doors on floor " + floor_);
		open_ = true;
	}

	/**
	 * Close the elevator's doors.
	 */
	public void close () {
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] elevator " + id_
		    + " closing doors on floor " + floor_);
		open_ = false;
	}

	/**
	 * Push the button for the specified floor so the elevator will stop when it
	 * next reaches that floor.
	 * 
	 * @param floor
	 *          the floor to stop at
	 */
	public void pushButton ( int floor ) {
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] elevator " + id_
		    + " button pushed for floor " + floor);
		buttons_[floor] = true;

		// TODO wake up the elevator if it is idling
		synchronized ( this ) {
			this.notify();
		}

	}

	/**
	 * Unpush the button for the specified floor so the elevator will not stop
	 * when it next reaches that floor.
	 * 
	 * @param floor
	 *          the floor to not stop at
	 */
	public void unpushButton ( int floor ) {
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] elevator " + id_
		    + " button unpushed for floor " + floor);
		buttons_[floor] = false;
	}

	/**
	 * Has the button for a particular floor been pushed?
	 * 
	 * @param floor
	 *          the floor
	 * @return true if the button for 'floor' has been pushed, false if not
	 */
	public boolean buttonPushed ( int floor ) {
		return buttons_[floor];
	}

	/**
	 * Is the elevator currently going up?
	 * 
	 * @return true if the elevator is going up, false if it is going down
	 */
	public boolean goingUp () {
		return up_;
	}

	/**
	 * Is there room on the elevator?
	 * 
	 * @return true if there is room for at least one more passenger, false if the
	 *         elevator is full
	 */
	public boolean hasRoom () {
		return capacity_ > passengers_.size();
	}

	/**
	 * Get the elevator's ID.
	 * 
	 * @return the elevator's ID
	 */
	public int getID () {
		return id_;
	}

	/**
	 * Handle an elevator having arrived on a floor - open the doors, tell the
	 * passengers about the arrival, and turn off the button for that floor.
	 */
	public void arrive () {
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] elevator " + id_
		    + " has arrived at floor " + floor_);

		// open the doors
		open();

		// TODO notify the passengers on the elevator that it has arrived
		synchronized ( passengers_ ) {
			passengers_.notifyAll();
		}

		// turn the button off
		unpushButton(floor_);
	}

	/**
	 * A person gets on the elevator. The elevator's doors must be open, and there
	 * must be room for the person on the elevator.
	 * 
	 * @param passenger
	 *          the person getting on
	 */
	public void enter ( Person passenger ) {
		if ( !open_ ) {
			throw new IllegalStateException("doors are not open");
		}
		if ( !hasRoom() ) {
			throw new IllegalStateException("elevator is at capacity");
		}
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] passenger "
		    + passenger.getID() + " entering elevator " + id_ + " on floor "
		    + floor_);
		passengers_.add(passenger);
	}

	/**
	 * A person gets off the elevator. The elevator's doors must be open.
	 * 
	 * @param passenger
	 *          the person getting off (must be on the elevator)
	 */
	public void exit ( Person passenger ) {
		if ( !open_ ) {
			throw new IllegalStateException("doors are not open");
		}
		if ( !passengers_.contains(passenger) ) {
			throw new IllegalArgumentException("passenger is not on the elevator");
		}
		System.out.println(System.currentTimeMillis() + " "
		    + Thread.currentThread().getName() + ": [elevator] passenger "
		    + passenger.getID() + " exiting elevator " + id_ + " on floor "
		    + floor_);
		passengers_.remove(passenger);
	}

	/**
	 * Are the elevator's doors open?
	 * 
	 * @return true if the elevator's doors are open, false if they are closed
	 */
	public boolean doorsOpen () {
		return open_;
	}

	/**
	 * Get the floor the elevator is on. (This is the last floor the elevator was
	 * on if it is between floors.)
	 * 
	 * @return the floor the elevator is on
	 */
	public int getFloor () {
		return floor_;
	}

	/**
	 * Is the elevator idling? The elevator should idle (not move) if no buttons
	 * are pushed.
	 * 
	 * @return true if the elevator should idle (no buttons are pushed), false
	 *         otherwise
	 */
	public boolean idle () {
		synchronized ( buttons_ ) {
			for ( boolean button : buttons_ ) {
				if ( button ) {
					return false;
				}
			}
			return true;
		}
	}
}
