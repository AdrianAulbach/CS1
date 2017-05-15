package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * @author Rolf Zurbrügg
 */
public class User extends PersistentObject {

    private String username = null;
    private String passwordHash = null;
    private byte[] salt = null;
    private String eMail = null;
    private String resetEmailToken = null;
    private boolean aktive = false;

    // Needed for Salt generation.
    private final SecureRandom random;
    private static final int SALT_LENGTH = 32;
    private PasswordService passwordService;


    public User(String username, String password, String eMail, boolean aktive) throws NoSuchAlgorithmException, InvalidKeySpecException {


        // create hasch for user  https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java#2861125
        salt = new byte[SALT_LENGTH];
        this.random = new SecureRandom();
        random.nextBytes(salt);

        // crate password hash for user using password and hash.
        this.passwordHash = passwordService.returnPasswordHashSalted(password,salt).toString();



        //ToDo implement reset email token
        String resetEmailToken = "abc";

        this.username = username;
        this.eMail = eMail;
        this.resetEmailToken = resetEmailToken;
        this.aktive = aktive;
    }
    // creates empty user.
    public User(){

        random = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }



    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getResetEmailToken() {
        return resetEmailToken;
    }

    public void setResetEmailToken(String resetEmailToken) {
        this.resetEmailToken = resetEmailToken;
    }

    public boolean isAktive() {
        return aktive;
    }

    public void setAktive(boolean aktive) {
        this.aktive = aktive;
    }
}
