/**
 * A calendar that determines the order in which items leave their queues.
 * The conveyor belt is a queue, as are the wait lines at processing stations.
 * An event corresponds to an item leaving its queue.
 * 
 * @author xellis
 * @version 3.0
 * @see ConveyorBelt
 * @see ShipmentProcessStation
 */
public class EventCalendar {

	/** Collection of process stations for the simulation */
	private LineOfItems[] station;
	/** Queue of items that feed the process stations */
	private LineOfItems factoryLine;

	/**
	 * Initializes the queues in the EventCalendar.
	 * 
	 * @param station
	 *            all processing stations
	 * @param factoryLine
	 *            holds items coming from the factory
	 */
	public EventCalendar(LineOfItems[] station,
			LineOfItems factoryLine) {
		this.station = station;
		this.factoryLine = factoryLine;
	}

	/**
	 * Determines which station line or line of items from the factory
	 * contains the next item to be processed. 
	 * 
	 * @return The line whose front item has the earliest departure time.
	 * @throws IllegalStateException if all lines are empty.
	 */
	public LineOfItems nextToBeProcessed() {
		// Time the next item leaves a queue
		int nextFromConveyorBelt = factoryLine.departTimeNext();
		int soonest = 0;
		for (int k = 1; k < station.length; k++)
			if (station[k].departTimeNext() < station[soonest].departTimeNext())
				soonest = k;
		int departureFromStationLine = station[soonest].departTimeNext();

		// Are all queues empty?
		if (nextFromConveyorBelt == Integer.MAX_VALUE
				&& departureFromStationLine == Integer.MAX_VALUE) {
			throw new IllegalStateException();
		}

		// Is the next event a package leaving the conveyer belt?
		if (nextFromConveyorBelt <= departureFromStationLine) {
			return factoryLine;
		}
		
		// The next event is a package leaving a processing station.
		return station[soonest];
	}
}
