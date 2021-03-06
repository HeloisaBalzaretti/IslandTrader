/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

/**
 * Every object in the game is an entity that has to have an identifier that
 * will facilitate the game environment.
 *
 * @author Maria Heloisa Balzaretti
 */
public abstract class Entity {
	/**
	 * id number will be used to identify each entity needed in the game
	 */
	protected int idNumber;
	/**
	 * the name of the Entity
	 */
	protected String name;
	/**
	 * The description of the Entity
	 */
	protected String description;

	/**
	 * @return the name of the Entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description of the Entity
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Creates a new Entity for the project with the given idNumber
	 *
	 * @param idNumber    This Entity idNumber
	 * @param name        this entity name
	 * @param description this entity description
	 */
	public Entity(int idNumber, String name, String description) {
		this.idNumber = idNumber;
		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the Entity idNumber
	 *
	 * @return this Entity idNumber
	 */
	public int getIdNumber() {
		return idNumber;
	}

	/**
	 * Changes the Entity idNumber
	 *
	 * @param idNumber This Entity new identifier
	 */
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
}