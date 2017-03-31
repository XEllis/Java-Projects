/**
 * MovieInventory Object contains at most 10 Movies. 
 * User can add a Movie to the inventory if there is space and it is not there already. 
 * User can remove a Movie from the inventory if the title of the Movie is there. 
 * User can also display the list of Movies in the inventory. 
 * @author xellis
 *
 */
public class MovieInventory {
	
	/**
	 * MovieInventory size 
	 */
	private static final int INVENTORY_SIZE = 10;
	/**
	 * Movie list 
	 */
	private Movie [] movies;
	/**
	 * Create an empty Movie list 
	 */
	public MovieInventory() {
		movies = new Movie [INVENTORY_SIZE];
	}
	/**
	 * Return the String representation of the MovieInventory. 
	 * @return String representation of the MovieInventory 
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Movie Inventory: \n");
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			buf.append(i + 1);
			buf.append(". ");
			if (movies[i] == null) {
				buf.append("Empty");
			} else {
				buf.append(movies[i]);
			}
			buf.append("\n");
		}
		String s = buf.toString();
		return s;
	}
	/**
	 * Return true if the Movie can be added to the inventory. 
	 * If the Movie is a duplicate or if there is no more space, 
	 * the method returns false. 
	 * @param m Movie to add to the inventory 
	 * @return true if the Movie can be added to the inventory 
	 */
	public boolean addMovie(Movie m) {
		//Check for duplicate movies
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null && movies[i].equals(m)) {
				return false; // movie already exists
			}
		}
		boolean added = false; //flag
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] == null) { //the movie slot is empty
				movies[i] = m;
				added = true;
				break; //if we added, break out of the loop
			}
		}
		return added; //return the flag
	}
	/**
	 * Return true if the Movie can be removed from the inventory. 
	 * If the title of the Movie does not exist in the inventory, 
	 * the method returns false. 
	 * @param title title of the Movie to remove from the inventory 
	 * @return true if the Movie can be removed from the inventory 
	 */
	public boolean removeMovieByTitle(String title) {
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null && movies[i].getTitle().equals(title)) {
				movies[i] = null;
				return true;
			}
		}
		return false;
	}
	
}
