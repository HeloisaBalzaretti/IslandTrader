package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import islandtrader.core.RescuedSailor;
import islandtrader.core.Ship;
import islandtrader.core.Trader;

class RescuedSailorTest {

	@Test
	public void randomEventSpecificActionTest() {
		RescuedSailor sailor = new RescuedSailor(10, "test", "test", 10, (float) 10.0);
		Ship ship = new Ship(1, "test", "test", 10, 10, 10, 10, 10);
		Trader trader = new Trader("test", "description", ship);
		double initValue = trader.getTraderAccountBalance();
		sailor.randomEventSpecificAction(trader);

		assertNotEquals(initValue, trader.getTraderAccountBalance());
	}
}
