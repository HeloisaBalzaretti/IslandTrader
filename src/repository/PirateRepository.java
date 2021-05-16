/**
 *
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Pirate;

/**
 * @author Maria
 *
 */
public class PirateRepository extends BaseRepository {

	private final int ID_NUMBER = 0;
	private final int NAME = 1;
	private final int DESCRIPTION = 2;
	private final int NUMBER_NEEDED_TO_SCAPE_THIS_PIRATE = 3;
	private final int PIRATE_LEVEL_OF_HAPINESS = 4;
	private final String PIRATES_CSV_FILEPATH = "csvFiles/pirates.csv";

	@Override
	public ArrayList<Pirate> getList() {
		List<List<String>> piratesList = getResultListfromFile(PIRATES_CSV_FILEPATH);
		piratesList.remove(0);
		return getPirates(piratesList);
	}

	private ArrayList<Pirate> getPirates(List<List<String>> piratesList) {
		ArrayList<Pirate> piratesArray = new ArrayList<>();
		for (List<String> pirate : piratesList) {
			int idNumber = Integer.parseInt(pirate.get(ID_NUMBER));
			String name = pirate.get(NAME);
			String description = pirate.get(DESCRIPTION);
			int numberNeededTodefendPirate = Integer.parseInt(pirate.get(NUMBER_NEEDED_TO_SCAPE_THIS_PIRATE));
			int pirateHapinessLevel = Integer.parseInt(pirate.get(PIRATE_LEVEL_OF_HAPINESS));

			Pirate newpirate = new Pirate(idNumber, name, description, numberNeededTodefendPirate, pirateHapinessLevel);
			piratesArray.add(newpirate);
		}
		return piratesArray;
	}
}
