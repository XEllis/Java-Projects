/**
 * A database of Patrons that provides the list operations required to support the single-patron operations described in AccountManager. 
 * The upper limit of the number of patrons in the database is 20. 
 * PatronDB is implemented as a custom array-based list of Patrons.
 * @author xelllis
 *
 */
public class PatronDB {
	
	/**
	 * The maximum number of patrons the system can support.
	 */
	public static final int MAX_SIZE = 20;
	/**
	 * The number of patrons currently in the system.
	 */
	private int size;
	/**
	 * The list of patrons currently in the system.
	 */
	private Patron[] list;
	
	/**
	 * PatronDB constructor.
	 */
	public PatronDB() {
		list = new Patron[MAX_SIZE];
		size = 0;
	}
	
	/**
	 * Returns the patron in the list whose id matches the first parameter and password matches the second. 
	 * Throws an IllegalArgumentException if the id or password are null or if the password is incorrect for the given id 
	 * or if the patron is not in the database.
	 * @param id the given id
	 * @param password the given password
	 * @return the patron in the list whose id matches the first parameter and password matches the second
	 */
	public Patron verifyPatron(String id, String password) {
		if (id == null || password == null) {
			throw new IllegalArgumentException(Constants.EXP_INCORRECT);
		}
		for (int i = 0; i < size; i++) {
			if (id.compareToIgnoreCase(list[i].getId()) == 0) {
				if (list[i].verifyPassword(password)) {
					return list[i];
				} else {
					throw new IllegalArgumentException(Constants.EXP_INCORRECT);
				}
			}
		}
		throw new IllegalArgumentException(Constants.EXP_INCORRECT);
	}
	
	/**
	 * Used only for testing. Returns a string of ids of patrons in the list in list order. 
	 * Successive ids are separated by newlines, including a trailing newline.
	 * @return a string of ids of patrons in the list in list order
	 */
	public String listAccounts() {
		String s = "";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < size; i++) {
			buf.append(list[i].getId() + "\n");
		}
		s = buf.toString();
		return s;
	}
	
	/**
	 * Adds a new patron to the list, where the id is the first parameter, password is the second, 
	 * and maximum allowed books checked out is the third. 
	 * Throws an IllegalStateException if the database is full. 
	 * Throws an IllegalArgumentException if there is whitespace in the id or password, or if the id or password are empty, 
	 * or if there is already a patron in the database with the same id.
	 * @param id the patron's id to be added
	 * @param password the patron's password to be added
	 * @param maxCheckedOut the maximum allowable number of books this patron to be is allowed to check out
	 */
	public void addNewPatron(String id, String password, int maxCheckedOut) {
		if (size == MAX_SIZE) {
			throw new IllegalStateException(Constants.EXP_PATRON_DB_FULL);
		}
		Patron patron;
		patron = new Patron(id, password, maxCheckedOut);
		if (size == 0) {
			list[size] = patron;
			size += 1;
		} else {
			int index = 0;
			for (int i = 0; i < size; i++) {
				if (patron.compareTo(list[i]) == 0) {
					throw new IllegalArgumentException(Constants.EXP_PATRON_DB_ACCOUNT_EXISTS);
				}
			}
			for (int j = 0; j < size; j++) {
				if (patron.compareTo(list[j]) > 0) {
					index += 1;
				}
			}
			for (int k = size; k > index; k--) {
				list[k] = list[k - 1];
			}
			list[index] = patron;
			size += 1;
		}
	}
	
	/**
	 * Removes the patron with the given id from the list and returns any books that patron has checked out to the inventory. 
	 * Throws an IllegalArgumentException if the account does not exist.
	 *  @param id the given id
	 */
	public void cancelAccount(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException(Constants.EXP_INCORRECT);
		}
		boolean flag = false;
		for (int i = 0; i < size; i++) {
			flag = flag || (id.compareToIgnoreCase(list[i].getId()) == 0);
		}
		if (flag) {
			int index = 0;
			for (int j = 0; j < size; j++) {
				if (id.compareToIgnoreCase(list[j].getId()) == 0) {
					index = j;
				}
			}
			list[index].closeAccount();
			for (int k = index; k < size - 1; k++) {
				list[k] = list[k + 1];
			}
			size -= 1;
		} else {
			throw new IllegalArgumentException(Constants.EXP_INCORRECT);
		}
	}

}
