package lasm;

import java.util.HashMap;

/**
 * A Label represents a label in a Lasm program.  In addition to properties
 * inherited from ProgramItem, a Label has a name.  There can only be one
 * Label with a given name.  The Label with a given name can be created,
 * if it does not yet exist, or retrieved, if it does exist, using the
 * static method Label.valueOf(name).  A name must begin with a letter
 * or an underscore and can contain only letters, digits. and underscores.
 * Names are sensitive.  Labels in an assemble program cannot begin with
 * underscore; such labels are reserved for internal use by an assembler.
 */
public class Label extends ProgramItem {
	
	private final static HashMap<String, Label> labels = new HashMap<>();
	
	private final String name;

	/**
	 * Get the Label with the given name, creating it if it does not
	 * already exist.  Will throw an IllegalArgumentExeption if the name 
	 * is null or is not a legal Label name.
	 */
	public static Label valueOf(String name) {
		if ( ! labels.containsKey(name) )
			labels.put(name, new Label(name));
		return labels.get(name);
	}
	
	/**
	 * Test whether a label with a given name exits.  (Does NOT throw
	 * an Exception for an illegal or null name.)
	 */
	public static boolean exists(String name) {
		return labels.containsKey(name);
	}
	
	/**
	 * Returns the name of this Label.
	 */
	public String getName() {
		return name;
	}
	
	private Label(String name) {
		if (name == null || name.trim().length() == 0)
			throw new IllegalArgumentException("Label name can't be null.");
		name = name.trim();
		if ( ! name.matches("[a-zA-z_][a-zA-Z0-9_]*"))
			throw new IllegalArgumentException("Illegal label name: " + name);
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

}
