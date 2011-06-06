package tinylayeraroundgit.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.wizard.WizardDialog;

import tinylayeraroundgit.wizard.MergeBranchWizard;


public class MergeBranchAction extends AbstractActionDelegate {

	@Override
	public void run( IAction action ) {
		MergeBranchWizard wizard = new MergeBranchWizard( getSelectedResources() );
		WizardDialog dialog = new WizardDialog( getShell(), wizard );
		dialog.create();
		dialog.open();
	}

}
