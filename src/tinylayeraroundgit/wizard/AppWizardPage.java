package tinylayeraroundgit.wizard;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;


public class AppWizardPage extends WizardPage {

	private AppWizard appWizard;

	public AppWizardPage( String pageName, String title, String description, AppWizard appWizard ) {
		super( pageName );
		
		setTitle( title );
		setDescription( description );
		
		setAppWizard( appWizard );
	}

	@Override
	public void createControl( Composite parent ) {
	}

	private void setAppWizard( AppWizard appWizard ) {
		this.appWizard = appWizard;
	}

	public AppWizard getAppWizard() {
		return appWizard;
	}
	
}