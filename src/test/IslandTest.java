package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import islandtrader.core.Island;
import islandtrader.core.Store;

class IslandTest {

	@Test
	public void getDistanceToAnotherIslandInKmTest() {
		Store store = new Store("store", "atisland", 2, 1);
		int[] distanceInDays = { 10, 5 };
		Island island = new Island(10, "Island test", "test", distanceInDays, store);

		int expectedDistance = distanceInDays[0] * 24 * 20;
		assertEquals(expectedDistance, island.getDistanceToAnotherIslandInKm(0));
	}

	@Test
	public void getDistanceToAnotherIslandInDays() {
		Store store = new Store("store", "atisland", 2, 1);
		int[] distanceInDays = { 10, 5 };
		Island island = new Island(10, "Island test", "test", distanceInDays, store);

		int expectedDistance = distanceInDays[0];
		assertEquals(expectedDistance, island.getDistanceToAnotherIslandInDays(0));
	}
}
