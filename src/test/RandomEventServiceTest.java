package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import islandtrader.core.Pirate;
import islandtrader.core.RandomEvent;
import islandtrader.core.RescuedSailor;
import islandtrader.core.UnfortunateWeather;
import service.RandomEventService;

class RandomEventServiceTest {

	@Test
	public void getRandomEventsForRouteTest() {
		RescuedSailor sailor = new RescuedSailor(10, "test", "test", 10, (float) 10.0);
		UnfortunateWeather weather = new UnfortunateWeather(10, "test", "test");
		Pirate pirate = new Pirate(10, "test", "test", 2, 5);
		ArrayList<Pirate> pirates = new ArrayList<Pirate>();
		pirates.add(pirate);
		ArrayList<UnfortunateWeather> weatherList = new ArrayList<UnfortunateWeather>();
		weatherList.add(weather);
		ArrayList<RescuedSailor> sailors = new ArrayList<RescuedSailor>();
		sailors.add(sailor);

		RandomEventService service = new RandomEventService(pirates, sailors, weatherList);
		ArrayList<RandomEvent> randomEvents = service.getRandomEventsForRoute();

		assertEquals(3, randomEvents.size());
	}
}
