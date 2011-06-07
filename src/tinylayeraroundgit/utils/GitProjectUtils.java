package tinylayeraroundgit.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import tinylayeraroundgit.git.GitCommand;
import tinylayeraroundgit.git.GitCommandResult;


public class GitProjectUtils {
	
	private GitProjectUtils() {}
	
	public static void refresh( IProject project ) {
		try {
			project.refreshLocal( IProject.DEPTH_INFINITE, null );
		} catch ( CoreException e ) {
			e.printStackTrace();
		}

	}
	
	public static List<String> getBranches( IProject project ) {
		
		assert project != null;
		
		ArrayList<String> result = new ArrayList<String>();
		
		GitCommand gitCommand = new GitCommand( "branch", false );
		
		try {
			List<GitCommandResult> gitCommandResults = gitCommand.executeOn( project, true );
			
			for( GitCommandResult gitCommandResult : gitCommandResults ) {
				
				// TODO Extract this to method:
				if( gitCommandResult.getExitCode() == 0 ) {
					String[] lines = gitCommandResult.getStdout().split( "\n" );
					for( String line : lines ) {
						line = line.replaceAll( "^\\*\\s*", "" );
						line = line.trim();
						result.add( line );
					}
				}
			}
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<String> getBranches( List<IProject> projects ) {
		
		assert projects != null;
		
		ArrayList<String> result = new ArrayList<String>();
		
		for( IProject project : projects ) {
			List<String> branches = getBranches( project );
			
			for( String branch : branches ) {
				if( ! result.contains( branch ) ) {
					result.add( branch );
				}
			}
		}
		
		return result;
	}

}
