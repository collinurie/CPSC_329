package lasm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides a static method that parses a Lasm program.
 */
public class Parser {
	
	/**
	 * Reads a Lasm program from a Scanner and parses it.  If errors are found in the
	 * program, they are printed to standard output and the return value of the method is
	 * null.  (Only the first 20 errors will be reported.)  If no errors are found,
	 * the return value is an ArrayList containing the ProgramItems from the program.
	 * The program items include Labels, Directives, and Instructions.  (The line number
	 * of each ProgramItem has been set, but addresses have not been computed for Labels.)
	 */
	public static ArrayList<ProgramItem> parse(Scanner in) {
		ArrayList<ProgramItem> items = new ArrayList<>();
		ArrayList<ProgramItem> dataItems = new ArrayList<>();
		boolean inDataSection = false;
		int lineNumber = 0;
		int errorCount = 0;
		int pos;
		while (in.hasNextLine()) {
			lineNumber++;
			String line = in.nextLine().trim();
			if (line.length() == 0 || line.startsWith("#")) // ignore empty or comment line
				continue;
			try {
				while (line.matches("[a-zA-Z][a-zA-Z0-9_]*:.*")) {
					// handle labels first, since other things can follow a label on a line
					pos = line.indexOf(":");
					String labelName = line.substring(0,pos).trim();
					if (Label.exists(labelName))
						throw new IllegalArgumentException("Duplicate label: " + labelName);
					Label labelItem = Label.valueOf(labelName); // can throw error
					labelItem.setLineNumber(lineNumber);
					if (inDataSection)
						dataItems.add(labelItem);
					else
						items.add(labelItem);
					line = line.substring(pos+1).trim();
				}
				if (line.startsWith(".asciz ") || line.startsWith(".asciiz ")) { 
					// handle strings next, since they can have special chars #, ", \ 
					pos = line.indexOf(" ");
					line = line.substring(pos).trim();
					if (line.length() == 0 || !line.startsWith("\""))
						throw new IllegalArgumentException("Missing string for .asciz directive");
					StringBuilder builder = new StringBuilder();
					int i = 1;
					while (i < line.length() && line.charAt(i) != '"') {
						char ch = line.charAt(i++);
						if (ch < 32 || ch == 127)
							throw new IllegalArgumentException("Non-printing character in string.");
						if (ch > 127)
							throw new IllegalArgumentException("Non-ASCII character in string.");
						if (ch == '\\') {
							if (i == line.length())
								break;
							ch = line.charAt(i++);
							if (ch == 'n')
								ch = '\n';
							else if (ch != '\\' && ch != '"')
								throw new IllegalArgumentException("Illegal special char '\\" + ch + "' in string.");
						}
						builder.append(ch);
					}
					if (i == line.length())
						throw new IllegalArgumentException("Missing terminating quote mark for string");
					i++; // skip terminating "
					line = line.substring(i).trim();
					if (line.length() > 0 && ! line.startsWith("#"))
						throw new IllegalArgumentException("Illegal data after end of string.");
					Directive directiveItem = new Directive(".asciz", builder.toString());
					directiveItem.setLineNumber(lineNumber);
					if (inDataSection)
						dataItems.add(directiveItem);
					else
						items.add(directiveItem);
				}
				pos = line.indexOf("#");
				if (pos >= 0) // strip off comment
					line = line.substring(0,pos).trim();
				if (line.length() == 0)
					continue;
				if (line.startsWith(".")) {  // line is a directive (not .asciz)
					if (line.equals(".data"))
						inDataSection = true;
					else if (line.equals(".text"))
						inDataSection = false;
					else {
						pos = line.indexOf(" ");
						if (pos == -1)
							throw new IllegalArgumentException("Error in directive (no text after period).");
						String directive = line.substring(0,pos);
						line = line.substring(pos+1).trim(); // data for directive
						int dirVal = parseNumber(line); // Can throw error
						Directive directiveItem = new Directive(directive, dirVal); // can throw error
						directiveItem.setLineNumber(lineNumber);
						if (inDataSection)
							dataItems.add(directiveItem);
						else
							items.add(directiveItem);
					}
				}
				else { // must be an instruction
					pos = line.indexOf(" ");
					String insName, insData;
					if (pos == -1) {
						insName = line.toLowerCase();
						insData = "";
					}
					else {
						insName = line.substring(0,pos).toLowerCase();
						insData = line.substring(pos+1).trim();
					}
					if (!Instruction.exists(insName))
						throw new IllegalArgumentException("Unknown instruction: " + insName);
					Object[] data = parseInsData(Instruction.instructionType(insName), insData);
					Instruction insItem = new Instruction(insName, data);
					insItem.setLineNumber(lineNumber);
					if (inDataSection)
						throw new IllegalArgumentException("Cannot have instructions in .data section.");
					items.add(insItem);
				}
			}
			catch (IllegalArgumentException e) {
				errorCount++;
				System.out.println(">>>Line " + lineNumber + ":  " + line);
				System.out.println("***Error:  " + e.getMessage());
				System.out.println();
				if (errorCount == 20) {
					System.out.println("***Too many errors!  Exiting.");
					System.out.println();
					break;
				}
			}
		}
		if (errorCount > 0)
			return null;
		else {
			items.addAll(dataItems);
			return items;
		}
	}
			
