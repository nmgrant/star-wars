/**
 * Medical is a representation of a droid that heals target units
 * 
 * @author Nicholas Grant
 */
public class Medical extends Droid {
	/**
	 * Initializes a Medical object using input parameters
	 * @param n		Medical's name
	 */
	public Medical( String n ) {
		super( n, 20 );
	}
	/**
	 * Medical's implementation of Entity's doTask method. Heals a target unit for 50HP, but not over its max HP.
	 * @param e		Medical's target
	 * @param t		Not used for this implementation
	 */
	@Override
	public void doTask( Entity e, int t ) {
		if ( e instanceof Healable ) {
			((Healable) e).heal( 50 );
			System.out.println( e.getName()+" is healed for 50 HP!" );
		}
		else {
			System.out.println( "Invalid target." );
		}
	}
}
