package tinylayeraroundgit.wizard.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import tinylayeraroundgit.wizard.AppWizard;
import tinylayeraroundgit.wizard.AppWizardPage;

public class BranchOptionsPage extends AppWizardPage {

	public BranchOptionsPage( String pageName, String title, String description, AppWizard appWizard ) {
		super( pageName, title, description, appWizard );
	}

	public void createControl( Composite parent ) {
		
		Composite composite = new Composite( parent, SWT.NONE );
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		
		composite.setLayout( layout );
		setControl( composite );
		
		// TODO
	}

}
