import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;


public class LarcSim {
	
	static short[] memory = new short[65536];
	static short[] register = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	

	/**
	 * The main routine reads a Larc machine language program from a specified
	 * input file.  The instructions are stored in an array of short, and the
	 * array is passed to a loadProgram() method, which should load the
	 * program into the (simulated) memory.  Then an execute() method is
	 * called, which should simulate the execution of the program.  The name of
	 * the input file can be given as a command-line parameter; if it is not,
	 * then a JFileChooser will be opened to allow the user to select the
	 * file.  If the specified file does not contain a legal Larc machine
	 * language program, the main routine will report the error and exit
	 * without trying to load or execute the program.
	 */
	public static void main(String[] args) {
		Scanner in;
		ArrayList<Integer> instructions = new ArrayList<Integer>();
		try {
			if (args.length > 0) {
				String filename = args[0];
				try {
					in = new Scanner(new File(filename));
				}
				catch (FileNotFoundException e) {
					throw new Exception("Cannot open file \"" + filename + "\".");
				}
			}
			else {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Select machine language program file.");
				if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
					System.exit(0);
				try {
					in = new Scanner(chooser.getSelectedFile());
				}
				catch (FileNotFoundException e) {
					throw new Exception("Cannot open file \"" + chooser.getSelectedFile().getName() + "\".");
				}
			}
			if (! in.hasNextLine() )
				throw new Exception("Input file is empty.");
			int lineNumber = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine().trim().toUpperCase();
				lineNumber++;
				if (line.startsWith("#") || line.length() == 0)
					continue;
				int num = 0; 
				if (line.startsWith("0X")) { // hexadecimal
					if (line.length() != 6)
						throw new Exception("Illegal instruction on line " + lineNumber + ".");
					for (int i = 2; i < 6; i++) {
						char ch = line.charAt(i);
						if ('0' <= ch && ch <= '9')
							num = num<<4 | (ch-'0');
						else if ('A' <= ch && ch <= 'F')
							num = num<<4 | (ch-'A'+10);
						else
							throw new Exception("Illegal instruction on line " + lineNumber + ".");
					}
					instructions.add(num);
				}
				else {
					if (line.length() != 16)
						throw new Exception("Illegal instruction on line " + lineNumber + ".");
					for (int i = 0; i < 16; i++) {
						char ch = line.charAt(i);
						if (ch == '0')
							num = num*2;
						else if (ch == '1')
							num = num<<1 | 1;
						else
							throw new Exception("Illegal instruction on line " + lineNumber + ".");
					}
					instructions.add(num);
				}
			}
			if (instructions.size() > 65536)
				throw new Exception("More instructions in program than will fit in Larc memory.");
		}
		catch (Exception e) {
			System.out.println();
			System.out.println("*** An error occurred while trying to load the program.");
			System.out.println("*** " + e.getMessage());
			System.out.println();
		}
		short[] program = new short[instructions.size()];
		for (int i = 0; i < instructions.size(); i++)
			program[i] = instructions.get(i).shortValue();
		try {
			loadProgram(program);
			System.out.println("Program has been loaded into memory.");
			System.out.println("----------------------------------------------------");
			execute();
			System.out.println("----------------------------------------------------");
			System.out.println("Program has halted.");
		}
		catch (Exception e) {
			System.out.println("*** INTERNAL ERROR: Larc simulator has crashed!");
			System.out.println("*** " + e);
		}
	}


	/***************************************************************************************
	 * This method will load a Larc machine language program into Larc memory,
	 * starting at memory location zero.
	 * 
	 * @param machineLanguageProgram  Contains the list of machine language instructions
	 *   of the program to be executed. 
	 ****************************************************************************************/
	private static void loadProgram(short[] machineLanguageProgram) {
		
		// loops through machine language program and copies it into memory. 
		for(int i = 0; i < machineLanguageProgram.length; i++) {
			memory[i] = machineLanguageProgram[i];
		}// end loop 
		
	}// end loadProgram
	
	
	/****************************************************************************************
	 * This method will simulate the execution of the Larc machine language 
	 * program that has been stored in the Larc memory, starting at PC = 0;
	 ****************************************************************************************/
	private static void execute() {
		boolean halt = false;
		int pc = 0;
		
		while(!halt) {
			register[0] = 0;
			int opCode = memory[pc]>>12 & 15;
			int byte2 = memory[pc] >> 8 & 15;
			int byte3 = memory[pc] >> 4 & 15;
			int byte4 = memory[pc] & 15;
			//System.out.println(opCode +""+ byte2+""+byte3+""+byte4);
			pc++;

			/**
			 * TODO: fetch command, get opcode   8 
			 */
			switch(opCode) {
			case 0:// addition
				register[byte2] = (short) (register[byte3] + register[byte4]);
				break;
			case 1: // subtraction
				register[byte2] = (short) (register[byte3] - register[byte4]);
				break;
			case 2:// multiplication
				register[byte2] = (short) (register[byte3] * register[byte4]);
				break;
			case 3:// division
				register[byte2] = (short) (register[byte3] / register[byte4]);
				break;
			case 4: // shift left
				register[byte2] = (short) (register[byte3] << register[byte4]);
				break;
			case 5:// shift right 
				register[byte2] = (short) (register[byte3] >>> register[byte4]);
				break;
			case 6: // NOR
				register[byte2] = (short) ~(register[byte3] | register[byte4]);
				break;
			case 7:// if less than 
				if(register[byte3] < register[byte4])
					register[byte2] = 1;
				else
					register[byte2] = 0;
				break;
			case 8:// load immediate 
				register[byte2] = (short)((byte3<<4)+byte4);
				break;
			case 9: // load upper immediate
				register[byte2] = (short)((byte3<<12)+ (byte4<<8));
				break;
			case 10: // branch if 0
				/**
				 * TODONE: somehow get sign extension on this to check for negative numbers??
				 */
				if(register[byte2] == 0) {
//					byte b3 = (byte)byte3;
//					byte b4 = (byte)byte4;
					int shiftInt = (byte3<<4) + byte4;
					//pc += (byte3<<4) + byte4;
					byte shift = (byte)shiftInt;
					pc += shift;
				}
				break;
			case 11:// branch if !0
				/**
				 * TODONE: somehow get sign extension on this to check for negative numbers??
				 */
				if(register[byte2] != 0) {
					//pc += (byte3<<4) + byte4;
					
					int shiftInt = (byte3<<4) + byte4;
					//pc += (byte3<<4) + byte4;
					byte shift = (byte)shiftInt;
					pc += (int)shift;
					
				}
				break;
			case 12:// memory load
				int loc = register[byte3];
				//register[byte2] = memory[byte3 + byte4];
				register[byte2] = memory[loc];
				break;
				
			case 13:// memory store
				int loca = register[byte3];
				memory[loca] = register[byte2];
				//memory[byte2] = register[byte3+byte4];
				break;
			case 14: // memory jump
				/**
				 * TODO: need to understand this more.... Go to office hours :/
				 */
				register[byte2] = (short) pc;
				pc = register[byte3];
				break;
			case 15:// System Call
				sysCall();
				break;
			default:
				System.out.println("OPCODE not recognized... :(");
			
			}
			
			if(pc > 1000) halt = true;

			
		}// end while
		for(int i = 0; i < register.length; i++) {
			//System.out.println("REG["+i+"]: "+register[i]);
		}
		
	}// end execute
	
	/****************************************************************************************
	 * determines what sys call was made and then acts on behalf of the system.
	 ****************************************************************************************/
	public static void sysCall(){
		Scanner s = new Scanner(System.in);
		
		switch(register[1]) {
		case 0: // halt
			//System.exit(0);
			return;
			//end case 0
			
		case 1: // print string       i < register[2] + register[3]
			for(int i = register[2]; i < register[2] + register[3]; i++) {
				char character = (char)memory[i];// gets the binary number and turns it into a char
				if(memory[i] == 0) {
					break;// this would mean its at the end of string
				}
				else {
					System.out.print(character);
				}
			}
			break;
			// end case 1
			
		case 2: // print int
			System.out.print(register[2]);
			break;
			//end case 2 
			
		case 3:// read string
			String str = s.nextLine();
			int memIndex = register[2];
			for(int i = 0; i < str.length(); i++) { // gets each char and places it in memory
				short ch = (short)str.charAt(i);
				memory[memIndex] = ch;
				memIndex++;
			}
			break;
			// end case 3
			
		case 4:// read int
			short num = (short)s.nextInt();
			register[1] = num;
			break;
			// end case 4
			
		}
	}


} // end LarcSim