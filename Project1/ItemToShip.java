import java.awt.Color;

/**
 * Creates ItemToShip with an arrivalTime and a processTime. 
 * @author xellis
 * 
 */
public abstract class ItemToShip {
	
	/**
	 * Before the item reaches the queue of its ShipmentProcessStation, stationIndex should be -1. 
	 */
	public static final int INITIAL_STATION_IDX = -1;
	
	/**
	 * Time when the item enters a shipment process station line. 
	 * Time is measured by the number of seconds from the beginning of the simulation. 
	 */
	private int arrivalTime;
	
	/**
	 * Number of seconds the item waits in a shipment process station queue before processing. 
	 */
	private int waitTime;
	
	/**
	 * Number of seconds required to process this item 
	 * (does not include time to wait in the shipment process station queue prior to actual processing). 
	 */
	private int processTime;
	
	/**
	 * The index of the ShipmentProcessStation that the item has selected. 
	 */
	private int stationIndex;
	
	/**
	 * True if the item is currently on a ShipmentProcessStation queue. 
	 * False if the item is still on the ConveyorBelt or if it has left the ShipmentProcessStation queue. 
	 */
	private boolean waitingProcessing;
	
	/**
	 * Creates ItemToShip with an arrivalTime and a processTime. 
	 * If the arrivalTime or processTime are less than 0, throw an IllegalArgumentException. 
	 * @param arrivalTime time when the item enters a shipment process station line 
	 * @param processTime number of seconds required to process this item 
	 */
	public ItemToShip(int arrivalTime, int processTime) {
		if (arrivalTime < 0 || processTime < 0) {
			throw new IllegalArgumentException();
		}
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		stationIndex = INITIAL_STATION_IDX;
	}

	/**
	 * Returns time when the item enters a shipment process station line. 
	 * @return the arrivalTime 
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Returns number of seconds the item waits in a shipment process station queue before processing. 
	 * @return the waitTime 
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Sets the waitTime. 
	 * @param waitTime the waitTime to set 
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * Returns number of seconds required to process this item. 
	 * @return the processTime 
	 */
	public int getProcessTime() {
		return processTime;
	}

	/**
	 * Returns the index of the ShipmentProcessStation that the item has selected. 
	 * @return the stationIndex 
	 */
	public int getStationIndex() {
		return stationIndex;
	}

	/**
	 * Sets the stationIndex. 
	 * @param stationIndex the stationIndex to set 
	 */
	protected void setStationIndex(int stationIndex) {
		waitingProcessing = true;
		this.stationIndex = stationIndex;
	}

	/**
	 * Returns true if the item is currently on a ShipmentProcessStation queue, 
	 * false if the item is still on the ConveyorBelt or if it has left the ShipmentProcessStation queue. 
	 * @return the waitingProcessing 
	 */
	public boolean isWaitingInLineAtStation() {
		return waitingProcessing;
	}
	
	/**
	 * Takes the item out of the waiting queue and completes the processing. 
	 * Sets waitingProcessing to false. 
	 */
	public void removeFromWaitingLine() {
		waitingProcessing = false;
	}
	
	/**
	 * Makes the item to enter the back of the queue for the ShipmentProcessStaion that the item has selected. 
	 * @param station ShipmentProcessStation array 
	 */
	public abstract void getInLine(ShipmentProcessStation[] station);
	
	/**
	 * Returns the color of the item 
	 * @return Color the color of the item 
	 */
	public abstract Color getColor();
	
}
