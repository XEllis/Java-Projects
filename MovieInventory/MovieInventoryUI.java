import java.util.Scanner;

/**
 * MovieInventoryUI Object contains a MovieInventory and a Scanner for reading console input. 
 * Continuously prompt user for entry. 
 * Perform 4 different options according to user input. 
 * 1) List the movies in the collection. 
 * 2) Add a Movie to the collection. 
 * 3) Remove a Movie from the collection. 
 * 4) Quit the program. 
 * @author xellis
 *
 */
public class MovieInventoryUI {
	
	/**
	 * MovieInventory 
	 */
	private MovieInventory inventory;
	/**
	 * Scanner for reading console input 
	 */
	private Scanner console;
	/**
	 * Construct a MovieInventoryUI object that contains a MovieInventory and a Scanner for reading console input. 
	 */
	public MovieInventoryUI() {
		inventory = new MovieInventory();
		console = new Scanner(System.in);
	}
	/**
	 * Control the looping of the user interface. 
	 */
	private void userInterface() {
		int entry = promptForEntry();
		while (entry != 4) {
			if (entry == 1) {
				listMovies();
			} else if (entry == 2) {
				addMovie();
			} else {
				removeMovieByTitle();
			}
			entry = promptForEntry();
		} 
		System.exit (0);
	}
	/**
	 * Prompt user for entry. 
	 * If user enters an integer outside of the range 1 to 4 inclusively 
	 * or if the user enters a non-integer, 
	 * the program re-prompts user for entry until user enters an integer from 1 to 4 inclusively. 
	 * @return integer number from 1 to 4 inclusively 
	 */
	private int promptForEntry() {
		int entry;
		menu();
		while (true) {
			if (!console.hasNextInt()) {
				console.nextLine();
				System.out.println("Invalid command.");
				menu();
			} else {
				int num = console.nextInt();
				if (num < 1 || num > 4) {
					console.nextLine();
					System.out.println("Invalid command.");
					menu();
				} else {
					entry = num;
					console.nextLine();
					break;
				}
			}
		}
		return entry;
	}
	/**
	 * Display a menu with four options. 
	 */
	private void menu() {
		System.out.println("\nMovieInventory Menu\n1. List Movies\n2. Add Movie\n3. Remove Movie by Title\n4. Quit\n\nEntry? ");
	}
	/**
	 * List the movies in the inventory collection. 
	 */
	private void listMovies() {
		System.out.println(inventory);
	}
	/**
	 * UI functionality for adding a Movie.
	 */
	private void addMovie() {
		System.out.println("\nTitle? ");
		String title = console.nextLine();
		
		System.out.println("Release Year? ");
		while (!console.hasNextInt()) {
			console.nextLine();
			System.out.println("Invalid release year.");
			System.out.println("Release Year? ");
		}
		int releaseYear = console.nextInt();
		console.nextLine(); // throw away rest of line
		
		System.out.println("Genre? ");
		String genre = console.nextLine();
		
		System.out.println("Rating? ");
		String rating = console.nextLine();
		
		try {
			Movie m = new Movie(title, releaseYear, genre, rating);
			if (inventory.addMovie(m)) {
				System.out.println("Movie added to the collection.");
			} else {
				System.out.println("Movie cannot be added to the collection.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid release year.");
		}
	}
	/**
	 * UI functionality for removing a Movie.
	 */
	private void removeMovieByTitle() {
		System.out.println("Title to remove? ");
		String title = console.nextLine();
		if (inventory.removeMovieByTitle(title)) {
			System.out.println("Movie removed from the collection.");
		} else {
			System.out.println("Movie cannot be removed from the collection.");
		}
	}
	/**
	 * Continuously prompt user for entry. 
	 * Perform 4 different options according to user input. 
	 * 1) List the movies in the collection. 
     * 2) Add a Movie to the collection. 
     * 3) Remove a Movie from the collection. 
     * 4) Quit the program. 
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		MovieInventoryUI ui = new MovieInventoryUI();
		ui.userInterface();
	}
	
}
