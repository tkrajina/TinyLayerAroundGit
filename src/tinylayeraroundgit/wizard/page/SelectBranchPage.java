package tinylayeraroundgit.wizard.page;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import temp.TempDebug;
import tinylayeraroundgit.utils.EclipseResourcesUtils;
import tinylayeraroundgit.utils.GitProjectUtils;

public class SelectBranchPage extends WizardPage {

	Text state;
	
	private Combo branch;
	
	private List<IResource> selectedResources;

	public SelectBranchPage( String pageName, List<IResource> selectedResources ) {
		super( pageName );
		
		setTitle( "Address Information" );
		setDescription( "Please enter your address information" );
		
		setSelectedResources( selectedResources );
	}

	public void createControl( Composite parent ) {
		Composite composite = new Composite( parent, SWT.NONE );
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		
		composite.setLayout( layout );
		setControl( composite );
		
		new Label( composite, SWT.NONE ).setText( "Select branch:" );
		branch = new Combo( composite, SWT.NONE );
		
		List<String> branches = GitProjectUtils.getBranches( EclipseResourcesUtils.getProjects( selectedResources ) );
		
		TempDebug.print( branches );
	}

	public void setSelectedResources( List<IResource> selectedResources ) {
		this.selectedResources = selectedResources;
	}

	public List<IResource> getSelectedResources() {
		return selectedResources;
	}
	
}