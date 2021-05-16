/**
 *
 */
package islandtrader.core;

/**
 * Represents the Random Events that the Trader may encounter while sailing. The
 * Trader can encounter Pirates, Bad Weather or Sailors to rescue. each will
 * have a different action in the game. These are allocated randomly to the
 * Routes of each Island.
 *
 * @author Maria
 *
 */

public abstract class RandomEvent extends Entity implements Cloneable {
	protected float percentageChanceOfEncounter;

	/**
	 * Creates a new RandomEvent
	 *
	 * @param name
	 * @param description
	 * @param percentageChanceOfEncounter
	 */
	public RandomEvent(int idNumber, String name, String description) {
		super(idNumber, name, description);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * i.e. percentage in the format 0.2 to represent 20%.
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
		// fix it ***************************************
		return name + description;
	}

	/**
	 * This method is to be implemented depending on each RandomEvent action.
	 */
	public abstract void randomEventSpecificAction(Trader myTrader);

	public abstract String encounterMessage();

	public abstract String resultOfEncounterMessage();

	public abstract String getEventSpecificName();

}
