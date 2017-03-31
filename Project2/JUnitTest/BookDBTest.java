import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BookDB.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class BookDBTest {
	
	private BookDB books;

	/**
	 * Sets up the BookDBTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		books = new BookDB("books-example.txt");
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.BookDB#BookDB(String}
	 */
	@Test
	public void testBookDB() {
		BookDB books0 = null;
		try {
			books0 = new BookDB("books-example");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, books0);
			assertEquals(Constants.EXP_BAD_FILE, e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.BookDB#traverse()}.
	 */
	@Test
	public void testTraverse() {
		String s = "A Breath of Snow and Ashes by Diana Gabaldon\n";
		s += "Charlie and the Great Glass Elevator by Roald Dahl\n";
		s += "* Christine by Stephen King\n";
		s += "The Dark Is Rising by Susan Cooper\n";
		s += "Dead and Gone by Charlaine Harris\n";
		s += "The Dharma Bums by Jack Kerouac\n";
		s += "The Elements of Style (4th Edition) by Strunk and White\n";
		s += "* Fathers and Sons by Ivan Turgenev\n";
		s += "Hearts in Atlantis by Stephen King\n";
		s += "The Hero with a Thousand Faces by Joseph Campbell\n";
		s += "High Five by Janet Evanovich\n";
		s += "The House of the Seven Gables by Nathaniel Hawthorne\n";
		s += "The Kitchen God's Wife by Amy Tan\n";
		s += "Love in the Time of Cholera by Gabriel Garcia Marquez\n";
		s += "Meditations by Marcus Aurelius\n";
		s += "Mona Lisa Overdrive by William Gibson\n";
		s += "Mrs. Frisby and the Rats of NIMH by Robert C. O'Brien\n";
		s += "Perelandra by C. S. Lewis\n";
		s += "The Pickwick Papers by Charles Dickens\n";
		s += "Pride and Prejudice by Jane Austin\n";
		s += "Roll of Thunder, Hear My Cry by Mildred D. Taylor\n";
		s += "Shadow of the Hegemon by Orson Scott Card\n";
		s += "Skeleton Crew by Stephen King\n";
		s += "Special Topics in Calamity Physics by Marisha Pessl\n";
		s += "The Tale of Peter Rabbit by Beatrix Potter\n";
		s += "Wintersmith by Terry Pratchett\n";
		assertEquals(s, books.traverse());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.inventory.BookDB#findItemAt(int)}.
	 */
	@Test
	public void testFindItemAt() {
		try {
			books.findItemAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		try {
			books.findItemAt(26);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(Constants.EXP_INDEX_OUT_OF_BOUNDS, e.getMessage());
		}
		assertEquals("A Breath of Snow and Ashes by Diana Gabaldon", books.findItemAt(0).getInfo());
		assertEquals("Fathers and Sons by Ivan Turgenev", books.findItemAt(7).getInfo());
		assertEquals("Wintersmith by Terry Pratchett", books.findItemAt(25).getInfo());
	}

}
