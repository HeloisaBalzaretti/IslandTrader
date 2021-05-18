package islandtrader.core;

/**
 *
 * @author Maria
 *
 */
public class Trader extends Entity {
	private Ship shipOwned;
	private double traderAccountBalance;
	private final double STARTING_MONEY = 10000;

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

	public String getShipInformation() {
		return shipOwned.toString();

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
	 * @param amount
	 */
	public void addAmountToBalance(double amount) {
		this.traderAccountBalance += amount;
	}

	/**
	 * Remove amount from Trader AccountBalance when Trader buys goods and upgrades.
	 *
	 * @param amount
	 */
	public void removeAmountFromBalance(double amount) {
		traderAccountBalance -= amount;
		if (this.traderAccountBalance < 0) {
			this.traderAccountBalance = 0;
		}
	}

	public void cleanTraderAccountBalance() {
		this.traderAccountBalance = 0;
	}

}
