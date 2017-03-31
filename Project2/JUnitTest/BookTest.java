import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Book.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class BookTest {
	
	private Book book1;
	private Book book2;
	private Book book3;

	/**
	 * Sets up the BookTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		book1 = new Book("-1 BOOK1 ");
		book2 = new Book("0 BOOK 2");
		book3 = new Book("1  BOOK3");
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.Book#Book(String)}.
	 */
	@Test
	public void testBook() {
		Book book0 = null;
		String s = null;
		try {
			s = null;
			book0 = new Book(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, book0);
			assertEquals(s, e.getMessage());
		}
		try {
			s = "";
			book0 = new Book(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, book0);
			assertEquals(s, e.getMessage());
		}
		try {
			s = " A 1 ";
			book0 = new Book(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, book0);
			assertEquals(s, e.getMessage());
		}
		try {
			s = "7    ";
			book0 = new Book(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, book0);
			assertEquals(s, e.getMessage());
		}
		assertEquals(false, book1.isAvailable());
		assertEquals(false, book2.isAvailable());
		assertEquals(true, book3.isAvailable());
		assertEquals("BOOK1", book1.getInfo());
		assertEquals("BOOK 2", book2.getInfo());
		assertEquals("BOOK3", book3.getInfo());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.Book#getInfo()}.
	 */
	@Test
	public void testGetInfo() {
		assertEquals("BOOK1", book1.getInfo());
		assertEquals("BOOK 2", book2.getInfo());
		assertEquals("BOOK3", book3.getInfo());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.Book#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("* BOOK1", book1.toString());
		assertEquals("* BOOK 2", book2.toString());
		assertEquals("BOOK3", book3.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.Book#isAvailable()}.
	 */
	@Test
	public void testIsAvailable() {
		assertEquals(false, book1.isAvailable());
		assertEquals(false, book2.isAvailable());
		assertEquals(true, book3.isAvailable());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.Book#backToInventory()}.
	 */
	@Test
	public void testBackToInventory() {
		assertEquals(false, book1.isAvailable());
		assertEquals(false, book2.isAvailable());
		assertEquals(true, book3.isAvailable());
		book1.backToInventory();
		book2.backToInventory();
		book3.backToInventory();
		assertEquals(true, book1.isAvailable());
		assertEquals(true, book2.isAvailable());
		assertEquals(true, book3.isAvailable());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.Book#removeOneCopyFromInventory()}.
	 */
	@Test
	public void testRemoveOneCopyFromInventory() {
		assertEquals(false, book1.isAvailable());
		assertEquals(false, book2.isAvailable());
		assertEquals(true, book3.isAvailable());
		try {
			book1.removeOneCopyFromInventory();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(false, book1.isAvailable());
			assertEquals(Constants.EXP_BOOK_UNAVAILABLE, e.getMessage());
			
		}
		try {
			book2.removeOneCopyFromInventory();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(false, book2.isAvailable());
			assertEquals(Constants.EXP_BOOK_UNAVAILABLE, e.getMessage());
			
		}
		book3.removeOneCopyFromInventory();
		assertEquals(false, book3.isAvailable());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.Book#compareTo(edu.ncsu.csc216.wolf_library.inventory.Book)}.
	 */
	@Test
	public void testCompareTo() {
		Book book0 = null;
		try {
			book1.compareTo(book0);
			fail();
		} catch (NullPointerException e) {
			assertEquals(null, book0);
			assertEquals(Constants.EXP_CANNOT_COMPARE, e.getMessage());
		}
		assertEquals(0, book1.compareTo(book1));
		assertEquals(0, book2.compareTo(book2));
		assertEquals(0, book3.compareTo(book3));
		assertEquals(true, book1.compareTo(book2) > 0);
		assertEquals(true, book2.compareTo(book1) < 0);
		assertEquals(true, book2.compareTo(book3) < 0);
		assertEquals(true, book3.compareTo(book2) > 0);
		assertEquals(true, book1.compareTo(book3) < 0);
		assertEquals(true, book3.compareTo(book1) > 0);
		book0 = new Book("3 A csc book");
		assertEquals(true, book1.compareTo(book0) < 0);
		assertEquals(true, book0.compareTo(book1) > 0);
		book0 = new Book("0 The BOOK 2");
		assertEquals(true, book0.compareTo(book2) == 0);
		assertEquals(true, book2.compareTo(book0) == 0);
		book0 = new Book("-1 An apple");
		assertEquals(true, book3.compareTo(book0) > 0);
		assertEquals(true, book0.compareTo(book3) < 0);
	}

}
