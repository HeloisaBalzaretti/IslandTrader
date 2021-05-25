package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import islandtrader.core.Item;
import islandtrader.core.Store;
import islandtrader.core.StoreTradable;

class StoreTest {

	@Test
	public void addItemBoughtHistoryTest() {
		Item item = new Item(1, "chips", "tasty", (float) 2.0, (float) 2.0, (float) 2.0);
		StoreTradable storeTradable = new StoreTradable(item, 0, 0);

		Store store = new Store("store", "atisland", 2, 1);
		store.addItemBoughtHistory(storeTradable);
		assertEquals(1, store.getItemsAndUpgradesBought().size());

	}

	@Test
	public void addItemSoldtHistoryTest() {
		Item item = new Item(1, "chips", "tasty", (float) 2.0, (float) 2.0, (float) 2.0);
		StoreTradable storeTradable = new StoreTradable(item, 0, 0);
		Store store = new Store("store", "atisland", 2, 1);
		store.addItemSoldHistory(storeTradable);
		assertEquals(1, store.getItemsAndUpgradesSold().size());
	}

	@Test
	public void addStoreFeeToStoreTradablesPricesForSellingTest() {
		float itemPrice = (float) 10.0;
		Item item = new Item(1, "chips", "tasty", (float) 2.0, itemPrice, (float) 2.0);
		StoreTradable storeTradable = new StoreTradable(item, 0, 0);
		Store store = new Store("store", "atisland", 10, 1);
		ArrayList<StoreTradable> list = new ArrayList<StoreTradable>();
		list.add(storeTradable);
		store.addStoreFeeToStoreTradablesPricesForSelling(list);
		float expectedPrice = itemPrice * (1 + 10);
		assertEquals(expectedPrice, store.getItemsAndUpgradesToSell().get(0).getPrice());
	}

	@Test
	public void addStoreFeeToStoreTradablesPricesForBuyingTest() {
		float itemPrice = (float) 10.0;
		Item item = new Item(1, "chips", "tasty", itemPrice, (float) 2.0, (float) 2.0);
		StoreTradable storeTradable = new StoreTradable(item, 0, 0);
		Store store = new Store("store", "atisland", 10, 1);
		ArrayList<StoreTradable> list = new ArrayList<StoreTradable>();
		list.add(storeTradable);
		store.addStoreFeeToStoreTradablesPricesForBuying(list);
		float expectedPrice = itemPrice * (1 - 10);
		assertEquals(expectedPrice, store.getItemsAndUpgradesToBuy().get(0).getPrice());
	}

}
