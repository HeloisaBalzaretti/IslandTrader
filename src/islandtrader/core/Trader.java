/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * A Trader Object used to represent the player and captain the Ship and trade
 * StoreTradable goods
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class Trader extends Entity {

	/**
	 * Ship the Trader is captaining.
	 */
	private Ship shipOwned;

	/**
	 * Current money available for the Trader
	 */
	private double traderAccountBalance;

	/**
	 * Starting amount of money so the Trader can travel.
	 */
	private final double STARTING_MONEY = 12000;

	/**
	 * Trader object to represent the player.
	 *
	 * @param name        this Trader name, chosen by the player name
	 * @param description this Trader description, set by default
	 * @param shipOwned   the ship chosen by the player to captain
	 */
	public Trader(String name, String description, Ship shipOwned) {
		super(1, name, description);
		this.shipOwned = shipOwned;
		this.traderAccountBalance = STARTING_MONEY;
	}

	/**
	 * @return the shipOwned
	 */
	public Ship getShipOwned() {
		return shipOwned;
	}

	/**
	 * @param shipOwned the shipOwned to set
	 */
	public void setShipOwned(Ship shipOwned) {
		this.shipOwned = shipOwned;
	}

	/**
	 * @return the traderMoneyBalance
	 */
	public double getTraderAccountBalance() {
		return this.traderAccountBalance;
	}

	/**
	 * Add amount to Trader AccountBalance when Trader sells goods.
	 *
	 * @param amount to add to balance
	 */
	public void addAmountToBalance(double amount) {
		this.traderAccountBalance += amount;
	}

	/**
	 * Remove amount from Trader AccountBalance when Trader buys goods and upgrades.
	 *
	 * @param amount to remove from balance
	 */
	public void removeAmountFromBalance(double amount) {
		traderAccountBalance -= amount;
		if (this.traderAccountBalance < 0) {
			this.traderAccountBalance = 0;
		}
	}

	/**
	 * Sets the Trader Account to zero when Trader looses to the Pirate RandomEvent
	 */
	public void cleanTraderAccountBalance() {
		this.traderAccountBalance = 0;
	}

	@Override
	public String toString() {
		return String.format("%s, %s \nYou have %.2f $ available.", name, description, traderAccountBalance);
	}

}
