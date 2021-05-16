package islandtrader.ui;

import java.util.ArrayList;

import islandtrader.core.Island;
import islandtrader.core.Item;
import islandtrader.core.Ship;
import islandtrader.core.Store;
import islandtrader.core.StoreTradable;
import islandtrader.core.Trader;
import islandtrader.core.Upgrade;

public class GameEnvironmentStoreHelper {
	private final String GO_BACK = "0 [Go back]\n";
	private final String TRADABLES_HEADER = "ID - Name\n";
	private GameEnvironmentMessageHelper messageHelper;

	public GameEnvironmentStoreHelper() {
		this.messageHelper = new GameEnvironmentMessageHelper();
	}

	public String getStoreMenuMessage() {
		String menu = "Choose what you want to do:\n";
		menu += "0 - Go back\n";
		menu += "1 - Buy Items\n";
		menu += "2 - Sell Items\n";
		menu += "3 - Buy Upgrades\n";
		menu += "4 - Sell Upgrades\n";
		menu += "5 - Show purchase history\n";
		menu += "6 - Show selling history\n";
		return menu;
	}

	public void buyTradableFromStore(Trader trader, Store store, StoreTradable storeTradable) {
		float totalPrice = storeTradable.getPrice();
		int quantity = 1;
		if (storeTradable.getQuantity() > 1) {
			quantity = messageHelper.readInputInteger("Inform the quantity to buy:");
			if (quantity <= 0) {
				messageHelper.printMessage("Invalid quantity!");
				return;
			}
			totalPrice *= quantity;
		}
		if (canBuyTradable(trader, totalPrice, storeTradable, quantity)) {
			storeTradable.decreaseQuantity(quantity);
			trader.removeAmountFromBalance(totalPrice);

			if (storeTradable.getTradable() instanceof Upgrade) {
				Upgrade upgrade = (Upgrade) storeTradable.getTradable();
				trader.getShipOwned().increaseSpeed(upgrade.getIncreaseSpeed() * quantity);
				trader.getShipOwned().increaseSneakiness(upgrade.getIncreaseSpeed() * quantity);
				trader.getShipOwned().increaseEndurance(upgrade.getIncreaseEndurance() * quantity);
				trader.getShipOwned().increaseCargoHoldCapacity(upgrade.getIncreaseCargoCapacity() * quantity);
			} else {
				Item item = (Item) storeTradable.getTradable();
				float totalWeight = quantity * item.getWeight();
				trader.getShipOwned().decreaseCargoHoldCapacity(totalWeight);
			}
			String baseTemplate = "You bought %d %s from the store, costing you $%.2f. Your current balance is: $%.2f.\n";
			baseTemplate += "Current Features: Speed %.1f, Cargo Capacity %.1f kg, Sneakiness %d and Endurance %d.";
			messageHelper.printMessage(String.format(baseTemplate + "\n", quantity,
					storeTradable.getTradable().getName(), totalPrice, trader.getTraderAccountBalance(),
					trader.getShipOwned().getSailSpeed(), trader.getShipOwned().getCargoCapacity(),
					trader.getShipOwned().getSneakiness(), trader.getShipOwned().getEndurance()));
			addStoreTradableBoughtHistory(storeTradable, totalPrice, quantity, store);
			addStoreTradableToShip(storeTradable, totalPrice, quantity, trader.getShipOwned());
		}
	}

