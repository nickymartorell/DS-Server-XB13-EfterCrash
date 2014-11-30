package Testing;
import java.security.Key;
	import javax.crypto.spec.SecretKeySpec;
	import javax.crypto.Cipher;
	import java.util.Scanner;

	import sun.misc.*;
public class EncryptionTestClass {
	
	

		
			private static String algorithm = "AES";
			private static byte[] keyValue=new byte[] 
			{ 'M', 'A', 'D', 'E', 'B', 'Y', 'X', 'B', '1', '3', 'B', 'Y', 'T', 'E', '1', '6'};

			        // The encrypting is made here
			       
					public static String encrypt(String plainText) throws Exception 
			        {
			                Key key = generateKey();
			                Cipher chiper = Cipher.getInstance(algorithm);
			                chiper.init(Cipher.ENCRYPT_MODE, key);
			                byte[] encVal = chiper.doFinal(plainText.getBytes());
			                String encryptedValue = new BASE64Encoder().encode(encVal);
			                return encryptedValue;
			        }

			        // The decryption is made here
			      
					public static String decrypt(String encryptedText) throws Exception 
			        {
			                // generate key 
			                Key key = generateKey();
			                Cipher chiper = Cipher.getInstance(algorithm);
			                chiper.init(Cipher.DECRYPT_MODE, key);
			                byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
			                byte[] decValue = chiper.doFinal(decordedValue);
			                String decryptedValue = new String(decValue);
			                return decryptedValue;
			        }

			//generateKey() is used to generate a secret key for AES algorithm
			        private static Key generateKey() throws Exception 
			        {
			                Key key = new SecretKeySpec(keyValue, algorithm);
			                return key;
			        }

			        // performs encryption & decryption 
			        public static void main(String[] args) throws Exception 
			        {
			        	
			              	Scanner user_input=new Scanner(System.in);
			        		String plainText;
			        		System.out.print("enter something to encrypt");
			        		plainText=user_input.nextLine();
			        		user_input.close();
			                String encryptedText = encrypt(plainText);
			                String decryptedText = decrypt(encryptedText);

			                System.out.println("Input : " + plainText);
			                System.out.println("Encrypted Text : " + encryptedText);
			                System.out.println("Decrypted Text : " + decryptedText);
			        }

	}



