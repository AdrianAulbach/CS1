package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Sha1PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserManagementPresenter {

    private PasswordService passwordService = new Sha1PasswordService();

    public User createNewUser(String username, String password, String eMail, boolean active) throws NoSuchAlgorithmException, InvalidKeySpecException {

        User newUser = new User();

        newUser.setUsername(username);
        newUser.seteMail(eMail);
        newUser.setActive(active);

        byte[] salt = passwordService.createSalt();
        newUser.setSalt(salt);

        // crate password hash for user using password and hash.
        byte[] passwordHash = passwordService.returnPasswordHashSalted(password,salt);
        String base64hash = java.util.Base64.getEncoder().encodeToString(passwordHash);
        newUser.setPasswordHash(base64hash);

        //ToDo implement reset email token
        String resetEmailToken = "abc";

        return newUser;
    }
}