	private void addStoreTradableToShip(StoreTradable storeTradable, float totalPrice, int quantity, Ship ship) {
		StoreTradable storeTradableInShip = getStoreTradableFromTradableId(storeTradable.getTradable().getIdNumber(),
				ship.getCurrentCargoTradables());
		if (storeTradableInShip == null) {
			try {
				StoreTradable storeTradableToAdd = (StoreTradable) storeTradable.clone();
				storeTradableToAdd.setPrice(0);
				storeTradableToAdd.setQuantity(quantity);
				ship.addTradable(storeTradableToAdd);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		} else {
			storeTradableInShip.increaseQuantity(quantity);
		}
	}

	private boolean canBuyTradable(Trader trader, float totalPrice, StoreTradable storeTradable, int quantity) {
		boolean canBuy = true;
		if (trader.getTraderAccountBalance() < totalPrice) {
			canBuy = false;
			messageHelper.printMessage("You don't have enough money to buy the products!");
		} else if (quantity > storeTradable.getQuantity()) {
			canBuy = false;
			messageHelper.printMessage("Store doesn't have the selected quantity to sell!");
		} else if (storeTradable.getTradable() instanceof Item) {
			Item item = (Item) storeTradable.getTradable();
			float totalWeight = quantity * item.getWeight();
			if (trader.getShipOwned().getCargoCapacity() < totalWeight) {
				canBuy = false;
				messageHelper.printMessage("You don't have available cargo capactity to buy the items!");
			}
		}
		return canBuy;
	}

	private void addStoreTradableBoughtHistory(StoreTradable storeTradable, float totalPrice, int quantity,
			Store store) {
		try {
			StoreTradable storeTradableHistory = (StoreTradable) storeTradable.clone();
			storeTradableHistory.setPrice(totalPrice);
			storeTradableHistory.setQuantity(quantity);
			store.addItemBoughtHistory(storeTradableHistory);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public void sellTradableToStore(Trader trader, Store store, StoreTradable storeTradable) {
		float totalPrice = storeTradable.getPrice();
		int quantity = 1;
		if (storeTradable.getQuantity() > 1) {
			quantity = messageHelper.readInputInteger("Inform the quantity to sell:");
			if (quantity <= 0) {
				messageHelper.printMessage("Invalid quantity!");
				return;
			}
			totalPrice *= quantity;
		}
		if (canSellTradable(trader, storeTradable, quantity, trader.getShipOwned())) {
			storeTradable.decreaseQuantity(quantity);
			trader.addAmountToBalance(totalPrice);

			if (storeTradable.getTradable() instanceof Upgrade) {
				Upgrade upgrade = (Upgrade) storeTradable.getTradable();
				trader.getShipOwned().decreaseSpeed(upgrade.getIncreaseSpeed() * quantity);
				trader.getShipOwned().decreaseSneakiness(upgrade.getIncreaseSpeed() * quantity);
				trader.getShipOwned().decreaseEndurance(upgrade.getIncreaseEndurance() * quantity);
				trader.getShipOwned().decreaseCargoHoldCapacity(upgrade.getIncreaseCargoCapacity() * quantity);
			}

			String baseTemplate = "You sold %d %s to the store, getting you $%.2f. Your current balance is: $%.2f\n";
			baseTemplate += "Current Features: Speed %.1f, Cargo Capacity %.1f kg, Sneakiness %d and Endurance %d.";
			messageHelper.printMessage(String.format(baseTemplate + "\n", quantity,
					storeTradable.getTradable().getName(), totalPrice, trader.getTraderAccountBalance(),
					trader.getShipOwned().getSailSpeed(), trader.getShipOwned().getCargoCapacity(),
					trader.getShipOwned().getSneakiness(), trader.getShipOwned().getEndurance()));
			// TODO add to list of bought items
			addStoreTradableSoldHistory(storeTradable, totalPrice, quantity, store);
			removeStoreTradableToShip(storeTradable, quantity, trader.getShipOwned());

		}
	}

	private void removeStoreTradableToShip(StoreTradable storeTradable, int quantity, Ship ship) {
		StoreTradable storeTradableInShip = getStoreTradableFromTradableId(storeTradable.getTradable().getIdNumber(),
				ship.getCurrentCargoTradables());
		storeTradableInShip.decreaseQuantity(quantity);
		if (storeTradableInShip.getQuantity() == 0) {
			ship.removeTradable(storeTradableInShip);
		}
	}

	private boolean canSellTradable(Trader trader, StoreTradable storeTradable, int quantity, Ship ship) {
		boolean canSell = true;
		if (quantity > storeTradable.getQuantity()) {
			messageHelper.printMessage("Store doesn't buy that amount of quantity!");
			canSell = false;
		} else {
			StoreTradable storeTradableInShip = getStoreTradableFromTradableId(
					storeTradable.getTradable().getIdNumber(), ship.getCurrentCargoTradables());
			if (storeTradableInShip == null || storeTradableInShip.getQuantity() < quantity) {
				canSell = false;
				messageHelper.printMessage("You don't have tradables to sell to store!");
			}

			if (canSell && storeTradable.getTradable() instanceof Upgrade) {
				Upgrade upgrade = (Upgrade) storeTradable.getTradable();
				if (upgrade.getIncreaseCargoCapacity() > 0) {
					double totalCapacityToSell = upgrade.getIncreaseCargoCapacity() * quantity;
					if (trader.getShipOwned().getCargoCapacity() - totalCapacityToSell < 0) {
						messageHelper.printMessage("You can't sell the upgrade! You need to sell items first!");
						canSell = false;
					}
				}
			}
		}
		return canSell;
	}

	private void addStoreTradableSoldHistory(StoreTradable storeTradable, float totalPrice, int quantity, Store store) {
		try {
			StoreTradable storeTradableHistory = (StoreTradable) storeTradable.clone();
			storeTradableHistory.setPrice(totalPrice);
			storeTradableHistory.setQuantity(quantity);
			store.addItemSoldHistory(storeTradableHistory);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public void viewHistoryTraderGoods(ArrayList<Island> islands) {
		for (Island island : islands) {
			String template = "History of purchases and sellings in the island %s,  store %s\n";
			messageHelper.printMessage(String.format(template, island.getName(), island.getStore().getName()));
			messageHelper.printMessage("Buy History:");
			showBuyHistory(island.getStore());
			messageHelper.printMessage("Sell History:");
			showSellHistory(island.getStore());
			messageHelper.printMessage("\n");
		}
	}

	public void showBuyHistory(Store store) {
		if (store.getItemsAndUpgradesBought().size() > 0) {
			String itemsOrUpgradesToString = TRADABLES_HEADER;
			int index = 1;
			for (StoreTradable storeTradable : store.getItemsAndUpgradesBought()) {
				itemsOrUpgradesToString += getDescriptionHistoryBought(index, storeTradable);
				index++;
			}
			messageHelper.printMessage(itemsOrUpgradesToString);
		} else {
			messageHelper.printMessage("No item bought from the store!");
		}
	}

	public void showSellHistory(Store store) {
		if (store.getItemsAndUpgradesSold().size() > 0) {
			String itemsOrUpgradesToString = TRADABLES_HEADER;
			int index = 1;
			for (StoreTradable storeTradable : store.getItemsAndUpgradesSold()) {
				itemsOrUpgradesToString += getDescriptionHistorySold(index, storeTradable);
				index++;
			}
			messageHelper.printMessage(itemsOrUpgradesToString);
		} else {
			messageHelper.printMessage("No item sold to the store!");
		}
	}

	private String getUpgradeItemsToBuy(boolean showItems, Island currentIsland) {
		String itemsOrUpgradesToString = GO_BACK + TRADABLES_HEADER;
		for (StoreTradable storeTradable : currentIsland.getStore().getItemsAndUpgradesToSell()) {
			if (((showItems && storeTradable.getTradable() instanceof Item)
					|| (!showItems && storeTradable.getTradable() instanceof Upgrade))
					&& storeTradable.getQuantity() > 0) {
				itemsOrUpgradesToString += getDescriptionTraderBuying(storeTradable.getIdNumber(), storeTradable);
			}
		}
		return itemsOrUpgradesToString;
	}

	private String getUpgradeItemsToSell(boolean showItems, Island currentIsland) {
		String itemsOrUpgradesToString = GO_BACK + TRADABLES_HEADER;
		for (StoreTradable storeTradable : currentIsland.getStore().getItemsAndUpgradesToBuy()) {
			if (((showItems && storeTradable.getTradable() instanceof Item)
					|| (!showItems && storeTradable.getTradable() instanceof Upgrade))
					&& storeTradable.getQuantity() > 0) {
				itemsOrUpgradesToString += getDescriptionTraderSelling(storeTradable.getIdNumber(), storeTradable);
			}
		}
		return itemsOrUpgradesToString;
	}

	public String getItemsTraderCanBuy(Island currentIsland) {
		String header = "Inform the item id you wish to buy: \n";
		return header + getUpgradeItemsToBuy(true, currentIsland);
	}

	public String getUpgradesTraderCanBuy(Island currentIsland) {
		String header = "Inform the number of the upgrades you wish to buy: \n";
		return header + getUpgradeItemsToBuy(false, currentIsland);
	}

	public String getItemsTraderCanSell(Island currentIsland) {
		String header = "Inform the number of the items you wish to sell: \n";
		return header + getUpgradeItemsToSell(true, currentIsland);
	}

	public String getUpgradesTraderCanSell(Island currentIsland) {
		String header = "Inform the uograde id you wish to sell: \n";
		return header + getUpgradeItemsToSell(false, currentIsland);
	}

	public StoreTradable getStoreTradableFromStoreTradableId(int idNumber, ArrayList<StoreTradable> storeTradables,
			boolean isItem) {
		for (StoreTradable storeTradable : storeTradables) {
			if (((isItem && storeTradable.getTradable() instanceof Item)
					|| (!isItem && storeTradable.getTradable() instanceof Upgrade))
					&& storeTradable.getIdNumber() == idNumber) {
				return storeTradable;
			}
		}
		return null;
	}

	public StoreTradable getStoreTradableFromTradableId(int idNumber, ArrayList<StoreTradable> storeTradables) {
		for (StoreTradable storeTradable : storeTradables) {
			if (storeTradable.getTradable().getIdNumber() == idNumber) {
				return storeTradable;
			}
		}
		return null;
	}

	private String getDescriptionHistoryBought(int idNumber, StoreTradable storeTradable) {
		return String.format("%d - You bought %d %s, it costed $%.2f\n", idNumber, storeTradable.getQuantity(),
				storeTradable.getName(), storeTradable.getPrice());
	}

	private String getDescriptionHistorySold(int idNumber, StoreTradable storeTradable) {
		return String.format("%d - You sold %d %s, giving you $%.2f\n", idNumber, storeTradable.getQuantity(),
				storeTradable.getName(), storeTradable.getPrice());
	}

	private String getDescriptionTraderBuying(int idNumber, StoreTradable storeTradable) {
		if (storeTradable.getTradable() instanceof Upgrade) {
			Upgrade upgrade = (Upgrade) storeTradable.getTradable();
			String baseTemplate = "%d - %s, %s, it costs $%.2f (%d available).";
			baseTemplate += " Features: Speed +%d, Cargo Capacity +%d kg, Sneakiness +%d and Endurance +%d";
			return String.format(baseTemplate + "\n", idNumber, storeTradable.getName(), storeTradable.getDescription(),
					storeTradable.getPrice(), storeTradable.getQuantity(), upgrade.getIncreaseSpeed(),
					upgrade.getIncreaseCargoCapacity(), upgrade.getIncreaseSneakiness(),
					upgrade.getIncreaseEndurance());
		} else {
			Item item = (Item) storeTradable.getTradable();
			String baseFormat = "%d - %s, %s, it weights %.1f kg, it costs $%.2f (%d available).";
			return String.format(baseFormat + "\n", idNumber, storeTradable.getName(), storeTradable.getDescription(),
					item.getWeight(), storeTradable.getPrice(), storeTradable.getQuantity());
		}
	}

	private String getDescriptionTraderSelling(int idNumber, StoreTradable storeTradable) {
		if (storeTradable.getTradable() instanceof Upgrade) {
			String baseTemplate = "%d - %s, %s, and the store will buy it from you for $%.2f (store can buy only %d)";
			Upgrade upgrade = (Upgrade) storeTradable.getTradable();
			baseTemplate += ". Features: Speed -%d, Cargo Capacity -%d, Sneakiness -%d and Endurance -%d";
			return String.format(baseTemplate + "\n", idNumber, storeTradable.getName(), storeTradable.getDescription(),
					storeTradable.getPrice(), storeTradable.getQuantity(), upgrade.getIncreaseSpeed(),
					upgrade.getIncreaseCargoCapacity(), upgrade.getIncreaseSneakiness(),
					upgrade.getIncreaseEndurance());
		} else {
			Item item = (Item) storeTradable.getTradable();
			String baseFormat = "%d - %s, %s, it weights %.1f kg and the store will buy it from you for $%.2f (store can buy only %d)";
			return String.format(baseFormat + "\n", idNumber, storeTradable.getName(), storeTradable.getDescription(),
					item.getWeight(), storeTradable.getPrice(), storeTradable.getQuantity());
		}
	}
}
