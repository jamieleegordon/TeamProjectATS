package LoginPages;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Jamie-lee
 *
 * This class is used to encrypt all passwords when authorizing a login,
 * and to encrypt passwords when new accounts are created and new passwords are set.
 * This class uses the md5 hashing algorithm.
 */
public class Encryptor {

    public Encryptor() {}

    /**
     * method used to encrypt all passwords being entered using md5 encryption.
     */
    public String encryptPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] messageDigest = m.digest(input.getBytes());
        BigInteger big = new BigInteger(1,messageDigest);
        return big.toString(16);
    }
}
