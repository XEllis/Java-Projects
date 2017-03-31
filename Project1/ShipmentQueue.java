import java.util.LinkedList;

/**
 * Implements a simple queue (first-in, first-out list) of ItemsToShip.
 * @author xellis
 */
public class ShipmentQueue {
	
	/** The underlying queue data structure. */
	private LinkedList<ItemToShip> queue;  
	
	/**
	 * Creates an empty queue.
	 */
	public ShipmentQueue() { 
		queue = new LinkedList<ItemToShip>(); 
	}
	
	/**
	 * Returns the number of elements in the queue.
	 * @return the number of elements
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * Adds a new item to the back of the queue.
	 * @param item the item to add
	 */
	public void add(ItemToShip item){
		queue.addLast(item);
	}
	
	/**
	 * Removes and returns the front item from the queue. Will throw
	 * a NoSuchElementException if the queue is empty.
	 * @return the ItemToShip at the front of the queue
	 */
	public ItemToShip remove() {
		//The call to queue.removeFirst() will throw the NoSuchElementException if
		//the queue is empty.  The exception is allowed to pass through this method.
		return queue.removeFirst();
	}
	
	/**
	 * Gets the front element of the queue without removing it, or null
	 * if the queue is empty.
	 * @return the front element or null if the queue is empty
	 */
	public ItemToShip front() {
		return queue.peek();
	}
	
	/**
	 * Returns true if the queue is empty, false otherwise.
	 * @return true if the queue has no elements
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
