package islandtrader.core;

import java.util.ArrayList;
import java.util.Random;

public abstract class GameEnvironment {
	protected final int MINIMUM_DURATION = 20;
	protected final int MAXIMUM_DURATION = 50;
	protected int durationChosenInDays;
	protected int daysElapsed;
	protected boolean isGameOver = false;

	protected ArrayList<Island> availableIslands;
	protected Island currentIsland;
	protected Trader trader;

	protected Island getRandomIsland() {
		int min = 0;
		int max = availableIslands.size() - 1;
		return availableIslands.get(getRandomBetweenMinMax(min, max));
	}

	protected boolean canStillPlay() {
		return daysElapsed < durationChosenInDays && !isGameOver;
	}

	protected int getDaysRemaining() {
		return durationChosenInDays - daysElapsed;
	}

	/**
	 * @return the daysElapsed
	 */
	protected int getDaysElapsed() {
		return daysElapsed;
	}

	/**
	 * @param daysElapsed the daysElapsed to set
	 */
	protected void setDaysElapsed(int daysElapsed) {
		this.daysElapsed = daysElapsed;
	}

	protected RandomEvent getEventFromListOfPossibleEvents(ArrayList<RandomEvent> randomEventList) {

		int randomValue = getRandomBetweenMinMax(0, 100);

		for (RandomEvent randEve : randomEventList) {
			if (randomValue <= randEve.getPercentageChanceOfEncounter()) {
				return randEve;
			}

		}
		return null;

	}

	protected int getRandomBetweenMinMax(int min, int max) {

		return new Random().nextInt(max - min + 1) + min;
	}

	public abstract void startGame();
}
