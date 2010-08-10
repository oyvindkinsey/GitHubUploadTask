package no.kinsey.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.lang.StringBuilder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import java.io.File;

public class GitHubUploadTask extends Task {
    private String user;
	private String repo;
	private String username;
	private String token;
	private String path;

    Document PostToGitHub() {	
		String postUrl = String.format("http://github.com/%1$s/%2$s/downloads", user, repo);
		System.out.println("Posting to url " + postUrl);
		
		StringBuilder sb = new StringBuilder();

		try {
			// Construct data
			int fileSize = 1024;
			String description = "";
			String filename ="myfile1.zip";
			
			String data = "file_size=" + Integer.toString(fileSize) + "&" + 
				"file_name=" + filename + "&" + 
				"description" + URLEncoder.encode(description, "UTF-8") + "&" +
				"login=" + username + "&" +
				"token=" + token;

			// Send data
			URL url = new URL(postUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(conn.getInputStream());
			doc.getDocumentElement ().normalize ();
			return doc;
		} catch (Exception e) {
				System.out.println(e);
		}
		return null;
	}
	
    public void execute() throws BuildException {
       Document xml = PostToGitHub();
		if (xml != null) {
			NodeList list = xml.getElementsByTagName("*");
			String prefix, accesskeyid, bucket, https, acl, policy, mimeType, signature, redirect, expirationdate;
			for (int i=0; i<list.getLength(); i++) {
				// Get element
				Element element = (Element)list.item(i);
				String nodeName = element.getNodeName();
				
				if (nodeName == "prefix") {
					prefix = element.getNodeValue();
				}else if (nodeName =="accesskeyid") {
					accesskeyid = element.getNodeValue();
				}else if (nodeName =="bucket") {
					bucket = element.getNodeValue();
				}else if (nodeName =="https") {
					https = element.getNodeValue();
				}else if (nodeName =="acl") {
					acl = element.getNodeValue();
				}else if (nodeName =="policy") {
					policy = element.getNodeValue();
				}else if (nodeName =="mime-type") {
					mimeType = element.getNodeValue();
				}else if (nodeName =="signature") {
					signature = element.getNodeValue();
				}else if (nodeName =="redirect") {
					redirect = element.getNodeValue();
				}else if (nodeName =="expirationdate") {
					expirationdate = element.getNodeValue();
				}
				System.out.println(element.getNodeName());
			}
		}

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
	public void setPath(String path) {
        this.path = path;
    }
}