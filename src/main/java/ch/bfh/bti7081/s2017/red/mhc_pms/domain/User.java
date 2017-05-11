package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

/**
 * @author Rolf Zurbrügg
 */
public class User extends PersistentObject {

    private String username = null;
    private String passwordHash = null;
    private String salt = null;
    private String eMail = null;
    private String resetEmailToken = null;
    private boolean aktive = false;

    public User(String username, String password, String eMail, boolean aktive) {
        //ToDo implement hash
        String passwordHash = password;

        //ToDo implement salt
        String salt ="1234";

        //ToDo implement reset email token
        String resetEmailToken = "abc";

        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.eMail = eMail;
        this.resetEmailToken = resetEmailToken;
        this.aktive = aktive;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
