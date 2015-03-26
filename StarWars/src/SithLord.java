/**
 * SithLord is a representation of a person that can use the force
 * 
 * @author Nicholas Grant
 */
public class SithLord extends Person implements HasForce {
	/**The sith lord's lightsaber color*/
	private String saberColor;
	/**
	 * Initializes a sith lord using input parameters
	 * @param n		Sith lord's name
	 * @param q		Sith lord's catch phrase
	 * @param c		Sith lord's lightsaber color
	 */
	public SithLord( String n, String q, String c ) {
		super( n, 100, "lightsaber", q );
		saberColor = c;
	}
	/**
	 * SithLord's implementation of the HasForce interface's useForce method. Uses a random
	 * number between 1 and 2 to determine if the sith lord successfully uses the force. Then, another
	 * random number between 50 and 100 is used to determine how much damage is dealt. If
	 * the sith lord fails in using the force, its target will say its catch phrase, else the target will take
	 * damage from the sith lord and its health is modified accordingly. If the target is killed by
	 * the sith lord, the sith lord says its catch phrase. Whether the sith lord hits or misses, it's energy 
	 * level is reduced to 0.
	 * @param e			Sith lord's target
	 */
	public void useForce( Entity e ) {
		int forceHit = randInt( 1, 2 );
		int forceDmg = randInt( 50, 100 );
		
		if (forceHit != 1) { 
			System.out.println( getName()+" tries to use the force on " +e.getName()+ ", but it fails! "
			 +getName()+"'s energy is drained." );
			e.tookDmg = false;
			if ( e instanceof Person ) {
				System.out.print( e.getName()+": " );
				((Person) e).sayCatchPhrase();
			}
		}
		else {
			System.out.println( getName()+" successfully uses the force on "
			 +e.getName()+ "! "+getName()+ " deals " +forceDmg+ " damage. "
			 +getName()+"'s energy is drained." );
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
	
	/**
	 * SithLord's implementation of the Person's attack method. Uses a random
	 * number between 1 and 2 to determine if the sith lord's attack hits. Then, another
	 * random number between 25 and 50 is used to determine how much damage is dealt. If
	 * the sith lord misses, its target will say its catch phrase, else the target will take
	 * damage from the sith lord and its health is modified accordingly. If the target is killed by
	 * the sith lord, the sith lord says its catch phrase. Whether the sith lord hits or misses, it loses 25 energy.
	 * @param e			Sith lord's target
	 */
	@Override
	public void attack( Entity e ) {
		int attackHit = randInt( 1, 2 );
		int attackDmg = randInt( 25 ,50 );
		
		if( attackHit != 1 ) {
			System.out.println( getName()+" tries to attack "+e.getName()+
			 ", but misses! "+getName()+" loses 25 energy." );
			e.tookDmg = false;
			if ( e instanceof Person ) {
				System.out.print( e.getName()+": " );
				((Person) e).sayCatchPhrase();
			}
		}
		else {
			System.out.println( getName()+" slashes "+e.getName()+" with his/her "
			 +saberColor+getWeapon()+" dealing "+attackDmg+" points of damage! "
			 +getName()+" loses 25 energy." );
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
	 * SithLord's implementation of Entity's doTask method. If the sith lord has no energy or below the
	 * amount of energy required for the task , it will not complete its task. Otherwise, the sith lord completes its task
	 * depending on the target and task input by the user.
	 * @param e		Sith lord's target
	 * @param t		Sith lord's task
	 */
	@Override
	public void doTask( Entity e, int t ) {
		if ( energy == 0 ) {
			System.out.println( getName()+" has no energy. "+getName()+
			 " cannot complete a task until he/she has positive energy." );
		}
		else if ( t == 1 && energy < 25 ) {
			System.out.println( getName()+" does not have enough energy to attack!" );
		}
		else if ( t == 1 ) {
			attack(e);
		}
		else if ( t == 2 && energy < 100 ) {
			System.out.println( getName()+" does not have enough energy to use " +
			 "the force!" );
		}
		else if ( t == 2 ) {
			useForce( e );
		}
		else {
			System.out.println( "Invalid target." );
		}
	}

}
