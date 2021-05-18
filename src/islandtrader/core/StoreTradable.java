package islandtrader.core;

/**
 * Represents the Store Tradable Relationship, also of type Entity as each
 * store-tradable will have its own relationship to be identified, which will be
 * used to relate the Tradables to the Stores and the quantity of each Tradable
 * that the Store buys/sells. This class is mainly used in the StoreRepository
 * class- Repository package.
 *
 * @author
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
	private String template = "%s - %s ->%.2f$";
	private Tradable tradable;
	private float price;
	private int quantity;

	/**
	 * A new StoreTradable using parameters from Super(Entity).
	 *
	 * @param tradable
	 * @param idNumber
	 * @param quantity
	 */
	public StoreTradable(Tradable tradable, int idNumber, int quantity) {
		super(idNumber, tradable.getName(), tradable.getDescription());
		this.tradable = tradable;
		this.quantity = quantity;
	}

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

	public void increasePrice(float price) {
		this.price += price;
	}

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

	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	@Override
	public String toString() {
		return String.format(template, this.name, this.description, this.price);
	}
}