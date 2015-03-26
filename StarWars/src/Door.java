/**
 * Door is a representation of an entity that can be opened once its computer is hacked.
 * 
 * @author Nicholas Grant
 */
public class Door extends Entity {
	/**State of the door*/
	protected boolean isOpen;
	/**
	 * Initializes the Door object using input parameters.
	 * @param n		Door's name.
	 */
	public Door(String n) {
		super( n, 0 );
	}
	/**
	 * Opens a Door instance once the computer has opened it.
	 * @param e		The door target being opened.
	 * @param t		Not used for this implementation.
	 */
	@Override
	public void doTask ( Entity e, int t ) {
		if ( e instanceof Door ) {
			if ( isOpen ) {
				System.out.println( "The door has been successfully opened!" );
			}
			else {
				System.out.println( "The door has not been opened yet." );
			}
		}
		else {
			System.out.println( "Invalid target." );
		}
	}
}
