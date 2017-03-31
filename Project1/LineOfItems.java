/**
 * Operations for a line of items to be shipped.
 * @author xellis
 * @version 2.1
 */
public interface LineOfItems {

	/**
	 * Does the line have more items?
	 * @return true if the line has more items
	 */
	boolean hasNext();

	/**
	 * Processes the item at the front of the line, removing it from the line.
	 * @return the item that was just processed
	 */
	ItemToShip processNext();

	/**
	 * Determines the time that the next item in line will leave the line.
	 * If the line is empty, that time is Integer.MAX_VALUE.
	 * @return the departure time of the next item in the line or Integer.MAX_VALUE if the
	 * line is empty.
	 */
	int departTimeNext();

	/**
	 * Determines the number of items in line.
	 * @return the number items in line
	 */
	int size();
}
