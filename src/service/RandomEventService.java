package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import islandtrader.core.Pirate;
import islandtrader.core.RandomEvent;
import islandtrader.core.RescuedSailor;
import islandtrader.core.UnfortunateWeather;

public class RandomEventService {

	private ArrayList<Pirate> pirates;
	private ArrayList<RescuedSailor> sailors;
	private ArrayList<UnfortunateWeather> weatherList;
	private final int MIN_PERCENTAGE_OF_ENCOUNTER = 5;
	private final int MAX_PERCENTAGE_OF_ENCOUNTER = 70;

	/**
	 * The constructor of the class. It receives a list of pirates, sailors and
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
			event = (RandomEvent) list.get(GetRandomNumberFromMax(list.size() - 1)).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		event.setPercentageChanceOfEncounter(getPercentageOfEncounterEvent());
		return event;
	}

	/**
	 * Get the random percentage value for a random event.
	 *
	 * @return
	 */
	private int getPercentageOfEncounterEvent() {
		return getRandomNumberFromMinToMax(MIN_PERCENTAGE_OF_ENCOUNTER, MAX_PERCENTAGE_OF_ENCOUNTER);
	}

	private int GetRandomNumberFromMax(int max) {
		return getRandomNumberFromMinToMax(0, max);
	}

	private int getRandomNumberFromMinToMax(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
