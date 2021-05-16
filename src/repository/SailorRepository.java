/**
 *
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.RescuedSailor;
import islandtrader.core.Ship;
import islandtrader.core.Trader;

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

	public static void main(String[] args) {

		SailorRepository sailorRepository = new SailorRepository();
		ArrayList<RescuedSailor> sailors = sailorRepository.getList();
		Ship barco = new Ship(1, "Karvi",
				"The smallest vessel. General purpose ship mainly used for fishin and trade, but occasionally for military use.",
				20, 300, 16, 50, 2);
		Trader myTrader = new Trader("Heloisa", "Very Brave Sailer", barco);

		for (RescuedSailor sailor : sailors) {
			System.out.println(myTrader.getTraderAccountBalance());
			System.out.println(sailor.encounterMessage());
			sailor.randomEventSpecificAction(myTrader);
			System.out.println(sailor.resultOfEncounterMessage());
			System.out.println(myTrader.getTraderAccountBalance());
		}
	}

}
