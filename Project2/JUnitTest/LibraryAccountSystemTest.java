import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LibraryAccountSystem.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class LibraryAccountSystemTest {
	
	private LibraryAccountSystem accountSystem;

	/**
	 * Sets up the LibraryAccountSystemTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accountSystem = new LibraryAccountSystem();
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		accountSystem.addNewPatron("patron1", "patron1", 1);
		accountSystem.addNewPatron("patron2", "patron2", 2);
		accountSystem.addNewPatron("patron3", "patron3", 3);
		accountSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		accountSystem.login("patron1", "patron1");
		try {
			accountSystem.login(Constants.ADMIN, Constants.ADMIN);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(true, accountSystem.isPatronLoggedIn());
			assertEquals(Constants.EXP_LAS_USER_ALREADY_LOGGED_IN, e.getMessage());
		}
		assertEquals("patron1", accountSystem.getCurrentPatron().getId());
		accountSystem.logout();
		
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		try {
			accountSystem.login("patron1", "patron1");
			fail();
		} catch (IllegalStateException e) {
			assertEquals(true, accountSystem.isAdminLoggedIn());
			assertEquals(Constants.EXP_LAS_USER_ALREADY_LOGGED_IN, e.getMessage());
		}
		accountSystem.logout();
		
		try {
			accountSystem.login(Constants.ADMIN, "ADMIN");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(Constants.EXP_INCORRECT, e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#logout()}.
	 */
	@Test
	public void testLogout() {
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		assertEquals(true, accountSystem.isAdminLoggedIn());
		accountSystem.logout();
		assertEquals(false, accountSystem.isAdminLoggedIn());
		
		accountSystem.login("patron2", "patron2");
		assertEquals(true, accountSystem.isPatronLoggedIn());
		accountSystem.logout();
		assertEquals(false, accountSystem.isPatronLoggedIn());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#getCurrentPatron()}.
	 */
	@Test
	public void testGetCurrentPatron() {
		accountSystem.login("patron2", "patron2");
		assertEquals("patron2", accountSystem.getCurrentPatron().getId());
		accountSystem.logout();
		assertEquals(null, accountSystem.getCurrentPatron());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#isAdminLoggedIn()}.
	 */
	@Test
	public void testIsAdminLoggedIn() {
		assertEquals(false, accountSystem.isAdminLoggedIn());
		
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		assertEquals(true, accountSystem.isAdminLoggedIn());
		accountSystem.logout();
		assertEquals(false, accountSystem.isAdminLoggedIn());
		
		accountSystem.login("patron3", "patron3");
		assertEquals(false, accountSystem.isAdminLoggedIn());
		assertEquals(true, accountSystem.isPatronLoggedIn());
		accountSystem.logout();
		assertEquals(false, accountSystem.isPatronLoggedIn());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#isPatronLoggedIn()}.
	 */
	@Test
	public void testIsPatronLoggedIn() {
		assertEquals(false, accountSystem.isPatronLoggedIn());
		
		accountSystem.login("patron1", "patron1");
		assertEquals(true, accountSystem.isPatronLoggedIn());
		accountSystem.logout();
		assertEquals(false, accountSystem.isPatronLoggedIn());
		
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		assertEquals(false, accountSystem.isPatronLoggedIn());
		assertEquals(true, accountSystem.isAdminLoggedIn());
		accountSystem.logout();
		assertEquals(false, accountSystem.isAdminLoggedIn());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#addNewPatron(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddNewPatron() {
		try {
			accountSystem.addNewPatron("patron0", "patron0", 1);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_ACCESS_DENIED, e.getMessage());
		}
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		accountSystem.addNewPatron("patron5", "patron5", 5);
		assertEquals("patron1\npatron2\npatron3\npatron5\n", accountSystem.listAcounts());
		accountSystem.addNewPatron("patron4", "patron4", 4);
		assertEquals("patron1\npatron2\npatron3\npatron4\npatron5\n", accountSystem.listAcounts());
		accountSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#cancelAccount(java.lang.String)}.
	 */
	@Test
	public void testCancelAccount() {
		try {
			accountSystem.cancelAccount("patron1");
			fail();
		} catch (IllegalStateException e) {
			assertEquals(Constants.EXP_ACCESS_DENIED, e.getMessage());
		}
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		accountSystem.cancelAccount("patron2");
		assertEquals("patron1\npatron3\n", accountSystem.listAcounts());
		accountSystem.addNewPatron("patron5", "patron5", 5);
		assertEquals("patron1\npatron3\npatron5\n", accountSystem.listAcounts());
		accountSystem.addNewPatron("patron4", "patron4", 4);
		assertEquals("patron1\npatron3\npatron4\npatron5\n", accountSystem.listAcounts());
		accountSystem.cancelAccount("patron1");
		assertEquals("patron3\npatron4\npatron5\n", accountSystem.listAcounts());
		accountSystem.cancelAccount("patron5");
		assertEquals("patron3\npatron4\n", accountSystem.listAcounts());
		accountSystem.logout();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem#listAcounts()}.
	 */
	@Test
	public void testListAcounts() {
		assertEquals("patron1\npatron2\npatron3\n", accountSystem.listAcounts());
		accountSystem.login(Constants.ADMIN, Constants.ADMIN);
		accountSystem.addNewPatron("patron5", "patron5", 5);
		assertEquals("patron1\npatron2\npatron3\npatron5\n", accountSystem.listAcounts());
		accountSystem.addNewPatron("patron4", "patron4", 4);
		assertEquals("patron1\npatron2\npatron3\npatron4\npatron5\n", accountSystem.listAcounts());
		accountSystem.cancelAccount("patron5");
		accountSystem.cancelAccount("patron1");
		accountSystem.cancelAccount("patron3");
		accountSystem.cancelAccount("patron4");
		accountSystem.cancelAccount("patron2");
		assertEquals("", accountSystem.listAcounts());
		accountSystem.logout();
	}

}
