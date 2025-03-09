package gitMail;

public class GitCommitEmail {
	  public static void gitCommit() {
	        try {
	            // Get the commit hash dynamically
	            String repoPath = "C:/Users/SSYS FAMILY/Downloads/testng/testng";  // Replace with your Git repository path
	            String commitHash = GitUtils.getLatestCommitHash(repoPath);
	 
	            // Construct the commit URL (GitHub example)
	            String commitUrl = "https://gitlab.com/yogezz/amltest/commit/" + commitHash;
	 
	            // Send email with the commit URL
	            EmailUtility.sendEmail(commitUrl);
	 
	            System.out.println("Email sent successfully with commit URL!");
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
