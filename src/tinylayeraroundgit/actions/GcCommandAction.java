package tinylayeraroundgit.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;

import tinylayeraroundgit.git.Command;


public class GcCommandAction extends AbstractActionDelegate {

	public GcCommandAction() {
		super();
	}

	@Override
	public void run( IAction action ) {
		Command gitCommand = new Command( "git gc" );
		
		try {
			gitCommand.executeOn( getSelectedResources(), false );
		} catch ( InterruptedException e ) {
			MessageDialog.openError( getShell(), "Error", "Error executing gc" );
			
			e.printStackTrace();
		}
	}

}
