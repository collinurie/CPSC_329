import java.util.LinkedList;
import java.util.Queue;

/**
 * A person who can ride elevators.
 */
public class Person {

	private int id_; // unique ID for the person
	private Queue<Integer> floors_; // the person's destinations

	/**
	 * Create a person with the specified ID and a list of floors to visit. Floor 0
	 * is the ground floor.
	 * 
	 * @param id
	 *            ID (should be unique)
	 * @param floors
	 *            list of floors to visit, separated by spaces (the first floor is
	 *            where the person starts)
	 */
	public Person(int id, String floors) {
		System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + ": creating person "
				+ id + ": " + floors);
		id_ = id;
		floors_ = new LinkedList<Integer>();
		for (String floor : floors.split(",")) {
			floors_.add(Integer.parseInt(floor));
		}
	}

	/**
	 * Get the person's ID.
	 * 
	 * @return the person's ID
	 */
	public int getID() {
		return id_;
	}

	/**
	 * Does the person have more floors to visit?
	 * 
	 * @return true if the person has more floors to visit, false if not
	 */
	public boolean hasNextDest() {
		return !floors_.isEmpty();
	}

	/**
	 * Get the next floor to visit, removing it from the destination list.
	 * 
	 * @return the next floor to visit
	 */
	public int nextDest() {
		return floors_.remove();
	}

}
