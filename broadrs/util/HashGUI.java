package broadrs.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashGUI {

public static String md5(String senha){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            return hash.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Erro MD5: "+ex.getMessage());
            return null;
        }
   }

}
