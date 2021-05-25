/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Class that represents a basic item or upgrade that will be traded between
 * Trader and Island'Stores
 *
 * @author Maria Heloisa Balzaretti
 */
public abstract class Tradable extends Entity {

	/**
	 * The default price that the Store would pay to buy item/upgrade from Trader.
	 */
	private float priceToBuy;
	/**
	 * The default price that the Store would accept to sell item/upgrade from
	 * Trader.
	 */
	private float priceToSell;

	/**
	 * Creates a new Tradable Object.
	 *
	 * @param name        this Tradable name
	 * @param description this Tradable description
	 * @param priceToBuy  this Tradable priceToBuy
	 * @param priceToSell this Tradable priceToSell
	 * @param idNumber    this Tradable idNumber
	 */
	public Tradable(String name, String description, float priceToBuy, float priceToSell, int idNumber) {
		super(idNumber, name, description);
		this.priceToBuy = priceToBuy;
		this.priceToSell = priceToSell;
	}

	/**
	 * Gets the item/upgrade priceToBuy of this Tradable. The price that store will
	 * accept to pay the trader to buy the Tradable from the trader
	 *
	 * @return this Tradable's priceToBuy.
	 */
	public float getPriceToBuy() {
		return priceToBuy;
	}

	/**
	 * Changes the priceToBuy of this Tradable. The price that store will accept to
	 * pay trader to buy from the trader
	 *
	 * @param priceToBuy This Tradable's new priceToBuy.
	 */
	public void setPriceToBuy(float priceToBuy) {
		this.priceToBuy = priceToBuy;
	}

	/**
	 * Gets the item or upgrade priceToSell of this Tradable. The price that store
	 * will accept to receive from trader to sell the item/upgrade to the trader
	 *
	 * @return this Tradable's priceToSell.
	 */
	public float getPriceToSell() {
		return priceToSell;
	}

	/**
	 * Changes the priceToSell of this Tradable.
	 *
	 * The price that store will accept to receive from trader to sell the item or
	 * upgrade to the trader
	 *
	 * @param priceToSell This Tradable's new priceToSell.
	 */
	public void setPriceToSell(float priceToSell) {
		this.priceToSell = priceToSell;
	}

	/**
	 * Changes the toString method of this Tradable.
	 *
	 * @return information about the item or upgrade changes the default toString
	 *         method
	 */
	@Override
	public String toString() {
		return String.format("[%s, %s, it costs %.2f$ $ for you buy and the store will buy it from you for %.2f$ $]",
				name, description, priceToBuy, priceToSell);
	}
}
