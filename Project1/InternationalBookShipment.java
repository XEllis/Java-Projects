import java.awt.Color;

/**
 * Creates InternationalBookShipment with an arrivalTime and a processTime. 
 * @author xellis
 *
 */
public class InternationalBookShipment extends ItemToShip {
	
	/**
	 * Color of the InternationalBookShipment. 
	 */
	private Color color = Color.RED;
	
	/**
	 * The percentage of the total number of the ShipmentProcessStation 
	 * that the InternationalBookShipment can select. 
	 */
	private static final double INTERNATIONAL_STATIONS_PERCENT = 0.25;
	
	/**
	 * Creates InternationalBookShipment with an arrivalTime and a processTime. 
	 * @param arrivalTime time when the item enters a shipment process station line 
	 * @param processTime number of seconds required to process this item 
	 */
	public InternationalBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
	}
	
	/**
	 * Makes the InternationalBookShipment to enter the back of the queue for the ShipmentProcessStaion 
	 * that the InternationalBookShipment has selected. 
	 * @param station ShipmentProcessStation array 
	 */
	@Override
	public void getInLine(ShipmentProcessStation[] station) {
		int stationIndex = INITIAL_STATION_IDX;
		int size = Integer.MAX_VALUE;
		int numberOfInternationalStaions = (int) Math.ceil(INTERNATIONAL_STATIONS_PERCENT * station.length);
		int minStationIndex = station.length - numberOfInternationalStaions;
		for (int i = minStationIndex; i < station.length; i++) {
			if (station[i].size() < size) {
				stationIndex = i;
				size = station[i].size();
			}
		}
		setStationIndex(stationIndex);
		station[stationIndex].addItemToLine(this);
	}
	
	/**
	 * Returns the color of the InternationalBookShipment. 
	 * @return Color the color of the InternationalBookShipment 
	 */
	@Override
	public Color getColor() {
		return color;
	}
	
}
