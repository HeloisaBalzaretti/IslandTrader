/**
 * Repository package contain all the classes used to read the CSV files that contain the
 *  game Objects information, name, description etc.
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.RescuedSailor;

/**
 * Represents a database for the RescuedSailor RandomEvent, it is used to create
 * an ArrayList of type RescuedSailor, with information from RescuedSailor.csv.
 * Each constant property represents a property located at the index of the
 * sublist that will be used to build the RescuedSailor object.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class SailorRepository extends BaseRepository {

	/**
	 * this RescuedSailor Quantity of sailors to rescue is at index 3 of the sublist
	 * of the rescuedSailorList
	 */
	private final int QUANTITY_OF_SAILORS_TO_RESCUE = 3;

	/**
	 * this RescuedSailor monetary reward per sailor is at index 4 of the sublist of
	 * the rescuedSailorList
	 */
	private final int MONETARY_REWARD_PER_SAILOR = 4;

	/**
	 * The file path to the sailors CSV file with the information about all the
	 * Rescued sailors met in the game.
	 */
	private final String SAILORS_CSV_FILEPATH = "csvFiles/sailors.csv";

	/**
	 * This method is a helper method to the getSailors(List<List<String>>
	 * rescuedSailorList) and used to get the list of sublists of Strings that
	 * represent the properties for the RescuedSailor. It removes the first line
	 * that contains the headers/names of properties.
	 */
	@Override
	public ArrayList<RescuedSailor> getList() {
		List<List<String>> rescuedSailorList = getResultListfromFile(SAILORS_CSV_FILEPATH);
		rescuedSailorList.remove(0);
		return getSailors(rescuedSailorList);
	}

	/**
	 * This method is used to convert the String List rescuedSailorList to the
	 * RescuedSailor type, creating a new instance of RescuedSailor for each sublist
	 * and adding it to the ArrayList and so creates the RescuedSailor database for
	 * the project. It uses the previously set constants to set the right properties
	 * for each instance of RescuedSailor.
	 *
	 * @param rescuedSailorList
	 * @return
	 */
	private ArrayList<RescuedSailor> getSailors(List<List<String>> rescuedSailorList) {
		ArrayList<RescuedSailor> rescuedSailorsArray = new ArrayList<>();
		for (List<String> sailor : rescuedSailorList) {
			int idNumber = Integer.parseInt(sailor.get(ID_NUMBER));
			String name = sailor.get(NAME);
			String description = sailor.get(DESCRIPTION);
			int quantitySailorsToRescue = Integer.parseInt(sailor.get(QUANTITY_OF_SAILORS_TO_RESCUE));
			float rewardPerSailor = Float.parseFloat(sailor.get(MONETARY_REWARD_PER_SAILOR));
			RescuedSailor newSailor = new RescuedSailor(idNumber, name, description, quantitySailorsToRescue,
					rewardPerSailor);
			rescuedSailorsArray.add(newSailor);
		}
		return rescuedSailorsArray;
	}

}
