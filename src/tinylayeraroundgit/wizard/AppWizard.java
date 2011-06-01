package tinylayeraroundgit.wizard;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.Wizard;

public abstract class AppWizard extends Wizard {

	private List<IResource> selectedResources;

	public AppWizard( List<IResource> selectedResources ) {
		this.setSelectedResources( selectedResources );
	}
	
	@Override
	public abstract boolean performFinish();

	private void setSelectedResources( List<IResource> selectedResources ) {
		this.selectedResources = selectedResources;
	}

	public List<IResource> getSelectedResources() {
		return selectedResources;
	}

}
