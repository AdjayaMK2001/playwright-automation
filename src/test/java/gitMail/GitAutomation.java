package gitMail;

import java.io.IOException;
import java.nio.file.Paths;

public class GitAutomation {
	   public static void commitAndPushExtentReport() throws Exception {
	    	// Navigate to the root of your Git project directory
	    	String repoPath = "D:/logicPrograms/GithubTask";  
	    	String extentReportPath = repoPath + "/extent-report/extent-report.html";  
	 
	 
	        // Git commit and push commands
	        String[] gitAddCommand = {"git", "add", extentReportPath};
	        String[] gitCommitCommand = {"git", "commit", "-m", "\"Update Extent Report\""};
	        String[] gitPushCommand = {"git", "push", "origin", "main"};  
	 
	        // Execute the git commands
	        executeGitCommand(gitAddCommand, repoPath);
	        executeGitCommand(gitCommitCommand, repoPath);
	        executeGitCommand(gitPushCommand, repoPath);
	        
	        EmailUtility.sendEmail("Report", Paths.get("extent-report/extent-report.html").toAbsolutePath().toString());
	    }
	 
	    private static void executeGitCommand(String[] command, String workingDir) throws IOException, InterruptedException {
	        ProcessBuilder processBuilder = new ProcessBuilder(command);
	        processBuilder.directory(new java.io.File(workingDir));
	        Process process = processBuilder.start();
	        process.waitFor();
	    }
	 
}
