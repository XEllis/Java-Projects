import java.util.*;

/**
 * A simple factory class whose only task is to generate book shipments.<br />
 *   International shipments are generated 20% of the time, with process time 25 - 28 seconds. <br />
 *   Rush shipments are generated 25% of the time, with process time 10 - 12 seconds.<br />
 *   Regular shipments are generated 55% of the time, with process time 15 - 19 seconds.
 * @author xellis
 * @see InternationalBookShipment
 * @see RegularBookShipment
 * @see RushBookShipment
 */
public class BookShipmentFactory {
	/** Absolute time for book shipments created for the simulation.  The 
	 * time starts at zero and increases by up to MAX_SHIPMENT_GENERATION_DELAY
	 * for each shipment created by the factory. */
	private static int time = 0; 
	/** Random object with seed that allows for testing of simulation */
	private static Random randomNumber = new Random(10); 
	/** Maximum delay between creation of book shipments */
	private static final double MAX_SHIPMENT_GENERATION_DELAY = 5.4;
	/** Percentage of time an overseas book shipment should be created */
	private static final double INTERNATIONAL_PERCENT = .20;
	/** Percentage of time a priority book shipment should be created */
	private static final double PRIORITY_PERCENT = .25;
	/** Minimum amount of time required to process an international shipment */
	private static final int INTERNATIONAL_MIN_TIME = 25;
	/** Process time range for an international shipment */
	private static final int INTERNATIONAL_TIME_RANGE = 3;
	/** Minimum amount of time required to process a priority book shipment */
	private static final int PRIORITY_MIN_TIME = 10;
	/** Process time range for a priority book shipment */
	private static final int PRIORITY_TIME_RANGE = 2;
	/** Minimum amount of time required to process a ordinary book shipment */
	private static final int REGULAR_MIN_TIME = 15;
	/** Process time range for an ordinary book shipment */
	private static final int REGULAR_TIME_RANGE = 4;
	
	/**
	 * Generate a new book shipment as described in the class comments.  
	 * @return the book shipment created
	 */
	public static ItemToShip generateBookShipment() {
		// Update the overall time with up to the floor of MAX_PKG_GENERATION_DELAY seconds.
		// The value is cast to an int, which is the floor of the original double.
		time += (int)(randomNumber.nextDouble() * MAX_SHIPMENT_GENERATION_DELAY);

		// Random number that determines which type of shipment will be created.  The generated number
		// is between 0 and 1.0.  By splitting the priorities across the range of numbers generated, we
		// can simulate creation of different book shipments with the appropriate priorities.
		double x = randomNumber.nextDouble();
		if (x < INTERNATIONAL_PERCENT) {
			// If the generated number is less than INTERNATIONAL_PERCENT, create an overseas shipment
			// with a process time range between INTERNATIONAL_MIN_TIME and INTERNATIONAL_TIME_RANGE.
			return new InternationalBookShipment(time, 
					INTERNATIONAL_MIN_TIME + (int) (randomNumber.nextDouble() * INTERNATIONAL_TIME_RANGE)); 
		}
		else if (x < INTERNATIONAL_PERCENT + PRIORITY_PERCENT) {
			// If the generated number is less than INTERNATIONAL_PERCENT + PRIORITY_PERCENT, create a rush
			// shipment with a process time range between PRIORITY_MIN_TIME and PRIORITY_TIME_RANGE.
			return new RushBookShipment(time, 
					PRIORITY_MIN_TIME + (int) (randomNumber.nextDouble() * PRIORITY_TIME_RANGE));
		}
		else {
			// Otherwise, create a regular shipment with a process time range between REGULAR_MIN_TIME 
			// and REGULAR_TIME_RANGE.
			return new RegularBookShipment(time, 
					REGULAR_MIN_TIME + (int) (randomNumber.nextDouble() * REGULAR_TIME_RANGE));
		}
	}

	/**
	 * Resets the factory.  Use this for testing ONLY!
	 */
	public static void resetFactory() {
		time = 0;
		randomNumber = new Random(10);
	}
}
