package Login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    public Hashing(){

    }
    public String passwordHashing(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] input = password.getBytes();
        byte[] output = md.digest(input);

        StringBuilder hexString = new StringBuilder();
        for(int i = 0; i < output.length; i++){
            String hex = Integer.toHexString(0xff & output[i]);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
