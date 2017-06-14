package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author Rolf Zurbrügg
 */
@Entity
@Table(name = "users")
public class User extends PersistentObject {

    private String username = null;
    private String passwordHash = null;
    private String salt = null;
    private String eMail = null;
    private String resetEmailToken = null;
    private boolean active = false;


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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
