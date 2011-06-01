package tinylayeraroundgit.git;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

public class GitCommand {
	
	private String command;
	
	private boolean refreshProjest = false;
	
	public GitCommand( String command, boolean refresh ) {
		super();
		
		this.setCommand( command );
		this.setRefreshProjest( refresh );
	}
	
	public void executeOn( IResource selectedResource ) throws InterruptedException {
		List<IResource> selection = new ArrayList<IResource>();
		
		selection.add( selectedResource );
		
		executeOn( selection );
	}
	
	public void executeOn( List<IResource> selectedResources ) throws InterruptedException {
		GitCommandExecutor executor = new GitCommandExecutor( this, selectedResources );
		
		executor.execute();
	}

	public void setCommand( String command ) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setRefreshProjest( boolean refreshProjest ) {
		this.refreshProjest = refreshProjest;
	}

	public boolean isRefreshProjest() {
		return refreshProjest;
	}

}