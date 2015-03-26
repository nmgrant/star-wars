/**
 * Droid is an abstract representation of an entity
 * name and HP.
 * 
 * @author Nicholas Grant
 */
public abstract class Droid extends Entity {
	/**
	 * Initializes a Droid using input parameters.
	 * @param n		Droid name.
	 * @param h		Droid HP.
	 */
	public Droid( String n, int h ) {
		super( n, h );
	}
	
}