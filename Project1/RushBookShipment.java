import java.awt.Color;

/**
 * Creates RushBookShipment with an arrivalTime and a processTime. 
 * @author xellis
 *
 */
public class RushBookShipment extends ItemToShip {
	
	/**
	 * Color of the RushBookShipment. 
	 */
	private Color color = Color.BLACK;
	
	/**
	 * The minimum index of the ShipmentProcessStation that the RushBookShipment can select. 
	 */
	private static final int MIN_STATION_INDEX = 0;
	
	/**
	 * Creates RushBookShipment with an arrivalTime and a processTime. 
	 * @param arrivalTime time when the item enters a shipment process station line 
	 * @param processTime number of seconds required to process this item 
	 */
	public RushBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
	}
	
	/**
	 * Makes the RushBookShipment to enter the back of the queue for the ShipmentProcessStaion 
	 * that the RushBookShipment has selected. 
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
	 * Returns the color of the RushBookShipment. 
	 * @return Color the color of the RushBookShipment 
	 */
	@Override
	public Color getColor() {
		return color;
	}
	
}
