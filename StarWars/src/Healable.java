/**
 * Healable is an interface for objects that can be healed
 * 
 * @author Nicholas Grant
 */
public interface Healable {
	/**
	 * Method to make the object use its heal method
	 * @param hp
	 */
	public void heal( int hp );
}