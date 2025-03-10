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
	public static void sendEmail(String reportName, String reportPath) throws Exception {
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
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("demo72986@gmail.com"));
        message.setSubject("Extent Report Commit URL");

        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart textPart = new MimeBodyPart();
        
        String repoPath = "D:/logicPrograms/GithubTask";  // Replace with your Git repository path
        String commitHash = GitUtils.getLatestCommitHash(repoPath);
        String commitUrl = "https://github.com/AdjayaMK2001/playwright-automation/commit/" + commitHash;

        textPart.setText("Hi,\n\nThe Extent Report has been committed. You can view the commit here: " + commitUrl);
        String reportUrl = "https://AdjayaMK2001/playwright-automation/extent-report/extent-report.html"; // Replace with actual hosted link

        textPart.setContent(
            "<h3>Hi,</h3>" +
            "<p>The Extent Report is ready. You can view it here:</p>" +
            "<p><a href='" + reportUrl + "' target='_blank'>Open Report</a></p>" +
            "<p>Best regards,<br>Earn Next</p>",
            "text/html"
        );

        
        multipart.addBodyPart(textPart);

        // Attach the HTML report file to the email
        File reportFile = new File(reportPath);
        if (reportFile.exists()) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(reportFile);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(reportFile.getName());
            multipart.addBodyPart(attachmentPart);
        } else {
            System.err.println("Report file not found: " + reportFile.getAbsolutePath());
        }

        message.setContent(multipart);

        // Send the email
        Transport.send(message);
        System.out.println("Email sent!");
    }
    
}
