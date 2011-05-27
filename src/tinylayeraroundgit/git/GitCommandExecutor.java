package tinylayeraroundgit.git;

import org.eclipse.core.resources.IProject;

import tinylayeraroundgit.utils.ProcessThread;


public class GitCommandExecutor {
	
	private GitCommand gitCommand;
	
	public static void main( String[] args ) throws Exception {
//		GitCommandExecutor gitCommandExecutor = new GitCommandExecutor( new GitCommand( "gui" ) );
//		
//		gitCommandExecutor.execute();
	}

	public GitCommandExecutor( GitCommand gitCommand ) {
		this.setGitCommand( gitCommand );
	}
	
	public void execute() throws InterruptedException {
		for( IProject project : gitCommand.getTargetProjects() ) {
			executeOnProject( project );
		}
	}

	private int executeOnProject( IProject project ) throws InterruptedException {
		String command = getGitCommand().getCommand();
		
		if( ! command.toLowerCase().trim().startsWith( "git" ) ) {
			command = "git " + command;
		}
		
		ProcessThread processThread = new ProcessThread( command, project.getLocation().toFile() );
		
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
			
}
