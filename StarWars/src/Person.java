/**
 * Person is an abstract representation of an entity
 * 
 * @author Nicholas Grant
 */
public abstract class Person extends Entity {
	/**The person's weapon*/
	private String weapon;
	/**The person's catch phrase*/
	private String quip;
	/**
	 * Initializes a person using input parameters
	 * @param n		Person's name
	 * @param h		Person's HP
	 * @param w		Person's weapon
	 * @param q		Person's catch phrase
	 */
	public Person( String n, int h, String w, String q ) {
		super( n, h );
		weapon = w;
		quip = q;
	}
	/**
	 * Prints the person's catch phrase
	 */
	public void sayCatchPhrase() {
		System.out.println( quip );
	}
	/**
	 * Causes the person to attack a target
	 * @param e		Person's target
	 */
	public abstract void attack( Entity e );
	
	/**
	 * Returns the person's weapon
	 * @return		Person's weapon
	 */
	public String getWeapon() {
		return weapon;
	}
	
	
}