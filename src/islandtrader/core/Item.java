package islandtrader.core;

/**
 *
 */

/**
 * @author Represents an Item of type Tradable that will be traded between Store
 *         and Trader. Trader has to sell Item to make profit
 * 
 *
 */
public class Item extends Tradable {

	/**
	 * weight will be used to calculate the space that the cargo hold capacity has
	 * available or used.
	 */
	private float weight;

	/**
	 * Creates a new Item using parameters from Super(Tradable) and weight
	 * parameter.
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
