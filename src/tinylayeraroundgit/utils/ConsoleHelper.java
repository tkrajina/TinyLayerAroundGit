package tinylayeraroundgit.utils;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;


public class ConsoleHelper {

	private static ConsoleHelper instance;
	private MessageConsole gitConsole;

	private ConsoleHelper() {
		init();
	}

	private void init() {
		gitConsole = new MessageConsole( "git console", null );
		
		// myConsole.activate();
		
		ConsolePlugin.getDefault().getConsoleManager().addConsoles( new IConsole[] { gitConsole } );
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView( gitConsole );
	}

	public static ConsoleHelper getInstance() {
		if( instance == null ) {
			instance = new ConsoleHelper();
		}

		return instance;
	}
	
	public MessageConsole getGitConsole() {
		return gitConsole;
	}
	
	public void writeToConsole( String message ) {
		writeToConsole( message, null );
	}
	
	/**
	 * 
	 * @param message
	 * @param color See for example {@link SWT#COLOR_BLACK} and other similar constants
	 */
	public void writeToConsole( String message, Integer color ) {
		final MessageConsoleStream stream = gitConsole.newMessageStream();
		stream.setActivateOnWrite( true );
		
		if( color != null ) {
			Color swtColor = Display.getCurrent().getSystemColor( color );
			stream.setColor( swtColor );
		}
		
		stream.print( message );
		
		if( ! message.endsWith( "\n" ) ) {
			stream.println();
		}
		
		try {
			stream.close();
		} catch ( IOException e1 ) {
			e1.printStackTrace();
		}
	}

}
