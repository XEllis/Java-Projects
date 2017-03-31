/**
 * A library patron. 
 * Each patron has a checkedOutQueue and a reserveQueue of books currently checked out and books on reserve.
 * @author xellis
 *
 */
public class Patron extends User {
	
	/** 
	 * The maximum allowable number of books this patron is allowed to check out.
	 */
	private int maxCheckedOut;
	/**
	 * The number of books this patron currently has checked out.
	 */
	private int numCheckedOut;
	/**
	 * Books that the patron currently has checked out. Elements are added to the end of this list.
	 */
	private MultiPurposeList<Book> checkedOut;
	/**
	 * Books in the patron's reserve queue. Elements are added to the end of this list.
	 */
	private MultiPurposeList<Book> reserveQueue;
	
	/**
	 * The first parameter is the patron's id, the next is the password, and the last is the maximum number of books this patron can check out. 
	 * Throws an IllegalArgumentException if the arguments are null or of length 0 after trimming whitespace from if the arguments contain any whitespace after the trim. 
	 * If the maximum number of books is less than 1, an IllegalArugmentException is thrown. 
	 * If the patron's id is "admin" and IllegalArgumentException is thrown.
	 * @param id the patron's id
	 * @param password the patron's password
	 * @param maxCheckedOut the maximum allowable number of books this patron is allowed to check out
	 */
	public Patron(String id, String password, int maxCheckedOut) {
		super(id, password);
		if (id == null || password == null) {
			throw new IllegalArgumentException(Constants.EXP_PATRON_NULL);
		}
		if (id.compareToIgnoreCase(Constants.ADMIN) == 0) {
			throw new IllegalArgumentException(Constants.EXP_PATRON_ADMIN);
		}
		if (maxCheckedOut < 1) {
			throw new IllegalArgumentException(Constants.EXP_PATRON_MAX);
		}
		this.maxCheckedOut = maxCheckedOut;
		numCheckedOut = 0;
		checkedOut = new MultiPurposeList<Book>();
		reserveQueue = new MultiPurposeList<Book>();
	}
	
	/**
	 * Returns a string of books in the reserve queue in the order in which they were reserved. 
	 * Books are shown by info and successive books are separated by newlines, including a trailing newline.
	 * @return a string of books in the reserve queue in the order in which they were reserved
	 */
	public String traverseReserveQueue() {
		String s = "";
		if (reserveQueue.isEmpty()) {
			return s;
		} else {
			reserveQueue.resetIterator();
			StringBuffer buf = new StringBuffer();
			while (reserveQueue.hasNext()) {
				buf.append(reserveQueue.next().getInfo() + "\n");
			}
			s = buf.toString();
			return s;
		}
	}
	
	/**
	 * Returns a string of checked out books in the order in which they were checked out. 
	 * Books are shown by info and successive books are separated by newlines, including a trailing newline.
	 * @return a string of checked out books in the order in which they were checked out
	 */
	public String traverseCheckedOut() {
		String s = "";
		if (checkedOut.isEmpty()) {
			return s;
		} else {
			checkedOut.resetIterator();
			StringBuffer buf = new StringBuffer();
			while (checkedOut.hasNext()) {
				buf.append(checkedOut.next().getInfo() + "\n");
			}
			s = buf.toString();
			return s;
		}
	}
	
	/**
	 * Closes this account and returns all checked out books to the library.
	 */
	public void closeAccount() {
		if (!checkedOut.isEmpty()) {
			checkedOut.resetIterator();
			while (checkedOut.hasNext()) {
				checkedOut.next().backToInventory();
			}
			checkedOut = new MultiPurposeList<Book>();
			reserveQueue = new MultiPurposeList<Book>();
		}
	}
	
	/**
	 * Removes the book in the given position from the checked out list and returns it to the library inventory. 
	 * Throws an IndexOutOfBoundsException if the position is out of bounds.
	 * @param index the index of the book in the list
	 */
	public void returnBook(int index) {
		if (index < 0 || index >= numCheckedOut) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		checkedOut.remove(index).backToInventory();
		numCheckedOut -= 1;
		reserveQueue.resetIterator();
		Book item;
		int position = -1;
		while (reserveQueue.hasNext()) {
			position += 1;
			item = reserveQueue.next();
			if (item.isAvailable()) {
				checkedOut.addToRear(item);
				item.removeOneCopyFromInventory();
				numCheckedOut += 1;
				reserveQueue.remove(position);
				break;
			}
		}
	}
	
	/**
	 * Moves the book in the given position ahead one position in the reserve queue. 
	 * Throws an IndexOutOfBoundsException if the position is out of bounds. 
	 * If the position is 0, there is no exception but there is also no change in the list.
	 * @param index the index of the book in the list
	 */
	public void moveAheadOneInReserves(int index) {
		if (index < 0 || index >= reserveQueue.size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		reserveQueue.moveAheadOne(index);
	}
	
	/**
	 * Removes the book in the given position from the reserve queue. 
	 * Throws an IndexOutOfBoundsException if the position is out of bounds.
	 * @param index the index of the book in the list
	 */
	public void unReserve(int index) {
		if (index < 0 || index >= reserveQueue.size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		reserveQueue.remove(index);
	}
	
	/**
	 * Places the book at the end of the reserve queue. 
	 * Throws an IllegalArgumentException if the argument is null.
	 * @param book 
	 */
	public void reserve(Book book) {
		if (book == null) {
			throw new IllegalArgumentException(Constants.EXP_PATRON_NULL_BOOK);
		}
		if (book.isAvailable() && numCheckedOut < maxCheckedOut) {
			checkedOut.addToRear(book);
			book.removeOneCopyFromInventory();
			numCheckedOut += 1;
		} else {
			reserveQueue.addToRear(book);
		}
	}

}
