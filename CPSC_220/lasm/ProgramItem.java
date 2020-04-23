package lasm;

/**
 * Abstract base class for items in a Larc assembly language program.  Concrete
 * subclasses represent Labels, Directives, and Instructions.  Every program
 * item has a lineNumber, which is set by the parser to record the line number
 * on the program where the item was found.  It also has an address and a size,
 * which are not used in any way by the parser (they could be used in code
 * analysis and code generation).  The values for line number, size, and
 * address are initially equal to -1.
 */
abstract public class ProgramItem {
	
	private int address = -1;
	private int size = -1;
	private int lineNumber = -1;
	
	public void setAddress(int address) {
		this.address = address;
	}
	
	public int getAddress() {
		return address;
	}
	
	public void setLineNumber(int line) {
		lineNumber = line;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
}
