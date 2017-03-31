import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for CurrencyCollection.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * 
 * @author xellis
 *
 */
public class CurrencyCollectionTest {
	private CurrencyCollection collection1;
	private CurrencyCollection collection2;
	private static final int INITIAL_COUNT_1 = 0;
	private static final int INITIAL_COUNT_2 = 10;

	/**
	 * Sets up the CurrencyCollectionTest by creating two representative Currency Collection objects
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		collection1 = new CurrencyCollection();
		collection2 = new CurrencyCollection(INITIAL_COUNT_2);
	}

	/**
	 * The test determines if the Currency returned from the Currency Collection object 
	 * is the same as what the Currency was initialized to at the given index.
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyAtIdx(int)}.
	 */
	@Test
	public void testGetCurrencyAtIdx() {
		Currency penney  = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, INITIAL_COUNT_1);
		Currency dollar = new Currency(CurrencyCollection.ONE_VALUE, CurrencyCollection.ONE_NAME, INITIAL_COUNT_2);
		Currency twenty = new Currency(CurrencyCollection.TWENTY_VALUE, CurrencyCollection.TWENTY_NAME, INITIAL_COUNT_2);
		assertEquals(penney, collection1.getCurrencyAtIdx(0));
		assertEquals(dollar, collection2.getCurrencyAtIdx(4));
		assertEquals(twenty, collection2.getCurrencyAtIdx(7));
		int number1 = -1;
		int number2 = 8;
		try {
			collection1.getCurrencyAtIdx(number1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(number1 < 0);
		}
		try {
			collection2.getCurrencyAtIdx(number2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(number2 >= CurrencyCollection.NUM_SLOTS);
		}
	}

	/**
	 * The test uses the getCount() method from the Currency class to understand how the 
	 * count is changed when using the modifyDenomination() method
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#modifyDenomination(int, int)}.
	 */
	@Test
	public void testModifyDenomination() {
		try {
			collection1.modifyDenomination(7, 10);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue( (7 != CurrencyCollection.PENNY_VALUE) && (7 != CurrencyCollection.NICKEL_VALUE) &&
					(7 != CurrencyCollection.DIME_VALUE) && (7 != CurrencyCollection.QUARTER_VALUE) &&
					(7 != CurrencyCollection.ONE_VALUE) && (7 != CurrencyCollection.FIVE_VALUE) &&
					(7 != CurrencyCollection.TEN_VALUE) && (7 != CurrencyCollection.TWENTY_VALUE) );
		}
		assertEquals(10, collection2.getCurrencyAtIdx(0).getCount());
		try {
			collection2.modifyDenomination(0, -11);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, collection2.getCurrencyAtIdx(0).getCount());
		}
		collection1.modifyDenomination(5, 8);
		assertEquals(8, collection1.getCurrencyAtIdx(1).getCount());
		collection2.modifyDenomination(500, -10);
		assertEquals(0, collection2.getCurrencyAtIdx(5).getCount());
	}

	/**
	 * The test uses the getCount() method from the Currency class to understand how the 
	 * currency in the payment is added to the current Currency Collection.
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#depositCurrencyCollection(edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 */
	@Test
	public void testDepositCurrencyCollection() {
		collection1.depositCurrencyCollection(collection2);
		collection2.depositCurrencyCollection(collection1);
		for (int i = 0; i < CurrencyCollection.NUM_SLOTS; i++) {
			assertEquals(10, collection1.getCurrencyAtIdx(i).getCount());
			assertEquals(20, collection2.getCurrencyAtIdx(i).getCount());
		}
	}

	/**
	 * Test how the count is changed when using refundByAmount(int) method. 
	 * Test how the currency is refunded when using refundByAmount(int) method. 
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#refundByAmount(int)}.
	 */
	@Test
	public void testRefundByAmount() {
		try {
			collection1.refundByAmount(1);
			fail();
		} catch (IllegalArgumentException e) {
			for (int i = 0; i < CurrencyCollection.NUM_SLOTS; i++) {
				assertEquals(0, collection1.getCurrencyAtIdx(i).getCount());
			}
		}
		try {
			collection2.refundByAmount(36430);
			fail();
		} catch (IllegalArgumentException e) {
			for (int i = 0; i < CurrencyCollection.NUM_SLOTS; i++) {
				assertEquals(10, collection2.getCurrencyAtIdx(i).getCount());
			}
		}
		CurrencyCollection refundCollection1 = collection1.refundByAmount(0);
		CurrencyCollection refundCollection2 = collection2.refundByAmount(36409);
		for (int i = 0; i < CurrencyCollection.NUM_SLOTS; i++) {
			assertEquals(0, refundCollection1.getCurrencyAtIdx(i).getCount());
			assertEquals(0, collection1.getCurrencyAtIdx(i).getCount());
			if (i == 0) {
				assertEquals(9, refundCollection2.getCurrencyAtIdx(i).getCount());
				assertEquals(1, collection2.getCurrencyAtIdx(i).getCount());
			} else {
				assertEquals(10, refundCollection2.getCurrencyAtIdx(i).getCount());
				assertEquals(0, collection2.getCurrencyAtIdx(i).getCount());
			}
			
		}
		
	}

	/**
	 * The test uses getCurrencyAtIdx(int) method to determine if the 
	 * Currency[] returned from the Currency Collection object 
	 * is the same as what the Currency Collection was initialized to.
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyCollection()}.
	 */
	@Test
	public void testGetCurrencyCollection() {
		Currency[] collection3 = collection1.getCurrencyCollection();
		Currency[] collection4 = collection2.getCurrencyCollection();
		for (int i = 0; i < CurrencyCollection.NUM_SLOTS; i++) {
			assertEquals(collection1.getCurrencyAtIdx(i), collection3[i]);
			assertEquals(collection2.getCurrencyAtIdx(i), collection4[i]);
		}
	}

	/**
	 * The test determines if the balance returned from the Currency Collection object 
	 * is the same as what the balance was initialized to.
	 * 
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getBalance()}.
	 */
	@Test
	public void testGetBalance() {
		assertEquals(0, collection1.getBalance());
		assertEquals(36410, collection2.getBalance());
	}

}
