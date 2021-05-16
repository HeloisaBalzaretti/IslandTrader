package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Item;
import islandtrader.core.Store;
import islandtrader.core.StoreTradable;
import islandtrader.core.Tradable;
import islandtrader.core.Upgrade;

/**
 * Represents a database of Stores, it is used to create an ArrayList of Stores,
 * from stores.csv, that then will be given to the Islands. Here we also add the
 * StoreTradable objects(which are tradables that have the correct price
 * according to the store it is being added to) Therefore, the Stores will have
 * their storage of items to buy and items to sell ready to be traded.
 *
 * @author heloisa
 *
 */
public class StoreRepository extends BaseRepository {
	/**
	 * ID_NUMBER, NAME, DESCRIPTION, STORE_FEE_TO_CHANGE_PRICES represents a
	 * property located at the index of the sublist that will be used to build the
	 * Store
	 */
	private final int ID_NUMBER = 0;
	private final int NAME = 1;
	private final int DESCRIPTION = 2;
	private final int STORE_FEE_TO_CHANGE_PRICES = 3;
	/**
	 * ID_STORE_TRADABLE represents the unic identifier number for the relationship
	 * between a Store and a Tradable(each line in the
	 * StoreRelashionshipItemUpgrade.csv files) ID_NUMBER_STORE represents the
	 * identifier to which the Tradable shall be sent to. ID_NUMBER_ITEM_OR_UPGRADE
	 * the Tradable to be inserted in the given store. BUYS_FROM_TRADER,
	 * SELLS_TO_TRADER, represents a True or False(0 or 1) situation to decide if
	 * the store sells or buys that Tradable(or both. QUANTITY_BUY_FROM_TRADER,
	 * QUANTITY_SELL_TO_TRADER the given quantities of each Tradable that the Store
	 * will have available to buy or sell.
	 */
	private final int ID_STORE_TRADABLE = 0;
	private final int ID_NUMBER_STORE = 1;
	private final int ID_NUMBER_ITEM_OR_UPGRADE = 2;
	private final int BUYS_FROM_TRADER = 3;
	private final int SELLS_TO_TRADER = 4;
	private final int QUANTITY_BUY_FROM_TRADER = 5;
	private final int QUANTITY_SELL_TO_TRADER = 6;
	/**
	 * STORE_CSV_FILEPATH and STORE_ITEM_UPGRADE_CSV_FILEPATH the filepaths to the
	 * csv files that are used to create the Stores and the file that represents a
	 * relationship between the store and it's' Tradables available.
	 */
	private final String STORE_CSV_FILEPATH = "csvFiles/stores.csv";
	private final String STORE_ITEM_UPGRADE_CSV_FILEPATH = "csvFiles/storeRelationshipItemUpgrade.csv";

	/**
	 * from baseRepository, this method is a helper method to the getStores() and
	 * used to get the list of sublists of Strings that represent the properties for
	 * the Store. It removes the first line that contains the headers/name of
	 * properties.
	 */
	@Override
	public ArrayList<Store> getList() {
		List<List<String>> storesList = getResultListfromFile(STORE_CSV_FILEPATH);
		storesList.remove(0);
		return getStores(storesList);
	}

	/**
	 * This method is used to convert the storesList to the Store type, creating a
	 * new instance of Store for each sublist and adding it to the ArrayList and so
	 * creates the Store "database" for the project. uses the previously set
	 * constants to set the right properties for each instance of Store.
	 *
	 * @param storesList
	 * @return an array with Stores.
	 */
	private ArrayList<Store> getStores(List<List<String>> storesList) {
		ArrayList<Store> storesArray = new ArrayList<>();

		for (List<String> store : storesList) {
			int idNumber = Integer.parseInt(store.get(ID_NUMBER));
			String name = store.get(NAME);
			String description = store.get(DESCRIPTION);
			float storeFee = Float.parseFloat(store.get(STORE_FEE_TO_CHANGE_PRICES));

			Store newStore = new Store(name, description, storeFee, idNumber);
			storesArray.add(newStore);
		}
		return storesArray;
	}

