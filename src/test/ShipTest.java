package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import islandtrader.core.Ship;

class ShipTest {

	@Test
	public void getCrewCostToSailDailyTest() {
		int initialCrew = 10;
		Ship ship = new Ship(1, "test", "test", 10, 10, initialCrew, 10, 10);
		double initialCrewCostToSail = 3.9;
		assertEquals(initialCrew * initialCrewCostToSail, ship.getCrewCostToSailDaily());
	}

	@Test
	public void applyDamageToCurrentHealthStatusTest() {
		Ship ship = new Ship(1, "test", "test", 10, 10, 10, 10, 10);
		double initalHealth = ship.getCurrentHealthStatus();
		ship.applyDamageToCurrentHealthStatus(100);
		assertNotEquals(initalHealth, ship.getCurrentHealthStatus());
	}

	@Test
	public void repairTest() {
		Ship ship = new Ship(1, "test", "test", 10, 10, 10, 10, 10);
		double initalHealth = ship.getCurrentHealthStatus();
		ship.applyDamageToCurrentHealthStatus(100);
		ship.repair();
		assertEquals(initalHealth, ship.getCurrentHealthStatus());
	}
}
