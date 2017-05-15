package ch.bfh.bti7081.s2017.red.mhc_pms.domain;


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


    public User() {
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

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getEmail() {
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

    public void setActive(boolean active) {
        this.aktive = active;
    }
}
