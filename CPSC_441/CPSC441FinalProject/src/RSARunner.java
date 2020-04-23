import java.math.BigInteger;
import java.util.Scanner;

/**********************************************************************************************
 * @author Collin Urie																		  *
 * 																							  *
 * This is not a proper simulation of how this would be implemented due 					  *
 * to the fact that the program will know both the public key and the private key.			  *
 * This is simply a demonstration of how a message would be encrypted, and then				  *
 * decrypted after it has been sent. 														  *
 *********************************************************************************************/
public class RSARunner {
	
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		
		// gather message to be encrypted then decrypted
		System.out.print("Enter message: ");
		String initialMessage = s.nextLine();
		
		RSAKeyGenerator keys = new RSAKeyGenerator(1024);
		
		
		// gather essential info to encrypt then decrypt. 
		BigInteger publicKey = keys.publicKey;
		BigInteger privateKey = keys.privateKey;
		BigInteger modulus = keys.modulus;
		
		
		System.out.println("\nOriginal Message : "+ initialMessage);
		
		// encrypt message 
		String encryptedMessage = encrypt(initialMessage,publicKey,modulus);
		System.out.println("\nEncrypted Message : "+ encryptedMessage);
		
		// decrypt message
		String decryptedMessage = decrypt(encryptedMessage,privateKey,modulus);
		System.out.println("\nDecrypted Message : "+ decryptedMessage);


		
	}
	
	/********************************************************************************************
	 * Takes a message and the two pieced of data it needs to encrypt the message,
	 * then encrypts the message into a big integer.
	 * @param mess - (BigInteger) message
	 * @param key - (BigInteger) key
	 * @param mod - (BigIngeger) modulus
	 * @return the encrypted message
	 ********************************************************************************************/
	public static String encrypt(String mess, BigInteger key, BigInteger mod) {
		String encryptedMessage = (new BigInteger(mess.getBytes())).modPow(key, mod).toString();
		return encryptedMessage;
	}// end encrypt
	
	
	/********************************************************************************************
	 * Takes a message and the two pieced of data it needs to decrypt the message,
	 * then decrypts the message into a big integer.
	 * @param mess - (BigInteger) message
	 * @param key - (BigInteger) key
	 * @param mod - (BigIngeger) modulus
	 * @return the decrypted message
	 ********************************************************************************************/
	public static String decrypt(String mess, BigInteger key, BigInteger mod) {
		String decryptedMessage =  new String((new BigInteger(mess)).modPow(key, mod).toByteArray());
		return decryptedMessage;
	}// end decrypt
	
	

}
