/**
 * Used to generate a random number for the attack hit/miss and damage
 * 
 * @author Nicholas Grant
 */
import java.util.*;

public class RandomGenerator {
	/**
	 * Returns a random number between the input parameter min and max
	 * @param min		Smallest number randomly generated
	 * @param max		Highest number randomly generated
	 * @return		The random number generated
	 */
	public int randInt( int min, int max ) {
		Random rand = new Random();
		
	    int randomNum = rand.nextInt( (max - min) + 1 ) + min;

	    return randomNum;
	}
}
