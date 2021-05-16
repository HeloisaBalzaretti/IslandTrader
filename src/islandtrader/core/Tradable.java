package islandtrader.core;

/**
 * @author This ABSTRACT class represents a basic item or upgrade that the
 *         Island'store will have available to sell to the trader or buy from
 *         trader
 */
public abstract class Tradable extends Entity {

	/**
	 * These are the main properties of the Tradable: -name: the item or upgrade
	 * name. -description: the item or upgrade description. -priceToSell: The
	 * default price that the Store would accept to sell item/upgrade from Trader.
	 * -priceToBuy: The default price that the Store would pay to buy item/upgrade
	 * from Trader.
	 */
	private float priceToBuy;
	private float priceToSell;

	/**
	 * Creates a new Tradable with the given properties. includes the
	 * super(idNumber) from Entity.
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
	 * Gets the item/upgrade priceToSell of this Tradable. The price that store will
	 * accept to receive from trader to sell the item/upgrade to the trader
	 *
	 * @return this Tradable's priceToSell.
	 */
	public float getPriceToSell() {
		return priceToSell;
	}

	/**
	 * Changes the priceToSell of this Tradable. The price that store will accept to
	 * receive from trader to sell the item/upgrade to the trader
	 *
	 * @param priceToSell This Tradable's new priceToSell.
	 */
	public void setPriceToSell(float priceToSell) {
		this.priceToSell = priceToSell;
	}

	/**
	 * Changes the toString method of this Tradable. This should return a good
	 * description of the item/upgrade
	 */
	@Override
	public String toString() {
		return "[" + name + ", " + description + ", it costs " + priceToBuy
				+ "$ for you buy and the store will buy it from you for " + priceToSell + "$]";
	}
}
