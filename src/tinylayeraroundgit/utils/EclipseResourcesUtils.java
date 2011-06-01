package tinylayeraroundgit.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

public class EclipseResourcesUtils {
	
	private EclipseResourcesUtils() {}
	
	public static List<IProject> getProjects( List<IResource> resources ) {
		
		assert resources != null;
		
		ArrayList<IProject> result = new ArrayList<IProject>();
		
		for( IResource resource : resources ) {
			result.add( resource.getProject() );
		}
		
		return result;
	}

}
