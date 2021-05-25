package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import islandtrader.core.Ship;
import islandtrader.core.Trader;
import islandtrader.core.UnfortunateWeather;

class UnfortunateWeatherTest {

	@Test
	public void randomEventSpecificActionTest() {
		UnfortunateWeather weather = new UnfortunateWeather(10, "test", "test");
		Ship ship = new Ship(1, "test", "test", 10, 10, 10, 10, 10);
		Trader trader = new Trader("test", "description", ship);
		weather.randomEventSpecificAction(trader);
		int maxHealthStatus = 1000;
		assertEquals(false, ship.isAbleToSail());
		assertNotEquals(maxHealthStatus, ship.getCurrentHealthStatus());
	}
}
