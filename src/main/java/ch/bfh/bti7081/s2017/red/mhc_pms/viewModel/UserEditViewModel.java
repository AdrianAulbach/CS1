package ch.bfh.bti7081.s2017.red.mhc_pms.viewModel;

/**
 * Created by Rolf on 30/05/17.
 */
public class UserEditViewModel {
    private String userName;
    private String password;
    private String email;
    private boolean active;
    private byte[] salt;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}

