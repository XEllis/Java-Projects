import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Log.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class LogTest {
	
	private Log myLog;
	private int numStations;
	private int numShipments;
	private ShipmentProcessStation[] station;
	private ConveyorBelt theBelt;
	private ItemToShip package1;
	private ItemToShip package2;
	private ItemToShip package3;
	private ItemToShip package4;
	private ItemToShip package5;
	private ItemToShip package6;
	private ItemToShip package7;
	
	/**
	 * Sets up the LogTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		numStations = 5;
		numShipments = 7;
		myLog = new Log();
		station = new ShipmentProcessStation[numStations];
		for (int i = 0; i < station.length; i++) {
			station[i] = new ShipmentProcessStation(myLog);
		}
		BookShipmentFactory.resetFactory();
		theBelt = new ConveyorBelt(numShipments, station);
		BookShipmentFactory.resetFactory();
		package1 = theBelt.processNext();
		package2 = theBelt.processNext();
		package3 = theBelt.processNext();
		package4 = theBelt.processNext();
		package5 = theBelt.processNext();
		package6 = theBelt.processNext();
		package7 = theBelt.processNext();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#getNumCompleted()}.
	 */
	@Test
	public void testGetNumCompleted() {
		assertEquals(0, myLog.getNumCompleted());
		assertEquals(0, (int) myLog.averageWaitTime());
		assertEquals(0, (int) myLog.averageProcessTime());
		myLog.logItem(package1);
		assertEquals(1, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(10, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package2);
		assertEquals(2, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(26, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package3);
		assertEquals(3, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(42, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package4);
		assertEquals(4, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(68, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package5);
		assertEquals(5, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(86, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package6);
		assertEquals(6, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(97, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package7);
		assertEquals(7, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(112, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(true, package1.isWaitingInLineAtStation());
		assertEquals(true, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
		assertEquals(true, package4.isWaitingInLineAtStation());
		assertEquals(true, package5.isWaitingInLineAtStation());
		assertEquals(true, package6.isWaitingInLineAtStation());
		assertEquals(true, package7.isWaitingInLineAtStation());
		
		station[0].processNext();
		assertEquals(8, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(122, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(9, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(138, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[2].processNext();
		assertEquals(10, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(154, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[3].processNext();
		assertEquals(11, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(180, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[4].processNext();
		assertEquals(12, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(198, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[0].processNext();
		assertEquals(13, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(209, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(14, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(224, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(false, package3.isWaitingInLineAtStation());
		assertEquals(false, package4.isWaitingInLineAtStation());
		assertEquals(false, package5.isWaitingInLineAtStation());
		assertEquals(false, package6.isWaitingInLineAtStation());
		assertEquals(false, package7.isWaitingInLineAtStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#logItem(edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip)}.
	 */
	@Test
	public void testLogItem() {
		assertEquals(0, myLog.getNumCompleted());
		assertEquals(0, (int) myLog.averageWaitTime());
		assertEquals(0, (int) myLog.averageProcessTime());
		myLog.logItem(package1);
		assertEquals(1, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(10, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package2);
		assertEquals(2, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(26, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package3);
		assertEquals(3, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(42, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package4);
		assertEquals(4, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(68, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package5);
		assertEquals(5, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(86, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package6);
		assertEquals(6, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(97, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package7);
		assertEquals(7, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(112, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(true, package1.isWaitingInLineAtStation());
		assertEquals(true, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
		assertEquals(true, package4.isWaitingInLineAtStation());
		assertEquals(true, package5.isWaitingInLineAtStation());
		assertEquals(true, package6.isWaitingInLineAtStation());
		assertEquals(true, package7.isWaitingInLineAtStation());
		
		station[0].processNext();
		assertEquals(8, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(122, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(9, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(138, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[2].processNext();
		assertEquals(10, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(154, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[3].processNext();
		assertEquals(11, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(180, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[4].processNext();
		assertEquals(12, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(198, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[0].processNext();
		assertEquals(13, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(209, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(14, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(224, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(false, package3.isWaitingInLineAtStation());
		assertEquals(false, package4.isWaitingInLineAtStation());
		assertEquals(false, package5.isWaitingInLineAtStation());
		assertEquals(false, package6.isWaitingInLineAtStation());
		assertEquals(false, package7.isWaitingInLineAtStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#averageWaitTime()}.
	 */
	@Test
	public void testAverageWaitTime() {
		assertEquals(0, myLog.getNumCompleted());
		assertEquals(0, (int) myLog.averageWaitTime());
		assertEquals(0, (int) myLog.averageProcessTime());
		myLog.logItem(package1);
		assertEquals(1, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(10, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package2);
		assertEquals(2, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(26, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package3);
		assertEquals(3, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(42, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package4);
		assertEquals(4, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(68, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package5);
		assertEquals(5, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(86, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package6);
		assertEquals(6, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(97, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package7);
		assertEquals(7, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(112, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(true, package1.isWaitingInLineAtStation());
		assertEquals(true, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
		assertEquals(true, package4.isWaitingInLineAtStation());
		assertEquals(true, package5.isWaitingInLineAtStation());
		assertEquals(true, package6.isWaitingInLineAtStation());
		assertEquals(true, package7.isWaitingInLineAtStation());
		
		station[0].processNext();
		assertEquals(8, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(122, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(9, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(138, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[2].processNext();
		assertEquals(10, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(154, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[3].processNext();
		assertEquals(11, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(180, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[4].processNext();
		assertEquals(12, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(198, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[0].processNext();
		assertEquals(13, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(209, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(14, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(224, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(false, package3.isWaitingInLineAtStation());
		assertEquals(false, package4.isWaitingInLineAtStation());
		assertEquals(false, package5.isWaitingInLineAtStation());
		assertEquals(false, package6.isWaitingInLineAtStation());
		assertEquals(false, package7.isWaitingInLineAtStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#averageProcessTime()}.
	 */
	@Test
	public void testAverageProcessTime() {
		assertEquals(0, myLog.getNumCompleted());
		assertEquals(0, (int) myLog.averageWaitTime());
		assertEquals(0, (int) myLog.averageProcessTime());
		myLog.logItem(package1);
		assertEquals(1, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(10, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package2);
		assertEquals(2, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(26, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package3);
		assertEquals(3, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(42, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package4);
		assertEquals(4, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(68, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package5);
		assertEquals(5, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(86, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package6);
		assertEquals(6, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(97, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		myLog.logItem(package7);
		assertEquals(7, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(112, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(true, package1.isWaitingInLineAtStation());
		assertEquals(true, package2.isWaitingInLineAtStation());
		assertEquals(true, package3.isWaitingInLineAtStation());
		assertEquals(true, package4.isWaitingInLineAtStation());
		assertEquals(true, package5.isWaitingInLineAtStation());
		assertEquals(true, package6.isWaitingInLineAtStation());
		assertEquals(true, package7.isWaitingInLineAtStation());
		
		station[0].processNext();
		assertEquals(8, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(122, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(9, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(138, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[2].processNext();
		assertEquals(10, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(154, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[3].processNext();
		assertEquals(11, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(180, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[4].processNext();
		assertEquals(12, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(198, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[0].processNext();
		assertEquals(13, myLog.getNumCompleted());
		assertEquals(-3, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(209, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		station[1].processNext();
		assertEquals(14, myLog.getNumCompleted());
		assertEquals(0, (int) (myLog.getNumCompleted() * myLog.averageWaitTime()));
		assertEquals(224, (int) (myLog.getNumCompleted() * myLog.averageProcessTime()));
		
		assertEquals(false, package1.isWaitingInLineAtStation());
		assertEquals(false, package2.isWaitingInLineAtStation());
		assertEquals(false, package3.isWaitingInLineAtStation());
		assertEquals(false, package4.isWaitingInLineAtStation());
		assertEquals(false, package5.isWaitingInLineAtStation());
		assertEquals(false, package6.isWaitingInLineAtStation());
		assertEquals(false, package7.isWaitingInLineAtStation());
	}

}
