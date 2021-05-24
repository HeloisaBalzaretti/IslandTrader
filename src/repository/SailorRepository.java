/**
 *
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.RescuedSailor;

/**
 * @author Maria
 *
 */
public class SailorRepository extends BaseRepository {
	private final int ID_NUMBER = 0;
	private final int NAME = 1;
	private final int DESCRIPTION = 2;
	private final int QUANTITY_OF_SAILORS_TO_RESCUE = 3;
	private final int MONETARY_REWARD_PER_SAILOR = 4;
	private final String SAILORS_CSV_FILEPATH = "csvFiles/sailors.csv";

	@Override
	public ArrayList<RescuedSailor> getList() {
		List<List<String>> rescuedSailorList = getResultListfromFile(SAILORS_CSV_FILEPATH);
		rescuedSailorList.remove(0);
		return getSailors(rescuedSailorList);
	}

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
