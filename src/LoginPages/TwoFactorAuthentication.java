package LoginPages;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

/**
 *
 * @author Abdul
 *
 * This class is used to email the user a code which needs to be used to log in to the system via two-factor authentication for an extra level of security.
 */
public class TwoFactorAuthentication {

    public static void Send2FAEmail(String email, String code) throws IOException, MessagingException {
        // Load mail properties from config file
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        props.load(fis);

        // Get mail credentials from config file
        final String username = props.getProperty("mail.username");
        final String password = props.getProperty("mail.password");
        final String fromAddress = props.getProperty("mail.fromAddress");

        // create the SMTP client
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(p, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // create the message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromAddress));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email)
        );
        message.setSubject("2 Factor Authentication");
        message.setText("""
            Hi,
            Your 2 Factor Authentication Code is: 
            """ + code);

        // send the message
        Transport.send(message);

        JOptionPane.showMessageDialog(null, "Code Sent!");
    }
}
