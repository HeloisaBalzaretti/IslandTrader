/**
 * Repository package contain all the classes used to read the CSV files that contain the
 *  game Objects information, name, description etc.
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.Island;
import islandtrader.core.Item;
import islandtrader.core.Pirate;
import islandtrader.core.RescuedSailor;
import islandtrader.core.Route;
import islandtrader.core.Store;
import islandtrader.core.UnfortunateWeather;
import islandtrader.core.Upgrade;
import service.RandomEventService;

/**
 * Represents a database of Islands, it is used to create an ArrayList of
 * Islands, from islands.csv. Here we also add the the Stores, that are already
 * populated with the StoreTradable objects(which are tradables that have the
 * correct price and quantity according to the store where it is being traded)
 * Therefore, the Islands will have their Stores ready to the trade of goods and
 * upgrades. Also here the Islands have their available routes settled and with
 * the random events also already defined. The random events will change every
 * new game. but are still allocated randomly for each route.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class IslandRepository extends BaseRepository {

	/**
	 * DISTANCE_MATRIX Matrix constant that stores the distances in days from one
	 * island to another. It is is at index 3 of the sublist of the IslandsList
	 */
	private final int DISTANCE_MATRIX = 3;

	/**
	 * ISLAND_CSV_FILEPATH constant that stores the information to build the islands
	 * using the islands.csv file in the repository.
	 */
	private final String ISLAND_CSV_FILEPATH = "csvFiles/islands.csv";

	/**
	 * Returns an Array List of objects Island constructed in getIslands(islandList)
	 * method from the given database.
	 */
	@Override
	public ArrayList<Island> getList() {
		List<List<String>> aList = getResultListfromFile(ISLAND_CSV_FILEPATH);
		aList.remove(0);
		return getIslands(aList);
	}

	/**
	 * Helper method for the function getList(). Receives a list of strings
	 * constructed by getList() from the given database to returns a list of objects
	 * Island. As this is the main method that creates the Island objects, it needs
	 * to receive all other Arrays of objects to be able to build the Island with
	 * the populated Store and the Route with the RandomEvents also in place to
	 * happen. The randomEvents change every time the game starts. However it is
	 * being allocated randomly to the Routes, with the probability of happening, to
	 * meet the requirements.
	 *
	 *
	 * @param a list of lists of strings
	 * @return a list of objects Island.
	 */

	private ArrayList<Island> getIslands(List<List<String>> islandList) {

		ArrayList<Island> islandsArray = new ArrayList<>();

		StoreRepository storeRepository = new StoreRepository();
		ArrayList<Store> stores = storeRepository.getList();

		ItemRepository itemRepository = new ItemRepository();
		ArrayList<Item> newItems = itemRepository.getList();

		UpgradeRepository upgradeRepository = new UpgradeRepository();
		ArrayList<Upgrade> newUpgrades = upgradeRepository.getList();

		PirateRepository pirateRepository = new PirateRepository();
		ArrayList<Pirate> pirates = pirateRepository.getList();

		UnfortunateWeatherRepository weatherRepository = new UnfortunateWeatherRepository();
		ArrayList<UnfortunateWeather> weatherList = weatherRepository.getList();

		SailorRepository sailorRepository = new SailorRepository();
		ArrayList<RescuedSailor> sailors = sailorRepository.getList();

		RandomEventService res = new RandomEventService(pirates, sailors, weatherList);

		storeRepository.SetStoresTradables(stores, newItems, newUpgrades);
		int storeIndex = 0;
		for (List<String> island : islandList) {
			int idNumber = Integer.parseInt(island.get(ID_NUMBER));
			String name = island.get(NAME);
			String description = island.get(DESCRIPTION);

			String[] distanceListString = island.get(DISTANCE_MATRIX).split(",");

			List<Integer> distanceList = new ArrayList<Integer>();
			for (String distance : distanceListString) {
				distanceList.add(Integer.parseInt(distance.trim()));
			}
			int[] array = distanceList.stream().mapToInt(i -> i).toArray();

			Island newIsland = new Island(idNumber, name, description, array, stores.get(storeIndex));
			newIsland.getStore().setDescription(String.format(newIsland.getStore().getDescription(), name));
			newIsland.setRoutes(getRoutesForIsland(newIsland, res));
			islandsArray.add(newIsland);
			storeIndex++;
		}
		setRouteNames(islandsArray);
		return islandsArray;
	}

	/**
	 * We set the route names based on the islands it connects.
	 *
	 * @param islands
	 */
	private void setRouteNames(ArrayList<Island> islands) {
		for (Island island : islands) {
			for (int index = 0; index < island.getRoutes().length; index++) {
				Route route = island.getRoutes()[index];
				if (route != null) {
					route.setRouteNameByIslandName(islands.get(index).getName());
				}
			}
		}
	}

	/**
	 * Get the routes associated with the island, setting their random events
	 *
	 * @param newIsland
	 * @param res
	 * @return
	 */
	private Route[] getRoutesForIsland(Island newIsland, RandomEventService res) {
		Route[] routes = new Route[newIsland.getDistancesInDays().length];
		for (int distanceIndex = 0; distanceIndex < newIsland.getDistancesInDays().length; distanceIndex++) {
			if (newIsland.getDistancesInDays()[distanceIndex] != 0) {
				routes[distanceIndex] = new Route(distanceIndex + 1, res.getRandomEventsForRoute());
			}
		}
		return routes;
	}
}
