package gitMail;

import java.io.IOException;

public class GitAutomation {
	   public static void commitAndPushExtentReport() throws IOException, InterruptedException {
	    	// Navigate to the root of your Git project directory
	    	String repoPath = "D:\\logicPrograms\\GithubTask";  // Replace with the path to your project
	    	String extentReportPath = repoPath + "/extent-report/extent-report.html";  // Path to the generated ExtentReport
	 
	 
	        // Git commit and push commands
	        String[] gitAddCommand = {"git", "add", extentReportPath};
	        String[] gitCommitCommand = {"git", "commit", "-m", "\"Update Extent Report\""};
	        String[] gitPushCommand = {"git", "push", "origin", "main"};  // Replace with your branch name if necessary
	 
	        // Execute the git commands
	        executeGitCommand(gitAddCommand, repoPath);
	        executeGitCommand(gitCommitCommand, repoPath);
	        executeGitCommand(gitPushCommand, repoPath);
	        
	        GitCommitEmail.gitCommit();
	    }
	 
	    private static void executeGitCommand(String[] command, String workingDir) throws IOException, InterruptedException {
	        ProcessBuilder processBuilder = new ProcessBuilder(command);
	        processBuilder.directory(new java.io.File(workingDir));
	        Process process = processBuilder.start();
	        process.waitFor(); // Wait for the command to finish
	    }
	 
}
