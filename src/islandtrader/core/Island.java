/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Class that models an Island Object.
 *
 * @author Maria Heloisa Balzaretti
 */
public class Island extends Entity {

	/**
	 * A matrix used to set the distance between Islands in days.
	 */
	private int[] distancesInDays;
	/**
	 * The Store of this Island
	 */
	private Store store;

	/**
	 * The routes available from this Island
	 */
	private Route[] routes;

	/**
	 * Constructor of the class. Creates an object island with the given parameters.
	 *
	 * @param islandIdNumber     idNumber of the Island.
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
	 * @return this Island's Store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the Store to set
	 */
	public void setStore(Store store) {
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

	/**
	 * Gets the list of routes.
	 *
	 * @return
	 */
	public Route[] getRoutes() {
		return routes;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(Route[] routes) {
		this.routes = routes;
	}

	/**
	 * Takes the idNumber of another Island as a parameter to return the distance in
	 * km from this Island. distances[x] is in days * 24hs * 20km/h average =
	 * distance in Kms.
	 *
	 * @param islandNumber idNumber of the destiny.
	 * @return distance in Km
	 */
	public int getDistanceToAnotherIslandInKm(int islandNumber) {
		return distancesInDays[islandNumber] * 24 * 20;
	}

	/**
	 * Takes the idNumber of another Island as a parameter to return the distance in
	 * days from this Island.
	 *
	 * @param islandNumber
	 * @return distance in days
	 */
	public int getDistanceToAnotherIslandInDays(int islandNumber) {
		return distancesInDays[islandNumber];
	}

	/**
	 * Overrides the method toString() to return a meaningful string.
	 */
	@Override
	public String toString() {
		return String.format("%s: %s", name, description);
	}

}
