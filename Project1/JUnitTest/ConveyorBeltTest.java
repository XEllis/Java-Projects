import static org.junit.Assert.*;

import java.awt.Color;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ConveyorBelt.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class ConveyorBeltTest {
	
	private int numStations1;
	private int numStations2;
	private int numStations3;
	private int numShipments0;
	private int numShipments;
	private Log myLog;
	private ShipmentProcessStation[] station1;
	private ShipmentProcessStation[] station2;
	private ShipmentProcessStation[] station3;
	private ConveyorBelt theBelt0;
	private ConveyorBelt theBelt1;
	private ConveyorBelt theBelt2;
	private ConveyorBelt theBelt3;
	
	/**
	 * Sets up the ConveyorBeltTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		numStations1 = 3;
		numStations2 = 7;
		numStations3 = 9;
		numShipments0 = 0;
		numShipments = 7;
		myLog = new Log();
		station1 = new ShipmentProcessStation[numStations1];
		for (int i = 0; i < station1.length; i++) {
			station1[i] = new ShipmentProcessStation(myLog);
		}
		station2 = new ShipmentProcessStation[numStations2];
		for (int i = 0; i < station2.length; i++) {
			station2[i] = new ShipmentProcessStation(myLog);
		}
		station3 = new ShipmentProcessStation[numStations3];
		for (int i = 0; i < station3.length; i++) {
			station3[i] = new ShipmentProcessStation(myLog);
		}
		BookShipmentFactory.resetFactory();
		theBelt0 = new ConveyorBelt(numShipments0, station1);
		BookShipmentFactory.resetFactory();
		theBelt1 = new ConveyorBelt(numShipments, station1);
		BookShipmentFactory.resetFactory();
		theBelt2 = new ConveyorBelt(numShipments, station2);
		BookShipmentFactory.resetFactory();
		theBelt3 = new ConveyorBelt(numShipments, station3);
		BookShipmentFactory.resetFactory();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		assertEquals(false, theBelt0.hasNext());
		assertEquals(true, theBelt1.hasNext());
		assertEquals(true, theBelt2.hasNext());
		assertEquals(true, theBelt3.hasNext());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#processNext()}.
	 */
	@Test
	public void testProcessNext() {
		ItemToShip package0 = null;
		ItemToShip package1 = null;
		ItemToShip package2 = null;
		ItemToShip package3 = null;
		ItemToShip package4 = null;
		ItemToShip package5 = null;
		ItemToShip package6 = null;
		ItemToShip package7 = null;
		// theBelt0
		try {
			package0 = theBelt0.processNext();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(true, package0 == null);
			assertEquals(false, theBelt0.hasNext());
		}
		// theBelt1
		package1 = theBelt1.processNext();
		assertEquals(3, package1.getArrivalTime());
		assertEquals(10, package1.getProcessTime());
		assertEquals(0, package1.getWaitTime());
		assertEquals(Color.BLACK, package1.getColor());
		assertEquals(0, package1.getStationIndex());
		assertEquals(true, package1.isWaitingInLineAtStation());
		package2 = theBelt1.processNext();
		assertEquals(4, package2.getArrivalTime());
		assertEquals(16, package2.getProcessTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(Color.BLUE, package2.getColor());
		assertEquals(1, package2.getStationIndex());
		assertEquals(true, package2.isWaitingInLineAtStation());
		package3 = theBelt1.processNext();
		assertEquals(8, package3.getArrivalTime());
		assertEquals(16, package3.getProcessTime());
		assertEquals(0, package3.getWaitTime());
		assertEquals(Color.BLUE, package3.getColor());
		assertEquals(2, package3.getStationIndex());
		assertEquals(true, package3.isWaitingInLineAtStation());
		package4 = theBelt1.processNext();
		assertEquals(12, package4.getArrivalTime());
		assertEquals(26, package4.getProcessTime());
		assertEquals(12, package4.getWaitTime());
		assertEquals(Color.RED, package4.getColor());
		assertEquals(2, package4.getStationIndex());
		assertEquals(true, package4.isWaitingInLineAtStation());
		package5 = theBelt1.processNext();
		assertEquals(12, package5.getArrivalTime());
		assertEquals(18, package5.getProcessTime());
		assertEquals(8, package5.getWaitTime());
		assertEquals(Color.BLUE, package5.getColor());
		assertEquals(1, package5.getStationIndex());
		assertEquals(true, package5.isWaitingInLineAtStation());
		package6 = theBelt1.processNext();
		assertEquals(16, package6.getArrivalTime());
		assertEquals(11, package6.getProcessTime());
		assertEquals(-3, package6.getWaitTime());
		assertEquals(Color.BLACK, package6.getColor());
		assertEquals(0, package6.getStationIndex());
		assertEquals(true, package6.isWaitingInLineAtStation());
		package7 = theBelt1.processNext();
		assertEquals(17, package7.getArrivalTime());
		assertEquals(15, package7.getProcessTime());
		assertEquals(21, package7.getWaitTime());
		assertEquals(Color.BLUE, package7.getColor());
		assertEquals(1, package7.getStationIndex());
		assertEquals(true, package7.isWaitingInLineAtStation());
		try {
			package0 = theBelt1.processNext();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(true, package0 == null);
			assertEquals(false, theBelt1.hasNext());
		}
		// theBelt2
		package1 = theBelt2.processNext();
		assertEquals(3, package1.getArrivalTime());
		assertEquals(10, package1.getProcessTime());
		assertEquals(0, package1.getWaitTime());
		assertEquals(Color.BLACK, package1.getColor());
		assertEquals(0, package1.getStationIndex());
		assertEquals(true, package1.isWaitingInLineAtStation());
		package2 = theBelt2.processNext();
		assertEquals(4, package2.getArrivalTime());
		assertEquals(16, package2.getProcessTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(Color.BLUE, package2.getColor());
		assertEquals(1, package2.getStationIndex());
		assertEquals(true, package2.isWaitingInLineAtStation());
		package3 = theBelt2.processNext();
		assertEquals(8, package3.getArrivalTime());
		assertEquals(16, package3.getProcessTime());
		assertEquals(0, package3.getWaitTime());
		assertEquals(Color.BLUE, package3.getColor());
		assertEquals(2, package3.getStationIndex());
		assertEquals(true, package3.isWaitingInLineAtStation());
		package4 = theBelt2.processNext();
		assertEquals(12, package4.getArrivalTime());
		assertEquals(26, package4.getProcessTime());
		assertEquals(0, package4.getWaitTime());
		assertEquals(Color.RED, package4.getColor());
		assertEquals(5, package4.getStationIndex());
		assertEquals(true, package4.isWaitingInLineAtStation());
		package5 = theBelt2.processNext();
		assertEquals(12, package5.getArrivalTime());
		assertEquals(18, package5.getProcessTime());
		assertEquals(0, package5.getWaitTime());
		assertEquals(Color.BLUE, package5.getColor());
		assertEquals(3, package5.getStationIndex());
		assertEquals(true, package5.isWaitingInLineAtStation());
		package6 = theBelt2.processNext();
		assertEquals(16, package6.getArrivalTime());
		assertEquals(11, package6.getProcessTime());
		assertEquals(0, package6.getWaitTime());
		assertEquals(Color.BLACK, package6.getColor());
		assertEquals(4, package6.getStationIndex());
		assertEquals(true, package6.isWaitingInLineAtStation());
		package7 = theBelt2.processNext();
		assertEquals(17, package7.getArrivalTime());
		assertEquals(15, package7.getProcessTime());
		assertEquals(0, package7.getWaitTime());
		assertEquals(Color.BLUE, package7.getColor());
		assertEquals(6, package7.getStationIndex());
		assertEquals(true, package7.isWaitingInLineAtStation());
		try {
			package0 = theBelt2.processNext();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(true, package0 == null);
			assertEquals(false, theBelt2.hasNext());
		}
		// theBelt3
		package1 = theBelt3.processNext();
		assertEquals(3, package1.getArrivalTime());
		assertEquals(10, package1.getProcessTime());
		assertEquals(0, package1.getWaitTime());
		assertEquals(Color.BLACK, package1.getColor());
		assertEquals(0, package1.getStationIndex());
		assertEquals(true, package1.isWaitingInLineAtStation());
		package2 = theBelt3.processNext();
		assertEquals(4, package2.getArrivalTime());
		assertEquals(16, package2.getProcessTime());
		assertEquals(0, package2.getWaitTime());
		assertEquals(Color.BLUE, package2.getColor());
		assertEquals(1, package2.getStationIndex());
		assertEquals(true, package2.isWaitingInLineAtStation());
		package3 = theBelt3.processNext();
		assertEquals(8, package3.getArrivalTime());
		assertEquals(16, package3.getProcessTime());
		assertEquals(0, package3.getWaitTime());
		assertEquals(Color.BLUE, package3.getColor());
		assertEquals(2, package3.getStationIndex());
		assertEquals(true, package3.isWaitingInLineAtStation());
		package4 = theBelt3.processNext();
		assertEquals(12, package4.getArrivalTime());
		assertEquals(26, package4.getProcessTime());
		assertEquals(0, package4.getWaitTime());
		assertEquals(Color.RED, package4.getColor());
		assertEquals(6, package4.getStationIndex());
		assertEquals(true, package4.isWaitingInLineAtStation());
		package5 = theBelt3.processNext();
		assertEquals(12, package5.getArrivalTime());
		assertEquals(18, package5.getProcessTime());
		assertEquals(0, package5.getWaitTime());
		assertEquals(Color.BLUE, package5.getColor());
		assertEquals(3, package5.getStationIndex());
		assertEquals(true, package5.isWaitingInLineAtStation());
		package6 = theBelt3.processNext();
		assertEquals(16, package6.getArrivalTime());
		assertEquals(11, package6.getProcessTime());
		assertEquals(0, package6.getWaitTime());
		assertEquals(Color.BLACK, package6.getColor());
		assertEquals(4, package6.getStationIndex());
		assertEquals(true, package6.isWaitingInLineAtStation());
		package7 = theBelt3.processNext();
		assertEquals(17, package7.getArrivalTime());
		assertEquals(15, package7.getProcessTime());
		assertEquals(0, package7.getWaitTime());
		assertEquals(Color.BLUE, package7.getColor());
		assertEquals(5, package7.getStationIndex());
		assertEquals(true, package7.isWaitingInLineAtStation());
		assertEquals(false, station3[7].hasNext());
		assertEquals(false, station3[7].hasNext());
		try {
			package0 = theBelt3.processNext();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(true, package0 == null);
			assertEquals(false, theBelt3.hasNext());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#departTimeNext()}.
	 */
	@Test
	public void testDepartTimeNext() {
		assertEquals(Integer.MAX_VALUE, theBelt0.departTimeNext());
		assertEquals(3, theBelt1.departTimeNext());
		assertEquals(3, theBelt2.departTimeNext());
		assertEquals(3, theBelt3.departTimeNext());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, theBelt0.size());
		assertEquals(7, theBelt1.size());
		assertEquals(7, theBelt2.size());
		assertEquals(7, theBelt3.size());
	}

}
