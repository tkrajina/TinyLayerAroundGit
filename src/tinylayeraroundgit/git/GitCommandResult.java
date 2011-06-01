package tinylayeraroundgit.git;

import org.eclipse.core.resources.IFile;

/**
 * TODO Javadoc
 * TODO References to {@link IFile} and {@link GitCommand}
 * 
 * @author Tomo Krajina
 */
public class GitCommandResult {
	
	private String stdout;
	private String stderr;
	
	private int exitCode;
	
	public GitCommandResult( String stdout, String stderr, int exitCode ) {
		super();
		
		setStdout( stdout );
		setStderr( stderr );
		setExitCode( exitCode );
	}

	public void setStdout( String stdout ) {
		this.stdout = stdout;
	}

	public String getStdout() {
		return stdout;
	}

	public void setStderr( String stderr ) {
		this.stderr = stderr;
	}

	public String getStderr() {
		return stderr;
	}

	public void setExitCode( int exitCode ) {
		this.exitCode = exitCode;
	}

	public int getExitCode() {
		return exitCode;
	}

}
