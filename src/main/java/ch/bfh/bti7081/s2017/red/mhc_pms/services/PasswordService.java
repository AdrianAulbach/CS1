package ch.bfh.bti7081.s2017.red.mhc_pms.services;

/**
 * Define the secure password functionalities
 *
 * Created by Rolf on 15/05/17.
 */
public interface PasswordService {

    /**
     * generates the password hasch given the password and the salt
     * @param password String containing the password
     * @param salt byte array containing the salt
     * @return returns the (password+slat) hash.
     */
    byte[] returnPasswordHashSalted(String password, byte[] salt);

    byte[] createSalt();
}
