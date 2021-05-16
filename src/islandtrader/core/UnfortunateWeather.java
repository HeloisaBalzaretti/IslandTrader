/**
 *
 */
package islandtrader.core;

import java.util.Random;

/**
 * Represents the RandomEvent "Unfortunate Weather, this could be a Thunder
 * storm, very Strong Winds etc and will give some random amount of damage to
 * the Trader's Ship, the Ship may endure more if it has any upgrades to help
 * it. The repair cost is based on the amount of damage
 *
 * @author Maria
 *
 */
public class UnfortunateWeather extends RandomEvent {
	/**
	 * amountOfDamageToShip will be used to reduce ship endurance.
	 */
	private float randomAmountOfDamage = 0;
	private final String MESSAGE_ENCOUNTER = "Ohh...How unfortunate! A %s approaches...%s";
	private final String MESSAGE_AMOUNT_OF_DAMAGE = "The %s was too strong. Lucky you, your ship endured."
			+ " However it took %.2f%% of damage!";
	private final double MAX_PERCENT_OF_DAMAGE = 100;

	/**
	 * A new Unfortunate Weather of type RandomEvent.
	 *
	 * @param name
	 * @param description
	 */
	public UnfortunateWeather(int idNumber, String name, String description) {
		super(idNumber, name, description);

	}

	/**
	 * Apply damage to the Ship Health, based on endurance of Ship.
	 */
	@Override
	public void randomEventSpecificAction(Trader myTrader) {

		this.randomAmountOfDamage = generateRandomAmountOfDamage();
		double currentHealth = myTrader.getShipOwned().getCurrentHealthStatus();
		double currentEndurance = myTrader.getShipOwned().getEndurance() / 100;
		double totalDamage = randomAmountOfDamage - currentEndurance;
		double newHealth = currentHealth - (currentHealth * (1 - totalDamage));
		myTrader.getShipOwned().applyDamageToCurrentHealthStatus(Math.round(newHealth));
		myTrader.getShipOwned().setAbleToSail(false);
	}

	@Override
	public String encounterMessage() {
		return String.format(MESSAGE_ENCOUNTER, this.name, this.description);
	}

	@Override
	public String resultOfEncounterMessage() {
		return String.format(MESSAGE_AMOUNT_OF_DAMAGE, this.name, (this.randomAmountOfDamage * MAX_PERCENT_OF_DAMAGE));
	}

	/**
	 * The method generate a random number from 0.1 to 0.9, which represents a
	 * percentage that will be used to incur damage to the Ship, based on the Ship
	 * endurance and upgrades. It is between 0.1 to give a minimum damage of 10% and
	 * a maximum damage of 90%. We don't want the Ship to sink!
	 *
	 * @param amountOfDamageToShip the amountOfDamageToShip to set
	 */
	private float generateRandomAmountOfDamage() {
		Random random = new Random();
		float randomDamage = random.nextInt(9) + 1;
		randomDamage = randomDamage / 10;
		return randomDamage;
	}

	@Override
	public String getEventSpecificName() {
		return "Unfortunate Weather";
	}
}
