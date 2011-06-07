package tinylayeraroundgit.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;

import tinylayeraroundgit.git.GitCommand;


public class GcCommandAction extends AbstractActionDelegate {

	public GcCommandAction() {
		super();
	}

	@Override
	public void run( IAction action ) {
		GitCommand gitCommand = new GitCommand( "gc" );
		
		try {
			gitCommand.executeOn( getSelectedResources(), false );
		} catch ( InterruptedException e ) {
			MessageDialog.openError( getShell(), "Error", "Error executing gc" );
			
			e.printStackTrace();
		}
	}

}
