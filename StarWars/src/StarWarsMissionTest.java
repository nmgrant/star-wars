/**
 * Test implementation of Entity and all its subclasses
 * 
 * @author Nicholas Grant
 */
import java.util.*;
public class StarWarsMissionTest {
	public static void main(String[] args) {
		/*
		 * Initialize a scanner variable
		 */
		Scanner scan = new Scanner( System.in );
		/*
		 * Introduces the user and allows them to enter a name and 
		 * catch phrase for their jedi
		 */
		System.out.println( "Welcome to the Star Wars Galaxy!" );
		System.out.println( "Enter a name for your Jedi: " );
		String jediName = scan.nextLine();
		System.out.println( "Enter a catch phrase for your Jedi: " );
		String jediQuip = scan.nextLine();
		/*
		 * Initializes the user's jedi as well as a Medical and Astromech droid
		 */
		Jedi jedi = new Jedi( jediName, jediQuip,"green" );
		Medical medic = new Medical( "2-1B" );
		Astromech astromech = new Astromech( "R2-D2" );
		/*
		 * Populates an entity array of size 8 with the jedi, the two droids, 
		 * and 5 rebel troopers. This is the rebel team.
		 */
		Entity [] rebels = new Entity [8];
		rebels [0] = jedi;
		rebels [1] = new Rebel( "Rebel Trooper 1","Nice try!" );
		rebels [2] = new Rebel( "Rebel Trooper 2","Nice try!" );
		rebels [3] = new Rebel( "Rebel Trooper 3","Nice try!" );
		rebels [4] = new Rebel( "Rebel Trooper 4","Nice try!" );
		rebels [5] = new Rebel( "Rebel Trooper 5","Nice try!" );
		rebels [6] = medic;
		rebels [7] = astromech;
		/*
		 * Initializes the sith lord
		 */
		SithLord sithLord = new SithLord( "Darth Vader","You underestimate the " +
				"power of the dark side!","red" );
		/*
		 * Populates an entity array
		 * This is the imperial team
		 */
		Entity [] imperials = new Entity [6];
		imperials [0] = sithLord;
		imperials [1] = new Stormtrooper( "Stormtrooper 1","Too easy!" );
		imperials [2] = new Stormtrooper( "Stormtrooper 2","Too easy!" );
		imperials [3] = new Stormtrooper( "Stormtrooper 3","Too easy!" );
		imperials [4] = new Stormtrooper( "Stormtrooper 4","Too easy!" );
		imperials [5] = new Stormtrooper( "Stormtrooper 5","Too easy!" );
		/*
		 * Initializes a computer and door used in the second mission
		 */
		Computer computer = new Computer( "Imperial Base Central Terminal" );
		Door door = new Door ( "Imperial Base Entrance" );
		/*
		 * Calls the missionMenu() method to prompt the user to enter a mission choice
		 */
		int mission = missionMenu();
		/*
		 * The first mission is a simple "destroy the enemy team" mission
		 * When an entire team is dead, the mission is over
		 * If the rebel team dies, the mission is failed
		 * If the imperial team dies, the mission is successful
		 */
		if ( mission == 1 ) {
			/*
			 * Introduces the user to the scenario
			 */
			System.out.println( "" );
			System.out.println( "You find the Sith Lord and his troops patrolling " +
			 "the area below! Ambush them!" );
			System.out.println( "" );
			/*
			 * Controls the combat sequence of the mission
			 * The loop will terminate once an entire team dies or the user dies
			 */
			do {
				/*
				 * Prints the rebel and imperial teams to show progress
				 */
				printRebels( rebels );
				printImperials( imperials );
				/*
				 * Prompts the user to choose a task
				 */
				int task = taskMenuAmbush();
				/*
				 * The first task choice is to simply attack a target
				 */
				if ( task == 1 ) {
					/*
					 * Prompts the user for a target based on a list of the enemies
					 */
					Entity target = targetMenu( imperials );
					System.out.println( "" );
					/*
					 * Error checking for when the user targets a dead entity
					 */
					while ( !target.getActive() ) {
						System.out.println( "That target is not alive! " +
						 "Please choose another target." );
						target = targetMenu( imperials );
					}
					/*
					 * Carries out the attack
					 */
					jedi.doTask( target, task );
				}
				/*
				 * The second task choice is to use the force on a target
				 */
				if ( task == 2 ) {
					/*
					 * Prompts the user for a target based on a list of the enemies
					 */
					Entity target = targetMenu( imperials );
					System.out.println( "" );
					/*
					 * Error checking for if the user targets a dead entity
					 */
					while ( !target.getActive() ) {
						System.out.println( "That target is not alive! " +
						 "Please choose another target." );
						target = targetMenu( imperials );
					}
					/*
					 * Uses the force on the target
					 */
					jedi.doTask( target,task );
				}
				/*
				 * The third choice has the user command the medical droid to heal the most wounded ally
				 */
				if( task == 3 ) {
					System.out.println( "" );
					/*
					 * If the medical droid is dead, it cannot heal.
					 */
					if ( !medic.getActive() ) {
						System.out.println( medic.getName()+" is inactive! " +
						 "It cannot heal!" );
					}
					/*
					 * Carries out the medical droid's heal task
					 */
					else {
						medic.doTask( lowestHpRebel(rebels), 1 );
					}
				}
				/*
				 * Automates the rebel troopers and imperial attacks
				 */
				rebelAttacks( rebels, imperials );
				imperialAttacks( imperials, rebels );
				System.out.println( "" );
				/*
				 * Restores a set amount of energy to the rebel and imperial units
				 */
				recoverEnergy( rebels );
				recoverEnergy( imperials );
			} while( teamActive( rebels ) && teamActive( imperials ) 
			   && jedi.getActive() );
			/*
			 * Prints the rebel and imperial teams after the combat sequence is over
			 */
			printRebels( rebels );
			printImperials( imperials );
			/*
			 * If the rebel team is still alive, 
			 * the user has succeeded in their mission
			 */
			if ( teamActive( rebels ) && jedi.getActive() ) {
				System.out.println( "Congratulations! " +
				 "You defeated the Sith Lord and his troops!" );
			}
			/*
			 * If the imperial team is still alive, 
			 * the user has failed in their mission
			 */
			else if ( teamActive( imperials ) ) {
				System.out.println( "You have been defeated by the Imperial troops! "
				 + "Mission failed." );
			}
			System.out.println( "--------" );
			System.out.println( "Mission complete." );
		}
		/*
		 * The second mission requires the user to hack the computer 
		 * controlling the door to enter the imperial base. 
		 * Once inside, the user must duel the sith lord.
		 */
		else if ( mission == 2 ) {
			/*
			 * Introduces the user to the scenario
			 */
			System.out.println( "You approach the Imperial base. " +
			 "There are troops patrolling, but they're looking away!" );
			System.out.println( "Use the astromech to hack the computer and " +
			 "open the door!" );
			/*
			 * Prompts the user to choose a task
			 */
			int task = taskMenuHack();
			/*
			 * Carries out the astromech's task to hack the computer and open the door
			 */
			if ( task == 1 ) {
				while ( !computer.isHacked ) {
					astromech.doTask( computer, 1 );
				}
				computer.doTask( door, 1 );
				door.doTask( door, 1 );
			}
			System.out.println( "You have successfully entered the base. Defeat the " +
			 "Sith Lord while your troops fend off the " +
			 "stormtroopers to complete the mission." );
			System.out.println( "" );
			/*
			 * Now the user must fight the sith lord one-on-one
			 * This loop continues until the user or the sith lord dies
			 */
			 do {
				/*
				 * Random variable used to decide whether the sith 
				 * attacks or uses the force
				 */
				Random sithTask = new Random();
				/*
				 * Prints the jedi and sith lord to show progress
				 */
				printJedi( jedi );
				printSithLord( sithLord );
				/*
				 * Prompts the user to choose a task
				 */
				int taskDuel = taskMenuDuel();
				/*
				 * Task 1 simply attacks the sith lord
				 */
				if ( taskDuel == 1 ) {
					jedi.doTask( sithLord, taskDuel );
				}
				/*
				 * Task 2 uses the force on the sith lord
				 */
				if ( taskDuel == 2 ) {
					jedi.doTask( sithLord, taskDuel ); 
				}
				/*
				 * If the sith lord is still alive, 
				 * he randomly attacks or uses the force on the jedi
				 */
				if ( sithLord.getHp() > 0 ) {
					sithLord.doTask( jedi, sithTask.nextInt(2)+1 );
				}
				/*
				 * Both units recover 25 energy.
				 */
				recoverEnergy( jedi );
				recoverEnergy( sithLord );
				
			} while( sithLord.getActive() && jedi.getActive() );
			/*
			 * If the sith lord is still alive, then the user failed the mission
			 */
			if ( sithLord.getActive( )) {
				((Person) sithLord).sayCatchPhrase();
				System.out.println("Mission failed. Try again!");
			}
			/*
			 * Otherwise, the jedi is still alive and the mission was a success
			 */
			if ( jedi.getActive() ) {
				System.out.println( "Congratulations! You infiltrated the Imperial Base " +
				 "and defeated the Sith Lord! The base now belongs to the rebels." );
				System.out.println( "-------" );
				System.out.println( "Mission Complete" );
			}
		}
		
    }
	/**
	 * Method used to restore energy to a unit
	 * @param e		Target entity
	 */
	public static void recoverEnergy( Entity e ) {
		final int ENERGY_RECOVERY = 25;
		if( e.getEnergy()+ENERGY_RECOVERY >= 100 )
			e.modifyEnergy( 100 );
		else if ( e.getEnergy() < 100 )
			e.modifyEnergy( e.getEnergy()+ENERGY_RECOVERY );	
	}
	/**
	 * Method used to prompt the user to hack the door in the second mission
	 * @return		User's choice
	 */
	public static int taskMenuHack() {
		System.out.println( "What would you like to do?" );
		System.out.println( "1. Hack the computer to open the door." );
		return getValidInt( 1, 1 );
	}
	/**
	 * Method used to recover energy to an entire team
	 * @param e		Target team
	 */
	public static void recoverEnergy(Entity[] e) {
		final int ENERGY_RECOVERY = 25;
		for ( Entity entity : e ) {
			if( entity.getEnergy()+ENERGY_RECOVERY >= 100 )
				entity.modifyEnergy( 100 );
			else if ( entity.getEnergy() < 100 )
				entity.modifyEnergy( entity.getEnergy()+ENERGY_RECOVERY );
		}
	}
	/**
	 * Method used to find the lowest health unit to heal it
	 * @param r		Target team
	 * @return		Lowest health unit
	 */
	public static Entity lowestHpRebel( Entity[] r ) {
		Entity lowestHp = null;
		final int REBEL_MAX_HP = 50;
		int min = REBEL_MAX_HP;
		for( Entity rebel : r ) {
			if ( rebel instanceof Healable && rebel.getHp() < min && rebel.getHp() > 0 ) {
				min = rebel.getHp();
				lowestHp = rebel; 
			}
		}
		return lowestHp;
	}
	/**
	 * Method used to prompt the user to select a mission
	 * @return		Mission choice
	 */
	public static int missionMenu() {
		System.out.println( "Choose a mission: " );
		System.out.println( "1. Ambush the Sith Lord" );
		System.out.println( "2. Capture the Imperial Base" );
		return getValidInt( 1, 2 );
	}
	/**
	 * Method used to prompt the user to select a target
	 * @param e		Target team
	 * @return		Target choice
	 */
	public static Entity targetMenu( Entity [] e ) {
		System.out.println( "Choose a target: " );
		System.out.println( "1. "+e[0].getName() );
		System.out.println( "2. "+e[1].getName() );
		System.out.println( "3. "+e[2].getName() );
		System.out.println( "4. "+e[3].getName() );
		System.out.println( "5. "+e[4].getName() );
		System.out.println( "6. "+e[5].getName() );
		int target = getValidInt( 1, 6 );
		return e[target-1];
	}
	/**
	 * Method used to prompt the user to select a task for mission 1's combat sequence
	 * @return		Task choice
	 */
	public static int taskMenuAmbush() {
		System.out.println( "What would you like to do?" );
		System.out.println( "1. Attack with Lightsaber." );
		System.out.println( "2. Attack with Force." );
		System.out.println( "3. Command the droid to heal the most wounded rebel." );
		return getValidInt( 1, 3 );
	}
	/**
	 * Method used to prompt the user to select a task for mission 2's duel
	 * @return			User's choice
	 */
	public static int taskMenuDuel() {
		System.out.println( "Choose your action." );
		System.out.println( "1. Attack with Lightsaber." );
		System.out.println( "2. Attack with Force." );
		return getValidInt( 1, 2 );
	}
	/**
	 * Error checks for user input based on a lower and upper bound
	 * @param low		Lower bound for user input
	 * @param high		Upper bound for user input
	 * @return			User's integer input
	 */
	public static int getValidInt( int low, int high ) {
		Scanner in = new Scanner( System.in );
		boolean invalid = true;
		int value = 0;
		while ( invalid ) {
			if( in.hasNextInt()) {
				value = in.nextInt();
				if ( value >= low && value <= high ) {
					invalid = false;
				}
				else {
					System.out.println( "Invalid- Retry: " );
				}
			}
			else {
				in.next();
				System.out.println( "Invalid input- Retry: " );
			}
		}
		return value;
	}
	/**
	 * Automates the rebel trooper's attacks toward the imperial troops
	 * Will only attack active enemy units and if the enemy team is active
	 * @param r			Rebel team
	 * @param i			Imperial team
	 */
	public static void rebelAttacks( Entity [] r, Entity [] i ) {
		Random randImperial = new Random();
		int target;
		for ( Entity rebel : r ) {
			if ( (rebel instanceof Person) && !(rebel instanceof Jedi) 
			 && rebel.getActive() ) {
				do {
					target = randImperial.nextInt( i.length );
					teamActive( i );
				} while( !i[target].getActive() && teamActive( i ) );
				if ( !teamActive( i ) ) {
					return;
				}
				else {
					rebel.doTask( i[target], 1 );
				}
			}
		}
	}
	/**
	 * Automates the imperial troop's attacks toward the rebel troops
	 * Will only attack active enemy units and if the enemy team is active
	 * @param i			Imperial team
	 * @param r			Rebel team
	 */
	public static void imperialAttacks( Entity [] i, Entity [] r ) {
		Random randRebel = new Random();
		Random sithTask = new Random();
		int target;
		for ( Entity imperial : i ) {
			if ( imperial instanceof SithLord && imperial.getActive() ) {
				do {
					target = randRebel.nextInt( r.length );
				} while( !r[target].getActive() && teamActive(r) );
				if ( !teamActive( r ) ) {
					return;
				}
				else {
					imperial.doTask( r[target], sithTask.nextInt(2)+1 );
				}
			}
			else if ( imperial.getActive() ) {
				do {
					target = randRebel.nextInt( r.length );
				} while( !r[target].getActive() && teamActive( r ) );
				if ( !teamActive( r ) ) {
					return;
				}
				else {
					imperial.doTask( r[target], 1 );
				}
			}
		}
	}
	/**
	 * Method used to check if a team is active (at least one unit active)
	 * @param e		Team being checked
	 * @return		Whether the team is active or not
	 */
	public static boolean teamActive( Entity [] e ) {
		boolean teamActive = false;
		for ( Entity entity : e ) {
			if( entity.getActive() && entity instanceof Person ) {
				teamActive = true;
			}
			else if( entity.getActive() && entity instanceof Droid ) {
				teamActive = true;
			}
		}
		return teamActive;
	}
	/**
	 * Prints the rebel team's names, HP, and energy
	 * @param r			Rebel team
	 */
	public static void printRebels( Entity [] r ) {
		System.out.println( "Rebels" );
		System.out.println( "--------" );
		for ( int index = 0; index < r.length; index++ ) {
			System.out.println( r[index] );
		}
		System.out.println( "" );
	}
	/**
	 * Prints the imperial team's names, HP, and energy
	 * @param i			Imperial team
	 */
	public static void printImperials( Entity [] i ) {
		System.out.println( "Imperials" );
		System.out.println("--------");
		for ( int index = 0; index < i.length; index++) {
			System.out.println(i[index]);
		}
		System.out.println("");
	}
	/**
	 * Prints the jedi's name, HP, and energy
	 * @param j			Target Jedi
	 */
	public static void printJedi( Entity j ) {
		System.out.println( "Jedi" );
		System.out.println( "-------" );
		System.out.println( j );
		System.out.println( "" );
	}
	/**
	 * Prints the sith lord's name, HP, and energy
	 * @param s			Target Sith lord
	 */
	public static void printSithLord( Entity s ) {
		System.out.println( "Sith Lord" );
		System.out.println( "-------" );
		System.out.println( s );
		System.out.println( "" );
	}
}

