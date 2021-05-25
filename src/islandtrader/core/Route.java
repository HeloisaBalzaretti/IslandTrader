/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

import java.util.ArrayList;

/**
 *
 * Routes represents the routes that connect the Islands, each Route has 3
 * possible random events that could happen, or nothing happening is also
 * possibility
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class Route extends Entity {

	/**
	 * A collection of connected Islands
	 */
	private ArrayList<Island> connectIslands;

	/**
	 * A collection of the possible events for each route
	 */
	private ArrayList<RandomEvent> possibleEvents;

	/**
	 * A template for this Route name
	 */
	private final String ROUTE_NAME_TEMPLATE = "to %s";

	/**
	 * A template used when informing the Trader of the possible randomEvents in
	 * this route
	 */
	private static final String PROBABILITY_RANDOM_EVENT_MESSAGE = "\nThe possible random encounters you may have if you take this route are:"
			+ "<br>\n => %s %s (Chance of encounter: %.0f%%)<br>\n => %s %s (Chance of encounter: %.0f%%)<br>\n => %s %s (Chance of encounter: %.0f%%)";

	/**
	 * A Route object created with the possible randomEvents.
	 *
	 * @param idNumber       this Route id number
	 * @param possibleEvents array of RandomEvent possible for the Route
	 */
	public Route(int idNumber, ArrayList<RandomEvent> possibleEvents) {
		super(idNumber, "", "");
		this.possibleEvents = possibleEvents;
		this.description = getDescription(possibleEvents);
	}

	/**
	 * Return a description with all the possible random events that may happen in
	 * the Route and the probability of happening
	 *
	 * @param possibleEvents
	 * @return a summary description of the random events for the route
	 */
	private static String getDescription(ArrayList<RandomEvent> possibleEvents) {
		RandomEvent firstEvent = possibleEvents.get(0);
		RandomEvent secondEvent = possibleEvents.get(1);
		RandomEvent thirdEvent = possibleEvents.get(2);

		return String.format(Route.PROBABILITY_RANDOM_EVENT_MESSAGE, firstEvent.getEventSpecificName(),
				firstEvent.getName(), firstEvent.getPercentageChanceOfEncounter(), secondEvent.getEventSpecificName(),
				secondEvent.getName(), secondEvent.getPercentageChanceOfEncounter(), thirdEvent.getEventSpecificName(),
				thirdEvent.getName(), thirdEvent.getPercentageChanceOfEncounter());

	}

	/**
	 * Set the name according to the islands it connects
	 *
	 * @param islandName this island name to be appended to the route name.
	 */
	public void setRouteNameByIslandName(String islandName) {
		this.name = String.format(ROUTE_NAME_TEMPLATE, islandName);
	}

	/**
	 * @return the connectIslands
	 */
	public ArrayList<Island> getConnectIslands() {
		return connectIslands;
	}

	/**
	 * @param connectIslands the connectIslands to set
	 */
	public void setConnectIslands(ArrayList<Island> connectIslands) {
		this.connectIslands = connectIslands;
	}

	/**
	 * @return the possibleEvents
	 */
	public ArrayList<RandomEvent> getPossibleEvents() {
		return possibleEvents;
	}

	/**
	 * @param possibleEvents the possibleEvents to set
	 */
	public void setPossibleEvents(ArrayList<RandomEvent> possibleEvents) {
		this.possibleEvents = possibleEvents;
		this.description = Route.getDescription(possibleEvents);
	}

	/**
	 * Adds the possible event to the Route
	 *
	 * @param newEvent to be added to route
	 */
	public void addPossibleEventToRoute(RandomEvent newEvent) {
		possibleEvents.add(newEvent);
	}

	/**
	 * Removes the event from Route
	 *
	 * @param event to be removed from route
	 */
	public void removeEventFromRoute(RandomEvent event) {
		possibleEvents.remove(event);
	}

	/**
	 * the default toString method to return information about the route.
	 */
	@Override
	public String toString() {
		return this.name + " - " + this.description;
	}

}
