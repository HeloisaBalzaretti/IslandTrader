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
 * Class that models objects of the class Island for the database.
 *
 * @author
 */
public class IslandRepository extends BaseRepository {
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
	 * DISTANCE_MATRIX Matrix constant that stores the distances in days from each
	 * island to each other.
	 */
	private final int DISTANCE_MATRIX = 3;

	/**
	 * ISLAND_CSV_FILEPATH constant that stores the column index number of the .csv
	 * file in database.
	 */
	private final String ISLAND_CSV_FILEPATH = "csvFiles/islands.csv";

	/**
	 * Returns an Array List of objects Island constructed in getIslands() method
	 * from the given database.
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
	 * Island.
	 *
	 * @param a list of lists of strings
	 * @return a list of objects Island.
	 */

	private ArrayList<Island> getIslands(List<List<String>> aList) {

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

		for (List<String> island : aList) {
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
	 * We set the route names based on the list of islands.
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

	/**

	*/
	public void getIslandRoutes(ArrayList<Island> islands) {
		for (Island island : islands) {
			System.out.println("Routes");
			for (Route route : island.getRoutes()) {
				if (route != null) {
					System.out.println(route.getName());
					System.out.println(route.getDescription());
					System.out.println(route.getPossibleEvents());
				}
			}

		}

	}

}
