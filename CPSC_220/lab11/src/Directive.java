/**
 * Represents a directive in a Lasm program.  The only legal directive names
 * are ".asciz", ".word", and ".space".   A directive has associated data.
 * For ".asciz", the data is a string.  For ".space" and ".word", the data
 * is an integer.
 */
public class Directive extends ProgramItem {
	
	final private String name;
	final private String stringValue;
	final private int intValue;
	
	/**
	 * Create a directive whose value is a String.  The only legal value for the
	 * name parameter is ".asciz".  The value string cannot be null.
	 */
	public Directive(String name, String value) {
		if (name == null)
			throw new IllegalArgumentException("Directive name can't be null.");
		name = name.toLowerCase();
		if ( ! (name.equals(".word") || name.equals(".asciz") || name.equals(".space")) )
			throw new IllegalArgumentException("Unknown directive: " + name);
		if ( ! name.equals(".asciz") )
			throw new IllegalArgumentException("Only .asciz directive has a string argument.");
		if (value == null)
			throw new IllegalArgumentException("String arugment for .asciz can't be null.");
		this.name = name;
		this.stringValue = value;
		this.intValue = Integer.MIN_VALUE;
	}
	
	/**
	 * Create a directive whose value is an integer.  The only legal value for the
	 * name parameter are ".space" and ".word".  The data value must be expressible
	 * in 16 bits.  For ".space", the value parameter must be in the range 1 to 65535.
	 * For ".word" the data value must be in the range -32768 to 65535.
	 */
	public Directive(String name, int value) {
		if (name == null)
			throw new IllegalArgumentException("Directive name can't be null.");
		name = name.toLowerCase();
		if ( ! (name.equals(".word") || name.equals(".asciz") || name.equals(".space")) )
			throw new IllegalArgumentException("Unknown directive: " + name);
		if ( name.equals(".asciz") )
			throw new IllegalArgumentException(".asciz directive requires a string argument.");
		if ( name.equals(".space") && value < 0 || value > 65535)
			throw new IllegalArgumentException("Argument for .space must be in the range 0 to 65535");
		if ( name.equals(".space") && value < -32768 || value > 65535)
			throw new IllegalArgumentException("Argument for .space must be in the range -32768 to 65535");
		this.name = name;
		this.stringValue = null;
		this.intValue = value;
	}

	/**
	 * Returns the name of this directive.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the string value associated with an ".asciz" directive.  For ".space"
	 * and ".data", the return value is null.
	 */
	public String getStringValue() {
		return stringValue;
	}
	
	/**
	 * Return the integer value associated with this directive.  (For ".asciz" directives, the 
	 * return vallue is Integer.MIN_VALUE.)
	 */
	public int getIntValue() {
		return intValue;
	}
	
	/**
	 * Returns a string containing the name of this directive, followed by a space and
	 * the value of the directive.  The string is one that would represent the directive
	 * if it occurs in a Lasm program.
	 */
	public String toString() {
		if (stringValue == null)
			return name + " " + intValue;
		else {
			StringBuilder b = new StringBuilder();
			b.append(name);
			b.append(" \"");
			for (int i = 0; i < stringValue.length(); i++) {
				char ch = stringValue.charAt(i);
				if (ch == '\n')
					b.append("\\n");
				else if (ch == '"')
					b.append("\\\"");
				else if (ch == '\\')
					b.append("\\\\");
				else
					b.append(ch);
			}
			b.append("\"");
			return b.toString();
		}
	}

}
