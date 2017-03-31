import static org.junit.Assert.*;

import java.awt.Color;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ShipmentProcessStation.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class ShipmentProcessStationTest {
	
	private int numStations1;
	private int numStations2;
	private int numStations3;
	private int numShipments;
	private Log myLog;
	private ShipmentProcessStation[] station1;
	private ShipmentProcessStation[] station2;
	private ShipmentProcessStation[] station3;
	private ConveyorBelt theBelt1;
	private ConveyorBelt theBelt2;
	private ConveyorBelt theBelt3;
	private ShipmentProcessStation shipmentProcessStation;
	private ItemToShip package1;
	private ItemToShip package2;
	private ItemToShip package3;
	private ItemToShip package4;
	private ItemToShip package5;
	private ItemToShip package6;
	private ItemToShip package7;
	
	/**
	 * Sets up the ShipmentProcessStationTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		numStations1 = 4;
		numStations2 = 6;
		numStations3 = 8;
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
		theBelt1 = new ConveyorBelt(numShipments, station1);
		BookShipmentFactory.resetFactory();
		theBelt2 = new ConveyorBelt(numShipments, station2);
		BookShipmentFactory.resetFactory();
		theBelt3 = new ConveyorBelt(numShipments, station3);
		BookShipmentFactory.resetFactory();
		shipmentProcessStation = new ShipmentProcessStation(myLog);
		package1 = theBelt1.processNext();
		package2 = theBelt1.processNext();
		package3 = theBelt1.processNext();
		package4 = theBelt1.processNext();
		package5 = theBelt1.processNext();
		package6 = theBelt1.processNext();
		package7 = theBelt1.processNext();
		theBelt2.processNext();
		theBelt2.processNext();
		theBelt2.processNext();
		theBelt2.processNext();
		theBelt2.processNext();
		theBelt2.processNext();
		theBelt2.processNext();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		assertEquals(false, shipmentProcessStation.hasNext());
		shipmentProcessStation.addItemToLine(package1);
		assertEquals(true, shipmentProcessStation.hasNext());
		shipmentProcessStation.addItemToLine(package2);
		assertEquals(true, shipmentProcessStation.hasNext());
		shipmentProcessStation.addItemToLine(package3);
		assertEquals(true, shipmentProcessStation.hasNext());
		shipmentProcessStation.addItemToLine(package4);
		assertEquals(true, shipmentProcessStation.hasNext());
		shipmentProcessStation.addItemToLine(package5);
		assertEquals(true, shipmentProcessStation.hasNext());
		shipmentProcessStation.addItemToLine(package6);
		assertEquals(true, shipmentProcessStation.hasNext());
		shipmentProcessStation.addItemToLine(package7);
		assertEquals(true, shipmentProcessStation.hasNext());
		for (int i = 0; i < station1.length; i++) {
			assertEquals(true, station1[i].hasNext());
		}
		for (int i = 0; i < station2.length; i++) {
			assertEquals(true, station2[i].hasNext());
		}
		for (int i = 0; i < station3.length; i++) {
			assertEquals(false, station3[i].hasNext());
		}
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		for (int i = 0; i < station3.length - 1; i++) {
			assertEquals(true, station3[i].hasNext());
		}
		assertEquals(false, station3[station3.length - 1].hasNext());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#processNext()}.
	 */
	@Test
	public void testProcessNext() {
		ItemToShip package0 = null;
		try {
			package0 = shipmentProcessStation.processNext();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(true, package0 == null);
			assertEquals(false, shipmentProcessStation.hasNext());
			assertEquals(0, shipmentProcessStation.size());
		}
		package0 = station1[0].processNext();
		assertEquals(3, package0.getArrivalTime());
		assertEquals(10, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station1[1].processNext();
		assertEquals(4, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station1[2].processNext();
		assertEquals(8, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station1[3].processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(26, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.RED, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station1[0].processNext();
		assertEquals(16, package0.getArrivalTime());
		assertEquals(11, package0.getProcessTime());
		assertEquals(-3, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station1[1].processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(18, package0.getProcessTime());
		assertEquals(8, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station1[2].processNext();
		assertEquals(17, package0.getArrivalTime());
		assertEquals(15, package0.getProcessTime());
		assertEquals(7, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		
		package0 = station2[0].processNext();
		assertEquals(3, package0.getArrivalTime());
		assertEquals(10, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station2[1].processNext();
		assertEquals(4, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station2[2].processNext();
		assertEquals(8, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station2[3].processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(18, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station2[4].processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(26, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.RED, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station2[5].processNext();
		assertEquals(16, package0.getArrivalTime());
		assertEquals(11, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station2[1].processNext();
		assertEquals(17, package0.getArrivalTime());
		assertEquals(15, package0.getProcessTime());
		assertEquals(3, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		package0 = station3[0].processNext();
		assertEquals(3, package0.getArrivalTime());
		assertEquals(10, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station3[1].processNext();
		assertEquals(4, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station3[2].processNext();
		assertEquals(8, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station3[3].processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(18, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station3[4].processNext();
		assertEquals(16, package0.getArrivalTime());
		assertEquals(11, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station3[5].processNext();
		assertEquals(17, package0.getArrivalTime());
		assertEquals(15, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = station3[6].processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(26, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.RED, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());

		package0 = null;
		try {
			package0 = station3[station3.length - 1].processNext();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(true, package0 == null);
			assertEquals(false, station3[station3.length - 1].hasNext());
			assertEquals(0, station3[station3.length - 1].size());
		}
		shipmentProcessStation.addItemToLine(package1);
		shipmentProcessStation.addItemToLine(package2);
		shipmentProcessStation.addItemToLine(package3);
		shipmentProcessStation.addItemToLine(package4);
		shipmentProcessStation.addItemToLine(package5);
		shipmentProcessStation.addItemToLine(package6);
		shipmentProcessStation.addItemToLine(package7);
		package0 = shipmentProcessStation.processNext();
		assertEquals(3, package0.getArrivalTime());
		assertEquals(10, package0.getProcessTime());
		assertEquals(0, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = shipmentProcessStation.processNext();
		assertEquals(4, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(9, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = shipmentProcessStation.processNext();
		assertEquals(8, package0.getArrivalTime());
		assertEquals(16, package0.getProcessTime());
		assertEquals(21, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = shipmentProcessStation.processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(26, package0.getProcessTime());
		assertEquals(33, package0.getWaitTime());
		assertEquals(Color.RED, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = shipmentProcessStation.processNext();
		assertEquals(12, package0.getArrivalTime());
		assertEquals(18, package0.getProcessTime());
		assertEquals(59, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = shipmentProcessStation.processNext();
		assertEquals(16, package0.getArrivalTime());
		assertEquals(11, package0.getProcessTime());
		assertEquals(73, package0.getWaitTime());
		assertEquals(Color.BLACK, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
		package0 = shipmentProcessStation.processNext();
		assertEquals(17, package0.getArrivalTime());
		assertEquals(15, package0.getProcessTime());
		assertEquals(83, package0.getWaitTime());
		assertEquals(Color.BLUE, package0.getColor());
		assertEquals(false, package0.isWaitingInLineAtStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#departTimeNext()}.
	 */
	@Test
	public void testDepartTimeNext() {
		assertEquals(Integer.MAX_VALUE, shipmentProcessStation.departTimeNext());
		assertEquals(13, station1[0].departTimeNext());
		assertEquals(20, station1[1].departTimeNext());
		assertEquals(24, station1[2].departTimeNext());
		assertEquals(38, station1[3].departTimeNext());
		station1[0].processNext();
		station1[1].processNext();
		station1[2].processNext();
		station1[3].processNext();
		assertEquals(24, station1[0].departTimeNext());
		assertEquals(38, station1[1].departTimeNext());
		assertEquals(39, station1[2].departTimeNext());
		assertEquals(Integer.MAX_VALUE, station1[3].departTimeNext());
		
		assertEquals(13, station2[0].departTimeNext());
		assertEquals(20, station2[1].departTimeNext());
		assertEquals(24, station2[2].departTimeNext());
		assertEquals(30, station2[3].departTimeNext());
		assertEquals(38, station2[4].departTimeNext());
		assertEquals(27, station2[5].departTimeNext());
		station2[0].processNext();
		station2[1].processNext();
		assertEquals(Integer.MAX_VALUE, station2[0].departTimeNext());
		assertEquals(35, station2[1].departTimeNext());
		
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		assertEquals(13, station3[0].departTimeNext());
		assertEquals(20, station3[1].departTimeNext());
		assertEquals(24, station3[2].departTimeNext());
		assertEquals(30, station3[3].departTimeNext());
		assertEquals(27, station3[4].departTimeNext());
		assertEquals(32, station3[5].departTimeNext());
		assertEquals(38, station3[6].departTimeNext());
		assertEquals(Integer.MAX_VALUE, station3[7].departTimeNext());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, shipmentProcessStation.size());
		shipmentProcessStation.addItemToLine(package1);
		assertEquals(1, shipmentProcessStation.size());
		shipmentProcessStation.addItemToLine(package2);
		assertEquals(2, shipmentProcessStation.size());
		shipmentProcessStation.addItemToLine(package3);
		assertEquals(3, shipmentProcessStation.size());
		shipmentProcessStation.addItemToLine(package4);
		assertEquals(4, shipmentProcessStation.size());
		shipmentProcessStation.addItemToLine(package5);
		assertEquals(5, shipmentProcessStation.size());
		shipmentProcessStation.addItemToLine(package6);
		assertEquals(6, shipmentProcessStation.size());
		shipmentProcessStation.addItemToLine(package7);
		assertEquals(7, shipmentProcessStation.size());
		assertEquals(2, station1[0].size());
		assertEquals(2, station1[1].size());
		assertEquals(2, station1[2].size());
		assertEquals(1, station1[3].size());
		assertEquals(1, station2[0].size());
		assertEquals(2, station2[1].size());
		assertEquals(1, station2[2].size());
		assertEquals(1, station2[3].size());
		assertEquals(1, station2[4].size());
		assertEquals(1, station2[5].size());
		for (int i = 0; i < station3.length; i++) {
			assertEquals(0, station3[i].size());
		}
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		theBelt3.processNext();
		assertEquals(1, station3[0].size());
		assertEquals(1, station3[1].size());
		assertEquals(1, station3[2].size());
		assertEquals(1, station3[3].size());
		assertEquals(1, station3[4].size());
		assertEquals(1, station3[5].size());
		assertEquals(1, station3[6].size());
		assertEquals(0, station3[7].size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#addItemToLine(edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip)}.
	 */
	@Test
	public void testAddItemToLine() {
		shipmentProcessStation.addItemToLine(package1);
		shipmentProcessStation.addItemToLine(package2);
		shipmentProcessStation.addItemToLine(package3);
		shipmentProcessStation.addItemToLine(package4);
		shipmentProcessStation.addItemToLine(package5);
		shipmentProcessStation.addItemToLine(package6);
		shipmentProcessStation.addItemToLine(package7);
		assertEquals(0, package1.getWaitTime());
		assertEquals(9, package2.getWaitTime());
		assertEquals(21, package3.getWaitTime());
		assertEquals(33, package4.getWaitTime());
		assertEquals(59, package5.getWaitTime());
		assertEquals(73, package6.getWaitTime());
		assertEquals(83, package7.getWaitTime());
	}

}
