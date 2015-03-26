/**
 * Abstract representation of a generic type of entity.
 * 
 * @author Nicholas Grant
 */
import java.util.*;

public abstract class Entity {
	/**The name of the entity*/
	private String name;
	/**The amount of HP the entity has*/
	protected int hp;
	/**The amount of energy the entity has*/
	protected int energy;
	/**Whether the entity took damage or not*/
	protected boolean tookDmg;
	/**Whether the entity is still alive or not*/
	private boolean active;
	/**The entity's task*/
	private String task;
	/**Whether the entity is performing its task*/
	protected boolean doingTask;
	/**
	 * Initializes a generic entity with 100 max energy and active.
	 * @param n		Entity's name
	 * @param h		Entity's HP
	 */
	public Entity( String n, int h ) {
		name = n;
		hp = h;
		energy = 100;
		active = true;
	}
	/**
	 * The generic doTask method for all entities
	 * @param e		The target of the task
	 * @param t		The task choice
	 */
	public abstract void doTask( Entity e, int t );
	/**
	 * Used to return the name of an entity
	 * @return		Entity's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Used to return the HP of an entity
	 * @return		Entity's HP
	 */
	public int getHp() {
		return hp;
	}
	/**
	 * Used to return an entity's active state
	 * @return		Entity's active state (alive or dead)
	 */
	public boolean getActive() {
		return active;
	}
	/**
	 * Used to set an entity's active state
	 * @param a		The desired active state for an entity
	 */
	public void setActive( boolean a ) {
		active = a;
	}
	/**
	 * Used to change an entity's current HP
	 * @param h		Desired amount of current HP
	 */
	public void modifyHp( int h ) {
		hp = h;
	}
	/**
	 * Used to return the task of an entity
	 * @return		Entity's task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * Used to set the task of an entity
	 * @param t		
	 */
	public void setTask( String t ) {
		task = t;
	}
	/**
	 * Used for randomizing an entity's actions
	 * @param min		The lower bound of the random number
	 * @param max		The upper bound of the random number
	 * @return			A random number between the min and max parameters
	 */
	public int randInt( int min, int max ) {
		Random rand = new Random();
	    int randomNum = rand.nextInt( (max - min) + 1 ) + min;
	    return randomNum;
	}
	/**
	 * Used to return an entity's current energy amount
	 * @return		Entity's current energy amount
	 */
	public int getEnergy() {
		return energy;
	}
	/**
	 * Used to modify the entity's current energy amount
	 * @param enrg		Desired current amount of energy
	 */
	public void modifyEnergy( int enrg ) {
		energy = enrg;
	}
	/**
	 * Overrides inherited toString method for printing the entities properly
	 */
	@Override
	public String toString() {
		return name+":   "+hp+"HP"+"   "+energy+" Energy";
	}
}





	


