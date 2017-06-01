package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Created by Rolf on 15/05/17.
 */
public class Sha1PasswordService implements PasswordService {

    private SecureRandom random;
    private static final int DESIRED_KEY_LENGTH = 256;
    private static final int ITERATIONS = 20*1000;
    private static final int SALT_LENGTH = 32;

    @Override
    public String returnPasswordHashSalted(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LENGTH);
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = new byte[0];
        try {
            hash = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
       String base64hash = java.util.Base64.getEncoder().encodeToString(hash);
        return base64hash;
    }

    @Override
    public byte[] createSalt() {
        // create hasch for user  https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java#2861125
        byte[] salt = new byte[SALT_LENGTH];
        random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
}
