package islandtrader.core;

import java.util.ArrayList;

/**
 * Class that models a Ship.
 *
 * @author Sebastian
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
	 * numberOfCrew that the Ship takes.
	 */
	private int numberOfCrew;
	/**
	 * endurance / resistance capacity of the Ship.
	 */
	private int endurance;
	/**
	 * sneakiness of the Ship. Improves the chances to escape pirates attacks.
	 */
	private int sneakiness;

	private boolean ableToSail = true;
	private ArrayList<StoreTradable> currentCargoTradables;
	private ArrayList<StoreTradable> soldCargoTradables = new ArrayList<StoreTradable>();
	private double currentHealthStatus = 100;
	private final double CREW_COST_TO_SAIL = 2.90;
	private final double FULL_HEALTH = 100;

	/**
	 * Constructor of the class. Creates and object Ship with the given parameters.
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

	public void increaseSpeed(int increaseBy) {
		this.sailSpeed += increaseBy;
	}

	public void decreaseSpeed(int decreaseBy) {
		this.sailSpeed -= decreaseBy;
	}

	public void increaseCargoHoldCapacity(double increaseBy) {
		this.cargoCapacity += increaseBy;
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
	 * Gets the number of crew that this ship takes.
	 *
	 * @return number of crew
	 */
	public int getNumberOfCrew() {
		return numberOfCrew;
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

	public void decreaseSneakiness(int decreaseBy) {
		this.sneakiness -= decreaseBy;
	}

	/**
	 * Overrides the toString() method to return a more meaningful description.
	 */

	@Override
	public String toString() {
		// return "You have selected " + name + " ship for this journey. Description: "
		// + description + " Sail speed: "
		// + sailSpeed + " km/h...MODIFY!!!";
		String shipInfoTemplate = this.name + ": " + sailSpeed + " | " + cargoCapacity + " Kgs | " + endurance + " | "
				+ sneakiness + " | " + numberOfCrew;

		return shipInfoTemplate;
	}

	public double getCrewCostToSailDaily() {
		return numberOfCrew * CREW_COST_TO_SAIL;
	}

	public double getCrewCostToSailByTotalDays(int days) {
		return getCrewCostToSailDaily() * days;
	}

	public boolean isAbleToSail() {
		return ableToSail;
	}

	public void setAbleToSail(boolean ableToSail) {
		this.ableToSail = ableToSail;
	}

	public double getCurrentHealthStatus() {
		return currentHealthStatus;
	}

	public void applyDamageToCurrentHealthStatus(double amountDamage) {
		this.currentHealthStatus -= Math.max(0, amountDamage);
	}

	public void decreaseCargoHoldCapacity(double amountToDecrease) {
		this.cargoCapacity -= amountToDecrease;
	}

	/**
	 * Trader can see the amount applied before buying
	 *
	 * @param amountUpgrade
	 * @return
	 */
	public double getNewCargoHoldCapacity(double amountUpgrade) {
		return cargoCapacity + amountUpgrade;
	}

	public ArrayList<StoreTradable> getCurrentCargoTradables() {
		return currentCargoTradables;
	}

	public void setCurrentCargoTradables(ArrayList<StoreTradable> cargoTradables) {
		this.currentCargoTradables = cargoTradables;
	}

	public ArrayList<StoreTradable> getSoldCargoTradables() {
		return soldCargoTradables;
	}

	public void setSoldCargoTradables(ArrayList<StoreTradable> allCargoTradables) {
		this.soldCargoTradables = allCargoTradables;
	}

	public void addTradable(StoreTradable tradable) {
		this.currentCargoTradables.add(tradable);
	}

	public void removeTradable(StoreTradable tradable) {
		this.currentCargoTradables.remove(tradable);
	}

	public void removeAllCargoTradables() {
		this.currentCargoTradables.clear();
	}

	public void increaseEndurance(int amountEndurance) {
		endurance += amountEndurance;
	}

	public void decreaseEndurance(int amountEndurance) {
		endurance -= amountEndurance;
	}

	public int getNewEndurance(int amountEndurance) {
		return endurance += amountEndurance;
	}

	public void damage(double damage) {
		currentHealthStatus -= damage;
		if (currentHealthStatus < FULL_HEALTH) {
			this.ableToSail = false;
		}
	}

	public void repair() {
		this.currentHealthStatus = FULL_HEALTH;
	}

}
