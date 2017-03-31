/**
 * Abstract class for system users (patrons or admin).
 * @author xellis
 *
 */
public abstract class User {
	
	/**
	 * The user's id.
	 */
	private String id;
	/**
	 * The hashCode() of the password string.
	 */
	private int password;
	
	/**
	 * Constructor uses the first parameter for the id and the second for the password. 
	 * Throws an IllegalArgumentException if the arguments are null or of length 0 after trimming whitespace from the ends 
	 * or if the arguments contain any whitespace after the trim.
	 * @param id the user's id
	 * @param password the user's password
	 */
	public User(String id, String password) {
		if (id == null || password == null) {
			throw new IllegalArgumentException(Constants.EXP_PATRON_NULL);
		}
		if (id.trim().equals("") || password.trim().equals("")){
			throw new IllegalArgumentException(Constants.EXP_PATRON_EMPTY);
		}
		for (int i = 0; i < id.trim().length(); i++) {
			if (Character.isWhitespace(id.trim().charAt(i))) {
				throw new IllegalArgumentException(Constants.EXP_PATRON_WHITESPACE);
			}
		}
		for (int j = 0; j < password.trim().length(); j++) {
			if (Character.isWhitespace(password.trim().charAt(j))) {
				throw new IllegalArgumentException(Constants.EXP_PATRON_WHITESPACE);
			}
		}
		this.id = id.trim();
		this.password = password.trim().hashCode();
	}
	
	/**
	 * Returns true if the hashCode() of the parameter matches the password 
	 * (which is stored as a hashCode() of the password string).
	 * @param password the password string
	 * @return true if the hashCode() of the parameter matches the password
	 */
	public boolean verifyPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password == password.hashCode();
	}
	
	/**
	 * Returns the user's id.
	 * @return the user's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Case-insensitive comparison of two users by id
	 * @param user system user
	 * @return the value 0 if the argument's id string is equal to this user's id string; 
	 * a value less than 0 if this user's id string is lexicographically less than the argument's id string; 
	 * and a value greater than 0 if this user's id string is lexicographically greater than the argument's id string.
	 */
	public int compareTo(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		return id.compareToIgnoreCase(user.id);
	}
	
}
