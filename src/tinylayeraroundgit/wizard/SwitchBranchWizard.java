package tinylayeraroundgit.wizard;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;

import temp.TempDebug;
import tinylayeraroundgit.git.GitCommand;
import tinylayeraroundgit.wizard.page.SelectBranchPage;


public class SwitchBranchWizard extends AppWizard {
	
	private SelectBranchPage selectBranchPage;

	public SwitchBranchWizard( List<IResource> selectedResources ) {
		super( selectedResources );
	}
	
	public void addPages() {
		this.selectBranchPage = new SelectBranchPage( 
				"Select branch", 
				"Select branch", 
				"Please select (or enter) the branch you want to switch on", 
				this );
		addPage( selectBranchPage );
		
		/* TODO, later:
		BranchOptionsPage branchOptionsPage = new BranchOptionsPage(
				"Branch options", 
				"Branch options", 
				"", 
				this );
		addPage( branchOptionsPage );
		*/
	}

	@Override
	public boolean performFinish() {
		String branchName = selectBranchPage.getSelectedBranch();
		
		if( branchName != null ) {
			branchName = branchName.replaceAll( "\\s", "" );
		}
		
		if( branchName == null || branchName.trim().length() == 0 ) {
			MessageDialog.openInformation( getShell(), "Invalid branch", "Invalid branch:" + branchName );
		}
		
		GitCommand gitCommand = new GitCommand( "checkout " + branchName );
		
		try {
			gitCommand.executeOn( getSelectedResources(), true );
			
			return true;
		} catch ( InterruptedException e ) {
			MessageDialog.openInformation( getShell(), "Checkout error", "Error checkouting:" + branchName );
			
			return false;
		}
	}

}