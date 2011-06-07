package tinylayeraroundgit.actions;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import tinylayeraroundgit.utils.GitProjectUtils;


public class EditGitignoreCommandAction extends AbstractActionDelegate {

	public EditGitignoreCommandAction() {
		super();
	}

	@Override
	public void run( IAction action ) {
		
		for( IProject project : getSelectedProjects() ) {
			openEditor( project );
		}

	}

	private void openEditor( IProject project ) {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		
		IFile file = project.getFile( "/.gitignore" );
		if( ! file.exists() ) {
			try {
				file.create( new ByteArrayInputStream( new byte[ 0 ] ), false, null );
				GitProjectUtils.refresh( project );
			} catch ( CoreException e ) {
				MessageDialog.openError( getShell(), "Error", "Error creating .gitignore" );

				e.printStackTrace();
			}
		}

		// SIne we want a text editor:
		String dummyTextFileName = "sample.txt";
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor( dummyTextFileName );
		
		try {
			page.openEditor( new FileEditorInput( file ), desc == null ? null : desc.getId() );
		} catch ( PartInitException e ) {
			e.printStackTrace();
		}
	}

}
