package tinylayeraroundgit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessThread extends Thread {
    
    private StringBuffer out = new StringBuffer();
    
    private boolean writeOutput = true;

    private String commandLine;
    private Process process;

    private int exitValue;

	private File path;

    public ProcessThread( String commandLine, File path ) {
        this.commandLine = commandLine;
        this.path = path;
    }
    
    /**
     * Without this class the process on Windows hangs...
     * 
     * @author Tomo Krajina
     */
    class StreamGobbler extends Thread {

        private InputStream is;

        private String type;
        
        StreamGobbler( InputStream is, String type ) {
            this.is = is;
            this.type = type;
        }

        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader( is );
                BufferedReader br = new BufferedReader( isr );
                String line = null;
                while ( ( line = br.readLine() ) != null ) {
                    System.out.println( type + ">" + line );
                    if( writeOutput ) {
                        out.append( line ).append( '\n' );
                    }
                }
            }
            catch ( IOException e ) {
            	// TODO
            	e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        try {
            this.process = Runtime.getRuntime().exec( commandLine, null, path );

            StreamGobbler errorGobbler = new StreamGobbler( this.process.getErrorStream(), "STDERR" );
            StreamGobbler outputGobbler = new StreamGobbler( this.process.getInputStream(), "STDOUT" );

            errorGobbler.start();
            outputGobbler.start();

            this.setExitValue( this.process.waitFor() );
        }
        catch ( Exception e ) {
            System.err.println( e.getMessage() );
        }
    }
    
    public Process getProcess() {
        return this.process;
    }

    public boolean started() {
        return this.process != null;
    }

    /** The current output. */
    public String getOutput() {
        return out.toString();
    }

    /** Kills the process. */
    public void kill() {
        if( this.process != null ) {
            this.process.destroy();
        }
    }
    
    public int waitToEnd() throws InterruptedException {
    	while( this.process == null ) {
    		Thread.sleep( 100 );
    	}
    	
        this.process.waitFor();
        
        return getExitValue();
    }
    
    public void setWriteOutput( boolean writeOutput ) {
        this.writeOutput = writeOutput;
    }

    public boolean isWriteOutput() {
        return writeOutput;
    }

    private void setExitValue( int exitValue ) {
        this.exitValue = exitValue;
    }

    public int getExitValue() {
        return exitValue;
    }
    
}