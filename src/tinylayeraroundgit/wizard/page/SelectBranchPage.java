package tinylayeraroundgit.wizard.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import tinylayeraroundgit.utils.EclipseResourcesUtils;
import tinylayeraroundgit.utils.GitProjectUtils;
import tinylayeraroundgit.wizard.AppWizard;
import tinylayeraroundgit.wizard.AppWizardPage;

public class SelectBranchPage extends AppWizardPage {

	Text state;
	
	private List branchList;
	
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
		branchList = new List( composite, SWT.SINGLE );
		
		java.util.List<String> branches = GitProjectUtils.getBranches( EclipseResourcesUtils.getProjects( getAppWizard().getSelectedResources() ) );
		
		for( String branch : branches ) {
			branchList.add( branch );
		}
	}
	
	public String getSelectedBranch() {
		String[] selection = branchList.getSelection();
		
		if( selection == null || selection.length == 0 ) {
			return null;
		}
		
		return selection[ 0 ];
	}

}