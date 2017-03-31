import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for PatronDB.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class PatronDBTest {
	
	private PatronDB patrons;

	/**
	 * Sets up the PatronDBTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		patrons = new PatronDB();
		patrons.addNewPatron("patron1", "patron1", 1);
		patrons.addNewPatron("patron2", "patron2", 2);
		patrons.addNewPatron("patron3", "patron3", 3);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.PatronDB#verifyPatron(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testVerifyPatron() {
		try {
			patrons.verifyPatron(null, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		try {
			patrons.verifyPatron("", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		try {
			patrons.verifyPatron("", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		try {
			patrons.verifyPatron("patron1 ", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		try {
			patrons.verifyPatron("patron1", " patron1");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		Patron patron0 = patrons.verifyPatron("patron2", "patron2");
		assertEquals("patron2", patron0.getId());
		patron0 = patrons.verifyPatron("patron3", "patron3");
		assertEquals("patron3", patron0.getId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.PatronDB#listAccounts()}.
	 */
	@Test
	public void testListAccounts() {
		PatronDB patrons0 = new PatronDB();
		assertEquals("", patrons0.listAccounts());
		assertEquals("patron1\npatron2\npatron3\n", patrons.listAccounts());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.PatronDB#addNewPatron(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddNewPatron() {
		try {
			patrons.addNewPatron("Patron1", "Patron1", 4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_PATRON_DB_ACCOUNT_EXISTS, e.getMessage());
			assertEquals("patron1\npatron2\npatron3\n", patrons.listAccounts());
		}
		patrons.addNewPatron("Patron0", "Patron0", 1);
		assertEquals("Patron0\npatron1\npatron2\npatron3\n", patrons.listAccounts());
		patrons.addNewPatron("a", "a", 5);
		patrons.addNewPatron("z", "z", 6);
		patrons.addNewPatron("b", "b", 7);
		patrons.addNewPatron("w", "w", 8);
		patrons.addNewPatron("c", "c", 9);
		patrons.addNewPatron("d", "d", 10);
		patrons.addNewPatron("b1", "b1", 11);
		patrons.addNewPatron("w1", "w1", 12);
		patrons.addNewPatron("e", "e", 13);
		patrons.addNewPatron("h", "h", 14);
		patrons.addNewPatron("u", "u", 15);
		patrons.addNewPatron("w3", "w3", 16);
		patrons.addNewPatron("c1", "c1", 17);
		patrons.addNewPatron("d1", "d1", 18);
		patrons.addNewPatron("b2", "b2", 19);
		patrons.addNewPatron("w2", "w2", 20);
		try {
			patrons.addNewPatron("not", "able", 2);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_PATRON_DB_FULL, e.getMessage());
			assertEquals("a\nb\nb1\nb2\nc\nc1\nd\nd1\ne\nh\nPatron0\npatron1\npatron2\npatron3\nu\nw\nw1\nw2\nw3\nz\n", patrons.listAccounts());
		}
		try {
			patrons.verifyPatron("not", "able");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.PatronDB#cancelAccount(java.lang.String)}.
	 */
	@Test
	public void testCancelAccount() {
		String s = null;
		try {
			patrons.cancelAccount(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		s = "";
		try {
			patrons.cancelAccount(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		s = "notThere";
		try {
			patrons.cancelAccount(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
		s = "patron2";
		patrons.cancelAccount(s);
		assertEquals("patron1\npatron3\n", patrons.listAccounts());
	}

}
