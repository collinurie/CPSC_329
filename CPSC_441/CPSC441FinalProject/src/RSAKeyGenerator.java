import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyGenerator {
	private final static SecureRandom random = new SecureRandom();
		
	public BigInteger privateKey;
	public BigInteger publicKey;
	public BigInteger modulus;
		
	// generate an N-bit (roughly) public and private key
	RSAKeyGenerator(int n) {
	   BigInteger p = BigInteger.probablePrime(n/2, random);
	   BigInteger q = BigInteger.probablePrime(n/2, random);
	   BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
	
	   modulus    = p.multiply(q);                                  
	   publicKey  = new BigInteger("65537");    
	   privateKey = publicKey.modInverse(phi);
	}

}