	private static int parseNumber(String num) {
		try {
			if (num.startsWith("0x") || num.startsWith("0X")) {
				return Integer.parseInt(num.substring(2), 16);
			}
			else {
				return Integer.parseInt(num);
			}
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal number: " + num);
		}
	}
	
	private static Object[] parseInsData( int type, String data ) {
		if (data == null)
			throw new IllegalArgumentException("Data can't be null");  // should not happen
		data = data.trim();
		if (type == Instruction.REG0) {
			if (data.length() == 0)
				return new Object[] { };
			throw new IllegalArgumentException("Wrong number of arguments for instruction.");
		}
		if (data.length() == 0) {
			throw new IllegalArgumentException("Wrong number of arguments for instruction.");
		}
		String[] tokens = data.split("\\s+");
		int argCount = 2;
		if (type == Instruction.REG0)
			argCount = 0;
		else if (type == Instruction.REG1 || type == Instruction.LABEL || type == Instruction.REG2_BIMM || type == Instruction.REG2_LABEL)
			argCount = 1;
		else if (type == Instruction.REG3)
			argCount = 3;
		if (tokens.length != argCount)
			throw new IllegalArgumentException("Wrong number of arguments for instruction.");
		switch (type) {
		case Instruction.REG0: 
			return new Object[] {  };
		case Instruction.REG1: 
			return new Object[] { parseRegister(tokens[0]) };
		case Instruction.REG2: 
			return new Object[] { parseRegister(tokens[0]), parseRegister(tokens[1]) };
		case Instruction.REG3: 
			return new Object[] { parseRegister(tokens[0]),  parseRegister(tokens[1]), parseRegister(tokens[2]) };
		case Instruction.REG_LIMM: 
			return new Object[] { parseRegister(tokens[0]), parseNumber(tokens[1]) };
		case Instruction.REG_LABEL: 
			return new Object[] { parseRegister(tokens[0]), parseLabel(tokens[1]) };
		case Instruction.REG2_LABEL: 
			return new Object[] { parseRegister(tokens[0]), parseRegister(tokens[1]), parseLabel(tokens[2]) };
		case Instruction.REG2_BIMM: 
			return new Object[] { parseRegister(tokens[0]),  parseRegister(tokens[1]), parseNumber(tokens[2]) };
		case Instruction.LABEL: 
			return new Object[] { parseLabel(tokens[0]) };
		case Instruction.MEM:  // two tokens, but second is actually simm(register)
			int pos = tokens[1].indexOf("(");
			if (pos == -1 || tokens[1].charAt(tokens[1].length()-1) != ')')
				throw new IllegalArgumentException("Illegal syntax in memory address");
			String simm = tokens[1].substring(0,pos);
			String reg = tokens[1].substring(pos+1,tokens[1].length()-1);
			return new Object[] { parseRegister(tokens[0]), parseRegister(reg), parseNumber(simm) };

		default: 
			throw new IllegalArgumentException("Illegal instruction type: " + type); // should not happen.
		}
	}
	
	private final static String[] REG_NAMES = {
			"zero", "v0", "a0", "a1", "t0", "t1", "t2", "s0", 
			"s1", "s2", "sp", "ra", "at0", "at1", "k0", "k1"
	};
	
	private static Integer parseRegister(String reg) {
		if ( ! reg.startsWith("$"))
			throw new IllegalArgumentException("Register name must start with '$'.");
		int regNum = -1;
		String rn = reg.substring(1).toLowerCase();
		for (int i = 0; i < 16; i++) {
			if (rn.equals(REG_NAMES[i])) {
				regNum = i;
				break;
			}
		}
		if (regNum == -1) {
			try {
				regNum = Integer.parseInt(reg.substring(1));
				if (regNum < 0 || regNum > 15)
					throw new NumberFormatException();
			}
			catch (NumberFormatException e) {
				throw new IllegalArgumentException("Illegal register name: " + reg);
			}
		}
		if (regNum > 11)
			throw new IllegalArgumentException("Attempt to use reserved register number " + regNum);
		return regNum;
	}
	
	private static String parseLabel(String str) {
		if (str.matches("[a-zA-z][a-zA-Z0-9_]*"))
			return str;
		throw new IllegalArgumentException("Illegal label: " + str);
	}

}
