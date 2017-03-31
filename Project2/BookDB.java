import java.io.File;
import java.util.Scanner;

/**
 * The database of books in the library inventory.
 * @author xellis
 *
 */
public class BookDB {
	
	/**
	 * The list of elements that comprise the books in the library inventory.
	 */
	private MultiPurposeList<Book> books;
	
	/**
	 * Constructs the database from a file. 
	 * Throws an IllegalArgumentException if the file cannot be read.
	 * @param fileName the name of the file
	 */
	public BookDB(String fileName) {
		books = new MultiPurposeList<Book>();
		Scanner fileScanner;
		String line;
		Book book;
		int index = 0;
		try {
			fileScanner = new Scanner(new File(fileName));
			while (fileScanner.hasNext()) {
				line = fileScanner.nextLine().trim();
				if (!line.equals("")) {
					book = new Book(line);
					if (books.isEmpty()) {
						books.addToRear(book);
					} else {
						books.resetIterator();
						while (books.hasNext()) {
							if (book.compareTo(books.next()) > 0) {
								index += 1;
							}
						}
						books.addItem(index, book);
					}
					index = 0;
				}
			}
			fileScanner.close();
		} catch (Exception e) {
			throw new IllegalArgumentException(Constants.EXP_BAD_FILE);
		}
	}
	
	/**
	 * Returns a string corresponding to the books in the database in the proper order. 
	 * Strings for successive books are separated by newlines, including the last book in the list 
	 * (that means the resulting string ends in a newline unless the BookDB is empty). 
	 * The string is appropriate for the display in the Library Inventory area [UC7].
	 * @return a string corresponding to the books in the database in the proper order
	 */
	public String traverse() {
		String s = "";
		if (books.isEmpty()) {
			return s;
		} else {
			books.resetIterator();
			StringBuffer buf = new StringBuffer();
			while (books.hasNext()) {
				buf.append(books.next().toString() + "\n");
			}
			s = buf.toString();
			return s;
		}
	}

	/**
	 * Returns the book at the given position. 
	 * Throws an IndexOutOfBoundsException if the position is out of range (less than 0 or >= size).
	 * @param index the given position
	 * @return the book at the given position
	 */
	public Book findItemAt(int index) {
		if (index < 0 || index >= books.size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		return books.lookAtItemN(index);
	}
	
}
