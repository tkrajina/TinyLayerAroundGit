package tinylayeraroundgit.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;

import tinylayeraroundgit.git.GitCommand;
import tinylayeraroundgit.git.GitCommandResult;


public class RunGitCommandAction extends AbstractActionDelegate {
	
	private boolean runOnlyIfChanged = false;
	
	class GitCommandInputValidator implements IInputValidator {

		@Override
		public String isValid( String newText ) {
			if( newText == null || newText.trim().length() == 0 ) {
				return "Invalid git command";
			}
			
			return null;
		}
		
	}
	
	public RunGitCommandAction() {
		this.runOnlyIfChanged = false;
	}
	
	public RunGitCommandAction( boolean runOnlyIfChanged ) {
		this.runOnlyIfChanged = runOnlyIfChanged;
	}

	@Override
	public void run( IAction action ) {
		InputDialog dialog = new InputDialog( getShell(), "Git command", "Git command", "status", new GitCommandInputValidator() );
		
		dialog.create();
		dialog.open();
		
		String command = dialog.getValue();
		
		if( command != null ) {
			GitCommand gitCommand = new GitCommand( command );
			
			try {
				// TODO: Change this, add asbtract method!
				for( IProject project : getSelectedProjects() ) {
					if( runOnlyIfChanged ) {
						if( isChanged( project ) ) {
							gitCommand.executeOn( project, true );
						}
					}
					else {
						gitCommand.executeOn( project, true );
					}
				}
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}

	private boolean isChanged( IProject project ) {
		GitCommand gitCommand = new GitCommand( "status --porcelain" );
		
		try {
			List<GitCommandResult> results = gitCommand.executeOn( project, false );
			
			GitCommandResult result = results.get( 0 );
			
			return result.getExitCode() == 0 && result.getStdout().trim().length() > 0;
		} catch ( InterruptedException e ) {
			MessageDialog.openError( getShell(), "Error", "Error checking status of " + project.getName() );
			return true;
		}
	}

}
