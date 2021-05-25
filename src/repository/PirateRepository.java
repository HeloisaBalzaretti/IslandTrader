/**
 * Repository package contain all the classes used to read the CSV files that contain the
 *  game Objects information, name, description etc.
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Pirate;

/**
 * Represents a database for the PirateRepository RandomEvent, it is used to
 * create an ArrayList of type PirateRepository, with information from
 * pirates.csv. Each constant property represents a property located at the
 * index of the sublist that will be used to build the Pirate object.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class PirateRepository extends BaseRepository {

	/**
	 * this number needed to escape from pirate is at index 3 of the sublist of the
	 * piratesList
	 */
	private final int NUMBER_NEEDED_TO_SCAPE_THIS_PIRATE = 3;

	/**
	 * this number is the pirate level of happiness and is is at index 4 of the
	 * sublist of the piratesList
	 */
	private final int PIRATE_LEVEL_OF_HAPPINESS = 4;

	/**
	 * The file path to the pirates.csv file with the information about all the
	 * Pirates met in the game.
	 */
	private final String PIRATES_CSV_FILEPATH = "csvFiles/pirates.csv";

	/**
	 * This method is a helper method to the getPirates( piratesList) and used to
	 * get the list of sublists of Strings that represent the properties for the
	 * Pirate. It removes the first line that contains the headers/names of
	 * properties.
	 */
	@Override
	public ArrayList<Pirate> getList() {
		List<List<String>> piratesList = getResultListfromFile(PIRATES_CSV_FILEPATH);
		piratesList.remove(0);
		return getPirates(piratesList);
	}

	/**
	 * This method is used to convert the String List piratesList to the Pirate
	 * type, creating a new instance of Pirate for each sublist and adding it to the
	 * ArrayList and so creates the Pirate database for the project. It uses the
	 * previously set constants to set the right properties for each instance of
	 * Pirate.
	 *
	 * @param piratesList
	 * @return ArrayList of Pirates
	 */
	private ArrayList<Pirate> getPirates(List<List<String>> piratesList) {
		ArrayList<Pirate> piratesArray = new ArrayList<>();
		for (List<String> pirate : piratesList) {
			int idNumber = Integer.parseInt(pirate.get(ID_NUMBER));
			String name = pirate.get(NAME);
			String description = pirate.get(DESCRIPTION);
			int numberNeededTodefendPirate = Integer.parseInt(pirate.get(NUMBER_NEEDED_TO_SCAPE_THIS_PIRATE));
			int pirateHapinessLevel = Integer.parseInt(pirate.get(PIRATE_LEVEL_OF_HAPPINESS));

			Pirate newpirate = new Pirate(idNumber, name, description, numberNeededTodefendPirate, pirateHapinessLevel);
			piratesArray.add(newpirate);
		}
		return piratesArray;
	}
}
