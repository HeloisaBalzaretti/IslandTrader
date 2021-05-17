/**
 *
 */
package islandtrader.core;

import java.util.ArrayList;

/**
 * @author maria
 *
 */
public class Route extends Entity {

	private ArrayList<Island> connectIslands;
	private ArrayList<RandomEvent> possibleEvents;

	private final String ROUTE_NAME_TEMPLATE = "to %s";
	private static final String PROBABILITY_RANDOM_EVENT_MESSAGE = "\nThe possible random encounters you may have if you take this route are:"
			+ "<br>\n => %s %s (Chance of encounter: %.0f%%)<br>\n => %s %s (Chance of encounter: %.0f%%)<br>\n => %s %s (Chance of encounter: %.0f%%)";

	public Route(int idNumber, ArrayList<RandomEvent> possibleEvents) {
		super(idNumber, "", "");
		this.possibleEvents = possibleEvents;
		this.description = GetDescription(possibleEvents);
	}

	private static String GetDescription(ArrayList<RandomEvent> possibleEvents) {
		RandomEvent firstEvent = possibleEvents.get(0);
		RandomEvent secondEvent = possibleEvents.get(1);
		RandomEvent thirdEvent = possibleEvents.get(2);

		return String.format(Route.PROBABILITY_RANDOM_EVENT_MESSAGE, firstEvent.getEventSpecificName(),
				firstEvent.getName(), firstEvent.getPercentageChanceOfEncounter(), secondEvent.getEventSpecificName(),
				secondEvent.getName(), secondEvent.getPercentageChanceOfEncounter(), thirdEvent.getEventSpecificName(),
				thirdEvent.getName(), thirdEvent.getPercentageChanceOfEncounter());

	}

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
		this.description = Route.GetDescription(possibleEvents);
	}

	public void addPossibleEventToRoute(RandomEvent newEvent) {
		possibleEvents.add(newEvent);
	}

	public void removeEventFromRoute(RandomEvent newEvent) {
		possibleEvents.remove(newEvent);
	}

	@Override
	public String toString() {
		return this.name + " - " + this.description;
	}

}
