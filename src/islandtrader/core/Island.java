package islandtrader.core;

/**
 * Class that models an Island.
 *
 * @author
 *
 */
public class Island extends Entity {

	/*
	 * Distance list. idNumber of the islands are used to access the index of the
	 * array to get the distance to other/s island/s.
	 */

	private int[] distancesInDays; // distance list corresponding to distanceMatrix[idNumber] from
									// IslandRepository. Each index corresponds to distance in days to each Island
									// idNumber.

	private Store store;

	private Route[] routes;

	/**
	 * Constructor of the class. Creates an object island with the given parameters.
	 *
	 * @param islandIdNumber     idNumber of the Island. Also index of the
	 *                           islandDistanceList.
	 * @param islandName         Name of the Island.
	 * @param islandDescription  Description of the Island.
	 * @param islandDistanceList Distance (in days at 20km/h) list to other Islands.
	 *                           Distance to this island is 0.
	 */
	public Island(int idNumber, String name, String description, int[] distanceInDays, Store store) {
		super(idNumber, name, description);
		this.distancesInDays = distanceInDays;
		this.store = store;
	}

	/**
	 * Gets the distance list to other islands (distance in days at 20km/h).
	 *
	 * @return distances list in days at 20km/h.
	 */
	public int[] getDistancesInDays() {
		return distancesInDays;
	}

	public Route[] getRoutes() {
		return routes;
	}

	/**
	 * Takes the idNumber of another Island as a parameter to return the distance in
	 * km to this.
	 *
	 * @param islandNumber idNumber of the destiny.
	 * @return distance in Km
	 */
	public int distanceToInKm(int islandNumber) {
		return distancesInDays[islandNumber] * 24 * 20; // distances[x] is in days * 24hs * 20km/h avg = distance in km
	}

	public int distanceToInDays(int islandNumber) {
		return distancesInDays[islandNumber];
	}

	/**
	 * Overrides the method toString() to return a meaningful string.
	 */
	@Override
	public String toString() {
		return name + ": " + description;
		/**
		 * + "\n Distance to Island: " + "\n Phuket - " + distanceToInKm(0) + "km \n
		 * Island of the Aztecs - " + distanceToInKm(1) + "km \n Shetland Islands - " +
		 * distanceToInKm(2) + "km \n Bermuda Triangle - " + distanceToInKm(3) + "km \n
		 * Trasmoz - " + distanceToInKm(4) + "km \n";
		 */
	}

	/**
	 * Takes destiny island's idNumber and the a distance list to return it's
	 * distance to origin.
	 *
	 * @param i         idNumber of destiny
	 * @param distances list of distances.
	 * @return distance to source.
	 */
	public int distanceTo(int i, int[] distances) {
		return distances[i];
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * @param routes2 the routes to set
	 */
	public void setRoutes(Route[] routes) {
		this.routes = routes;
	}

}
