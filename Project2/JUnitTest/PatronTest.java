import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Patron.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class PatronTest {
	
	private Patron patron1;
	private Patron patron2;
	private Patron patron3;
	private Book book0;
	private Book book1;
	private Book book2;
	private Book book3;
	
	/**
	 * Sets up the PatronTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		patron1 = new Patron("patron1", "patron1", 1);
		patron2 = new Patron("patron2", "patron2", 2);
		patron3 = new Patron("patron3", "patron3", 3);
		book0 = new Book("0 book0");
		book1 = new Book("1 book1");
		book2 = new Book("2 book2");
		book3 = new Book("3 book3");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#Patron(String, String, int)}.
	 */
	@Test
	public void testPatron(){
		Patron patron0 = null;
		String s = null;
		try {
			patron0 = new Patron(s, "password", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_NULL, e.getMessage());
		}
		try {
			patron0 = new Patron("id", s, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_NULL, e.getMessage());
		}
		try {
			patron0 = new Patron(" ", "password", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_EMPTY, e.getMessage());
		}
		try {
			patron0 = new Patron("id", "", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_EMPTY, e.getMessage());
		}
		try {
			patron0 = new Patron("i d", "password", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_WHITESPACE, e.getMessage());
		}
		try {
			patron0 = new Patron("id", "pass word", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_WHITESPACE, e.getMessage());
		}
		try {
			patron0 = new Patron("i\td", "password", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_WHITESPACE, e.getMessage());
		}
		try {
			patron0 = new Patron("id", "pass\tword", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_WHITESPACE, e.getMessage());
		}
		try {
			patron0 = new Patron("admin", "password", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_ADMIN, e.getMessage());
		}
		try {
			patron0 = new Patron("id", "password", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, patron0);
			assertEquals(Constants.EXP_PATRON_MAX, e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#traverseReserveQueue()}.
	 */
	@Test
	public void testTraverseReserveQueue() {
		patron1.reserve(book0);
		patron1.reserve(book1);
		patron1.reserve(book2);
		patron1.reserve(book3);
		assertEquals("book0\nbook2\nbook3\n", patron1.traverseReserveQueue());
		assertEquals(false, book1.isAvailable());
		patron2.reserve(book0);
		patron2.reserve(book1);
		patron2.reserve(book2);
		patron2.reserve(book3);
		assertEquals("book0\nbook1\n", patron2.traverseReserveQueue());
		patron3.reserve(book0);
		patron3.reserve(book1);
		patron3.reserve(book2);
		patron3.reserve(book3);
		assertEquals("book0\nbook1\n", patron3.traverseReserveQueue());
		assertEquals(false, book2.isAvailable());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#traverseCheckedOut()}.
	 */
	@Test
	public void testTraverseCheckedOut() {
		patron1.reserve(book0);
		patron1.reserve(book1);
		patron1.reserve(book2);
		patron1.reserve(book3);
		assertEquals("book1\n", patron1.traverseCheckedOut());
		assertEquals(false, book1.isAvailable());
		patron2.reserve(book0);
		patron2.reserve(book1);
		patron2.reserve(book2);
		patron2.reserve(book3);
		assertEquals("book2\nbook3\n", patron2.traverseCheckedOut());
		patron3.reserve(book0);
		patron3.reserve(book1);
		patron3.reserve(book2);
		patron3.reserve(book3);
		assertEquals("book2\nbook3\n", patron3.traverseCheckedOut());
		assertEquals(false, book2.isAvailable());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#closeAccount()}.
	 */
	@Test
	public void testCloseAccount() {
		patron1.closeAccount();
		assertEquals("", patron1.traverseCheckedOut());
		assertEquals("", patron1.traverseReserveQueue());
		
		patron1.reserve(book0);
		patron1.reserve(book1);
		patron1.reserve(book2);
		patron1.reserve(book3);
		assertEquals("book1\n", patron1.traverseCheckedOut());
		assertEquals("book0\nbook2\nbook3\n", patron1.traverseReserveQueue());
		patron1.closeAccount();
		assertEquals("", patron1.traverseCheckedOut());
		assertEquals("", patron1.traverseReserveQueue());
		
		patron2.reserve(book0);
		patron2.reserve(book1);
		patron2.reserve(book2);
		patron2.reserve(book3);
		assertEquals("book1\nbook2\n", patron2.traverseCheckedOut());
		assertEquals("book0\nbook3\n", patron2.traverseReserveQueue());
		patron2.closeAccount();
		assertEquals("", patron2.traverseCheckedOut());
		assertEquals("", patron2.traverseReserveQueue());
		
		patron3.reserve(book0);
		patron3.reserve(book1);
		patron3.reserve(book2);
		patron3.reserve(book3);
		assertEquals("book1\nbook2\nbook3\n", patron3.traverseCheckedOut());
		assertEquals("book0\n", patron3.traverseReserveQueue());
		patron3.closeAccount();
		assertEquals("", patron3.traverseCheckedOut());
		assertEquals("", patron3.traverseReserveQueue());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#returnBook(int)}.
	 */
	@Test
	public void testReturnBook() {
		try {
			patron1.returnBook(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		patron1.reserve(book0);
		patron1.reserve(book1);
		patron1.reserve(book2);
		patron1.reserve(book3);
		patron1.returnBook(0);
		assertEquals("book2\n", patron1.traverseCheckedOut());
		assertEquals("book0\nbook3\n", patron1.traverseReserveQueue());
		try {
			patron1.returnBook(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			patron1.returnBook(1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		
		patron2.reserve(book0);
		patron2.reserve(book1);
		patron2.reserve(book2);
		patron2.reserve(book3);
		patron2.returnBook(1);
		assertEquals("book1\nbook3\n", patron2.traverseCheckedOut());
		assertEquals("book0\n", patron2.traverseReserveQueue());
		
		patron3.reserve(book0);
		patron3.reserve(book1);
		patron3.reserve(book2);
		patron3.reserve(book3);
		patron3.returnBook(0);
		assertEquals("book3\n", patron3.traverseCheckedOut());
		assertEquals("book0\nbook1\n", patron3.traverseReserveQueue());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#moveAheadOneInReserves(int)}.
	 */
	@Test
	public void testMoveAheadOneInReserves() {
		try {
			patron1.moveAheadOneInReserves(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		patron1.reserve(book0);
		patron1.reserve(book1);
		patron1.reserve(book2);
		patron1.reserve(book3);
		try {
			patron1.moveAheadOneInReserves(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			patron1.moveAheadOneInReserves(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			patron1.moveAheadOneInReserves(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		patron1.moveAheadOneInReserves(0);
		assertEquals("book0\nbook2\nbook3\n", patron1.traverseReserveQueue());
		patron1.moveAheadOneInReserves(2);
		assertEquals("book0\nbook3\nbook2\n", patron1.traverseReserveQueue());
		patron1.moveAheadOneInReserves(1);
		assertEquals("book3\nbook0\nbook2\n", patron1.traverseReserveQueue());
		
		patron2.reserve(book0);
		patron2.reserve(book1);
		patron2.reserve(book2);
		patron2.reserve(book3);
		patron2.moveAheadOneInReserves(1);
		assertEquals("book1\nbook0\n", patron2.traverseReserveQueue());
		
		patron3.reserve(book0);
		patron3.reserve(book1);
		patron3.reserve(book2);
		patron3.reserve(book3);
		patron3.moveAheadOneInReserves(0);
		assertEquals("book0\nbook1\n", patron3.traverseReserveQueue());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#unReserve(int)}.
	 */
	@Test
	public void testUnReserve() {
		try {
			patron1.unReserve(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		patron1.reserve(book0);
		patron1.reserve(book1);
		patron1.reserve(book2);
		patron1.reserve(book3);
		try {
			patron1.unReserve(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			patron1.unReserve(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			patron1.unReserve(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		patron1.unReserve(1);
		assertEquals("book0\nbook3\n", patron1.traverseReserveQueue());
		patron1.unReserve(0);
		assertEquals("book3\n", patron1.traverseReserveQueue());
		
		patron2.reserve(book0);
		patron2.reserve(book1);
		patron2.reserve(book2);
		patron2.reserve(book3);
		patron2.unReserve(0);
		assertEquals("book1\n", patron2.traverseReserveQueue());
		patron2.unReserve(0);
		assertEquals("", patron2.traverseReserveQueue());
		
		patron3.reserve(book0);
		patron3.reserve(book1);
		patron3.reserve(book2);
		patron3.reserve(book3);
		patron3.unReserve(1);
		assertEquals("book0\n", patron3.traverseReserveQueue());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.Patron#reserve(edu.ncsu.csc216.wolf_library.inventory.Book)}.
	 */
	@Test
	public void testReserve() {
		Book book = null;
		try {
			patron1.reserve(book);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_PATRON_NULL_BOOK, e.getMessage());
		}
		patron1.reserve(book0);
		patron1.reserve(book1);
		patron1.reserve(book2);
		patron1.reserve(book3);
		patron1.reserve(book3);
		assertEquals("book0\nbook2\nbook3\nbook3\n", patron1.traverseReserveQueue());
		patron2.reserve(book0);
		patron2.reserve(book1);
		patron2.reserve(book2);
		patron2.reserve(book3);
		patron2.reserve(book2);
		assertEquals("book0\nbook1\nbook2\n", patron2.traverseReserveQueue());
		patron3.reserve(book0);
		patron3.reserve(book1);
		patron3.reserve(book2);
		patron3.reserve(book3);
		patron3.reserve(book3);
		patron3.reserve(book2);
		patron3.reserve(book3);
		assertEquals("book0\nbook1\nbook2\nbook3\n", patron3.traverseReserveQueue());
	}

}
