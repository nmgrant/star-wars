/**
 * Astromech is a representation of a Droid that hacks computers
 * 
 * @author Nicholas Grant
 */
public class Astromech extends Droid {
	
	/**
	 * Initializes the astromech using input parameters.
	 * @param n     Astromech's name
	 */
	public Astromech( String n ) {
		super( n, 20 );
	}
	/**
	 * Hacks a Computer instance.
	 * @param e		The target of the astromech's task.
	 * @param t		Not used for this implementation.
	 */
	@Override
	public void doTask( Entity e, int t ) {
		if ( e instanceof Computer ) {
			System.out.println( "The computer has been hacked!" );
			((Computer) e).isHacked = true;
		}
		else {
			System.out.println( "Invalid target." );
		}
	}
}
