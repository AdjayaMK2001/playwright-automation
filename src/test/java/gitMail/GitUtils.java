package gitMail;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitUtils {
	// Method to get the latest commit hash from the repository
    public static String getLatestCommitHash(String repoPath) throws Exception {
        // Define the Git command to get the latest commit hash
        String[] command = {"git", "log", "-1", "--pretty=format:%H"};
        // Set up the process to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new java.io.File(repoPath));  // Set the working directory to the Git repo path
 
        // Start the process and capture the output
        Process process = processBuilder.start();
        // Read the output of the command (commit hash)
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String commitHash = reader.readLine();  // Read the first line which is the commit hash
        // Wait for the process to complete
        process.waitFor();
 
        return commitHash;  
    }
 
}
