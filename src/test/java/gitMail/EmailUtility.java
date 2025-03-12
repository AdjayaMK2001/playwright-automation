package gitMail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import test.FilePaths;

public class EmailUtility {
	
	    public static void sendEmailWithReportLink(String toEmail, String subject, String reportUrl) {
	        String fromEmail = "ammu81748@gmail.com";  // Sender's email
	        String password = "zghx gbyt gyet wbay";    // Use app-specific password if 2FA is enabled

	        // Mail server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");

	        // Create a session
	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(fromEmail, password);
	            }
	        });

	        try {
	            // Create email message
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(fromEmail));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	            message.setSubject(subject);

	            // **Email Body with Report URL**
	            String htmlBody = "<html><body>"
	                    + "<p>Hi,</p>"
	                    + "<p>The test execution report is available. Click the link below to view it:</p>"
	                    + "<p><a href='" + reportUrl + "' target='_blank'>" + reportUrl + "</a></p>"
	                    + "<p>Best Regards,</p>"
	                    + "<p>Your Automation Team</p>"
	                    + "</body></html>";

	            // Set the email content
	            message.setContent(htmlBody, "text/html; charset=UTF-8");

	            // Send the email
	            Transport.send(message);
	            System.out.println("Email sent successfully with report link!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	   
	}


