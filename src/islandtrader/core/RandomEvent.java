/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Represents the Random Events that the Trader may encounter while sailing. The
 * Trader can encounter Pirates, Unfortunate Weather or Sailors to rescue. Each
 * will have a different action in the game. These are allocated randomly to the
 * Routes of each Island.
 *
 * @author Maria Heloisa Balzaretti
 *
 */

public abstract class RandomEvent extends Entity implements Cloneable {

	/**
	 * Percentage chance of encounter is used to calculate the chance of the
	 * RandomEvent happening in the route.
	 */
	protected float percentageChanceOfEncounter;

	/**
	 * Creates a new RandomEvent
	 *
	 * @param idNumbe
	 * @param name
	 * @param description
	 * @param percentageChanceOfEncounter
	 */
	public RandomEvent(int idNumber, String name, String description) {
		super(idNumber, name, description);
	}

	/**
	 * To be able to use the event with different properties.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Used to set the chance of the random encounter for each route. i.e.
	 * percentage in the format 0.2 to represent 20%.
	 *
	 * @return the percentage of chance of a given RandomEvent happening.
	 */
	public float getPercentageChanceOfEncounter() {
		return percentageChanceOfEncounter;
	}

	/**
	 * Sets the percentage chance of a given RandomEvent happening. i.e. percentage
	 * in the format 0.2 to represent 20%.
	 *
	 * @param percentageChanceOfEncounter is the new percentage chance for the
	 *                                    event.
	 */
	public void setPercentageChanceOfEncounter(float percentageChanceOfEncounter) {
		this.percentageChanceOfEncounter = percentageChanceOfEncounter;
	}

	/**
	 * Override toString method to give a background about the RandomEvent.
	 */
	@Override
	public String toString() {
		return name + description;
	}

	/**
	 * This method is to be implemented depending on each RandomEvent specific
	 * context.
	 */
	public abstract void randomEventSpecificAction(Trader myTrader);

	/**
	 * A message used to inform the Trader of the encounter.
	 *
	 * @return string to be set in each specific event
	 */
	public abstract String encounterMessage();

	/**
	 * A message used to inform the Trader of the outcome of the RandomEvent
	 *
	 * @return string to be set in each specific event
	 */
	public abstract String resultOfEncounterMessage();

	/**
	 * Gets the event specific name: Pirate, UnfortunateWeather or RescuedSailor
	 *
	 * @return string to be set in each specific event
	 */
	public abstract String getEventSpecificName();

}
