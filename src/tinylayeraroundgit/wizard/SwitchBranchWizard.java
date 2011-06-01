package tinylayeraroundgit.wizard;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.Wizard;

import tinylayeraroundgit.wizard.page.SelectBranchPage;


public class SwitchBranchWizard extends Wizard {
	
	private List<IResource> selectedResources;

	public SwitchBranchWizard( List<IResource> selection ) {
		super();
		
		setSelectedResources( selection );
	}
	
	public void addPages() {
		SelectBranchPage addressInfoPage = new SelectBranchPage( "Address Information", selectedResources );
		addPage( addressInfoPage );
	}

	@Override
	public boolean performFinish() {
		return false;
	}

	public void setSelectedResources( List<IResource> selectedResources ) {
		this.selectedResources = selectedResources;
	}

	public List<IResource> getSelectedResources() {
		return selectedResources;
	}

}