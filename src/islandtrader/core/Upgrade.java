/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Represents an upgrade that the Trader can buy to equip their ship. The trader
 * can also sell the upgrade to any store willing to buy it, in case the Trader
 * needs more money.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class Upgrade extends Tradable {
	/**
	 * the Upgrade properties represent: - - - increaseSneakiness: the amount of
	 * sneakiness to be added to the ship and help it escape from pirates. -
	 * increaseCargoCapacity: the amount of Cargo space to be added to the ship,
	 * allowing more items to be carried. -
	 */

	/**
	 * increaseSpeed: the amount of speed to be added to the ship.
	 */
	private int increaseSpeed = 0;

	/**
	 * increaseSneakiness: the amount of sneakiness to be added to the ship and help
	 * it escape from pirates.
	 */
	private int increaseSneakiness = 0;

	/**
	 * increaseCargoCapacity: the amount of Cargo space to be added to the ship
	 */
	private int increaseCargoCapacity = 0;

	/**
	 * increaseEndurance: the amount of endurance to be added to the ship and help
	 * it endure bad weather while out sailing.
	 */
	private int increaseEndurance = 0;

	/**
	 * Creates a new Upgrade Object of type Tradable
	 */
	/**
	 *
	 * @param name                  this Upgrade name
	 * @param description           this Upgrade description
	 * @param priceToBuy            this Upgrade price to buy
	 * @param priceToSell           this Upgrade price to sell
	 * @param idNumber              this Upgrade id number
	 * @param increaseEndurance     this Upgrade amount to increase in the Ship
	 *                              endurance
	 * @param increaseSneakiness    this Upgrade amount to increase in the Ship
	 *                              sneakiness
	 * @param increaseCargoCapacity this Upgrade amount to increase in the Ship
	 *                              cargo capacity
	 * @param increaseSpeed         this Upgrade amount to increase in the Ship
	 *                              speed
	 */
	public Upgrade(String name, String description, float priceToBuy, float priceToSell, int idNumber,
			int increaseEndurance, int increaseSneakiness, int increaseCargoCapacity, int increaseSpeed) {
		super(name, description, priceToBuy, priceToSell, idNumber);
		this.increaseEndurance = increaseEndurance;
		this.increaseSneakiness = increaseSneakiness;
		this.increaseCargoCapacity = increaseCargoCapacity;
		this.increaseSpeed = increaseSpeed;
	}

	/**
	 * Gets the amount of Speed that Upgrade will increase in the Ship.
	 *
	 * @return this Upgrade's increaseSpeed amount
	 */
	public int getIncreaseSpeed() {
		return increaseSpeed;
	}

	/**
	 * Changes the amount of increaseSpeed of this Upgrade
	 *
	 * @param increaseSpeed This Upgrade new amount of increaseSpeed
	 */
	public void setIncreaseSpeed(int increaseSpeed) {
		this.increaseSpeed = increaseSpeed;
	}

	/**
	 * Gets the amount of Sneakiness that Upgrade will increase in the Ship.
	 *
	 * @return this Upgrade's increaseSneakiness amount
	 */
	public int getIncreaseSneakiness() {
		return increaseSneakiness;
	}

	/**
	 * Changes the amount of increaseSneakiness of this Upgrade
	 *
	 * @param increaseSneakiness This Upgrade new amount of increaseSneakiness
	 */
	public void setIncreaseSneakiness(int increaseSneakiness) {
		this.increaseSneakiness = increaseSneakiness;
	}

	/**
	 * Gets the amount of CargoCapacity that Upgrade will increase in the Ship.
	 *
	 * @return this Upgrade's increaseCargoCapacity amount
	 */
	public int getIncreaseCargoCapacity() {
		return increaseCargoCapacity;
	}

	/**
	 * Changes the amount of increaseCargoCapacity of this Upgrade
	 *
	 * @param increaseCargoCapacity This Upgrade new amount of increaseCargoCapacity
	 */
	public void setIncreaseCargoCapacity(int increaseCargoCapacity) {
		this.increaseCargoCapacity = increaseCargoCapacity;
	}

	/**
	 * Gets the amount of Endurance that Upgrade will increase in the Ship.
	 *
	 * @return this Upgrade's increaseEndurance amount
	 */
	public int getIncreaseEndurance() {
		return increaseEndurance;
	}

	/**
	 * Changes the amount of increaseEndurance of this Upgrade
	 *
	 * @param increaseEndurance This Upgrade new amount of increaseEndurance
	 */
	public void setIncreaseEndurance(int increaseEndurance) {
		this.increaseEndurance = increaseEndurance;
	}

}
