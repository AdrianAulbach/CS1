package ch.bfh.bti7081.s2017.red.mhc_pms.services;

/**
 * The notification service allows to notify people with a message using
 * different ways.
 *
 * @author Samuel Egger
 */
public interface NotificationService {

    /**
     * Send an email with the given message.
     *
     * @param email the email address
     * @param message the message body
     */
    void sendEmailNotification(String email, String message);

    /**
     * Send a SMS with the given message.
     *
     * @param phoneNumber the phone number
     * @param message the message body
     */
    void sendSmsNotification(String phoneNumber, String message);

    /**
     * Send a push notification with the given message if push notifications are
     * supported by the end user phone.
     *
     * @param phoneNumber the phone number
     * @param message the message body
     */
    void sendPushNotification(String phoneNumber, String message);
}
