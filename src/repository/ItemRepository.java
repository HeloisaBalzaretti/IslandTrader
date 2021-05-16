package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Item;

/**
 * Represents a database of Items, it is used to create an ArrayList of Items,
 * from items.csv, that then will be given to the Stores to trade.
 *
 * @author heloisa
 *
 */
public class ItemRepository extends BaseRepository {
	/**
	 * Each constant property represents a property located at the index of the
	 * sublist that will be used to build the Item. the default prices for the items
	 * in the item.csv file are thought as the Store point of view. ID_NUMBER, NAME,
	 * DESCRIPTION, PRICE_STORE_SELLS_TO_TRADER, PRICE_STORE_BUYS_FROM_TRADER
	 * represent the super(Tradable) properties and WEIGHT is a property exclusive
	 * to the Item, used to calculate the ship cargo hold occupancy.
	 * ITEM_CSV_FILEPATH is the file path to the items.csv file
	 */
	private final int ID_NUMBER = 0;
	private final int NAME = 1;
	private final int DESCRIPTION = 2;
	private final int PRICE_STORE_SELLS_TO_TRADER = 3;
	private final int PRICE_STORE_BUYS_FROM_TRADER = 4;
	private final int WEIGHT = 5;
	private final String ITEM_CSV_FILEPATH = "csvFiles/items.csv";

	/**
	 * from baseRepository, this method is a helper method to the getItems() and
	 * used to get the list of sublists of Strings that represent the properties for
	 * the Item. It removes the first line that contains the headers/name of
	 * properties.
	 */
	@Override
	public ArrayList<Item> getList() {
		List<List<String>> itemsList = getResultListfromFile(ITEM_CSV_FILEPATH);
		itemsList.remove(0);
		return getItems(itemsList);
	}

	/**
	 * This method is used to convert the itemsList to the Item type, creating a new
	 * instance of Item for each sublist and adding it to the ArrayList and so
	 * creates the Item "database" for the project. uses the previously set
	 * constants to set the right properties for each instance of Item.
	 *
	 * @param itemsList (which contains sublists of Strings)
	 * @return an ArrayList of type Item.
	 */
	private ArrayList<Item> getItems(List<List<String>> itemsList) {
		ArrayList<Item> itemsArray = new ArrayList<>();
		for (List<String> item : itemsList) {
			int itemIdNumber = Integer.parseInt(item.get(ID_NUMBER));
			String itemName = item.get(NAME);
			String itemDescription = item.get(DESCRIPTION);
			float itemPriceSell = Float.parseFloat(item.get(PRICE_STORE_SELLS_TO_TRADER));
			float itemPriceBuy = Float.parseFloat(item.get(PRICE_STORE_BUYS_FROM_TRADER));
			float itemWeight = Float.parseFloat(item.get(WEIGHT));

			Item newItem = new Item(itemIdNumber, itemName, itemDescription, itemPriceBuy, itemPriceSell, itemWeight);
			itemsArray.add(newItem);
		}
		return itemsArray;
	}

}
