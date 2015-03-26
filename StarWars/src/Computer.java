/**
 * Computer is a representation of an entity that opens a door once hacked
 * 
 * @author Nicholas Grant
 */
public class Computer extends Entity {
	/**State of the computer(hacked or not hacked)*/
	protected boolean isHacked;
	/** Initializes the computer using input parameters.
	 * @param n     Computer's name
	 */
	public Computer( String n ) {
		super( n, 0 );
	}
	/**
	 * Opens a Door instance once hacked by an astromech.
	 * @param e		The target of the computer's task.
	 * @param t		Not used for this implementation.
	 */
	@Override
	public void doTask( Entity e, int t ) {
		if ( e instanceof Door ) {
			if ( isHacked ) {
				System.out.println( "The door may now be opened." );
				((Door) e).isOpen = true;
			}
			else {
				System.out.println( "The computer has not been hacked yet." );
			}
		}
		else {
			System.out.println( "Invalid target." );
		}
	}

}
