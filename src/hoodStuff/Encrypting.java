package hoodStuff;

import java.math.BigInteger;
import java.security.*;

/**
 * Created $(DATE)
 */
public class Encrypting
{
    private String content;

    /**
     *  Public methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String MD5(String string)
    {
        String hashText = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md5.digest(string.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashText = number.toString();
        } catch (NoSuchAlgorithmException e) {
            //TODO SYSTEM ERROR
            e.printStackTrace();
        }

        return hashText;
    }

    public String MD5()
    {
        String hashText = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md5.digest(this.content.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashText = number.toString();
        } catch (NoSuchAlgorithmException e) {
            //TODO SYSTEM ERROR
            e.printStackTrace();
        }

        return hashText;
    }

    /**
     *  Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public Encrypting() {}

    public Encrypting(String string)
    {
        this.content = string;
    }
}
