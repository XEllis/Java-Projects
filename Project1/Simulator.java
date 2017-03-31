import java.awt.Color;

/**
 * Runs the simulations step-by-step. Steps correspond to events: 
 * there is a step whenever an item leaves the conveyor belt to enter the line at a shipment process station, 
 * and there is a step whenever an item completes its processing and leaves its shipment process station. 
 * Reports on the results of each step as it goes along. 
 * @author xellis
 *
 */
public class Simulator {
	
	/**
	 * The minimum number of shipment process stations. 
	 */
	public static final int MIN_NUM_STATIONS = 3;
	
	/**
	 * The maximum number of shipment process stations. 
	 */
	public static final int MAX_NUM_STATIONS = 9;
	
	/**
	 * The number of shipment process stations. 
	 */
	private int numStations;
	
	/**
	 * The number of items. 
	 */
	private int numShipments;
	
	/**
	 * The number of steps taken so far. 
	 */
	private int stepsTaken;
	
	/**
	 * The item currently being handled by the simulator. 
	 */
	private ItemToShip currentShipment;
	
	/**
	 * The ConveyorBelt. 
	 */
	private ConveyorBelt theBelt;
	
	/**
	 * The array of ShipmentProcessStation. 
	 */
	private ShipmentProcessStation[] station;
	
	/**
	 * The EventCalendar to control the order of events. 
	 */
	private EventCalendar myCalendar;
	
	/**
	 * The Log to keep track of the shipping statistics. 
	 */
	private Log myLog;
	
	/**
	 * Creates a ConveyorBelt and an array of ShipmentProcessStations. 
	 * Creates the Log to keep track of the shipping statistics and 
	 * an EventCalendar to control the order of events. 
	 * @param numStations the number of shipment process stations 
	 * @param numShipments the number of items 
	 */
	public Simulator(int numStations, int numShipments) {
		if (numStations > MAX_NUM_STATIONS || numStations < MIN_NUM_STATIONS || numShipments < 1) {
			throw new IllegalArgumentException();
		}
		this.numStations = numStations;
		this.numShipments = numShipments;
		myLog = new Log();
		station = new ShipmentProcessStation[this.numStations];
		for (int i = 0; i < this.numStations; i++) {
			station[i] = new ShipmentProcessStation(myLog);
		}
		theBelt = new ConveyorBelt(this.numShipments, station);
		myCalendar = new EventCalendar(station, theBelt);
	}
	
	/**
	 * Handles the next event from the EventCalendar. 
	 */
	public void step() {
		currentShipment = null;
		LineOfItems nextEvent = myCalendar.nextToBeProcessed();
		currentShipment = nextEvent.processNext();
		stepsTaken += 1;
	}
	
	/**
	 * Returns number of steps taken so far. 
	 * @return number of steps taken so far 
	 */
	public int getStepsTaken() {
		return stepsTaken;
	}
	
	/**
	 * Returns total number of steps in the simulation. 
	 * @return total number of steps in the simulation 
	 */
	public int totalNumberOfSteps() {
		return 2 * numShipments;
	}
	
	/**
	 * Returns true if the simulation has not yet finished, false if it has. 
	 * @return true if the simulation has not yet finished, false if it has. 
	 */
	public boolean moreSteps() {
		if (stepsTaken < totalNumberOfSteps()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns index of ShipmentProcessStation selected by the currentShipment, 
	 * or -1 if currentShipment is null. 
	 * @return index of ShipmentProcessStation selected by the currentShipment, 
	 * or -1 if currentShipment is null. 
	 */
	public int getCurrentIndex() {
		if (currentShipment == null) {
			return ItemToShip.INITIAL_STATION_IDX;
		}
		return currentShipment.getStationIndex();
	}
	
	/**
	 * Returns Color of the currentShipment or null if currentShipment is null. 
	 * @return Color the color of the currentShipment or null if currentShipment is null 
	 */
	public Color getCurrentShipmentColor() {
		if (currentShipment == null) {
			return null;
		}
		return currentShipment.getColor();
	}
	
	/**
	 * Returns true if the most recently handled item completed processing and left a shipment process station line, 
	 * false if the most recently handled item left the conveyor belt to join a shipment process station line or if there is no current item. 
	 * @return true if the most recently handled item completed processing and left a shipment process station line, 
	 * false if the most recently handled item left the conveyor belt to join a shipment process station line or if there is no current item. 
	 */
	public boolean itemLeftSimulation() {
		if (currentShipment == null || currentShipment.isWaitingInLineAtStation()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns average number of seconds items had to wait in a shipment process station line 
	 * prior to their actual processing. 
	 * @return average number of seconds items had to wait in a shipment process station line 
	 * prior to their actual processing. 
	 */
	public double averageWaitTime() {
		return myLog.averageWaitTime();
	}
	
	/**
	 * Returns average number of seconds items spent in actual processing. 
	 * @return average number of seconds items spent in actual processing. 
	 */
	public double averageProcessTime() {
		return myLog.averageProcessTime();
	}

}
