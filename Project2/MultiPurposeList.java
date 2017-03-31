import java.util.NoSuchElementException;

/**
 * A generic class that supports the underlying list operations for the library inventory, reserve queues, and checked out queues. 
 * MultiPurposeList has a nested class named Node and is a custom implementation of a linked list.
 * @author xellis
 *
 * @param <T> Element type
 */
public class MultiPurposeList<T> {
	
	private Node head;
	private Node iterator;
	
	/**
	 * Constructs an empty list.
	 */
	public MultiPurposeList() {
		head = null;
	}
	
	/**
	 * Sets iterator to point to the first element in the list.
	 */
	public void resetIterator() {
		iterator = head;
	}
	
	/**
	 * True whenever iterator is pointing to a list element.
	 * @return whenever iterator is pointing to a list element
	 */
	public boolean hasNext() {
		return iterator != null;
	}
	
	/**
	 * Returns the element iterator is pointing to and moves iterator to point to the next element in the list. 
	 * Throws a NoSuchElementException if iterator is null or not pointing to a list element.
	 * @return the element iterator is pointing to
	 */
	public T next() {
		if (iterator == null) {
			throw new NoSuchElementException(Constants.EXP_NO_MORE_VALUES_IN_LIST);
		}
		T item = iterator.data;
		iterator = iterator.link;
		return item;
	}
	
	/**
	 * Adds an element (second parameter) at the given position. 
	 * Throws a NullPointerException if the item is null. 
	 * Throws an IndexOutOfBoundsException if the position is negative or greater than the list size.
	 * @param index the index of the element in the list
	 * @param item the element to be added
	 */
	public void addItem(int index, T item) {
		if (item == null) {
			throw new NullPointerException(Constants.EXP_LIST_ITEM_NULL);
		} 
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		if (index == 0) {
			head = new Node(item, head);
		} else {
			Node current = head;
			while (index > 1) {
				current = current.link;
				index -= 1;
			}
			current.link = new Node(item, current.link);
		}
		resetIterator();
	}
	
	/**
	 *  Returns true if the list contains no elements.
	 * @return true if the list contains no elements
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * Returns the element at the given position. 
	 * Throws an IndexOutOfBoundsException if the position is negative or greater than or equal to size.
	 * @param index the index of the element in the list
	 * @return the element at the given position
	 */
	public T lookAtItemN(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		T item;
		Node current = head;
		while (index > 0) {
			current = current.link;
			index -= 1;
		}
		item = current.data;
		return item;
	}
	
	/**
	 *  Adds an element to the rear of the list. 
	 *  Throws a NullPointerException if the item is null.
	 * @param item the element to be added
	 */
	public void addToRear(T item) {
		if (item == null) {
			throw new NullPointerException(Constants.EXP_LIST_ITEM_NULL);
		}
		addItem(size(), item);
	}
	
	/**
	 * Removes and returns the element in the given position. 
	 * Throws an IndexOutOfBoundsException if the position is negative or greater than or equal to size.
	 * @param index the index of the element in the list
	 * @return the element in the given position
	 */
	public T remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		T item;
		Node current = head;
		Node previous = null;
		while (index > 0) {
			previous = current;
			current = current.link;
			index -= 1;
		}
		item = current.data;
		if (current == head) {
			head = head.link;
		} else {
			previous.link = current.link;
		}
		resetIterator();
		return item;
	}
	
	/**
	 * Moves the element at the given position ahead one position in the list. 
	 * Does nothing if the element is already at the front of the list. 
	 * Throws an IndexOutOfBoundsException if the position is negative or greater than or equal to size.
	 * @param index the given position
	 */
	public void moveAheadOne(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		if (index != 0) {
			Node current = head;
			Node previous = null;
			while (index > 1) {
				previous = current;
				current = current.link;
				index -= 1;
			}
			T item;
			if (current == head) {
				item = head.link.data;
				head.link = head.link.link;
				head = new Node(item, head);
			} else {
				item = current.link.data;
				current.link = current.link.link;
				previous.link = new Node(item, current);	
			}
			resetIterator();
		}
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return the number of elements in the list
	 */
	public int size() {
		int size = 0;
		Node current = head;
		while(current != null) {
			size += 1;
			current = current.link;
		}
		return size;
	}
	
	/**
	 * Nested class for MultiPurposeList to create linked list.
	 * @author xiaohuizheng
	 *
	 */
	private class Node {
		public T data;
		public Node link;
		
		public Node(T data, Node link) {
			this.data = data;
			this.link = link;
		}
	}
	
}
