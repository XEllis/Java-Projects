import java.util.Scanner;

/**
 * A library book.
 * @author xellis
 *
 */
public class Book {
	
	/**
	 * The book information.
	 */
	private String info;
	/**
	 * The number of book in stock.
	 */
	private int numAvailable;
	
	/**
	 * Constructs a Book from a string of the format <number-in-stock><whitespace><book-info>. 
	 * This constructor throws an IllegalArgumentException if the string argument is not valid 
	 * (exception should be thrown if the number-in-stock cannot be read or if the book-info is an empty string after trimming white space). 
	 * If <number-in-stock> is negative, this constructor sets it to 0.
	 * @param info the book information
	 */
	public Book(String info) {
		if (info == null || info.equals("")) {
			throw new IllegalArgumentException(info);
		}
		Scanner scanner = new Scanner(info);
		if (!scanner.hasNextInt()) {
			scanner.close();
			throw new IllegalArgumentException(info);
		} else {
			numAvailable = scanner.nextInt();
			if (numAvailable < 0) {
				numAvailable = 0;
			}
			if (!scanner.hasNext()) {
				numAvailable = 0;
				scanner.close();
				throw new IllegalArgumentException(info);
			} else {
				this.info = scanner.nextLine().trim();
				scanner.close();
			}
		}
	}

	/**
	 * Returns the book information (title and author as they appear in the input file).
	 * @return the book information
	 */
	public String getInfo() {
		return info;
	}
	
	/**
	 * Returns the book info, prepended by "* " if there are no copies of the book currently in the library inventory.
	 * @return the book info
	 */
	@Override
	public String toString() {
		if (numAvailable == 0) {
			return Constants.CURRENTLY_UNAVAILABLE + info;
		}
		return info;
	}

	/**
	 * Returns whether there are copies of this book in stock in the inventory.
	 * @return whether there are copies of this book in stock in the inventory
	 */
	public boolean isAvailable() {
		return numAvailable != 0;
	}
	
	/**
	 * Puts a copy of the book back into the inventory stock.
	 */
	public void backToInventory() {
		numAvailable += 1;
	}
	
	/**
	 * Removes a copy of the book from the inventory stock. 
	 * Throws an IllegalStateException if there are no copies of the book currently in the inventory.
	 */
	public void removeOneCopyFromInventory() {
		if (numAvailable == 0) {
			throw new IllegalStateException(Constants.EXP_BOOK_UNAVAILABLE);
		}
		numAvailable -= 1;
	}
	
	/**
	 * Compares info of two books to determine their order in the inventory. 
	 * The comparison is case-insensitive and ignores "A", "An", and "The" leading tokens. 
	 * Throws a NullPointerException upon comparison to null.
	 * @param book the book to be compared to
	 * @return the value 0 if the argument's info string is equal to this book's info string; 
	 * a value less than 0 if this book's info string is lexicographically less than the argument's info string; 
	 * and a value greater than 0 if this book's info string is lexicographically greater than the argument's info string.
	 */
	public int compareTo(Book book) {
		if (book == null) {
			throw new NullPointerException(Constants.EXP_CANNOT_COMPARE);
		}
		String info1 = info;
		String info2 = book.getInfo();
		if (info1.startsWith("A") || info1.startsWith("An") || info1.startsWith("The")) {
			Scanner scanner1 = new Scanner(info1);
			scanner1.next();
			info1 = scanner1.nextLine().trim();
			scanner1.close();
		}
		if (info2.startsWith("A") || info2.startsWith("An") || info2.startsWith("The")) {
			Scanner scanner2 = new Scanner(info2);
			scanner2.next();
			info2 = scanner2.nextLine().trim();
			scanner2.close();
		}
		return info1.compareToIgnoreCase(info2);
	}
	
}
