package tinylayeraroundgit.git;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

import tinylayeraroundgit.utils.ProcessThread;


public class GitCommandExecutor {
	
	private List<IResource> selectedResources;
	
	private GitCommand gitCommand;
	
	public static void main( String[] args ) throws Exception {
//		GitCommandExecutor gitCommandExecutor = new GitCommandExecutor( new GitCommand( "gui" ) );
//		
//		gitCommandExecutor.execute();
	}

	public GitCommandExecutor( GitCommand gitCommand, List<IResource> selectedResources ) {
		super();
		
		this.setGitCommand( gitCommand );
		this.setSelectedResources( selectedResources );
	}
	
	public void execute() throws InterruptedException {
		for( IResource file : getSelectedResources() ) { 
			executeOn( file );
		}
	}

	private int executeOn( IResource file ) throws InterruptedException {
		String command = getGitCommand().getCommand();
		
		if( ! command.toLowerCase().trim().startsWith( "git" ) ) {
			command = "git " + command;
		}
		
		ProcessThread processThread = new ProcessThread( command, file.getLocation().toFile() );
		
		processThread.start();
		
		processThread.waitToEnd();
		
		return processThread.getExitValue();
	}

	private void setGitCommand( GitCommand gitCommand ) {
		assert gitCommand != null;
		
		this.gitCommand = gitCommand;
	}

	public GitCommand getGitCommand() {
		return gitCommand;
	}

	public void setSelectedResources( List<IResource> selectedResources ) {
		this.selectedResources = selectedResources;
	}

	public List<IResource> getSelectedResources() {
		return selectedResources;
	}
			
}
