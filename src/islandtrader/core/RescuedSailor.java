/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Represents the RandomEvent "Rescued Sailors", when the Traders have this
 * encounters they are rewarded with a monetary amount, that will vary depending
 * on the number of Rescued Sailors they save.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class RescuedSailor extends RandomEvent {

	/**
	 * Represents how many sailors need rescue
	 */
	private int quantityOfSailorsToRescue;

	/**
	 * the amount to be awarded to the Trader
	 */
	private double monetaryRewardPerSailor;

	/**
	 * Multiplies quantityOfSailorsToRescue and monetaryRewardPerSailor to give the
	 * final amount of money the Trader shall receive as a reward.
	 */
	private double totalMonetaryReward = 0;

	/**
	 * Creates a new RescuedSailor event of type RandomEvent
	 *
	 * @param idNumber                  this RescuedSailor id number
	 * @param name                      this RescuedSailor name
	 * @param description               this RescuedSailor description
	 * @param quantityOfSailorsToRescue this RescuedSailor quantity of crew members
	 *                                  to rescue
	 * @param monetaryRewardPerSailor   this RescuedSailor monetary reward to be
	 *                                  given to the trader, multiplied by size of
	 *                                  crew
	 */
	public RescuedSailor(int idNumber, String name, String description, int quantityOfSailorsToRescue,
			float monetaryRewardPerSailor) {
		super(idNumber, name, description);
		this.quantityOfSailorsToRescue = quantityOfSailorsToRescue;
		this.monetaryRewardPerSailor = monetaryRewardPerSailor;
		this.totalMonetaryReward = quantityOfSailorsToRescue * monetaryRewardPerSailor;
	}

	/**
	 * Gets the quantity of sailors to be rescued
	 *
	 * @return This number of Sailors to be rescued
	 */
	public int getQuantityOfSailorsToRescue() {
		return quantityOfSailorsToRescue;
	}

	/**
	 * Sets the number of Sailors to be rescued.
	 *
	 * @param quantityOfSailorsToRescue This new number of Sailors
	 */
	public void setQuantityOfSailorsToRescue(int quantityOfSailorsToRescue) {
		this.quantityOfSailorsToRescue = quantityOfSailorsToRescue;
	}

	/**
	 * Gets the amount of money per sailor that will be rewarded to the Trader
	 *
	 * @return This RescuedSailor reward amount
	 */
	public double getMonetaryRewardPerSailor() {
		return monetaryRewardPerSailor;
	}

	/**
	 * Sets the value of the monetary reward per sailor.
	 *
	 * @param monetaryReward This RescuedSailor monetary reward.
	 */

	public void setMonetaryRewardPerSailor(double monetaryReward) {
		this.monetaryRewardPerSailor = monetaryReward;
	}

	/**
	 * the totalMonetaryReward to set
	 */
	public void setTotalMonetaryReward() {
		this.totalMonetaryReward = monetaryRewardPerSailor * quantityOfSailorsToRescue;
	}

	/**
	 * From super RandomEvent class, this method gives the Trader a monetary reward.
	 */
	@Override
	public void randomEventSpecificAction(Trader trader) {
		trader.addAmountToBalance(totalMonetaryReward);
	}

	/**
	 * Overrides the super encounterMessage from RandomEvent class to the
	 * RescuedSailor encounter event
	 */
	@Override
	public String encounterMessage() {
		return "You encountered " + this.name + "! " + this.description;
	}

	/**
	 * Overrides the super resultOfEncounterMessage from RandomEvent class to the
	 * RescuedSailor encounter event
	 */
	@Override
	public String resultOfEncounterMessage() {
		String messageTemplate = "As you were very kind and rescued a crew of %d sailors, they are gifting you with $ %.2f!";
		return String.format(messageTemplate, quantityOfSailorsToRescue, this.totalMonetaryReward);
	}

	/**
	 * Used to personalize the game when applying the randomEvents to the routes.
	 */
	@Override
	public String getEventSpecificName() {
		return "Rescued Sailor";
	}

}
