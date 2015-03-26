/**
 * Rebel is a representation of a person that is healable
 * 
 * @author Nicholas Grant
 */
public final class Rebel extends Person implements Healable {
	/**
	 * Initializes a rebel using input parameters
	 * @param n			Rebel's name
	 * @param q			Rebel's catch phrase
	 */
	public Rebel( String n, String q ) {
		super( n, 50, "blaster", q );
	}
	/**
	 * Rebel's implementation of the Healable interface. Medical droid calls
	 * this method to heal a rebel.
	 * @param h			Desired amount of HP healed
	 */
	public void heal( int h ) {
		if ( (getHp() + h) > 50 ) {
			modifyHp( 50 );
		}
		else
			modifyHp( getHp() + h );
	}
	/**
	 * Rebel's implementation of the Person's attack method. Uses a random
	 * number between 1 and 2 to determine if the rebel's attack hits. Then, another
	 * random number between 20 and 30 is used to determine how much damage is dealt. If
	 * the rebel misses, its target will say its catch phrase, else the target will take
	 * damage from the rebel and its health is modified accordingly. If the target is killed by
	 * the rebel, the rebel says its catch phrase. Whether the rebel hits or misses, it loses 25 energy.
	 * @param e			Rebel's target
	 */
	@Override
	public void attack( Entity e ) {
		int attackHit = randInt( 1, 2 );
		int attackDmg = randInt( 20, 30 );
		
		if( attackHit != 1 ) {
			System.out.println( getName()+" tries to attack "+e.getName()+", but misses! "
			 +getName()+" loses 25 energy." );
			e.tookDmg = false;
			System.out.print( e.getName()+": " );
			((Person) e).sayCatchPhrase();
		}
		else {
			System.out.println( getName()+" blasts "+e.getName()+" for "+attackDmg+
			 " damage. "+getName()+" loses 25 energy." );
			e.tookDmg = true;
			e.modifyHp( e.getHp() - attackDmg );
			if( e.getHp() <= 0 ) {
				System.out.print( getName()+": " );
				sayCatchPhrase();
				e.modifyHp( 0 );
				e.setActive( false );
			}
		modifyEnergy( getEnergy() - 25 );
		}
	}
	/**
	 * Rebel's implementation of Entity's doTask method. If the rebel has no energy or below the
	 * amount of energy required for the task , it will not complete its task. Otherwise, the rebel completes its task
	 * depending on the target and task input by the user.
	 * @param e		Rebel's target
	 * @param t		Rebel's task
	 */
	@Override
	public void doTask( Entity e, int t ) {
		if ( energy == 0 ) {
			System.out.println( getName()+" has no energy. "+getName()+" cannot " +
			 "complete a task until he/she has positive energy." );
		}
		else if ( t == 1 && energy < 25 ) {
			System.out.println( getName()+" does not have enough energy to attack!" );
		}
		else if ( t == 1 ) {
			attack( e );
		}
		else {
			System.out.println( "Invalid target." );
		}
	}
}
