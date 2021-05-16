package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Ship;

/**
 * Class that models objects of the class Ship from the database.
 *
 * @author Sebastian
 *
 */
public class ShipRepository extends BaseRepository {
	/**
	 * ID_NUMBER constant that stores the column index number of the .csv file in
	 * database.
	 */
	private final int ID_NUMBER = 0;
	/**
	 * NAME constant that stores the column index number of the .csv file in
	 * database.
	 */
	private final int NAME = 1;
	/**
	 * DESCRIPTION constant that stores the column index number of the .csv file in
	 * database.
	 */
	private final int DESCRIPTION = 2;
	/**
	 * SAIL_SPEED constant that stores the column index number of the .csv file in
	 * database.
	 */
	private final int SAIL_SPEED = 3;
	/**
	 * CARGO_CAPACITY constant that stores the column index number of the .csv file
	 * in database.
	 */
	private final int CARGO_CAPACITY = 4;
	/**
	 * NUMBER_OF_CREW constant that stores the column index number of the .csv file
	 * in database.
	 */
	private final int NUMBER_OF_CREW = 5;
	/**
	 * ENDURANCE constant that stores the column index number of the .csv file in
	 * database.
	 */
	private final int ENDURANCE = 6;
	/**
	 * SHIP_CSV_FILEPATH constant that stores the column index number of the .csv
	 * file in database.
	 */
	private final String SHIP_CSV_FILEPATH = "csvFiles/ships.csv";

	private final int SNEAKINESS = 7;

	/**
	 * Returns an Array List of objects Ship constructed in getShips() method from
	 * the given database.
	 */
	@Override
	public ArrayList<Ship> getList() {
		List<List<String>> aList = getResultListfromFile(SHIP_CSV_FILEPATH);
		aList.remove(0);
		return getShips(aList);
	}

	/**
	 * Helper method for the function getList(). Receives a list of strings
	 * constructed by getList() from the given database to returns a list of objects
	 * Ship.
	 *
	 * @param a list of lists of strings
	 * @return a list of objects Ship.
	 */
	private ArrayList<Ship> getShips(List<List<String>> aList) {
		ArrayList<Ship> shipsArray = new ArrayList<>();

		for (List<String> ship : aList) {
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

//	public static void main(String[] args) throws FileNotFoundException, IOException {
//		ShipRepository shipRepository = new ShipRepository();
//		ArrayList<Ship> newShips = shipRepository.getList();
//		for (Ship ship : newShips) {
//			System.out.println(ship.toString());
//		}
//	}

}
