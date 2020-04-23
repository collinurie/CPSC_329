

import java.util.HashMap;

/**
 * Represents a machine language instruction.  An instruction has a name,
 * an opcode, and whatever data is appropriate for the instruction.
 * Names are not case sensitive.  Opcodes greater than 15 are for
 * pseudoinstructions.  Opcodes 15 or less correspond directly to
 * machine language opcodes.
 */
public class Instruction extends ProgramItem {

	private final static HashMap<String, Integer> opcodeNum;
	private final static HashMap<String, Integer> opcodeType;
	
	private static boolean allowBigLimm = false;
	private static boolean allowBigSimm = false;
	
	/**
	 * The "type" of an instruction determines what kind of data it takes.
	 * For example, an instruction of type Instruction.REG2 takes two registers 
	 * numbers, ra and rb, as data.  An instruction of type Instruction.REG_LABEL
	 * takes one register number, ra, and a string giving the name of a label.
	 */
	public final static int // instruction argument types
		REG0 = 0,    // no arguments; instruction name only (syscall, ret)
		REG1 = 1,    // one register (push, pop)
		REG2 = 2,    // two registers (jalr)
		REG3 = 3,    // three registers (ALU instructions)
		REG_LIMM = 4,    // register and LIMM (li, lui)
		REG_LABEL = 6,   // register and label (beqz, bnez, lwa, swa)
		LABEL = 5,       // label only (la, bl, b)
		MEM = 7,         // two registers and SIMM (lw, sw)
		REG_BIMM = 8,    // register and BIMM (lbi)
		REG2_BIMM = 9,   // 2 registers and a (big) immediate, not used (for things like ADDI $1 $2 345)
		REG2_LABEL = 10; // 2 registers and a Label, not used (for things like BGT $1 $2 Foo)
	
	private String name;  // The instruction name.
	private int opcode;   // The instruction opcode.
	private int type;     // The instruction type, given as one of the above constants.
	private int ra, rb, rc;        // Register numbers.
	private int simm, limm, bimm;  // Immediate values.
	private String label = null;  // A label name.
	
	/**
	 * Tests whether an instruction with the given name exists.
	 * Instruction names are not case sensitive.
	 */
	public static boolean exists(String op) {
		if (op == null)
			return false;
		else
			return opcodeNum.containsKey(op.trim().toLowerCase());
	}
	
	/**
	 * Get the instruction type for a given instruction name.  The return
	 * value is one of the constants such as Instruction.REG_LIMM.
	 * Throws IllegalArgumentExcpetino if the name is not an instruction name.
	 */
	public static int instructionType(String name) {
		if (name == null)
			throw new IllegalArgumentException("In struction name can't be null");
		Integer type = opcodeType.get(name.toLowerCase());
		if (type == null)
			throw new IllegalArgumentException("Unknown instruction name: " + name);
		return type;
	}
	
	/**
	 * Call Instruction.allowBigLimms() to allow LI, LUI to take 16-bit immediate values.
	 * The change cannot be reversed
	 */
	public static void allowBigLimms() {
		allowBigLimm = true;
	}
	
	/**
	 * Call Instruction.allowBigSimms() to allow SW, LW to take 16-bit immediate values.
	 * The change cannot be reversed
	 */
	public static void allowBigSimm() {
		allowBigSimm = true;
	}
	
	/**
	 * Construct an Instruction from an instruction name and the data values needed for 
	 * the instruction.  Example:  new Instruction("la", 3, "foo").  Note that for the 
	 * lw and sw instructions, the order of arguments must be ra, rb, SIMM.
	 * @param name the name of the instruction (not case sensitive, converted to lower case.
	 * @param arg data values for the instruction; all values will be integers and strings.
	 * @throws IllegalArgumentException if name is null of the name is not the name of an
	 *    instruction or if data values don't match the data required by the instruction.
	 *    Bad data can include null values in the argument list, values of the wrong type,
	 *    and values out of range.  Strings, which can only occur as label names, are
	 *    not checked for correct label name syntax.  A register is specified as a
	 *    number in the range 0 to 15, and an error occurs for an out-of-range value.
	 *    Immediate values are range-checked.  The normal range for LIMM is -127 to 255, and
	 *    values in the range 128 to 255 are converted into their negative 8-bit equivalents.
	 *    For a SIMM, the range is -7 to 15, and values in the range 8 to 15 are converted
	 *    into their negative 4-bit equivalents.
	 */
	public Instruction(String name, Object... arg) {
		if (name == null)
			throw new IllegalArgumentException("Instruction name can't be null.");
		name = name.toLowerCase();
		if (!opcodeNum.containsKey(name))
			throw new IllegalArgumentException("Unknown instruction: " + name);
		this.name = name;
		opcode = opcodeNum.get(name);
		type = opcodeType.get(name);
		int argCount = 2;
		if (type == REG0)
			argCount = 0;
		else if (type == REG1 || type == LABEL)
			argCount = 1;
		else if (type == REG3 || type == MEM || type == REG2_BIMM || type == REG2_LABEL)
			argCount = 3;
		if (arg.length != argCount)
			throw new IllegalArgumentException("Wrong number of arguments for instruction: " + name);
		for (int i = 0; i < argCount; i++) {
			if (arg[i] == null)
				throw new IllegalArgumentException("Argument for instruction can't be null.");
			if ((type == LABEL && i == 0) || (type == REG_LABEL && i == 1) || (type == REG2_LABEL && i == 2) ) {
				if ( ! (arg[i] instanceof String ) )
					throw new IllegalArgumentException("Unexpected type for argument " + i + "; should be String");
			}
			else {
				if ( ! (arg[i] instanceof Integer) )
					throw new IllegalArgumentException("Unexpected type for argument " + i + "; should be integer");
			}
		}
		switch (type) {
		case REG3:
			rc = (Integer)arg[2];
			// drop through
		case REG2:
			rb = (Integer)arg[1];
			// drop through
		case REG1:
			ra = (Integer)arg[0];
			break;
		case REG_LIMM:
			ra = (Integer)arg[0];
			limm = (Integer)arg[1];
			break;
		case REG_BIMM:
			ra = (Integer)arg[0];
			bimm = (Integer)arg[1];
			break;
		case REG_LABEL:
			ra = (Integer)arg[0];
			label = (String)arg[1];
			break;
		case LABEL:
			label = (String)arg[0];
			break;
		case MEM:
			ra = (Integer)arg[0];
			rb = (Integer)arg[1];
			simm = (Integer)arg[2];
			break;
		case REG2_BIMM:
			ra = (Integer)arg[0];
			rb = (Integer)arg[1];
			bimm = (Integer)arg[2];
			break;
		case REG2_LABEL:
			ra = (Integer)arg[0];
			rb = (Integer)arg[1];
			label = (String)arg[2];
			break;
		}
		if (ra < 0 || ra > 15)
			throw new IllegalArgumentException("Illegal register number for ra: " + ra);
		if (rb < 0 || rb > 15)
			throw new IllegalArgumentException("Illegal register number for rb: " + rb);
		if (rc < 0 || rc > 15)
			throw new IllegalArgumentException("Illegal register number for rc: " + rc);
		if (allowBigLimm) {
			if (limm < -32768 || limm > 65535)
				throw new IllegalArgumentException("Value out of range for big limm: " + limm);
		}
		else {
			if (limm < -128 || limm > 255)
				throw new IllegalArgumentException("Value out of range for limm: " + limm);
			if (limm > 127)
				limm = (byte)limm;
		}
		if (allowBigSimm) {
			if (simm < -32768 || simm > 65535)
				throw new IllegalArgumentException("Value out of range for big simm: " + simm);
		}
		else {
			if (simm < -8 || simm > 15)
				throw new IllegalArgumentException("Illegal value for simm: " + simm);
			if (simm > 7)
				simm = (simm << 28) >>> 27;
		}
		if (bimm < -32768 || bimm > 65535)
			throw new IllegalArgumentException("Illegal value for bimm: " + bimm);
		if (bimm < 0)
			bimm = bimm & 0xFFFF;
	}
	
