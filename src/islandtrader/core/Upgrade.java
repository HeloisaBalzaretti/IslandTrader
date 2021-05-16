package islandtrader.core;

/**
 *
 * @author x Represents an upgrade that the Trader can buy to equip their ship.
 *         The trader can also sell the upgrade to any store willing to buy it,
 *         in case the Trader needs money.
 */
public class Upgrade extends Tradable {
	/**
	 * the Upgrade properties represent: - increaseSpeed: the amount of speed to be
	 * added to the ship. - increaseSpeed: the amount of speed to be added to the
	 * ship and make it faster. - increaseSneakiness: the amount of sneakiness to be
	 * added to the ship and help it escape from pirates. - increaseCargoCapacity:
	 * the amount of Cargo space to be added to the ship, allowing more items to be
	 * carried. - increaseEndurance: the amount of endurance to be added to the ship
	 * and help it endure bad weather while out sailing.
	 */
	private int increaseSpeed = 0;
	private int increaseSneakiness = 0;
	private int increaseCargoCapacity = 0;
	private int increaseEndurance = 0;

	/**
	 * Creates a new Upgrade with the given parameters. These are from super class
	 * Tradable:
	 *
	 * @param name
	 * @param description
	 * @param priceToBuy
	 * @param priceToSell
	 * @param idNumber              These are special parameters that will be
	 *                              applied to the trader ship when Trader buys, or
	 *                              removed when trader sells it:
	 * @param increaseEndurance
	 * @param increaseSneakiness
	 * @param increaseCargoCapacity
	 * @param increaseSpeed
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
