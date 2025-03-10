package gitMail;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitUtils {
	// Method to get the latest commit hash from the repository
    public static String getLatestCommitHash(String repoPath) throws Exception {
        String[] command = {"git", "log", "-1", "--pretty=format:%H"};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new java.io.File(repoPath));  
 
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String commitHash = reader.readLine();  
        process.waitFor();
        return commitHash;  
    }
 
}
