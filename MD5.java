import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5 {

    private final static String salt = "lwerjqk34;qrhq3o4i78er;gjkehnk;fjklwehfa;lj.fdkvhd";

    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);

        String password = "password";

        String empty = null;

        String msg = "MD5 is one in a series of message digest algorithms designed by Professor Ronald Rivest of MIT (Rivest, 1992). When analytic work indicated that MD5's predecessor MD4 was likely to be insecure";

        System.out.println(password + " MD5 hashed to :  " + md5Hash(password));

        System.out.println(empty + " MD5 hashed to :  " + md5Hash(null));

        System.out.println(msg + " MD5 hashed to :  " + md5Hash(msg));
        
        String str = sc.nextLine();
        if( md5Hash(msg).equals( md5Hash(str)))
        {
        	System.out.println("Both String are Equal ");
        }
 
    }

    public static String md5Hash(String message) {
    	
        String md5 = "";

        if (null == message)
            return null;
        
        message = message + salt;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(message.getBytes(), 0, message.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        }

        return md5;

    }

}