/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Represents the Store Tradable Relationship, also of type Entity as each
 * store-tradable will have its own relationship to be identified, which will be
 * used to relate the Tradables to the Stores and the quantity of each Tradable
 * that the Store buys/sells. This class is mainly used in the StoreRepository
 * class- Repository package.
 *
 * @author Maria Heloisa Balzaretti
 */
public class StoreTradable extends Entity implements Cloneable {
	/**
	 * tradable of type Tradable represents the Item or Upgrade to be traded in a
	 * Store. price represents the pertinent price(selling or buying price where the
	 * store fee will be applied, it is separated because then we can update only
	 * the relevant Tradable if its buying or selling- or both. quantity sets a
	 * quantity of each tradable that the store has available to sell or wishes to
	 * buy from Trader.
	 */
	private Tradable tradable;

	/**
	 * the price selling or buying price where the store fee will be applied.
	 */
	private float price;

	/**
	 * The quantity available to be traded
	 */
	private int quantity;

	/**
	 * a template for the toString method message.
	 */
	private String template = "%s - %s ->%.2f$ | n/a | %d ";

	/**
	 * A new StoreTradable Object using parameters from Super(Entity).
	 */
	public StoreTradable(Tradable tradable, int idNumber, int quantity) {
		super(idNumber, tradable.getName(), tradable.getDescription());
		this.tradable = tradable;
		this.quantity = quantity;
	}

	/**
	 * To copy the object without affecting the original, to be added to the Ship
	 * cargo tradables.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * @return the Item or Upgrade that will be allocated to the Store
	 */
	public Tradable getTradable() {
		return tradable;
	}

	/**
	 * Sets the Item or Upgrade that will be allocated to the Store
	 *
	 * @param tradable
	 */
	public void setTradable(Tradable tradable) {
		this.tradable = tradable;
	}

	/**
	 *
	 * @return the price of Item or Upgrade, updated using the default Tradable
	 *         prices and the Fee of each Store
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the new price of Item or Upgrade depending on the Store is is being
	 * allocated to.
	 *
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Increases the price to Tradables to be sold, as each Store will make a
	 * different profit margin
	 *
	 * @param price
	 */
	public void increasePrice(float price) {
		this.price += price;
	}

	/**
	 * Decreases the price for Tradables that the Store is willing to buy - So the
	 * Trader will have to go somewhere else to make a profit, and not at the same
	 * place where the Trader got the tradables
	 *
	 * @param price
	 */
	public void decreasePrice(float price) {
		this.price -= price;
	}

	/**
	 *
	 * @return the quantity of the Item or Upgrade that the Store is buying or
	 *         selling
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * When the item is traded the quantity available will decrease in the Store.
	 *
	 * @param quantity
	 */
	public void decreaseQuantity(int quantity) {
		this.quantity -= quantity;
	}

	/**
	 * defines the quantity of each Item or Upgrade that the store wishes to buy or
	 * sell
	 *
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Increases the quantity available of each Tradable in Store
	 *
	 * @param quantity
	 */
	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	/**
	 * @Override the toString method to give more relevant information about the
	 *           Tradable for each Store
	 */
	@Override
	public String toString() {
		String templateItem = "%s - %s ->%.2f$ | %.2f kgs | %d ";
		if (tradable instanceof Item) {
			return String.format(templateItem, this.name, this.description, this.price, ((Item) tradable).getWeight(),
					this.quantity);
		}
		return String.format(template, this.name, this.description, this.price, this.quantity);
	}
}