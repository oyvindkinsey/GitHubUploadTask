package no.kinsey.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class GitHubUploadTask extends Task {
    private String user;
	private String repo;
	private String username;
	private String token;
	private String filename;

    // The method executing the task
    public void execute() throws BuildException {
        System.out.println(user);
    }

    
    public void setUser(String user) {
        this.user = user;
    }
	public void setRepo(String repo) {
        this.repo = repo;
    }
	public void setUsername(String username) {
        this.username = username;
    }
	public void setToken(String token) {
        this.token = token;
    }
	public void setFilename(String filename) {
        this.filename = filename;
    }
}