
/**
* Contains the basic classes to build the game.
* For example:Trader, Ship, Route, RandomEvent, Island, Store
*/
package islandtrader.core;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class with the main methods to allow the game to run only under the
 * required conditions from project scope. The command line application and the
 * GUI application will use the properties defined here along with their own
 * other necessary properties and methods
 *
 * @author Maria Heloisa Balzaretti
 *
 */

public abstract class GameEnvironment {
	/**
	 * The minimum duration of the game is 20 days
	 */
	protected final int MINIMUM_DURATION = 20;

	/**
	 * The maximum duration of the game is 50 days
	 */
	protected final int MAXIMUM_DURATION = 50;

	/**
	 * The duration of the game that the player has chosen between the minimum and
	 * maximum values.
	 */
	protected int durationChosenInDays;

	/**
	 * The total of days already elapsed in the game
	 */
	protected int daysElapsed;

	/**
	 * To help terminate the game when certain conditions are met.
	 */
	protected boolean isGameOver = false;

	/**
	 * The Array of the Island objects available to be visited in the game
	 */
	protected ArrayList<Island> availableIslands;

	/**
	 * The current Island where the Trader is located at the moment
	 */
	protected Island currentIsland;

	/**
	 * The trader is the player of the game.
	 */
	protected Trader trader;

	/**
	 * Gets randomly a home Island for the Trader
	 *
	 * @return
	 */
	protected Island getRandomIsland() {
		int min = 0;
		int max = availableIslands.size() - 1;
		return availableIslands.get(getRandomBetweenMinMax(min, max));
	}

	/**
	 * To know if the game can still happen. It checks for the number of elapsed
	 * days and the isGameOver boolean property.
	 *
	 * @return
	 */
	protected boolean canStillPlay() {
		return daysElapsed < durationChosenInDays && !isGameOver;
	}

	/**
	 * Gets the amount of remaining days available for the Trader to sail.
	 *
	 * @return
	 */
	public int getDaysRemaining() {
		return durationChosenInDays - daysElapsed;
	}

	/**
	 * @return the amount of daysElapsed, the days already used sailing
	 */
	public int getDaysElapsed() {
		return daysElapsed;
	}

	/**
	 * @param daysElapsed the daysElapsed to set
	 */
	public void setDaysElapsed(int daysElapsed) {
		this.daysElapsed = daysElapsed;
	}

	/**
	 * Gets a random event that happens when sailing
	 *
	 * @param randomEventList
	 * @return
	 */
	public RandomEvent getEventFromListOfPossibleEvents(ArrayList<RandomEvent> randomEventList) {

		int randomValue = getRandomBetweenMinMax(0, 100);

		for (RandomEvent randEve : randomEventList) {
			if (randomValue <= randEve.getPercentageChanceOfEncounter()) {
				return randEve;
			}
		}
		return null;
	}

	/**
	 * Gets a number randomly to help the random events to be chosen for the route
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	protected int getRandomBetweenMinMax(int min, int max) {

		return new Random().nextInt(max - min + 1) + min;
	}

	/**
	 * The entry point to start the game in each application.
	 */
	public abstract void startGame();
}
