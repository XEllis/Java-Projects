import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LibraryLendingSystem.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class LibraryLendingSystemTest {
	
	private LibraryLendingSystem lendingSystem;

	/**
	 * Sets up the LibraryLendingSystemTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		lendingSystem = new LibraryLendingSystem("books-example.txt");
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		lendingSystem.addNewPatron("patron1", "patron1", 1);
		lendingSystem.addNewPatron("patron2", "patron2", 2);
		lendingSystem.addNewPatron("patron3", "patron3", 3);
		lendingSystem.logout();
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#showInventory()}.
	 */
	@Test
	public void testShowInventory() {
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
		assertEquals(s, lendingSystem.showInventory());
		lendingSystem.login("patron1", "patron1");
		lendingSystem.reserveItem(13);
		s = "A Breath of Snow and Ashes by Diana Gabaldon\n";
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
		s += "* Love in the Time of Cholera by Gabriel Garcia Marquez\n";
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
		assertEquals(s, lendingSystem.showInventory());
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		lendingSystem.login("patron2", "patron2");
		assertEquals("patron2", lendingSystem.getCurrentUserId());
		lendingSystem.logout();
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		assertEquals(Constants.ADMIN, lendingSystem.getCurrentUserId());
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#logout()}.
	 */
	@Test
	public void testLogout() {
		assertEquals("", lendingSystem.getCurrentUserId());
		lendingSystem.login("patron3", "patron3");
		assertEquals("patron3", lendingSystem.getCurrentUserId());
		lendingSystem.logout();
		assertEquals("", lendingSystem.getCurrentUserId());
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		assertEquals(Constants.ADMIN, lendingSystem.getCurrentUserId());
		lendingSystem.logout();
		assertEquals("", lendingSystem.getCurrentUserId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#addNewPatron(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddNewPatron() {
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		lendingSystem.addNewPatron("a", "a", 1);
		lendingSystem.logout();
		lendingSystem.login("a", "a");
		assertEquals("a", lendingSystem.getCurrentUserId());
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#getCurrentUserId()}.
	 */
	@Test
	public void testGetCurrentUserId() {
		lendingSystem.login("patron2", "patron2");
		assertEquals("patron2", lendingSystem.getCurrentUserId());
		lendingSystem.logout();
		assertEquals("", lendingSystem.getCurrentUserId());
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		assertEquals(Constants.ADMIN, lendingSystem.getCurrentUserId());
		lendingSystem.logout();
		assertEquals("", lendingSystem.getCurrentUserId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#cancelAccount(java.lang.String)}.
	 */
	@Test
	public void testCancelAccount() {
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		lendingSystem.cancelAccount("patron2");
		lendingSystem.logout();
		try {
			lendingSystem.login("patron2", "patron2");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#reserveItem(int)}.
	 */
	@Test
	public void testReserveItem() {
		try {
			lendingSystem.reserveItem(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		try {
			lendingSystem.reserveItem(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.logout();

		lendingSystem.login("patron3", "patron3");
		lendingSystem.reserveItem(0);
		lendingSystem.reserveItem(8);
		lendingSystem.reserveItem(13);
		lendingSystem.reserveItem(25);
		assertEquals("Wintersmith by Terry Pratchett\n", lendingSystem.traverseReserveQueue());
		String s = "A Breath of Snow and Ashes by Diana Gabaldon\n";
		s += "Hearts in Atlantis by Stephen King\n";
		s += "Love in the Time of Cholera by Gabriel Garcia Marquez\n";
		assertEquals(s, lendingSystem.traverseCheckedOut());
		s = "A Breath of Snow and Ashes by Diana Gabaldon\n";
		s += "Charlie and the Great Glass Elevator by Roald Dahl\n";
		s += "* Christine by Stephen King\n";
		s += "The Dark Is Rising by Susan Cooper\n";
		s += "Dead and Gone by Charlaine Harris\n";
		s += "The Dharma Bums by Jack Kerouac\n";
		s += "The Elements of Style (4th Edition) by Strunk and White\n";
		s += "* Fathers and Sons by Ivan Turgenev\n";
		s += "* Hearts in Atlantis by Stephen King\n";
		s += "The Hero with a Thousand Faces by Joseph Campbell\n";
		s += "High Five by Janet Evanovich\n";
		s += "The House of the Seven Gables by Nathaniel Hawthorne\n";
		s += "The Kitchen God's Wife by Amy Tan\n";
		s += "* Love in the Time of Cholera by Gabriel Garcia Marquez\n";
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
		assertEquals(s, lendingSystem.showInventory());
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#reserveMoveAheadOne(int)}.
	 */
	@Test
	public void testReserveMoveAheadOne() {
		try {
			lendingSystem.reserveMoveAheadOne(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		try {
			lendingSystem.reserveMoveAheadOne(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.logout();
		
		lendingSystem.login("patron1", "patron1");
		lendingSystem.reserveItem(0);
		lendingSystem.reserveItem(1);
		lendingSystem.reserveItem(2);
		lendingSystem.reserveItem(3);
		String s = "Charlie and the Great Glass Elevator by Roald Dahl\n";
		s += "Christine by Stephen King\n";
		s += "The Dark Is Rising by Susan Cooper\n";
		assertEquals("A Breath of Snow and Ashes by Diana Gabaldon\n", lendingSystem.traverseCheckedOut());
		assertEquals(s, lendingSystem.traverseReserveQueue());
		lendingSystem.reserveMoveAheadOne(0);
		assertEquals(s, lendingSystem.traverseReserveQueue());
		lendingSystem.reserveMoveAheadOne(1);
		s = "Christine by Stephen King\n";
		s += "Charlie and the Great Glass Elevator by Roald Dahl\n";
		s += "The Dark Is Rising by Susan Cooper\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		lendingSystem.reserveMoveAheadOne(2);
		s = "Christine by Stephen King\n";
		s += "The Dark Is Rising by Susan Cooper\n";
		s += "Charlie and the Great Glass Elevator by Roald Dahl\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#removeSelectedFromReserves(int)}.
	 */
	@Test
	public void testRemoveSelectedFromReserves() {
		try {
			lendingSystem.removeSelectedFromReserves(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		try {
			lendingSystem.removeSelectedFromReserves(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.logout();
		
		lendingSystem.login("patron2", "patron2");
		lendingSystem.reserveItem(20);
		lendingSystem.reserveItem(21);
		lendingSystem.reserveItem(22);
		lendingSystem.reserveItem(23);
		lendingSystem.reserveItem(24);
		lendingSystem.reserveItem(25);
		
		String s = "Roll of Thunder, Hear My Cry by Mildred D. Taylor\n";
		s += "Shadow of the Hegemon by Orson Scott Card\n";
		assertEquals(s, lendingSystem.traverseCheckedOut());
		s = "Skeleton Crew by Stephen King\n";
		s += "Special Topics in Calamity Physics by Marisha Pessl\n";
		s += "The Tale of Peter Rabbit by Beatrix Potter\n";
		s += "Wintersmith by Terry Pratchett\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		
		lendingSystem.removeSelectedFromReserves(0);
		s = "Special Topics in Calamity Physics by Marisha Pessl\n";
		s += "The Tale of Peter Rabbit by Beatrix Potter\n";
		s += "Wintersmith by Terry Pratchett\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		
		lendingSystem.removeSelectedFromReserves(1);
		s = "Special Topics in Calamity Physics by Marisha Pessl\n";
		s += "Wintersmith by Terry Pratchett\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		
		lendingSystem.removeSelectedFromReserves(1);
		s = "Special Topics in Calamity Physics by Marisha Pessl\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#traverseReserveQueue()}.
	 */
	@Test
	public void testTraverseReserveQueue() {
		try {
			lendingSystem.traverseReserveQueue();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		try {
			lendingSystem.traverseReserveQueue();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.logout();
		
		lendingSystem.login("patron3", "patron3");
		lendingSystem.reserveItem(5);
		lendingSystem.reserveItem(6);
		lendingSystem.reserveItem(7);
		lendingSystem.reserveItem(8);
		lendingSystem.reserveItem(9);
		lendingSystem.reserveItem(10);
		lendingSystem.reserveItem(11);
		String s = "Fathers and Sons by Ivan Turgenev\n";
		s += "The Hero with a Thousand Faces by Joseph Campbell\n";
		s += "High Five by Janet Evanovich\n";
		s += "The House of the Seven Gables by Nathaniel Hawthorne\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		
		s = "The Dharma Bums by Jack Kerouac\n";
		s += "The Elements of Style (4th Edition) by Strunk and White\n";
		s += "Hearts in Atlantis by Stephen King\n";
		assertEquals(s, lendingSystem.traverseCheckedOut());
		
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#traverseCheckedOut()}.
	 */
	@Test
	public void testTraverseCheckedOut() {
		try {
			lendingSystem.traverseCheckedOut();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		try {
			lendingSystem.traverseCheckedOut();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.logout();
		
		lendingSystem.login("patron3", "patron3");
		lendingSystem.reserveItem(5);
		lendingSystem.reserveItem(6);
		lendingSystem.reserveItem(7);
		lendingSystem.reserveItem(8);
		lendingSystem.reserveItem(9);
		lendingSystem.reserveItem(10);
		lendingSystem.reserveItem(11);
		String s = "Fathers and Sons by Ivan Turgenev\n";
		s += "The Hero with a Thousand Faces by Joseph Campbell\n";
		s += "High Five by Janet Evanovich\n";
		s += "The House of the Seven Gables by Nathaniel Hawthorne\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		
		s = "The Dharma Bums by Jack Kerouac\n";
		s += "The Elements of Style (4th Edition) by Strunk and White\n";
		s += "Hearts in Atlantis by Stephen King\n";
		assertEquals(s, lendingSystem.traverseCheckedOut());
		
		lendingSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.lending_system.LibraryLendingSystem#returnItem(int)}.
	 */
	@Test
	public void testReturnItem() {
		try {
			lendingSystem.returnItem(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.login(Constants.ADMIN, Constants.ADMIN);
		try {
			lendingSystem.returnItem(0);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN, e.getMessage());
		}
		lendingSystem.logout();
		
		lendingSystem.login("patron1", "patron1");
		lendingSystem.reserveItem(11);
		lendingSystem.logout();
		
		lendingSystem.login("patron2", "patron2");
		lendingSystem.reserveItem(11);
		lendingSystem.reserveItem(12);
		lendingSystem.reserveItem(13);
		lendingSystem.reserveItem(14);
		lendingSystem.reserveItem(15);
		lendingSystem.returnItem(0);
		String s = "Love in the Time of Cholera by Gabriel Garcia Marquez\n";
		s += "Meditations by Marcus Aurelius\n";
		assertEquals(s, lendingSystem.traverseCheckedOut());
		
		s = "The House of the Seven Gables by Nathaniel Hawthorne\n";
		s += "Mona Lisa Overdrive by William Gibson\n";
		assertEquals(s, lendingSystem.traverseReserveQueue());
		
		lendingSystem.logout();
	}

}
