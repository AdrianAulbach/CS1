package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.security.SecureRandom;

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
    private final SecureRandom random;

    public User(String username, String password, String eMail, boolean aktive) {
        //ToDo implement hash
        String passwordHash = password;

        //ToDo implement salt // https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java#2861125
        salt = new byte[16];
        this.random = new SecureRandom();
        random.nextBytes(salt);

        //ToDo implement reset email token
        String resetEmailToken = "abc";

        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
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
