import java.awt.Color;

/**
 * Creates RegularBookShipment with an arrivalTime and a processTime. 
 * @author xellis
 *
 */
public class RegularBookShipment extends ItemToShip {
	
	/**
	 * Color of the RegularBookShipment. 
	 */
	private Color color = Color.BLUE;
	
	/**
	 * The minimum index of the ShipmentProcessStation that the RegularBookShipment can select. 
	 */
	private static final int MIN_STATION_INDEX = 1;
	
	/**
	 * Creates RegularBookShipment with an arrivalTime and a processTime. 
	 * @param arrivalTime time when the item enters a shipment process station line 
	 * @param processTime number of seconds required to process this item 
	 */
	public RegularBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
	}
	
	/**
	 * Makes the RegularBookShipment to enter the back of the queue for the ShipmentProcessStaion 
	 * that the RegularBookShipment has selected. 
	 * @param station ShipmentProcessStation array 
	 */
	@Override
	public void getInLine(ShipmentProcessStation[] station) {
		int stationIndex = INITIAL_STATION_IDX;
		int size = Integer.MAX_VALUE;
		for (int i = MIN_STATION_INDEX; i < station.length; i++) {
			if (station[i].size() < size) {
				stationIndex = i;
				size = station[i].size();
			}
		}
		setStationIndex(stationIndex);
		station[stationIndex].addItemToLine(this);
	}
	
	/**
	 * Returns the color of the RegularBookShipment. 
	 * @return Color the color of the RegularBookShipment 
	 */
	@Override
	public Color getColor() {
		return color;
	}
	
}