	/**
	 * This method is used to populate the Stores, adding Tradables to the Stores
	 * ArrayList of StoreTradables to buy or to the ArrayList of StoreTradables to
	 * sell.
	 *
	 * @param stores
	 * @param items
	 * @param upgrades
	 */
	public void SetStoresTradables(List<Store> stores, List<Item> items, List<Upgrade> upgrades) {
		List<Tradable> tradablesList = getTradablesList(items, upgrades);

		List<List<String>> storeItemsUpgradesList = getStoreItemsUpgradesList();

		ArrayList<StoreTradable> toBuy = new ArrayList<StoreTradable>();
		ArrayList<StoreTradable> toSell = new ArrayList<StoreTradable>();

		Integer currentStoreIdNumber = null;
		Store store = null;

		for (List<String> fileLine : storeItemsUpgradesList) {
			int idStoreTradable = Integer.parseInt(fileLine.get(ID_STORE_TRADABLE));
			Integer storeIdNumber = Integer.parseInt(fileLine.get(ID_NUMBER_STORE));
			int itemOrUpgradeIdNumber = Integer.parseInt(fileLine.get(ID_NUMBER_ITEM_OR_UPGRADE));
			int buysFromTrader = Integer.parseInt(fileLine.get(BUYS_FROM_TRADER));
			int sellsToTrader = Integer.parseInt(fileLine.get(SELLS_TO_TRADER));
			int quantityBuy = Integer.parseInt(fileLine.get(QUANTITY_BUY_FROM_TRADER));
			int quantitySell = Integer.parseInt(fileLine.get(QUANTITY_SELL_TO_TRADER));

			if (currentStoreIdNumber != storeIdNumber) {
				if (currentStoreIdNumber == null) {
					store = getStoreById(storeIdNumber, stores);
				} else {
					store.addStoreFeeToStoreTradablesPricesForBuying(toBuy);
					store.addStoreFeeToStoreTradablesPricesForSelling(toSell);
					toBuy = new ArrayList<StoreTradable>();
					toSell = new ArrayList<StoreTradable>();
					store = getStoreById(storeIdNumber, stores);
				}
				currentStoreIdNumber = storeIdNumber;

			}
			addTradableToStoreStorages(tradablesList, toBuy, toSell, idStoreTradable, itemOrUpgradeIdNumber,
					buysFromTrader, sellsToTrader, quantityBuy, quantitySell);
		}
		store.addStoreFeeToStoreTradablesPricesForBuying(toBuy);
		store.addStoreFeeToStoreTradablesPricesForSelling(toSell);
	}

	/**
	 * Helper method to the SetStoresTradables(List<Store> stores, List<Item> items,
	 * List<Upgrade> upgrades).
	 *
	 * It creates the Tradable objects, that are updated with the quantity when it
	 * creates the StoreTradable, and insert it in the relevant Store array(to buy
	 * or to sell)
	 *
	 * @param tradablesList
	 * @param toBuy
	 * @param toSell
	 * @param idStoreTradable
	 * @param itemOrUpgradeIdNumber
	 * @param buysFromTrader
	 * @param sellsToTrader
	 * @param quantityBuy
	 * @param quantitySell
	 */
	private void addTradableToStoreStorages(List<Tradable> tradablesList, ArrayList<StoreTradable> toBuy,
			ArrayList<StoreTradable> toSell, int idStoreTradable, int itemOrUpgradeIdNumber, int buysFromTrader,
			int sellsToTrader, int quantityBuy, int quantitySell) {
		Tradable tradable = getTradableById(itemOrUpgradeIdNumber, tradablesList);
		if (buysFromTrader == 1) {
			StoreTradable newStoreTradable = new StoreTradable(tradable, idStoreTradable, quantityBuy);
			toBuy.add(newStoreTradable);
		}
		if (sellsToTrader == 1) {
			StoreTradable newStoreTradable = new StoreTradable(tradable, idStoreTradable, quantitySell);
			toSell.add(newStoreTradable);
		}
	}

	/**
	 * Helper method to SetStoresTradables(...) it joins the Items and the Upgrades
	 * that the Store can trade.
	 *
	 * @param items    - list of Item
	 * @param upgrades - list of Upgrade
	 * @return a list of Tradables.
	 */
	private List<Tradable> getTradablesList(List<Item> items, List<Upgrade> upgrades) {
		List<Tradable> tradablesList = new ArrayList<Tradable>(items);
		tradablesList.addAll(upgrades);
		return tradablesList;
	}

	/**
	 * Helper to the addTradableToStoreStorages(...)
	 *
	 * @param idNumber
	 * @param tradables
	 * @return get the relevant Tradable by looking for its Identifier in a list of
	 *         Tradables
	 */
	private Tradable getTradableById(int idNumber, List<Tradable> tradables) {
		for (Tradable tradable : tradables) {
			if (tradable.getIdNumber() == idNumber) {
				return tradable;
			}
		}
		return null;
	}

	/**
	 * Helper method to the SetStoresTradables(....) To add the Tradable to the
	 * correct store.
	 *
	 * @param idNumber
	 * @param stores
	 * @return the correct Store identifier that will have the Tradable allocated to
	 *         it.
	 */
	private Store getStoreById(int idNumber, List<Store> stores) {
		for (Store store : stores) {
			if (store.getIdNumber() == idNumber) {
				return store;
			}
		}
		return null;
	}

	/**
	 * This method is used to read storeRelationshipItemUpgrade.csv file and get the
	 * list of sublists of Strings that represent the properties for the
	 * relationship between the Store and its' Tradables. It removes the first line
	 * that contains the headers/name of properties.
	 */
	private List<List<String>> getStoreItemsUpgradesList() {
		List<List<String>> storeItemsUpgradesList = getResultListfromFile(STORE_ITEM_UPGRADE_CSV_FILEPATH);
		storeItemsUpgradesList.remove(0);
		return storeItemsUpgradesList;
	}
}
