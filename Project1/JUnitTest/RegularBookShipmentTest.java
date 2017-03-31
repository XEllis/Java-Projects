import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RegularBookShipment.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class RegularBookShipmentTest {
	
	private ItemToShip package1;
	private ItemToShip package2;
	private ItemToShip package3;
	private Log myLog;
	private ShipmentProcessStation[] station;

	/**
	 * Sets up the RegularBookShipmentTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		package1 = new RegularBookShipment(4, 15);
		package2 = new RegularBookShipment(8, 16);
		package3 = new RegularBookShipment(12, 19);
		myLog = new Log();
		station = new ShipmentProcessStation[3];
		for (int i = 0; i < station.length; i++) {
			station[i] = new ShipmentProcessStation(myLog);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RegularBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		package1.getInLine(station);
		package2.getInLine(station);
		package3.getInLine(station);
		assertEquals(1, package1.getStationIndex());
		assertEquals(2, package2.getStationIndex());
		assertEquals(1, package3.getStationIndex());
		assertEquals(0, package1.getWaitTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(7, package3.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RegularBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		assertEquals(Color.BLUE, package1.getColor());
		assertEquals(Color.BLUE, package2.getColor());
		assertEquals(Color.BLUE, package3.getColor());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#ItemToShip(int,int)}.
	 */
	@Test
	public void testItemToShip() {
		ItemToShip package4 = null;
		try {
			package4 = new RegularBookShipment(-1, 11);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(true, package4 == null);
		}
		try {
			package4 = new RegularBookShipment(0, -10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(true, package4 == null);
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		assertEquals(4, package1.getArrivalTime());
		assertEquals(8, package2.getArrivalTime());
		assertEquals(12, package3.getArrivalTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		assertEquals(0, package1.getWaitTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(0, package3.getWaitTime());
		package1.getInLine(station);
		package2.getInLine(station);
		package3.getInLine(station);
		assertEquals(0, package1.getWaitTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(7, package3.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		assertEquals(0, package1.getWaitTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(0, package3.getWaitTime());
		package1.setWaitTime(7);
		package2.setWaitTime(14);
		package3.setWaitTime(21);
		assertEquals(7, package1.getWaitTime());
		assertEquals(14, package2.getWaitTime());
		assertEquals(21, package3.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		assertEquals(15, package1.getProcessTime());
		assertEquals(16, package2.getProcessTime());
		assertEquals(19, package3.getProcessTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getStationIndex()}.
	 */
	@Test
	public void testGetStationIndex() {
		assertEquals(-1, package1.getStationIndex());
		assertEquals(-1, package2.getStationIndex());
		assertEquals(-1, package3.getStationIndex());
		package1.getInLine(station);
		package2.getInLine(station);
		package3.getInLine(station);
		assertEquals(1, package1.getStationIndex());
		assertEquals(2, package2.getStationIndex());
		assertEquals(1, package3.getStationIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setStationIndex(int)}.
	 */
	@Test
	public void testSetStationIndex() {
		assertEquals(-1, package1.getStationIndex());
		assertEquals(-1, package2.getStationIndex());
		assertEquals(-1, package3.getStationIndex());
		package1.setStationIndex(7);
		package2.setStationIndex(14);
		package3.setStationIndex(21);
		assertEquals(7, package1.getStationIndex());
		assertEquals(14, package2.getStationIndex());
		assertEquals(21, package3.getStationIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#isWaitingInLineAtStation()}.
	 */
	@Test
	public void testIsWaitingInLineAtStation() {
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(false, package3.isWaitingInLineAtStation());
		package1.getInLine(station);
		package2.getInLine(station);
		package3.getInLine(station);
		assertEquals(true, package1.isWaitingInLineAtStation());
		assertEquals(true, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#removeFromWaitingLine()}.
	 */
	@Test
	public void testRemoveFromWaitingLine() {
		package1.getInLine(station);
		package2.getInLine(station);
		package3.getInLine(station);
		assertEquals(true, package1.isWaitingInLineAtStation());
		assertEquals(true, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
		for (int i = 1; i < station.length; i++) {
			station[i].processNext();
		}
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
		package1.getInLine(station);
		package2.getInLine(station);
		package3.getInLine(station);
		assertEquals(true, package1.isWaitingInLineAtStation());
		assertEquals(true, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
		package1.removeFromWaitingLine();
		package2.removeFromWaitingLine();
		package3.removeFromWaitingLine();
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(false, package3.isWaitingInLineAtStation());
	}

}
