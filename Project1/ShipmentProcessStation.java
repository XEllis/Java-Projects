import java.util.NoSuchElementException;

/**
 * Creates a ShipmentProcessStation with a log. 
 * @author xellis
 *
 */
public class ShipmentProcessStation implements LineOfItems {
	
	/**
	 * The ShipmentQueue of items waiting for or being processed at this station. 
	 */
	private ShipmentQueue line;
	
	/**
	 * The item at the front of the line logs its information here during its processing. 
	 */
	private Log log;
	
	/**
	 * The time when the queue for this station will finally be clear all of items currently in the queue. 
	 */
	private int timeWhenAvailable;
	
    /**
     * Creates a ShipmentProcessStation with a log. 
     * @param log the Log to keep count of the statistics 
     */
	public ShipmentProcessStation(Log log) {
		this.log = log;
		line = new ShipmentQueue();
	}

	/**
	 * Returns true if the line is not empty. 
	 * @return true if the line has more items 
	 */
	@Override
	public boolean hasNext() {
		return !line.isEmpty();
	}

	/**
	 * Removes the front item from the queue, logging its information in the process. 
	 * Returns the removed item. 
	 * @return the item that was just processed 
	 */
	@Override
	public ItemToShip processNext() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		log.logItem(line.front());
		line.front().removeFromWaitingLine();
		return line.remove();
	}

	/**
	 * Tells when the item at the front of the line (currently being processed) 
	 * will finish its processing and leave the simulation. 
	 * If the line is empty, return Integer.MAX_VALUE. 
	 * @return the departure time of the next item in the line 
	 * or Integer.MAX_VALUE if the line is empty 
	 */
	@Override
	public int departTimeNext() {
		int departTime = Integer.MAX_VALUE;
		if (!line.isEmpty()) {
			departTime = line.front().getArrivalTime() + line.front().getWaitTime() + line.front().getProcessTime();
		}
		return departTime;
	}

	/**
	 * Returns the number of items still in the line. 
	 * @return the number of items still in the line 
	 */
	@Override
	public int size() {
		return line.size();
	}
	
	/**
	 * Adds an item to the end of the queue. 
	 * Updates the item's waitTime as well as the time 
	 * when the items in the line will be clear of all current items. 
	 * @param item the ItemToShip to be added to the end of the ShipmentProcessStation 
	 */
	public void addItemToLine(ItemToShip item) {
		if (line.isEmpty()) {
			item.setWaitTime(0);
			timeWhenAvailable = item.getArrivalTime() + item.getProcessTime();
		} else {
			item.setWaitTime(timeWhenAvailable - item.getArrivalTime());
			timeWhenAvailable += item.getProcessTime();
		}
		line.add(item);
	}

}
