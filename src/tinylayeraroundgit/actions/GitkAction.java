package tinylayeraroundgit.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;

import tinylayeraroundgit.git.Command;


public class GitkAction extends AbstractActionDelegate {

	public GitkAction() {
		super();
	}

	@Override
	public void run( IAction action ) {
		Command gitCommand = new Command( "gitk" );
		
		try {
			gitCommand.executeOn( getSelectedResources(), true );
		} catch ( InterruptedException e ) {
			MessageDialog.openError( getShell(), "Error", "Error executing gitk" );
		}
	}

}
