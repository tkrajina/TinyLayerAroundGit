package tinylayeraroundgit.wizard;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;

import temp.TempDebug;
import tinylayeraroundgit.git.GitCommand;
import tinylayeraroundgit.wizard.page.SelectBranchPage;


public class MergeBranchWizard extends AppWizard {
	
	private SelectBranchPage selectBranchPage;

	public MergeBranchWizard( List<IResource> selectedResources ) {
		super( selectedResources );
	}
	
	public void addPages() {
		this.selectBranchPage = new SelectBranchPage( 
				"Select branch", 
				"Select branch", 
				"Please select (or enter) to merge with the current one", 
				this );
		addPage( selectBranchPage );
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
		
		GitCommand gitCommand = new GitCommand( "merge " + branchName, true );
		
		try {
			gitCommand.executeOn( getSelectedResources(), true );
			
			return true;
		} catch ( InterruptedException e ) {
			MessageDialog.openInformation( getShell(), "Merge error", "Error merging with:" + branchName );
			
			return false;
		}
	}

}