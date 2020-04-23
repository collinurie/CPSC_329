package lasm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * An assembler for the "Lasm" assembly language for the Larc model computer.
 */
public class Lasm {
		
	public static void main(String[] args) {
		
		File programFile;
		if (args.length > 0)
			programFile = new File(args[0]);
		else {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Select assembly language program file.");
			if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
				System.exit(0);
			programFile = chooser.getSelectedFile();
		}
		if (! (programFile.getName().endsWith(".s") || programFile.getName().endsWith(".S")) ) {
			System.out.println("***Error: File name must end with '.s'.");
			System.exit(0); // Might be needed to fully shut down if FileChooser was used.
			return;
		}
		Scanner in;
		try {
			in = new Scanner(programFile);
		}
		catch (FileNotFoundException e) {
			System.out.println("***Error: Can't open file for reading.");
			System.exit(0); 
			return;
		}
		ArrayList<ProgramItem> program;
		program = Parser.parse(in);
		in.close();
		if (program == null) {
			System.out.println("Could not parse " + programFile.getPath());
			System.exit(0); 
			return;
		}
		
		// FOR TESTING ONLY, print parsed program to standard output.
		printProgram(program);
		
/*
		// Create a PrintWriter to write the machine language program
		String outputFileName = programFile.getName();
		int period = outputFileName.lastIndexOf('.');
		outputFileName = outputFileName.substring(0, period);  // strip off file extension
		outputFileName += ".out";  // add new file extension for ML program
		File outputFile = new File( programFile.getParentFile(), outputFileName);
		PrintWriter out;
		try {
			out = new PrintWriter(outputFile);
		} catch (FileNotFoundException e) {
			System.out.println("File was successfully parsed, but could not write output file.");
		}
*/		
		
		System.exit(0);
	}
	

	/**
	 * Prints a Lasm program given as an ArrayList of ProgramItems to standard output.
	 */
	private static void printProgram(ArrayList<ProgramItem> program) {
		for (ProgramItem item: program) {
			if (item instanceof Label)
				System.out.println(item + ":");
			else
				System.out.println("    " + item);
		}
	}

}
