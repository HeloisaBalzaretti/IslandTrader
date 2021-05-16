package islandtrader.core;

import java.util.ArrayList;

/**
 * @author Represents a Store of type Entity that will exist in every Island, to
 *         allow trade of Tradables between Store and Trader.
 *
 *
 */
public class Store extends Entity {
	/**
	 * The Store properties: *name: Store name. *description: about the store and
	 * its tradables. *itemsAndUpgradesToBuy the collection of Items and Upgrades
	 * that the Store accepts buy from the Trader. *itemsAndUpgradesToSell the
	 * collection of Items and Upgrades that the Store has to sell to the Trader.
	 * *storePercentageFee: the Percentage fee is used to calculate and update the
	 * price of Tradables that the Store has. It vary depending on each
	 * Store-Island,i.e. 0.2 will increase price of Tradables that the Store is
	 * selling by 20% and decrease 20% the prices of Tradables that the Store is
	 * willing to buy from trader. (All Tradables have the default price, so we can
	 * apply the fee)
	 */
	private ArrayList<StoreTradable> itemsAndUpgradesToBuy;
	private ArrayList<StoreTradable> itemsAndUpgradesToSell;
	private ArrayList<StoreTradable> itemsAndUpgradesBought;
	private ArrayList<StoreTradable> itemsAndUpgradesSold;
	private float storePercentageFee;

	/**
	 * Creates a new Store using parameters from Super(Entity) and the particular
	 * store properties, taken from CSV file.
	 */
	public Store(String name, String description, float storePercentageFee, int idNumber) {
		super(idNumber, name, description);
		this.storePercentageFee = storePercentageFee;
		this.itemsAndUpgradesToBuy = new ArrayList<StoreTradable>();
		this.itemsAndUpgradesToSell = new ArrayList<StoreTradable>();
		itemsAndUpgradesBought = new ArrayList<StoreTradable>();
		itemsAndUpgradesSold = new ArrayList<StoreTradable>();
	}

	public void addItemBoughtHistory(StoreTradable storeTradable) {
		itemsAndUpgradesBought.add(storeTradable);
	}

	public void addItemSoldHistory(StoreTradable storeTradable) {
		itemsAndUpgradesSold.add(storeTradable);
	}

	public ArrayList<StoreTradable> getItemsAndUpgradesBought() {
		return itemsAndUpgradesBought;
	}

	public void setItemsAndUpgradesBought(ArrayList<StoreTradable> itemsAndUpgradesBought) {
		this.itemsAndUpgradesBought = itemsAndUpgradesBought;
	}

	public ArrayList<StoreTradable> getItemsAndUpgradesSold() {
		return itemsAndUpgradesSold;
	}

	public void setItemsAndUpgradesSold(ArrayList<StoreTradable> itemsAndUpgradesSold) {
		this.itemsAndUpgradesSold = itemsAndUpgradesSold;
	}

	/**
	 * This method takes an ArrayList of type StoreTradable, which contains the
	 * Tradables that the Store will accept Buy from Trader.It applies the
	 * storePercentageFee to reduce the Tradables price. Then it will add the
	 * Tradable with the correct price to the ArrayList itemsAndUpgradesToBuy -
	 * which is the Store's storage room for items to buy.
	 *
	 * @param itemsAndUpgrades
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
	 * Tradable with the correct price to the ArrayList itemsAndUpgradesToSell -
	 * which is the Store's storage room for items to Sell.
	 *
	 * @param itemsAndUpgrades
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
	 * * @return the ArrayList of Items and Upgrades that the Store accepts to Sell
	 * to Trader.
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
	 * @Override the toString method to return a description and other details about
	 *           the Store
	 */
	@Override
	public String toString() {
		// to do better
		return name + " Store, " + description + ", has a collection of " + itemsAndUpgradesToSell.size()
				+ " items or upgrades for you to buy and a collection of " + itemsAndUpgradesToBuy.size()
				+ " items or upgrades that the store would like to buy from you";
	}

}
