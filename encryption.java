import java.security.KeyPair;
import java.security.KeyPairGenerator;

 

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

 


import javax.crypto.Cipher;

 


public class encryption {
    public static void main(String args[]) throws Exception
    {
        String plainText = "With the dev server running, do the following three";
        Map<String,Object> keys = generateRSAKeys();
        
        PrivateKey privateKey = (PrivateKey) keys.get("private");
        PublicKey publicKey = (PublicKey) keys.get("public");
        
        String encryptedText = encryptMessage(plainText,privateKey);
        String decryptedText = decryptMessage(encryptedText,publicKey);
        
        System.out.println("input : " +plainText);
        System.out.println("encrypted : "+encryptedText);
        System.out.println("decrypted : "+decryptedText);
    }


    private static Map<String, Object> generateRSAKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey =keyPair.getPublic();
        
        Map<String,Object> keys = new HashMap<String,Object>();
        keys.put("private", privateKey);
        keys.put("public", publicKey);
        return keys;
    }
    
    private static String decryptMessage(String encryptedText, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
    }

 

    private static String encryptMessage(String plainText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }

 

}
 