package tinylayeraroundgit.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import tinylayeraroundgit.git.GitCommand;


public class RunGitCommandAction extends AbstractActionDelegate {
	
	class GitCommandInputValidator implements IInputValidator {

		@Override
		public String isValid( String newText ) {
			if( newText == null || newText.trim().length() == 0 ) {
				return "Invalid git command";
			}
			
			if( ! newText.startsWith( "git " ) ) {
				return "Your command must start with \"git \"";
			}
			
			return null;
		}
		
	}

	@Override
	public void run( IAction action ) {
		InputDialog dialog = new InputDialog( getShell(), "Git command", "Git command", "git ", new GitCommandInputValidator() );
		
		dialog.create();
		dialog.open();
		
		String command = dialog.getValue();
		
		if( command != null ) {
			GitCommand gitCommand = new GitCommand( command, true );
			
			try {
				gitCommand.executeOn( getSelectedResources() );
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}

}
