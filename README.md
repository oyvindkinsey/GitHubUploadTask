GitHubUploadTask
================
This is a task for Ant that allows you to automatically upload files to the GitHub download section. This is therefor suited for automatic dist targets.

How to use it
--
Add the following to your <code>build.xml</code> file

	<path id="githubhuploadtask.classpath">
		<pathelement location="."/>
		<fileset dir="location/ofjar">
			<include name="**/*.jar"/>
		</fileset>
	</path>
	<taskdef name="upload" classname="no.kinsey.ant.GitHubUploadTask" classpathref="githubhuploadtask.classpath" loaderref="githubhuploadtask.classpath.loader" />
		
Now you can use the upload task

    <upload user="${github.user}" repo="${github.repo}" username="${github.username}" token="${github.token}" description="just a test" path="${basedir}/file.zip" />

Example
-------
The GitHubUploadTask uses the task itself to upload the finished build to GitHub, so check out <code>build.xml</code>

In this project the user, repo and username needed for upload is stored in build.properties.

    github.user = oyvindkinsey
    github.repo = GitHubUploadTask
    github.username = oyvindkinsey
	
Since this is a public repository the token is kept secret and is supplied using a private <code>build.private.properties</code> file, but it could also be supplied using a build system or on the command line like this

    ant upload -Dgithub.token=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

License
====
This is licensed under the MIT license

Author
=====
The author is Øyvind Sean Kinsey (oyvind@kinsey.no / http://kinsey.no)
 