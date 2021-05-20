/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Represents an Item of type Tradable that will be traded between Store and
 * Trader. Trader has to sell Item to make profit.
 * 
 * @author Maria Heloisa Balzaretti.
 */
public class Item extends Tradable {

	/**
	 * weight of the item is used to calculate the space that the cargo hold
	 * capacity has available or used.
	 */
	private float weight;

	/**
	 * Creates a new Item Object of type Tradable.
	 */
	public Item(int idNumber, String name, String description, float priceToBuy, float priceToSell, float weight) {
		super(name, description, priceToBuy, priceToSell, idNumber);
		this.weight = weight;
	}

	/**
	 * Gets the weight of this Item.
	 *
	 * @return this Tradable's weight.
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * Changes the weight of this Item.
	 *
	 * @param weight This Item's new weight.
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

}
