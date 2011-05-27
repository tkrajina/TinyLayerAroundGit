package temp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;

import javax.swing.JOptionPane;

/** Temporary, do not commit! */
public class TempDebug {
	
	public static final boolean TRUE = true;
	
	public static final void alert( Object... objects ) {
		JOptionPane.showMessageDialog( null, joinObjects( "", objects ) );
	}
	
	public static final boolean ask( Object... objects ) {
		return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog( null, joinObjects( "", objects ), "?", JOptionPane.YES_NO_OPTION );
	}
	
	public static final void print( Object... objects ) {
		printLine( "TEMPDEBUG>", objects );
	}
	
	public static final void todo( Object... objects ) {}
	
	public static final void comment( Object... objects ) {}
	
	private static final void printLine( String prefix, Object[] objects ) {
		String result = joinObjects( prefix, objects );
		System.out.println( result );
	}

	private static String joinObjects( String prefix, Object[] objects ) {
		StringBuilder result = new StringBuilder( prefix );
		for( Object object : objects ) {
			result.append( objectToString( object ) );
		}
		return result.toString();
	}
	
	public static StackTraceElement getCurrentStackTraceElement() {
		return Thread.currentThread().getStackTrace()[ 2 ];
	}
	
	public static void printStackTrace() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for( int i = 2; i < stackTrace.length; i++ ) {
			StackTraceElement stackTraceElement = stackTrace[ i ];
			print( " at " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")" );
		}
	}
	
	private static String objectToString( Object object ) {
		if( object == null ) {
			return "null";
		}
		else if( object.getClass().isArray() ) {
			StringBuilder result = new StringBuilder();
			result.append( "[" );
			for( int i = 0 ; i < Array.getLength( object ); i++ ) {
				result.append( objectToString( Array.get( object, i ) ) );
				result.append( ", " );
			}
			result.append( "]" );
			
			return result.toString();
		}
		else if( object instanceof Throwable ) {
			Throwable throwable = (Throwable) object;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			throwable.printStackTrace( new PrintStream( out ) );
			
			try {
				out.close();
			} catch( Exception ignore ) {}
			
			return new String( out.toByteArray() );
		}
		return String.valueOf( object );
	}
	
}