/**
 * Repository package contain all the classes used to read the CSV files that contain the
 *  game Objects information, name, description etc.
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Upgrade;

/**
 * Represents a database of Upgrades, it is used to create an ArrayList of
 * Upgrades, from upgrades.csv, that then will be given to the Stores to trade.
 * Each constant property represents a property located at the index of the
 * sublist that will be used to build the Upgrade.
 *
 * @author Maria Heloisa Balzaretti
 *
 */

public class UpgradeRepository extends BaseRepository {

	/**
	 * this Upgrade price that store will use to sell to the Trader is at index 3 of
	 * the sublist of the upgradesList
	 */
	private final int PRICE_STORE_SELLS_TO_TRADER = 3;

	/**
	 * this Upgrade price that store will use to buy to the Trader is at index 4 of
	 * the sublist of the upgradesList
	 */
	private final int PRICE_STORE_BUYS_FROM_TRADER = 4;

	/**
	 * this amount of speed that the upgrade will increase in the Ship is at index 5
	 * of the sublist of the upgradesList
	 */
	private final int INCREASE_THE_SHIP_SPEED = 5;

	/**
	 * this amount of sneakiness that the upgrade will increase in the Ship is at
	 * index 6 of the sublist of the upgradesList
	 */
	private final int INCREASE_THE_SHIP_SNEAKINEES = 6;

	/**
	 * this amount of cargo capacity that the upgrade will increase in the Ship is
	 * at index 7 of the sublist of the upgradesList
	 */
	private final int INCREASE_THE_SHIP_CARGO_CAPACITY = 7;

	/**
	 * this amount of endurance that the upgrade will increase in the Ship is at
	 * index 8 of the sublist of the upgradesList
	 */
	private final int INCREASE_THE_SHIP_ENDURANCE = 8;

	/**
	 * The file path to the Upgrade CSV file with the information about all the
	 * Upgrades traded in the game
	 */
	private final String UPGRADES_CSV_FILEPATH = "csvFiles/upgrades.csv";

	/**
	 * from baseRepository, this method is a helper method to the
	 * getUpgrades(upgradesList) and used to get the list of sublists of Strings
	 * that represent the properties for the Upgrade. It removes the first line that
	 * contains the headers/names of properties.
	 */
	@Override
	public ArrayList<Upgrade> getList() {
		List<List<String>> upgradesList = getResultListfromFile(UPGRADES_CSV_FILEPATH);
		upgradesList.remove(0);
		return getUpgrades(upgradesList);
	}

	/**
	 * This method is used to convert the upgradesList to the Upgrade type, creating
	 * a new instance of Upgrade for each sublist and adding it to the ArrayList and
	 * so creates the Upgrade "database" for the project. uses the previously set
	 * constants to set the right properties for each instance of Upgrade.
	 *
	 * @param upgradesList (which contains sublists of Strings)
	 * @return an ArrayList of type Upgrade.
	 */
	public ArrayList<Upgrade> getUpgrades(List<List<String>> upgradesList) {
		ArrayList<Upgrade> upgradesArray = new ArrayList<>();
		for (List<String> upgrade : upgradesList) {
			int idNumber = Integer.parseInt(upgrade.get(ID_NUMBER));
			String name = upgrade.get(NAME);
			String description = upgrade.get(DESCRIPTION);
			float priceSell = Float.parseFloat(upgrade.get(PRICE_STORE_SELLS_TO_TRADER));
			float priceBuy = Float.parseFloat(upgrade.get(PRICE_STORE_BUYS_FROM_TRADER));
			int speed = Integer.parseInt(upgrade.get(INCREASE_THE_SHIP_SPEED));
			int sneakiness = Integer.parseInt(upgrade.get(INCREASE_THE_SHIP_SNEAKINEES));
			int cargoCapacity = Integer.parseInt(upgrade.get(INCREASE_THE_SHIP_CARGO_CAPACITY));
			int endurance = Integer.parseInt(upgrade.get(INCREASE_THE_SHIP_ENDURANCE));
			Upgrade newUpgrade = new Upgrade(name, description, priceSell, priceBuy, idNumber, endurance, sneakiness,
					cargoCapacity, speed);

			upgradesArray.add(newUpgrade);
		}
		return upgradesArray;
	}
}
