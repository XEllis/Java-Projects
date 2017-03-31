/**
 * Keeps count of the statistics: 
 * the number of items that have logged their information and left the simulation, 
 * the time it takes items to be processed, 
 * the time they wait in line prior to processing. 
 * @author xellis
 *
 */
public class Log {
	
	/**
	 * The number of items that have logged their information and left the simulation. 
	 */
	private int numCompleted;
	
	/**
	 * The sum of all wait times logged by items so far. 
	 */
	private int totalWaitTime;
	
	/**
	 * The sum of all times that items took to do actual processing (not including wait time). 
	 */
	private int totalProcessTime;
	
	/**
	 * Null Constructor. 
	 */
	public Log() {
		// Null Constructor
	}
	
	/**
	 * Returns the number of items that have logged their information and left the simulation. 
	 * @return the numCompleted the number of items that have logged their information and left the simulation 
	 */
	public int getNumCompleted() {
		return numCompleted;
	}
	
	/**
	 * Updates its three data members from the item that was just processed. 
	 * @param item the item that was just processed 
	 */
	public void logItem(ItemToShip item) {
		if (item != null) {
			numCompleted += 1;
			totalWaitTime += item.getWaitTime();
			totalProcessTime += item.getProcessTime();
		}
	}
	
	/**
	 * Returns the average number of seconds so far the item needs to wait 
	 * in a shipment process station queue before processing. 
	 * @return average wait time 
	 */
	public double averageWaitTime() {
		if (numCompleted == 0) {
			return 0.0;
		}
		return ((double) totalWaitTime) / numCompleted;
	}
	
	/**
	 * Returns the average number of seconds required so far to process the item (not including wait time). 
	 * @return average process time 
	 */
	public double averageProcessTime() {
		if (numCompleted == 0) {
			return 0.0;
		}
		return ((double) totalProcessTime) / numCompleted;
	}

}
