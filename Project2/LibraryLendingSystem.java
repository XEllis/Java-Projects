/**
 * Implements LendingManager.
 * @author xellis
 *
 */
public class LibraryLendingSystem implements LendingManager {
	
	/**
	 * The patron account part of the system.
	 */
	private AccountManager accounts;
	/**
	 * The database of books in the system.
	 */
	private BookDB bookInventory;
	
	/**
	 * Constructs the LibraryLendingSystem from a file. 
	 * Throws an IllegalArgumentException if the file cannot be read.
	 * @param fileName the name of the file
	 */
	public LibraryLendingSystem(String fileName) {
		bookInventory = new BookDB(fileName);
		accounts = new LibraryAccountSystem();
	}

	/**
	 * Traverse all items in the inventory.
	 * @return the string representing the items in the inventory
	 */
	@Override
	public String showInventory() {
		return bookInventory.traverse();
	}

	/**
	 * Set the user for the current context to a given value.
	 * @param id user's id
	 * @param password user's password
	 * @throws IllegalStateException if a patron or the admin is already logged in
	 * @throws IllegalArgumentException if the patron account does not exist
	 */
	@Override
	public void login(String id, String password) {
		accounts.login(id, password);
	}

	/**
	 * Logs the current user out of the system.
	 */
	@Override
	public void logout() {
		accounts.logout();
	}

	/**
	 * Add a new account to the patron database. The administrator must be logged in.
	 * @param id new patron's id
	 * @param password new patron's password
	 * @param num number/max limit associated with this patron
	 * @throws IllegalStateException if the database is full or the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be added to the patron database
	 */
	@Override
	public void addNewPatron(String id, String password, int num) {
		accounts.addNewPatron(id, password, num);
	}

	/**
	 * Returns the current user's id.  If there is no user logged in, an
	 * empty string is returned.
	 * @return the current user's id.
	 */
	@Override
	public String getCurrentUserId() {
		if (!accounts.isAdminLoggedIn() && !accounts.isPatronLoggedIn()) {
			return "";
		}
		if (accounts.isAdminLoggedIn()) {
			return Constants.ADMIN;
		}
		return accounts.getCurrentPatron().getId();
	}

	/**
	 * Cancel a patron account. 
	 * @param id patron's id
	 * @throws IllegalStateException if the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be removed due to some error
	 */
	@Override
	public void cancelAccount(String id) {
		accounts.cancelAccount(id);
	}

	/**
	 * Reserve the selected item for the reserve queue. 
	 * @param position position of the selected item in the inventory
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	@Override
	public void reserveItem(int position) {
		if (!accounts.isPatronLoggedIn()) {
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		accounts.getCurrentPatron().reserve(bookInventory.findItemAt(position));	
	}
	
	/**
	 * Move the item in the given position up 1 in the reserve queue. 
	 * @param position current position of item to move up one
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	@Override
	public void reserveMoveAheadOne(int position) {
		if (!accounts.isPatronLoggedIn()) {
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		accounts.getCurrentPatron().moveAheadOneInReserves(position);
	}

	/**
	 * Remove the item in the given position from the reserve queue.
	 * @param position position of the item in the queue
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	@Override
	public void removeSelectedFromReserves(int position) {
		if (!accounts.isPatronLoggedIn()) {
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		accounts.getCurrentPatron().unReserve(position);
	}

	/**
	 * Traverse all items in the reserve queue.
	 * @return string representation of items in the queue
	 * @throws IllegalStateException if no patron is logged in
	 */
	@Override
	public String traverseReserveQueue() {
		if (!accounts.isPatronLoggedIn()) {
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		return accounts.getCurrentPatron().traverseReserveQueue();
	}

	/**
	 * Traverse all items in the list of items checked out.
	 * @return string representation of checked out items
	 * @throws IllegalStateException if no patron is logged in
	 */
	@Override
	public String traverseCheckedOut() {
		if (!accounts.isPatronLoggedIn()) {
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		return accounts.getCurrentPatron().traverseCheckedOut();
	}

	/**
	 * Return the selected item to the inventory.
	 * @param position location in the list of items checked out of the item to return
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	@Override
	public void returnItem(int position) {
		if (!accounts.isPatronLoggedIn()) {
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		accounts.getCurrentPatron().returnBook(position);
	}

}
