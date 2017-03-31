import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Simulator.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * @author xellis
 *
 */
public class SimulatorTest {
	
	private int numStations;
	private int numShipments;
	private Simulator simulator;

	/**
	 * Sets up the SimulatorTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		numStations = 7;
		numShipments = 7;
		BookShipmentFactory.resetFactory();
		simulator = new Simulator(numStations, numShipments);
		BookShipmentFactory.resetFactory();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#Simulator(int, int)}.
	 */
	@Test
	public void testSimulator() {
		int tooFewStations = 2;
		try {
			new Simulator(tooFewStations, numShipments);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(true, tooFewStations < 3);
		}
		int tooManyStations = 10;
		try {
			new Simulator(tooManyStations, numShipments);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(true, tooManyStations > 9);
		}
		int tooFewPackages = 0;
		try {
			new Simulator(numStations, tooFewPackages);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(true, tooFewPackages < 1);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#step()}.
	 */
	@Test
	public void testStep() {
		assertEquals(0, simulator.getStepsTaken());
		assertEquals(-1, simulator.getCurrentIndex());
		assertEquals(null, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(1, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(2, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(3, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(4, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(5, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(6, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(7, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(8, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(9, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(26, (int) (simulator.averageProcessTime() * 2));
		
		simulator.step();
		assertEquals(10, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(42, (int) (simulator.averageProcessTime() * 3));
		
		simulator.step();
		assertEquals(11, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(53, (int) (simulator.averageProcessTime() * 4));
		
		simulator.step();
		assertEquals(12, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(71, (int) (simulator.averageProcessTime() * 5));
		
		simulator.step();
		assertEquals(13, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(86, (int) (simulator.averageProcessTime() * 6));
		
		simulator.step();
		assertEquals(14, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(false, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(112, (int) (simulator.averageProcessTime() * 7));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#getStepsTaken()}.
	 */
	@Test
	public void testGetStepsTaken() {
		assertEquals(0, simulator.getStepsTaken());
		for (int i = 0; i < simulator.totalNumberOfSteps(); i++) {
			simulator.step();
			assertEquals(i + 1, simulator.getStepsTaken());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#totalNumberOfSteps()}.
	 */
	@Test
	public void testTotalNumberOfSteps() {
		assertEquals(14, simulator.totalNumberOfSteps());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#moreSteps()}.
	 */
	@Test
	public void testMoreSteps() {
		assertEquals(true, simulator.moreSteps());
		for (int i = 0; i < simulator.totalNumberOfSteps() - 1; i++) {
			simulator.step();
			assertEquals(true, simulator.moreSteps());
		}
		simulator.step();
		assertEquals(false, simulator.moreSteps());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#getCurrentIndex()}.
	 */
	@Test
	public void testGetCurrentIndex() {
		assertEquals(0, simulator.getStepsTaken());
		assertEquals(-1, simulator.getCurrentIndex());
		assertEquals(null, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(1, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(2, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(3, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(4, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(5, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(6, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(7, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(8, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(9, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(26, (int) (simulator.averageProcessTime() * 2));
		
		simulator.step();
		assertEquals(10, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(42, (int) (simulator.averageProcessTime() * 3));
		
		simulator.step();
		assertEquals(11, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(53, (int) (simulator.averageProcessTime() * 4));
		
		simulator.step();
		assertEquals(12, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(71, (int) (simulator.averageProcessTime() * 5));
		
		simulator.step();
		assertEquals(13, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(86, (int) (simulator.averageProcessTime() * 6));
		
		simulator.step();
		assertEquals(14, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(false, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(112, (int) (simulator.averageProcessTime() * 7));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#getCurrentShipmentColor()}.
	 */
	@Test
	public void testGetCurrentShipmentColor() {
		assertEquals(0, simulator.getStepsTaken());
		assertEquals(-1, simulator.getCurrentIndex());
		assertEquals(null, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(1, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(2, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(3, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(4, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(5, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(6, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(7, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(8, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(9, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(26, (int) (simulator.averageProcessTime() * 2));
		
		simulator.step();
		assertEquals(10, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(42, (int) (simulator.averageProcessTime() * 3));
		
		simulator.step();
		assertEquals(11, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(53, (int) (simulator.averageProcessTime() * 4));
		
		simulator.step();
		assertEquals(12, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(71, (int) (simulator.averageProcessTime() * 5));
		
		simulator.step();
		assertEquals(13, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(86, (int) (simulator.averageProcessTime() * 6));
		
		simulator.step();
		assertEquals(14, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(false, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(112, (int) (simulator.averageProcessTime() * 7));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#itemLeftSimulation()}.
	 */
	@Test
	public void testItemLeftSimulation() {
		assertEquals(0, simulator.getStepsTaken());
		assertEquals(-1, simulator.getCurrentIndex());
		assertEquals(null, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(1, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(2, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(3, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(4, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(5, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(6, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(7, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(8, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(9, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(26, (int) (simulator.averageProcessTime() * 2));
		
		simulator.step();
		assertEquals(10, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(42, (int) (simulator.averageProcessTime() * 3));
		
		simulator.step();
		assertEquals(11, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(53, (int) (simulator.averageProcessTime() * 4));
		
		simulator.step();
		assertEquals(12, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(71, (int) (simulator.averageProcessTime() * 5));
		
		simulator.step();
		assertEquals(13, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(86, (int) (simulator.averageProcessTime() * 6));
		
		simulator.step();
		assertEquals(14, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(false, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(112, (int) (simulator.averageProcessTime() * 7));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#averageWaitTime()}.
	 */
	@Test
	public void testAverageWaitTime() {
		assertEquals(0, simulator.getStepsTaken());
		assertEquals(-1, simulator.getCurrentIndex());
		assertEquals(null, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(1, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(2, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(3, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(4, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(5, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(6, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(7, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(8, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(9, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(26, (int) (simulator.averageProcessTime() * 2));
		
		simulator.step();
		assertEquals(10, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(42, (int) (simulator.averageProcessTime() * 3));
		
		simulator.step();
		assertEquals(11, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(53, (int) (simulator.averageProcessTime() * 4));
		
		simulator.step();
		assertEquals(12, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(71, (int) (simulator.averageProcessTime() * 5));
		
		simulator.step();
		assertEquals(13, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(86, (int) (simulator.averageProcessTime() * 6));
		
		simulator.step();
		assertEquals(14, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(false, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(112, (int) (simulator.averageProcessTime() * 7));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#averageProcessTime()}.
	 */
	@Test
	public void testAverageProcessTime() {
		assertEquals(0, simulator.getStepsTaken());
		assertEquals(-1, simulator.getCurrentIndex());
		assertEquals(null, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(1, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(2, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(3, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(4, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(5, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(0, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(6, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(7, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(8, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(false, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(10, (int) simulator.averageProcessTime());
		
		simulator.step();
		assertEquals(9, simulator.getStepsTaken());
		assertEquals(1, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(26, (int) (simulator.averageProcessTime() * 2));
		
		simulator.step();
		assertEquals(10, simulator.getStepsTaken());
		assertEquals(2, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(42, (int) (simulator.averageProcessTime() * 3));
		
		simulator.step();
		assertEquals(11, simulator.getStepsTaken());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(Color.BLACK, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(53, (int) (simulator.averageProcessTime() * 4));
		
		simulator.step();
		assertEquals(12, simulator.getStepsTaken());
		assertEquals(3, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(71, (int) (simulator.averageProcessTime() * 5));
		
		simulator.step();
		assertEquals(13, simulator.getStepsTaken());
		assertEquals(4, simulator.getCurrentIndex());
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
		assertEquals(true, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(86, (int) (simulator.averageProcessTime() * 6));
		
		simulator.step();
		assertEquals(14, simulator.getStepsTaken());
		assertEquals(5, simulator.getCurrentIndex());
		assertEquals(Color.RED, simulator.getCurrentShipmentColor());
		assertEquals(false, simulator.moreSteps());
		assertEquals(true, simulator.itemLeftSimulation());
		assertEquals(0, (int) simulator.averageWaitTime());
		assertEquals(112, (int) (simulator.averageProcessTime() * 7));
	}

}
