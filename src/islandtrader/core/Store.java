/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

import java.util.ArrayList;

/**
 * Represents a Store of type Entity that will exist in every Island, to allow
 * trade of Tradables between Store and Trader.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class Store extends Entity {

	/**
	 * itemsAndUpgradesToBuy the collection of Items and Upgrades that the Store
	 * accepts buy from the Trader.
	 */
	private ArrayList<StoreTradable> itemsAndUpgradesToBuy;

	/**
	 * itemsAndUpgradesToSell the collection of Items and Upgrades that the Store
	 * has to sell to the Trader.
	 */
	private ArrayList<StoreTradable> itemsAndUpgradesToSell;

	/**
	 * itemsAndUpgradesBought the collection to store all the StoreTradables bought
	 * by the Trader in this Store.
	 */
	private ArrayList<StoreTradable> itemsAndUpgradesBought;

	/**
	 * itemsAndUpgradesBought the collection to store all the StoreTradables sold by
	 * the Trader in this Store.
	 */
	private ArrayList<StoreTradable> itemsAndUpgradesSold;

	/**
	 * storePercentageFee: the Percentage fee is used to calculate and update the
	 * price of StoreTradable that the Store has. It vary depending on each
	 * Store-Island,i.e. 0.2 will increase price of Tradables that the Store is
	 * selling by 20% and decrease 20% the prices of Tradables that the Store is
	 * willing to buy from trader. (All Tradables have the default price, so we can
	 * apply the fee)
	 */
	private float storePercentageFee;

	/**
	 * Creates a new Store using parameters from Super(Entity) and the particular
	 * store properties.
	 */
	public Store(String name, String description, float storePercentageFee, int idNumber) {
		super(idNumber, name, description);
		this.storePercentageFee = storePercentageFee;
		this.itemsAndUpgradesToBuy = new ArrayList<StoreTradable>();
		this.itemsAndUpgradesToSell = new ArrayList<StoreTradable>();
		itemsAndUpgradesBought = new ArrayList<StoreTradable>();
		itemsAndUpgradesSold = new ArrayList<StoreTradable>();
	}

	/**
	 * Adds the storeTradable (Item or Upgrade) to the collection
	 * itemsAndUpgradesBought
	 *
	 * @param storeTradable the Item or Upgrade to be stored
	 */

	public void addItemBoughtHistory(StoreTradable storeTradable) {
		itemsAndUpgradesBought.add(storeTradable);
	}

	/**
	 *
	 * Adds the storeTradable (Item or Upgrade) to the collection
	 * itemsAndUpgradesSold
	 *
	 * @param storeTradable the Item or Upgrade to be stored
	 */
	public void addItemSoldHistory(StoreTradable storeTradable) {
		itemsAndUpgradesSold.add(storeTradable);
	}

	/**
	 * Gets the collection of storeTradable (Item or Upgrade) bought in this Store
	 *
	 * @return itemsAndUpgradesBought
	 */
	public ArrayList<StoreTradable> getItemsAndUpgradesBought() {
		return itemsAndUpgradesBought;
	}

	/**
	 * Sets the collection of storeTradable (Item or Upgrade) bought in this Store
	 *
	 * @param itemsAndUpgradesBought the collection to be set
	 */
	public void setItemsAndUpgradesBought(ArrayList<StoreTradable> itemsAndUpgradesBought) {
		this.itemsAndUpgradesBought = itemsAndUpgradesBought;
	}

	/**
	 * Gets the collection of storeTradable (Item or Upgrade) sold in this Store
	 *
	 * @return itemsAndUpgradesSold
	 */
	public ArrayList<StoreTradable> getItemsAndUpgradesSold() {
		return itemsAndUpgradesSold;
	}

	/**
	 * Sets the collection of storeTradable (Item or Upgrade) sold in this Store
	 *
	 * @param itemsAndUpgradesSold
	 */
	public void setItemsAndUpgradesSold(ArrayList<StoreTradable> itemsAndUpgradesSold) {
		this.itemsAndUpgradesSold = itemsAndUpgradesSold;
	}

	/**
	 * This method takes an ArrayList of type StoreTradable, which contains the
	 * Tradables that the Store will accept Buy from Trader.It applies the
	 * storePercentageFee to reduce the Tradables price. Then it will add the
	 * Tradable with the correct price to the ArrayList itemsAndUpgradesToBuy which
	 * is the Store's storage catalog for items to buy from the Trader.
	 *
	 * @param itemsAndUpgrades ArrayList of type StoreTradable, which contains
	 *                         Tradables.
	 *
	 */
	public void addStoreFeeToStoreTradablesPricesForBuying(ArrayList<StoreTradable> itemsAndUpgrades) {
		for (StoreTradable storeTradable : itemsAndUpgrades) {
			float newPrice = storeTradable.getTradable().getPriceToBuy() * (1 - this.storePercentageFee);// reduce price
			storeTradable.setPrice(newPrice);
			this.itemsAndUpgradesToBuy.add(storeTradable);
		}
	}

	/**
	 * This method takes an ArrayList of type StoreTradable, which contains the
	 * Tradables that the Store will have available to Sell to the Trader.It applies
	 * the storePercentageFee to increase the Tradables price. Then it will add the
	 * Tradable with the correct price to the ArrayList itemsAndUpgradesToSell which
	 * is the Store's storage room with the items to Sell to the Trader.
	 *
	 * @param itemsAndUpgrades an ArrayList of type StoreTradable, which contains
	 *                         Tradables.
	 */
	public void addStoreFeeToStoreTradablesPricesForSelling(ArrayList<StoreTradable> itemsAndUpgrades) {
		for (StoreTradable storeTradable : itemsAndUpgrades) {
			float newPrice = storeTradable.getTradable().getPriceToSell() * (1 + this.storePercentageFee);
			storeTradable.setPrice(newPrice);
			this.itemsAndUpgradesToSell.add(storeTradable);
		}
	}

	/**
	 *
	 * @return the ArrayList of Items and Upgrades that the Store accepts to Buy
	 *         from Trader.
	 */
	public ArrayList<StoreTradable> getItemsAndUpgradesToBuy() {
		return itemsAndUpgradesToBuy;
	}

	/**
	 *
	 * @return the ArrayList of Items and Upgrades that the Store accepts to Sell to
	 *         Trader.
	 *
	 */
	public ArrayList<StoreTradable> getItemsAndUpgradesToSell() {
		return itemsAndUpgradesToSell;
	}

	/**
	 *
	 * @return the Store fee that the store applies to the Tradable prices.
	 */
	public float getStoreFee() {
		return storePercentageFee;
	}

	/**
	 * @Override the toString method to return information about this Store. return
	 *           name, description and the quantity of StoreTradables to sell and
	 *           buy.
	 */
	@Override
	public String toString() {

		return String.format(
				"%s Store, %s has a collection of %d items or upgrades for you to buy here and a collection of %d items or upgrades that the store would like to buy from you.",
				name, description, itemsAndUpgradesToSell.size(), itemsAndUpgradesToBuy.size());
	}

}
