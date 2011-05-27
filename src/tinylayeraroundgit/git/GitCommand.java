package tinylayeraroundgit.git;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;

public class GitCommand {
	
	private String command;
	
	private List<IProject> targetProjects = new ArrayList<IProject>();
	
	private boolean refreshProjest = false;
	
	public GitCommand( String command, List<IProject> targets, boolean refresh ) {
		super();
		
		if( targets != null ) {
			this.targetProjects.addAll( targets );
		}
		this.setCommand( command );
		this.setRefreshProjest( refresh );
	}
	
	public GitCommand( String command, IProject target, boolean refresh ) {
		this( command, (List<IProject>) null, refresh );
		
		this.addTarget( target );
	}
	
	public void execute() {
		
	}

	public void setCommand( String command ) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void addTarget( IProject project ) {
		this.targetProjects.add( project );
	}

	public List<IProject> getTargetProjects() {
		return targetProjects;
	}

	public void setRefreshProjest( boolean refreshProjest ) {
		this.refreshProjest = refreshProjest;
	}

	public boolean isRefreshProjest() {
		return refreshProjest;
	}

}