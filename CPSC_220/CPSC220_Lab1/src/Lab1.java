import java.util.Scanner;

/**
 * This program takes input strings that represents decimal, binary,
 * and hexadecimal numbers.  It converts each input string into a value
 * of type int, detecting any errors in the input.  It then converts
 * the int to binary and hexadecimal strings, and it outputs the value
 * in all three forms.  The program ends when an empty line is input.
 */
public class Lab1 {

	public static void main(String[] args) {
		System.out.println("Enter positive or negative decimal numbers,");
		System.out.println("binary numbers starting with 0b, or hexadecimal");
		System.out.println("numbers starting with 0x.  Press return to end.");
		System.out.println();
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("INPUT>> ");
			String token = in.nextLine().trim();
			if (token.length() == 0)
				return;
			int n;
			try {
				n = stringToInt(token);
			}
			catch (IllegalArgumentException e) {
				System.out.printf("Illegal input '%s'%nError: %s%n%n",
									token, e.getMessage());
				continue;
			}
			System.out.printf("Decimal: %d%nBinary: %s%nHexadecimal: %s%n%n",
									n, toBinaryString(n), toHexString(n));
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
	
	/***********************************************
	 * Takes a string and turns it into an integer
	 * @param token
	 * @return
	 ***********************************************/
	public static int stringToInt(String str) {
		
		long n = 0;
		
		// if it is a binary input
		if(str.charAt(1) == 'b') {
			
			str = str.substring(2);
			
			int place = 0;

			for(int i = str.length()-1; i > -1; i--) {
				if(str.charAt(i) == '1') {
					n += 1<<place;
				}
				place++;
			}
			//System.out.println("bin num is: " + n);
	
		}// end binary if check
		
		// if it is a hex input
		else if(str.charAt(1) == 'x') {
			
			String values = "0123456789abcdef";
			
			str = str.substring(2);
			
			int place = 0;
			
			for(int i = str.length()-1; i > -1; i--) {
				char tempCh = str.charAt(i);
				int tempVal = values.indexOf(tempCh);
				n += tempVal << 4*place++;
			}
			
			//System.out.println("Hex num is: "+n);
		
		}// end hex if check
		
		// must either be decimal or it is not valid.
		else {
			boolean neg = false;
			if(str.charAt(0) == '-') {
				neg = true;
				str = str.substring(1);
			}
			for(int i = 0; i < str.length(); i++ ) {
				char ch = str.charAt(i);
				// checks to make sure string has only number chars in it
				if(ch <= '9' && ch >= '0') 
					n = n * 10+(ch-'0');
				else 
			        throw new IllegalArgumentException("Illegal character '" + ch + "'in decimal string.");
				
				// if the number is too large
			    if (n > Integer.MAX_VALUE) 
			        throw new IllegalArgumentException("Decimal value outside range for int.");
			 
			}
			if(neg)
		    	n *= -1;
			
		}
		//System.out.println(n);
		
		int retVal = (int)n;
		
		return retVal;
	}// end stringToint()
	
	
	/************************************************
	 * takes an integer and turns it into a Binary string
	 * @param n
	 * @return a binary conversion of n, as a string
	 ************************************************/
	public static String toBinaryString(int n) {
	
		String bin = "";
		
		for(int i = 0; i < 32; i++) {// sorts through all 32 bits
			if( ((n >> i)& 1) == 1)
				bin =  1+ bin;
			else
				bin = 0+ bin;
		}
		
//		while(n>0) {
//			int temp = n%2;
//			bin = temp+bin;
//			n /= 2;			
//		}
		for(int i = 0; i < bin.length(); i++) {
			if(bin.charAt(i) == '1') {
				bin = bin.substring(i);
				break;
			}
		}

		bin = "0b"+bin;
		
		
		
		return bin;
	}// end toBinaryString
	
	
	/************************************************
	 * takes an integer and turns it into a Hex string
	 * @param n
	 * @return a hex conversion of n, as a string
	 ************************************************/
	public static String toHexString(int n) {
		
		String values = "0123456789abcdef";
		String hex = "";
		
		
		for(int i = 0; i < 8; i++) { // sorths through all 32 bits
			
			int val = n >> i*4 & 15; // gets the char for place value 
			hex = values.charAt(val) + hex; 
			

		}
		
//		while(n>0) {
//			int temp = n%16;//gets value
//			char tempChar = values.charAt(temp);// gets the char with the proper hex value
//			hex = tempChar+hex;// adds char to string
//			n /= 16;
//		}
		for(int i = 0; i < hex.length(); i++) {
			if(hex.charAt(i) != '0') {
				hex = hex.substring(i);
				break;
			}
		}
		
		hex = hex.toUpperCase();
		hex = "0x"+hex;
		
		return hex;
	}// end toHexString
 
}



////////////////////////////////////////////bin to dec//////////////////////////////////
//str = str.substring(2);
//int pow = 0;
//for(int i = str.length() -1; i > -1; i--) {
//	if(str.charAt(i) == '1') {
//		double temp = Math.pow(2,pow); // gets the value at that place value
//		n = n+(long)temp; // ads value to total number
//	}
//	pow++;
//		
//}

//System.out.println("dec num is: "+n);
////////////////////////////////////////////hex to dec//////////////////////////////////
//String valString = "0123456789abcdef";
//str = str.substring(2);
//int pow = 0;
//for(int i = str.length() -1; i > -1; i--) {
//	int val = valString.indexOf(str.charAt(i));
//	
//	double temp = (val)*Math.pow(16,pow); // gets the value at that place value
//	n = n+(long)temp; // ads value to total number
//	pow++;
//		
//}

//System.out.println("Hex num is: "+n);


