package tinylayeraroundgit.wizard;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.Wizard;

import tinylayeraroundgit.wizard.page.BranchOptionsPage;
import tinylayeraroundgit.wizard.page.SelectBranchPage;


public class SwitchBranchWizard extends AppWizard {
	
	public SwitchBranchWizard( List<IResource> selectedResources ) {
		super( selectedResources );
	}
	
	public void addPages() {
		SelectBranchPage addressInfoPage = new SelectBranchPage( 
				"Select branch", 
				"Select branch", 
				"Please select (or enter) the branch you want to switch on", 
				this );
		addPage( addressInfoPage );
		
		BranchOptionsPage branchOptionsPage = new BranchOptionsPage(
				"Branch options", 
				"Branch options", 
				"", 
				this );
		addPage( branchOptionsPage );
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}