/**
 * Jedi is a representation of a Person that is healable and can use the force
 * 
 * @author Nicholas Grant
 */
public class Jedi extends Person implements Healable, HasForce {
	/**Jedi's lightsaber color*/
	private String saberColor;
	/**
	 * Initializes the jedi using input parameters
	 * @param n		Jedi's name
	 * @param q		Jedi's catch phrase
	 * @param c		Jedi's lightsaber color
	 */
	public Jedi( String n, String q, String c ) {
		super( n, 100, "lightsaber", q );
		saberColor = c;
	}
	/**
	 * Jedi's implementation of the Healable interface. Medical droid calls
	 * this method to heal a jedi.
	 * @param h		Desired amount of HP healed
	 */
	public void heal( int h ) {
		if ( (getHp() + h) > 100 ) {
			modifyHp( 100 );
		}
		else
			modifyHp( getHp() + h );
	}
	/**
	 * Jedi's implementation of the Person's attack method. Uses a random
	 * number between 1 and 2 to determine if the jedi's attack hits. Then, another
	 * random number between 25 and 50 is used to determine how much damage is dealt. If
	 * the jedi misses, its target will say its catch phrase, else the target will take
	 * damage from the jedi and its health is modified accordingly. If the target is killed by
	 * the jedi, the jedi says its catch phrase. Whether the jedi hits or misses, it loses 25 energy.
	 * @param e			Jedi's target
	 */
	@Override
	public void attack( Entity e ) {
		int attackHit = randInt( 1, 2 );
		int attackDmg = randInt( 25, 50 );
		
		if(attackHit != 1) {
			System.out.println( getName()+" tries to attack "+e.getName()+", " +
			 "but misses! "+getName()+" loses 25 energy." );
			e.tookDmg = false;
			System.out.print( e.getName()+": " );
			((Person) e).sayCatchPhrase();
		}
		else {
			System.out.println( getName()+" slashes at "+e.getName()+" with his/her "
			 +saberColor+" "+getWeapon()+" dealing "+attackDmg+
			 " points of damage! "+getName()+" loses 25 energy." );
			e.tookDmg = true;
			e.modifyHp( e.getHp() - attackDmg );
			if( e.getHp() <= 0 ) {
				System.out.print( getName()+": " );
				sayCatchPhrase();
				e.modifyHp( 0 );
				e.setActive( false );
			}
		}
		modifyEnergy( getEnergy() - 25 );
	}
	/**
	 * Jedi's implementation of Entity's doTask method. If the jedi has no energy or below the
	 * amount of energy required for the task , it will not complete its task. Otherwise, the jedi completes its task
	 * depending on the target and task input by the user.
	 * @param e		Jedi's target
	 * @param t		Jedi's task
	 */
	@Override
	public void doTask( Entity e, int t ) {
		if ( energy == 0 ) {
			System.out.println( getName()+" has no energy. "+getName()+" cannot " +
			 "complete a task until he/she has positive energy.");
		}
		else if ( t == 1 && energy < 25 ) {
			System.out.println( getName()+" does not have enough energy to attack!" );
		}
		else if ( t == 1 ) {
			attack( e );
		}
		else if ( t == 2 && energy < 100 ) {
			System.out.println( getName()+" does not have enough energy to " +
			 "use the force!" );
		}
		else if ( t == 2 ) {
			useForce( e );
		}
		else {
			System.out.println( "Invalid target." );
		}
	}
	/**
	 * Jedi's implementation of the HasForce interface's useForce method. Uses a random
	 * number between 1 and 2 to determine if the jedi successfully uses the force. Then, another
	 * random number between 50 and 100 is used to determine how much damage is dealt. If
	 * the jedi fails in using the force, its target will say its catch phrase, else the target will take
	 * damage from the jedi and its health is modified accordingly. If the target is killed by
	 * the jedi, the jedi says its catch phrase. Whether the jedi hits or misses, it's energy level is reduced to 0.
	 * @param e			Jedi's target
	 */
	public void useForce( Entity e ) {
		int forceHit = randInt( 1,2 );
		int forceDmg = randInt( 50,100 );
		
		if ( forceHit != 1 ) { 
			System.out.println( getName()+" tries to use the force on " +e.getName()+ ", but it fails! "
			 +getName()+"'s energy is drained." );
			e.tookDmg = false;
			System.out.print( e.getName()+": " );
			((Person) e).sayCatchPhrase();
		}
		else {
			System.out.println( getName()+" successfully uses the force on " 
			 +e.getName()+ "! "+getName()+ " deals " +forceDmg+ " damage. "+
			 getName()+"'s energy is drained." );
			e.tookDmg = true;
			e.modifyHp( e.getHp() - forceDmg );
			if( e.getHp() <= 0 ) {
				System.out.print( getName()+": " );
				sayCatchPhrase();
				e.modifyHp( 0 );
				e.setActive( false );
			}
		}
		modifyEnergy( 0 );
	}
}