	/**
	 * Return the mnemonic name for this instruction.  The name is always in lower case.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the opcode for this instruction, which can be greater than 15 for pseudoinstructions.
	 */
	public int getOpcode() {
		return opcode;
	}
	
	/**
	 * Get the type of the instruction, which determines what kind of data it needs.
	 * The type is one of the constants Instruction.REG0, Instruction.REG1, etc.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Return the number of the first register value for this instruction, or return 0 if
	 * the instruction does not take a register argument.
	 */
	public int getRa() {
		return ra;
	}
	
	/**
	 * Return the number of the second register value for this instruction, or return 0 if
	 * the instruction does not take two register arguments.
	 */
	public int getRb() {
		return rb;
	}
	
	/**
	 * Return the number of the second register value for this instruction, or return 0 if
	 * the instruction does not take two register arguments.
	 */
	public int getRc() {
		return rc;
	}
	
	/**
	 * Return the SIMM value for this instruction, or return 0 if the
	 * instruction does not take a SIMM argument.
	 */
	public int getSimm() {
		return simm;
	}
	
	/**
	 * Return the LIMM value for this instruction, or return 0 if the
	 * instruction does not take a LIMM argument.
	 */
	public int getLimm() {
		return limm;
	}
	
	/**
	 * Return the BIMM value for this instruction, or return 0 if the
	 * instruction does not take a BIMM argument.
	 */
	public int getBimm() {
		return bimm;
	}
	
	/**
	 * Return the label name argument for this instruction, or return null
	 * instruction does not take a label argument.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Return a string representation of this instruction, as it might appear
	 * in an assembly language program/
	 */
	public String toString() {
		String str = name;
		switch (opcodeType.get(name)) {
		case REG1:
			str += " $" + ra;
			break;
		case REG2:
			str += " $" + ra;
			str += " $" + rb;
			break;
		case REG3:
			str += " $" + ra;
			str += " $" + rb;
			str += " $" + rc;
			break;
		case LABEL:
			str += " " + label;
			break;
		case REG_LIMM:
			str += " $" + ra;
			str += " " + limm;
			break;
		case REG_LABEL:
			str += " $" + ra;
			str += " " + label;
			break;
		case MEM:
			str += " $" + ra;
			str += " " + simm;
			str += "($" + rb + ")"; 
		}
		return str;
	}
	
	static { // static initializer initializes static member variables when class is loaded.
		opcodeNum = new HashMap<>();
		opcodeType = new HashMap<>();
		String[] names = {  // instruction names; instruction opcode is the array index.
			"add", "sub", "mul", "div", "sll", "srl", "nor", "slt",
			"li", "lui", "beqz", "bnez", "lw", "sw", "jalr", "syscall",
			"la", "lbi", "lwa", "swa", "b", "bl", "ret", "push", "pop", "mov"
		};
		int[] types = {  // instruction types corresponding to instruction names in the previous array.
			REG3, REG3, REG3, REG3, REG3, REG3, REG3, REG3, 
			REG_LIMM, REG_LIMM, REG_LABEL, REG_LABEL, MEM, MEM, REG2, REG0,
			REG_LABEL, REG_BIMM, REG_LABEL, REG_LABEL, LABEL, LABEL, REG0, REG1, REG1 , REG2
		};
		for (int i = 0; i < names.length; i++) {
			opcodeType.put(names[i],types[i]);
			opcodeNum.put(names[i],i);			
		}
	}

}
