package gitMail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {
	public static void sendEmail(String commitUrl) throws MessagingException {
        // Set email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // Set your email and password 
        final String username = "ammu81748@gmail.com";  
        final String password = "zghx gbyt gyet wbay";   
 
        // Create session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
 
        // Compose the message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("adjayamk@gmail.com"));  
        message.setSubject("Extent Report Commit URL");
        message.setText("The Extent Report has been committed. You can view the commit here: " + commitUrl);
 
        // Send the email
        Transport.send(message);
    }
}
