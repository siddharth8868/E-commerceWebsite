package helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;



public class PasswordUtil {

	public static String hashPassword(String password) throws NoSuchAlgorithmException {
	
		MessageDigest md =MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte[] myArray = md.digest();
		StringBuilder sb= new StringBuilder(myArray.length * 2);
		for(byte b: myArray){
			int v=b & 0xff;
			if(v <16){
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}
	
	
	public static String getSalt(){
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[32];
		r.nextBytes(saltBytes);
		return org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(saltBytes);
	}
	
	public static String hashAndSaltPassword(String password) throws NoSuchAlgorithmException {
		String salt = getSalt();
		return hashPassword(password + salt);
	}
	
}
