package gitMail;

public class GitCommitEmail {
	  public static void gitCommit() {
	        try {
	            // Get the commit hash dynamically
	            String repoPath = "D:/logicPrograms/GithubTask";  // Replace with your Git repository path
	            String commitHash = GitUtils.getLatestCommitHash(repoPath);
	 
	            // Construct the commit URL (GitHub example)
	            String commitUrl = "https://github.com/AdjayaMK2001/playwright-automation/commit/" + commitHash;
	 
	            // Send email with the commit URL
	            EmailUtility.sendEmail(commitUrl);
	 
	            System.out.println("Email sent successfully with commit URL!");
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
