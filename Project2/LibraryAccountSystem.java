/**
 * Implements AccountManager.
 * @author xellis
 *
 */
public class LibraryAccountSystem implements AccountManager {
	
	/**
	 * Database of library patrons.
	 */
	private PatronDB patronList;
	/**
	 * The single administrator for the system. This is a static variable.
	 */
	private static Admin adminUser = new Admin();
	/**
	 * The patron currently logged into the system.
	 */
	private Patron currentPatron;
	/**
	 * True if and only if the administrator is logged into the system.
	 */
	private boolean adminLoggedIn;
	/**
	 * True if and only if a patron is logged into the system.
	 */
	private boolean patronLoggedIn;
	
	/**
	 * LibraryAccountSystem constructor.
	 */
	public LibraryAccountSystem() {
		patronList = new PatronDB();
	}

	/**
	 * Logs a user into the system. 
	 * @param id user's id
	 * @param password user's password
	 * @throws IllegalStateException if a patron or the administrator is already logged in
	 * @throws IllegalArgumentException if the patron account does not exist
	 */
	@Override
	public void login(String id, String password) {
		if (adminLoggedIn || patronLoggedIn) {
			throw new IllegalStateException(Constants.EXP_LAS_USER_ALREADY_LOGGED_IN);
		}
		if (id.compareToIgnoreCase(adminUser.getId()) == 0) {
			if (!adminUser.verifyPassword(password)) {
				throw new IllegalArgumentException(Constants.EXP_INCORRECT);
			} else {
				adminLoggedIn = true;
			}
		} else {
			currentPatron = patronList.verifyPatron(id, password);
			patronLoggedIn = true;
		}
	}

	/**
	 * Logs the current patron or administrator out of the system.
	 */
	@Override
	public void logout() {
		if (adminLoggedIn) {
			adminLoggedIn = false;
		}
		if (patronLoggedIn) {
			patronLoggedIn = false;
			currentPatron = null;
		}
	}

	/**
	 * Returns the currently logged in user.
	 * @return the currently logged in user.
	 */
	@Override
	public Patron getCurrentPatron() {
		return currentPatron;
	}

	/**
	 * Is an administrator logged into the system?
	 * @return true if yes, false if no
	 */
	@Override
	public boolean isAdminLoggedIn() {
		return adminLoggedIn;
	}

	/**
	 * Is a patron logged into the system?
	 * @return true if yes, false if no
	 */
	@Override
	public boolean isPatronLoggedIn() {
		return patronLoggedIn;
	}

	/**
	 * Add a new patron to the patron database. The administrator must be logged in.
	 * @param id new patron's id
	 * @param password new patron's password
	 * @param num number associated with this patron
	 * @throws IllegalStateException if the database is full or the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be added to the patron database
	 */
	@Override
	public void addNewPatron(String id, String password, int num) {
		if (!adminLoggedIn) {
			throw new IllegalStateException(Constants.EXP_ACCESS_DENIED);
		}
		patronList.addNewPatron(id, password, num);
	}

	/**
	 * Cancel a patron account. 
	 * @param id patron's id
	 * @throws IllegalStateException if the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be removed due to some error
	 */
	@Override
	public void cancelAccount(String id) {
		if (!adminLoggedIn) {
			throw new IllegalStateException(Constants.EXP_ACCESS_DENIED);
		}
		patronList.cancelAccount(id);
	}
	
	/**
	 * List all patron accounts. 
	 * @return string of patron ids separated by newlines
	 */
	@Override
	public String listAcounts() {
		return patronList.listAccounts();
	}
	
	/**
	 * Nested class for LibraryAccountSystem to create the single administrator for the system.
	 * @author xiaohuizheng
	 *
	 */
	private static class Admin extends User {
		public Admin() {
			super(Constants.ADMIN, Constants.ADMIN);
		}
	}

}
