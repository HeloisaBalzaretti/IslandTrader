/**
 * Repository package contain all the classes used to read the CSV files that contain the
 *  game Objects information, name, description etc.
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Ship;

/**
 * Represents a database of Ships, it is used to create an ArrayList of type
 * Ship, from ships.csv, from where the Trader will choose a Ship to captain.
 * Each constant property represents a property located at the index of the
 * sublist that will be used to build the Upgrade.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class ShipRepository extends BaseRepository {

	/**
	 * this Ship sail speed is at index 3 of the sublist of the shipsList
	 */
	private final int SAIL_SPEED = 3;

	/**
	 * this Ship cargo capacity is at index 4 of the sublist of the shipsList
	 */
	private final int CARGO_CAPACITY = 4;

	/**
	 * this Ship number of crew is at index 5 of the sublist of the shipsList
	 */
	private final int NUMBER_OF_CREW = 5;

	/**
	 * this Ship endurance is at index 6 of the sublist of the shipsList
	 */
	private final int ENDURANCE = 6;

	/**
	 * this Ship sneakiness is at index 7 of the sublist of the shipsList
	 */

	private final int SNEAKINESS = 7;

	/**
	 * The file path to the Ship CSV file with the information about all the Ships
	 * available for the Trader to choose.
	 */
	private final String SHIP_CSV_FILEPATH = "csvFiles/ships.csv";

	/**
	 * This method is a helper method to the getShips(shipsList) and used to get the
	 * list of sublists of Strings that represent the properties for the Ship. It
	 * removes the first line that contains the headers/names of properties.
	 */
	@Override
	public ArrayList<Ship> getList() {
		List<List<String>> aList = getResultListfromFile(SHIP_CSV_FILEPATH);
		aList.remove(0);
		return getShips(aList);
	}

	/**
	 * This method is used to convert the shipsList to the Ship type, creating a new
	 * instance of Ship for each sublist and adding it to the ArrayList and so
	 * creates the Ship "database" for the project. uses the previously set
	 * constants to set the right properties for each instance of Ship.
	 *
	 * @param shipsList list of lists of strings
	 * @return shipsList Array of objects Ship.
	 */
	private ArrayList<Ship> getShips(List<List<String>> shipsList) {
		ArrayList<Ship> shipsArray = new ArrayList<>();

		for (List<String> ship : shipsList) {
			int IdNumber = Integer.parseInt(ship.get(ID_NUMBER));
			String Name = ship.get(NAME);
			String Description = ship.get(DESCRIPTION);
			Double SailSpeed = Double.parseDouble(ship.get(SAIL_SPEED));
			Double CargoCapacity = Double.parseDouble(ship.get(CARGO_CAPACITY));
			int NumberOfCrew = Integer.parseInt(ship.get(NUMBER_OF_CREW));
			int Endurance = Integer.parseInt(ship.get(ENDURANCE));
			int sneakiness = Integer.parseInt(ship.get(SNEAKINESS));
			Ship newShip = new Ship(IdNumber, Name, Description, SailSpeed, CargoCapacity, NumberOfCrew, Endurance,
					sneakiness);
			shipsArray.add(newShip);
		}
		return shipsArray;
	}
}
