/**
 * This package is a helper to configure the game.
 */
package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Import the core classes related to the RandomEvent.
 */
import islandtrader.core.Pirate;
import islandtrader.core.RandomEvent;
import islandtrader.core.RescuedSailor;
import islandtrader.core.UnfortunateWeather;

/**
 * It is a helper service for the RandomEvents, it is used to create the
 * RandomEvents that will be added to the routes in the IslandRepository
 * (getRoutesForIsland(Island newIsland, RandomEventService res)), according to
 * the percentage chance of encounter, which is not final to 100% so the Trader
 * has a chance of nothing happening.
 *
 * @author Maria Heloisa Balzaretti
 *
 */

public class RandomEventService {

	/**
	 * Array list of RandomEvent of type Pirate
	 */
	private ArrayList<Pirate> pirates;

	/**
	 * Array list of RandomEvent of type RescuedSailor
	 */
	private ArrayList<RescuedSailor> sailors;

	/**
	 * Array list of RandomEvent of type UnfortunateWeather
	 */
	private ArrayList<UnfortunateWeather> weatherList;

	/**
	 * The minimum percentage chance of encounter for the RandomEvent in the Route
	 */
	private final int MIN_PERCENTAGE_OF_ENCOUNTER = 5;

	/**
	 * The maximum percentage chance of encounter for the RandomEvent in the Route
	 */
	private final int MAX_PERCENTAGE_OF_ENCOUNTER = 80;

	/**
	 * Creates a new Random Event Service It receives a list of pirates, sailors and
	 * weatherList, so we don't need to provide it every time we need a list of
	 * random events.
	 *
	 * @param pirates
	 * @param sailors
	 * @param weatherList
	 */
	public RandomEventService(ArrayList<Pirate> pirates, ArrayList<RescuedSailor> sailors,
			ArrayList<UnfortunateWeather> weatherList) {
		this.pirates = pirates;
		this.sailors = sailors;
		this.weatherList = weatherList;
	}

	/**
	 * Get random events for a route. It gets randomly one pirate, one sailor and
	 * one weather, shuffling their positions in the list to make it even more
	 * random.
	 *
	 * @return
	 */
	public ArrayList<RandomEvent> getRandomEventsForRoute() {
		ArrayList<RandomEvent> events = new ArrayList<RandomEvent>();
		events.add(getRandomEventFromList(pirates));
		events.add(getRandomEventFromList(sailors));
		events.add(getRandomEventFromList(weatherList));
		Collections.shuffle(events);
		return events;
	}

	/**
	 * Get a random item from the list passed as argument, setting its percentage of
	 * encounter with a random value.
	 *
	 * We need to get a cloned object as we are going to change its percentage of
	 * encounter randomly.
	 *
	 * @param list
	 * @return
	 */
	private RandomEvent getRandomEventFromList(ArrayList<? extends RandomEvent> list) {
		RandomEvent event = null;
		try {
			event = (RandomEvent) list.get(getRandomNumberFromMax(list.size() - 1)).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		event.setPercentageChanceOfEncounter(getPercentageOfEncounterEvent());
		return event;
	}

	/**
	 * Gets the random percentage value for a random event.
	 *
	 * @return
	 */
	private int getPercentageOfEncounterEvent() {
		return getRandomNumberFromMinToMax(MIN_PERCENTAGE_OF_ENCOUNTER, MAX_PERCENTAGE_OF_ENCOUNTER);
	}

	/**
	 * Gets the maximum random number
	 *
	 * @param max
	 * @return
	 */
	private int getRandomNumberFromMax(int max) {
		return getRandomNumberFromMinToMax(0, max);
	}

	/**
	 * gets the random number
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	private int getRandomNumberFromMinToMax(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
