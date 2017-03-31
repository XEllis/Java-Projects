import java.util.NoSuchElementException;

/**
 * Creates a ConveyorBelt with an array of ShipmentProcessStation. 
 * @author xellis
 *
 */
public class ConveyorBelt implements LineOfItems {
	
	/**
	 * The queue of items created by the BookShipmentFactory in the ConveyorBelt constructor. 
	 * Items are added to the queue in order of their arrivalTime. 
	 */
	private ShipmentQueue queueFromFactory;
	
	/**
	 * An array of ShipmentProcessStation. 
	 */
	private ShipmentProcessStation[] station;
	
	/**
	 * Uses BookShipmentFactory to construct the number of items given by the parameter and 
	 * places them on the conveyor belt (by adding them to the ConveyorBelt queue). 
	 * @param numShipments the number of items for the simulation 
	 * @param station the array of ShipmentProcessStation 
	 */
	public ConveyorBelt(int numShipments, ShipmentProcessStation[] station) {
		queueFromFactory = new ShipmentQueue();
		for (int i = 0; i < numShipments; i++) {
			ItemToShip item = BookShipmentFactory.generateBookShipment();
			queueFromFactory.add(item);
		}
		this.station = station;
	}
	
	/**
	 * Returns true if the queue is not empty. 
	 * @return true if the queue has more items 
	 */
	@Override
	public boolean hasNext() {
		return !queueFromFactory.isEmpty();
	}

	/**
	 * Removes the front item from the queue and sends it a getInLine message. 
	 * Returns the removed item. 
	 * @return the item that was just processed 
	 */
	@Override
	public ItemToShip processNext() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		queueFromFactory.front().getInLine(station);
		return queueFromFactory.remove();
	}

	/**
	 * Tells when the item at the front of the ConveyorBelt queue will depart that queue 
	 * (and subsequently enter a ShipmentProcessStation queue). 
	 * If the queue is empty, return Integer.MAX_VALUE. 
	 * @return the departure time of the next item in the queue 
	 * or Integer.MAX_VALUE if the queue is empty 
	 */
	@Override
	public int departTimeNext() {
		int departTime = Integer.MAX_VALUE;
		if (!queueFromFactory.isEmpty()) {
			departTime = queueFromFactory.front().getArrivalTime();
		}
		return departTime;
	}

	/**
	 * Returns the number of items still in the queue. 
	 * @return the number of items still in the queue 
	 */
	@Override
	public int size() {
		return queueFromFactory.size();
	}

}
