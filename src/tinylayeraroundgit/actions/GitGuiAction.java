package tinylayeraroundgit.actions;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import temp.TempDebug;
import tinylayeraroundgit.git.GitCommand;
import tinylayeraroundgit.git.GitCommandExecutor;

public class GitGuiAction extends ObjectActionDelegate {
	
	/**
	 * Constructor for Action1.
	 */
	public GitGuiAction() {
		super();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		List<IProject> projects = getSelectedProjects();
		
		if( projects == null || projects.size() == 0 ) {
			openInformationDialog( "Problem", "No selected project found" );
			return;
		}
		
		IProject project = projects.get( 0 );
		
		GitCommand gitCommand = new GitCommand( "gui", project, true );
		
		GitCommandExecutor gitCommandExecutor = new GitCommandExecutor( gitCommand );
		
		try {
			gitCommandExecutor.execute();
		} catch ( InterruptedException e1 ) {
			e1.printStackTrace();
		}
		
		refreshProject( project );
		
//		TestWizard wizard = new TestWizard();
//		WizardDialog dialog = new WizardDialog( shell, wizard );
//		dialog.create();
//		dialog.open();
		
//		File workspaceLocation = project.getWorkspace().getRoot().getLocation().toFile();
	}

}
