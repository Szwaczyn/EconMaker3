package hoodStuff;

import java.math.BigInteger;
import java.security.*;

/**
 * Created $(DATE)
 */
public class StringOperation
{
    private String content;

    /**
     *  Public methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String encryptString()
    {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            sha.update(this.content.getBytes());
            byte[] msgDiest = sha.digest();
            this.content = msgDiest.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return this.content;
    }

    public String encryptString(String string)
    {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            sha.update(string.getBytes());
            byte[] msgDiest = sha.digest();
            this.content = msgDiest.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return this.content;
    }

    /**
     *  Private methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private String getHashText(byte[] cleanText)
    {
        StringBuilder sb = new StringBuilder();
        for(byte b : cleanText) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private MessageDigest getEncryptionMethod(MessageDigest encryptionMethod)
    {
        try {
            encryptionMethod = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Object: " + this.getClass() + " Method getEWncryption");
            e.printStackTrace();
        }

        return encryptionMethod;
    }

    /**
     *  Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public StringOperation() {}

    public StringOperation(String string)
    {
        this.content = string;
    }
}
