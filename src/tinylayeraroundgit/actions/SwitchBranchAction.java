package tinylayeraroundgit.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.wizard.WizardDialog;

import tinylayeraroundgit.wizard.SwitchBranchWizard;


public class SwitchBranchAction extends AbstractActionDelegate {

	public SwitchBranchAction() {
		super();
	}

	@Override
	public void run( IAction action ) {
		SwitchBranchWizard wizard = new SwitchBranchWizard( getSelectedResources() );
		WizardDialog dialog = new WizardDialog( getShell(), wizard );
		dialog.create();
		dialog.open();
	}

}
