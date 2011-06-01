package tinylayeraroundgit.wizard.page;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tinylayeraroundgit.utils.EclipseResourcesUtils;
import tinylayeraroundgit.utils.GitProjectUtils;
import tinylayeraroundgit.wizard.AppWizard;
import tinylayeraroundgit.wizard.AppWizardPage;

public class SelectBranchPage extends AppWizardPage {

	Text state;
	
	private Combo branchCombo;
	
	public SelectBranchPage( String pageName, String title, String description, AppWizard appWizard ) {
		super( pageName, title, description, appWizard );
	}

	public void createControl( Composite parent ) {
		
		Composite composite = new Composite( parent, SWT.NONE );
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		
		composite.setLayout( layout );
		setControl( composite );
		
		new Label( composite, SWT.NONE ).setText( "Select branch:" );
		branchCombo = new Combo( composite, SWT.NONE );
		
		List<String> branches = GitProjectUtils.getBranches( EclipseResourcesUtils.getProjects( getAppWizard().getSelectedResources() ) );
		
		for( String branch : branches ) {
			branchCombo.add( branch );
		}
	}

}