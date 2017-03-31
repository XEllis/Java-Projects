import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for MultiPurposeList.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class MultiPurposeListTest {
	
	private MultiPurposeList<Book> books;
	private Book book0;
	private Book book1;
	private Book book2;
	private Book book3;

	/**
	 * Sets up the MultiPurposeListTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		books = new MultiPurposeList<Book>();
		book0 = new Book("0 book0");
		book1 = new Book("1 book1");
		book2 = new Book("2 book2");
		book3 = new Book("3 book3");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#resetIterator()}.
	 */
	@Test
	public void testResetIterator() {
		books.resetIterator();
		assertEquals(false, books.hasNext());
		books.addItem(0, book0);
		assertEquals(true, books.hasNext());
		assertEquals("book0", books.next().getInfo());
		assertEquals(false, books.hasNext());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		books.resetIterator();
		assertEquals(false, books.hasNext());
		books.addItem(0, book1);
		books.addItem(1, book2);
		books.addItem(0, book3);
		assertEquals(true, books.hasNext());
		assertEquals("book3", books.next().getInfo());
		assertEquals("book1", books.next().getInfo());
		assertEquals("book2", books.next().getInfo());
		assertEquals(false, books.hasNext());
		books.addItem(1, book0);
		assertEquals("book3", books.next().getInfo());
		assertEquals("book0", books.next().getInfo());
		assertEquals("book1", books.next().getInfo());
		assertEquals("book2", books.next().getInfo());
		assertEquals(false, books.hasNext());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#next()}.
	 */
	@Test
	public void testNext() {
		books.resetIterator();
		assertEquals(false, books.hasNext());
		books.addItem(0, book1);
		books.addItem(1, book2);
		books.addItem(0, book3);
		assertEquals(true, books.hasNext());
		assertEquals("book3", books.next().getInfo());
		assertEquals("book1", books.next().getInfo());
		assertEquals("book2", books.next().getInfo());
		assertEquals(false, books.hasNext());
		books.addItem(1, book0);
		assertEquals("book3", books.next().getInfo());
		assertEquals("book0", books.next().getInfo());
		assertEquals("book1", books.next().getInfo());
		assertEquals("book2", books.next().getInfo());
		try {
			books.next();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(false, books.hasNext());
			assertEquals(Constants.EXP_NO_MORE_VALUES_IN_LIST, e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#addItem(int, java.lang.Object)}.
	 */
	@Test
	public void testAddItem() {
		Book book = null;
		try {
			books.addItem(0, book);
			fail();
		} catch (NullPointerException e) {
			assertEquals(Constants.EXP_LIST_ITEM_NULL, e.getMessage());
		}
		try {
			books.addItem(-1, book0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.addItem(1, book0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		books.addItem(0, book0);
		books.addItem(0, book3);
		books.addItem(1, book1);
		books.addItem(3, book2);
		books.addItem(3, book1);
		assertEquals("book3", books.next().getInfo());
		assertEquals("book1", books.next().getInfo());
		assertEquals("book0", books.next().getInfo());
		assertEquals("book1", books.next().getInfo());
		assertEquals("book2", books.next().getInfo());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertEquals(true, books.isEmpty());
		books.addItem(0, book1);
		assertEquals(false, books.isEmpty());
		books.addItem(1, book2);
		books.remove(1);
		assertEquals(false, books.isEmpty());
		books.remove(0);
		assertEquals(true, books.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#lookAtItemN(int)}.
	 */
	@Test
	public void testLookAtItemN() {
		try {
			books.lookAtItemN(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.lookAtItemN(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.lookAtItemN(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		books.addItem(0, book1);
		books.addItem(1, book2);
		books.addItem(0, book3);
		books.addItem(2, book0);
		assertEquals("book3", books.lookAtItemN(0).getInfo());
		assertEquals("book1", books.lookAtItemN(1).getInfo());
		assertEquals("book0", books.lookAtItemN(2).getInfo());
		assertEquals("book2", books.lookAtItemN(3).getInfo());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#addToRear(java.lang.Object)}.
	 */
	@Test
	public void testAddToRear() {
		Book book = null;
		try {
			books.addToRear(book);
			fail();
		} catch (NullPointerException e) {
			assertEquals(Constants.EXP_LIST_ITEM_NULL, e.getMessage());
		}
		books.addToRear(book0);
		books.addToRear(book1);
		books.addToRear(book2);
		books.addToRear(book3);
		assertEquals("book0", books.lookAtItemN(0).getInfo());
		assertEquals("book1", books.lookAtItemN(1).getInfo());
		assertEquals("book2", books.lookAtItemN(2).getInfo());
		assertEquals("book3", books.lookAtItemN(3).getInfo());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		try {
			books.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.remove(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		books.addItem(0, book1);
		books.addItem(1, book2);
		books.addItem(0, book3);
		books.addItem(2, book0);
		assertEquals("book3", books.remove(0).getInfo());
		assertEquals("book2", books.remove(2).getInfo());
		assertEquals("book0", books.remove(1).getInfo());
		assertEquals("book1", books.remove(0).getInfo());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#moveAheadOne(int)}.
	 */
	@Test
	public void testMoveAheadOne() {
		try {
			books.moveAheadOne(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.moveAheadOne(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.moveAheadOne(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		books.addItem(0, book1);
		books.addItem(1, book2);
		books.addItem(0, book3);
		books.addItem(1, book0);
		assertEquals("book3", books.lookAtItemN(0).getInfo());
		assertEquals("book0", books.lookAtItemN(1).getInfo());
		assertEquals("book1", books.lookAtItemN(2).getInfo());
		assertEquals("book2", books.lookAtItemN(3).getInfo());
		books.moveAheadOne(1);
		assertEquals("book0", books.lookAtItemN(0).getInfo());
		assertEquals("book3", books.lookAtItemN(1).getInfo());
		assertEquals("book1", books.lookAtItemN(2).getInfo());
		assertEquals("book2", books.lookAtItemN(3).getInfo());
		books.moveAheadOne(2);
		assertEquals("book0", books.lookAtItemN(0).getInfo());
		assertEquals("book1", books.lookAtItemN(1).getInfo());
		assertEquals("book3", books.lookAtItemN(2).getInfo());
		assertEquals("book2", books.lookAtItemN(3).getInfo());
		books.moveAheadOne(3);
		assertEquals("book0", books.lookAtItemN(0).getInfo());
		assertEquals("book1", books.lookAtItemN(1).getInfo());
		assertEquals("book2", books.lookAtItemN(2).getInfo());
		assertEquals("book3", books.lookAtItemN(3).getInfo());
		books.moveAheadOne(0);
		assertEquals("book0", books.lookAtItemN(0).getInfo());
		assertEquals("book1", books.lookAtItemN(1).getInfo());
		assertEquals("book2", books.lookAtItemN(2).getInfo());
		assertEquals("book3", books.lookAtItemN(3).getInfo());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.util.MultiPurposeList#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, books.size());
		books.addItem(0, book1);
		assertEquals(1, books.size());
		books.addItem(0, book2);
		books.addItem(1, book3);
		books.addItem(1, book0);
		assertEquals(4, books.size());
		books.remove(0);
		assertEquals(3, books.size());
		books.remove(0);
		books.remove(0);
		books.remove(0);
		assertEquals(0, books.size());
	}

}
