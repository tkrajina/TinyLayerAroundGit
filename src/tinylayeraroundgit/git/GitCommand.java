package tinylayeraroundgit.git;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;

public class GitCommand {
	
	private String command;
	
	public GitCommand( String command, boolean refresh ) {
		super();
		
		this.setCommand( command );
	}
	
	public List<GitCommandResult> executeOn( IResource selectedResource, boolean refreshProject ) throws InterruptedException {
		List<IResource> selection = new ArrayList<IResource>();
		
		selection.add( selectedResource );
		
		return executeOn( selection, refreshProject );
	}
	
	public List<GitCommandResult> executeOn( List<IResource> selectedResources, boolean refreshProject ) throws InterruptedException {
		GitCommandExecutor executor = new GitCommandExecutor( this, selectedResources );
		
		return executor.execute( refreshProject );
	}

	public void setCommand( String command ) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

}