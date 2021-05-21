/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

import java.util.ArrayList;

/**
 * Class that models a Ship.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class Ship extends Entity {

	/**
	 * sail Speed of the Ship.
	 */
	private double sailSpeed;
	/**
	 * cargoCapacity of the Ship in Kg.
	 */
	private double cargoCapacity;

	/**
	 * endurance / resistance capacity of the Ship.
	 */
	private int endurance;
	/**
	 * sneakiness of the Ship. Improves the chances to escape pirates attacks.
	 */
	private int sneakiness;

	/**
	 * numberOfCrew that the Ship takes.
	 */
	private int numberOfCrew;

	/**
	 * Crew cost to sail per day per crew member, 3.90 dollars were a lot of money
	 * some time ago...
	 */
	private final double CREW_COST_TO_SAIL = 3.90;

	/**
	 * Helper boolean property to the Ship Health and game play.
	 */
	private boolean ableToSail = true;

	/**
	 * A collection of current StoreTradable carried by the Ship and available for
	 * trade
	 */
	private ArrayList<StoreTradable> currentCargoTradables;

	/**
	 * A collection to keep track of the StoreTradable traded.
	 */
	private ArrayList<StoreTradable> soldCargoTradables = new ArrayList<StoreTradable>();

	/**
	 * The current Health Status of the Ship, varies when Ship takes random amount
	 * of damage from RandomEvent UnfortunateWeather
	 */
	private double currentHealthStatus = 100;

	/**
	 * The constant full health value used to calculate and set full health of the
	 * Ship.
	 */
	private final double FULL_HEALTH = 100;

	/**
	 * Creates a Ship object
	 *
	 * @param idNumber      id number
	 * @param name          name
	 * @param description   description
	 * @param sailSpeed     sail speed
	 * @param cargoCapacity cargo capacity in Kg
	 * @param numberOfCrew  number of crew the ship takes
	 * @param endurance     endurance / resistance capacity of the ship
	 */
	public Ship(int idNumber, String name, String description, double sailSpeed, double cargoCapacity, int numberOfCrew,
			int endurance, int sneakiness) {
		super(idNumber, name, description);
		this.sailSpeed = sailSpeed;
		this.cargoCapacity = cargoCapacity;
		this.numberOfCrew = numberOfCrew;
		this.endurance = endurance;
		this.sneakiness = sneakiness;
		this.currentCargoTradables = new ArrayList<StoreTradable>();
	}

	/**
	 * Gets the number of crew that this ship takes.
	 *
	 * @return number of crew
	 */
	public int getNumberOfCrew() {
		return numberOfCrew;
	}

	/**
	 * Gets the crew cost to sail daily, the Trader needs to pay the crew wages
	 * before sailing.
	 *
	 * @return
	 */
	public double getCrewCostToSailDaily() {
		return numberOfCrew * CREW_COST_TO_SAIL;
	}

	/**
	 * gets the Crew cost to Sail by the total days needed to get to the Island
	 *
	 * @param days
	 * @return
	 */
	public double getCrewCostToSailByTotalDays(int days) {
		return getCrewCostToSailDaily() * days;
	}

	/**
	 * Gets the Current Health status of the Ship. if it is less than 100% the ship
	 * must be fixed before it can sail again, also used to calculate the cost to
	 * fix the Ship.
	 *
	 * @return
	 */
	public double getCurrentHealthStatus() {
		return currentHealthStatus;
	}

	/**
	 * Applies damage from RandomEvent Unfortunate Weather to the Ship
	 *
	 * @param amountDamage
	 */
	public void applyDamageToCurrentHealthStatus(double amountDamage) {
		this.currentHealthStatus -= Math.max(0, amountDamage);
	}

	/**
	 * Apply the damage from RandomEvent UnfortunateWeather to the Ship
	 *
	 * @param damage
	 */
	public void applyDamageToShip(double damage) {
		currentHealthStatus -= damage;
		if (currentHealthStatus < FULL_HEALTH) {
			this.ableToSail = false;
		}
	}

	/**
	 * Repairs the Ship by setting the currentHealthStatus to 100%
	 */
	public void repair() {
		this.currentHealthStatus = FULL_HEALTH;
	}

	/**
	 * Checks that the Ship is able to sail
	 *
	 * @return
	 */
	public boolean isAbleToSail() {
		return ableToSail;
	}

	/**
	 * Sets the Ship ability to sail, when the ships takes damage it is set to false
	 *
	 * @param ableToSail
	 */
	public void setAbleToSail(boolean ableToSail) {
		this.ableToSail = ableToSail;
	}

	/**
	 * Gets the sail speed of this ship.
	 *
	 * @return sail speed
	 */
	public double getSailSpeed() {
		return sailSpeed;
	}

	/**
	 * Sets the sail speed of this ship.
	 *
	 * @param sailSpeed
	 */
	public void setSailSpeed(double sailSpeed) {
		this.sailSpeed = sailSpeed;
	}

	/**
	 * Increases the ship speed by the given amount Applied when a relevant upgrade
	 * is bought
	 *
	 * @param increaseBy
	 */
	public void increaseSpeed(int increaseBy) {
		this.sailSpeed += increaseBy;
	}

	/**
	 * Decreases speed to remove any upgrades that the Trader has and wishes to sell
	 * back.
	 *
	 * @param decreaseBy
	 */
	public void decreaseSpeed(int decreaseBy) {
		this.sailSpeed -= decreaseBy;
	}

	/**
	 * Gets the cargo capacity in Kg of this ship.
	 *
	 * @return cargo capacity
	 */
	public double getCargoCapacity() {
		return cargoCapacity;
	}

	/**
	 * Sets the cargo capacity of this ship to the given parameter. Used under
	 * upgrades in store.
	 *
	 * @param cargoCapacity cargo capacity increase to:
	 */
	public void setCargoCapacity(double cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}

	/**
	 * Increases the amount of Kgs the Ship cargo can carry, when Trader buys a
	 * relevant upgrade
	 *
	 * @param increaseBy
	 */
	public void increaseCargoHoldCapacity(double increaseBy) {
		this.cargoCapacity += increaseBy;
	}

	/**
	 * Used to calculate the capacity in use with the current goods.
	 *
	 * @param amountToDecrease
	 */
	public void decreaseCargoHoldCapacity(double amountToDecrease) {
		this.cargoCapacity -= amountToDecrease;
	}

	/**
	 * Trader can see the amount applied before buying an upgrade
	 *
	 * @param amountUpgrade
	 * @return
	 */
	public double getNewCargoHoldCapacity(double amountUpgrade) {
		return cargoCapacity + amountUpgrade;
	}

	/**
	 * Gets the endurance / resistance capacity of this ship.
	 *
	 * @return endurance
	 */
	public int getEndurance() {
		return endurance;
	}

	/**
	 * Sets the endurance of this ship to the given parameter. Used under upgrades
	 * in the store.
	 *
	 * @param endur endurance
	 */
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	/**
	 * Increase endurance of Ship when Trader buys a relevant upgrade to the Ship
	 *
	 * @param amountEndurance
	 */
	public void increaseEndurance(int amountEndurance) {
		endurance += amountEndurance;
	}

	/**
	 * Decreases the amount of endurance when Trader decides to sell the upgrade
	 * back.
	 *
	 * @param amountEndurance
	 */
	public void decreaseEndurance(int amountEndurance) {
		endurance -= amountEndurance;
	}

	/**
	 * The trader can see the new Endurance before buying the upgrade
	 *
	 * @param amountEndurance
	 * @return
	 */
	public int getNewEndurance(int amountEndurance) {
		return endurance += amountEndurance;
	}

	/**
	 * Gets the sneakiness of this ship.
	 *
	 * @return sneakiness
	 */
	public int getSneakiness() {
		return sneakiness;
	}

	/**
	 * Sets this ship sneakiness to the given parameter.
	 *
	 * @param sneakiness
	 */
	public void setSneakiness(int sneakiness) {
		this.sneakiness = sneakiness;
	}

	/**
	 * Increase sneakiness of this ship by the given parameter. Decreases if the
	 * given parameter is negative.
	 *
	 * @param sneaki_increase
	 */
	public void increaseSneakiness(int increaseBy) {
		this.sneakiness += increaseBy;
	}

	/**
	 * Decrease sneakiness of Ship when Trader sells some upgrade that previously
	 * increased the Sneakiness of the Ship
	 *
	 * @param decreaseBy
	 */
	public void decreaseSneakiness(int decreaseBy) {
		this.sneakiness -= decreaseBy;
	}

	/**
	 * A collection with all the current StoreTradables available to trade
	 *
	 * @return
	 */
	public ArrayList<StoreTradable> getCurrentCargoTradables() {
		return currentCargoTradables;
	}

	/**
	 * Sets this cargo StoreTradables
	 *
	 * @param cargoTradables
	 */
	public void setCurrentCargoTradables(ArrayList<StoreTradable> cargoTradables) {
		this.currentCargoTradables = cargoTradables;
	}

	/**
	 * adds StoreTradable to the collection of current StoreTradables available for
	 * trade.
	 *
	 * @param tradable
	 */
	public void addTradable(StoreTradable tradable) {
		this.currentCargoTradables.add(tradable);
	}

	/**
	 * removes the StoreTradable from the collection of current StoreTradables when
	 * Trader sells it.
	 *
	 * @param tradable
	 */
	public void removeTradable(StoreTradable tradable) {
		this.currentCargoTradables.remove(tradable);
	}

	/**
	 * Removes all StoreTradables from the collection of current StoreTradables when
	 * a RandomEvent Pirate takes all Trader goods.
	 */
	public void removeAllCargoTradables() {
		this.currentCargoTradables.clear();
	}

	/**
	 * Gets the StoreTradables sold
	 *
	 * @return
	 */
	public ArrayList<StoreTradable> getSoldCargoTradables() {
		return soldCargoTradables;
	}

	/**
	 * Sets the sold storeTradables
	 *
	 * @param soldCargoTradables
	 */
	public void setSoldCargoTradables(ArrayList<StoreTradable> soldCargoTradables) {
		this.soldCargoTradables = soldCargoTradables;
	}

	/**
	 * Overrides the toString() method to return a more meaningful description.
	 */
	@Override
	public String toString() {
		String shipInfoTemplate = "%s: %s |  %s |  %s |  %s |  %s";
		return String.format(shipInfoTemplate, name, sailSpeed, cargoCapacity, endurance, sneakiness, numberOfCrew);

	}

}
