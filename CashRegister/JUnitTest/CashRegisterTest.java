import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for CashRegister.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * 
 * @author xellis
 *
 */
public class CashRegisterTest {
	private CashRegister cashRegister;
	
	/**
	 * Sets up the CashRegisterTest by creating one representative Cash Register objects
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cashRegister = new CashRegister();
	}

	/**
	 * The test determines if the balance returned from the Cash Register object 
	 * is the same as what the balance was initialized to.
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#getCurrentBalance()}.
	 */
	@Test
	public void testGetCurrentBalance() {
		assertEquals(36410, cashRegister.getCurrentBalance());
	}

	/**
	 * Test how the purchase is processed when using processPurchase(int) method. 
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processPurchase(int, edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 */
	@Test
	public void testProcessPurchase() {
		CurrencyCollection payment1 = new CurrencyCollection(0);
		CurrencyCollection payment2 = new CurrencyCollection(10);
 		try {
			cashRegister.processPurchase(1, payment1);
		} catch (IllegalArgumentException e) {
			assertEquals(36410, cashRegister.getCurrentBalance());
		}
 		CurrencyCollection refund = cashRegister.processPurchase(1, payment2);
		assertEquals(36411, cashRegister.getCurrentBalance());
		assertEquals(36409, refund.getBalance());
	}

	/**
	 * Test how the refund is processed when using processRefund(int) method. 
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processRefund(int)}.
	 */
	@Test
	public void testProcessRefund() {
		try {
			cashRegister.processRefund(36430);
		} catch (IllegalArgumentException e) {
			assertEquals(36410, cashRegister.getCurrentBalance());
		}
		CurrencyCollection refund = cashRegister.processRefund(100);
		assertEquals(36310, cashRegister.getCurrentBalance());
		assertEquals(100, refund.getBalance());
		for (int i = 0; i < CurrencyCollection.NUM_SLOTS; i++) {
			if ( i == 4) {
				assertEquals(1, refund.getCurrencyAtIdx(i).getCount());
			} else {
				assertEquals(0, refund.getCurrencyAtIdx(i).getCount());
			}
		}
	}

}
