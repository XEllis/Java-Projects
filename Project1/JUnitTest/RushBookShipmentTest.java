import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RushBookShipment.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class RushBookShipmentTest {
	
	private ItemToShip package1;
	private ItemToShip package2;
	private ItemToShip package3;
	private Log myLog;
	private ShipmentProcessStation[] station;

	/**
	 * Sets up the RushBookShipmentTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		package1 = new RushBookShipment(3, 10);
		package2 = new RushBookShipment(16, 11);
		package3 = new RushBookShipment(17, 12);
		myLog = new Log();
		station = new ShipmentProcessStation[3];
		for (int i = 0; i < station.length; i++) {
			station[i] = new ShipmentProcessStation(myLog);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RushBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		package1.getInLine(station);
		package2.getInLine(station);
		package3.getInLine(station);
		assertEquals(0, package1.getStationIndex());
		assertEquals(1, package2.getStationIndex());
		assertEquals(2, package3.getStationIndex());
		assertEquals(0, package1.getWaitTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(0, package3.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RushBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		assertEquals(Color.BLACK, package1.getColor());
		assertEquals(Color.BLACK, package2.getColor());
		assertEquals(Color.BLACK, package3.getColor());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#ItemToShip(int,int)}.
	 */
	@Test
	public void testItemToShip() {
		ItemToShip package4 = null;
		try {
			package4 = new RushBookShipment(-1, 11);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(true, package4 == null);
		}
		try {
			package4 = new RushBookShipment(0, -10);
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
		assertEquals(3, package1.getArrivalTime());
		assertEquals(16, package2.getArrivalTime());
		assertEquals(17, package3.getArrivalTime());
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
		assertEquals(0, package3.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		assertEquals(0, package1.getWaitTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(0, package3.getWaitTime());
		package1.setWaitTime(0);
		package2.setWaitTime(3);
		package3.setWaitTime(10);
		assertEquals(0, package1.getWaitTime());
		assertEquals(3, package2.getWaitTime());
		assertEquals(10, package3.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		assertEquals(10, package1.getProcessTime());
		assertEquals(11, package2.getProcessTime());
		assertEquals(12, package3.getProcessTime());
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
		assertEquals(0, package1.getStationIndex());
		assertEquals(1, package2.getStationIndex());
		assertEquals(2, package3.getStationIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setStationIndex(int)}.
	 */
	@Test
	public void testSetStationIndex() {
		assertEquals(-1, package1.getStationIndex());
		assertEquals(-1, package2.getStationIndex());
		assertEquals(-1, package3.getStationIndex());
		package1.setStationIndex(0);
		package2.setStationIndex(3);
		package3.setStationIndex(10);
		assertEquals(0, package1.getStationIndex());
		assertEquals(3, package2.getStationIndex());
		assertEquals(10, package3.getStationIndex());
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
		for (int i = 0; i < station.length; i++) {
			station[i].processNext();
		}
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(false, package3.isWaitingInLineAtStation());
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
