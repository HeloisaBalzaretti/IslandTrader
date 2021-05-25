/**
 * Contains the basic classes to build the game.
 * For example:Trader, Ship, Route, RandomEvent, Island, Store
 */
package islandtrader.core;

import java.util.Random;

/**
 * Represents a Pirate to be used in the RandomEvents encounters that will
 * happen when Trader is sailing.
 *
 * @author Maria Heloisa Balzaretti
 */
public class Pirate extends RandomEvent {

	/**
	 * the random number that represents the dice played by the trader
	 */
	private int traderDicePlayed;

	/**
	 * the random number that represents the dice played by the trader in the
	 * attempt to convince the Pirate that he is happy taking only the Trader goods
	 */
	private int makePirateHappyDicePlayed;

	/**
	 * Pirate Hapinnes level to decide if he is in a good mood, so the Trader can
	 * try beat it with the dice,
	 */
	private int pirateHappinessLevel;

	/**
	 * The number used to be played against the Trader in the dice game, so the
	 * Trader has a chance to defeat this Pirate playing the dice.
	 */
	private int numberNeededToScapeThisPirate;

	/**
	 * A boolean value to say if the Pirate is Happy or not with the goods he got
	 * from Trader.
	 */
	private boolean isPirateHappy;

	/**
	 * A boolean value to say if the Trader escaped from the Pirate and defended the
	 * Ship
	 */
	private boolean traderCouldEscape;

	/**
	 * Template message used when Trader defeated the Pirate
	 */
	private final String DEFEATED_THE_PIRATE = "You successfully scaped from the pirate and saved all your goods";

	/**
	 * Template message used when Pirate takes all goods from Trader
	 */
	private final String PIRATE_TAKE_ALL_GOODS = "It was not enough to defeat the pirate and you lost your goods!";

	/**
	 * Template message used when Pirate is Happy enough only with the Trader'goods.
	 */
	private final String MESSAGE_HAPPY_PIRATE = "You played the dice again and got a %d and it was enough to convinced %s to be satisfied and happy taking only your goods,\n you can go on with your travels";

	/**
	 * Template message used when Pirate is not happy and wants to take the
	 * Trader'Ship.
	 */
	private final String MESSAGE_WALK_THE_PLANK = "You played the dice again and got a %d but you could not convince %s to be satisfied with your goods and to let you go. You lost your ship and your money...Go walk the plank and swim with the sharks.";;

	/**
	 * A new Pirate Object is instantiated
	 *
	 * @param idNumber
	 * @param name
	 * @param description
	 * @param numberNeededToScapeThisPirate
	 * @param happinessLevel
	 */
	public Pirate(int idNumber, String name, String description, int numberNeededToScapeThisPirate,
			int happinessLevel) {
		super(idNumber, name, description);
		this.pirateHappinessLevel = happinessLevel;
		this.numberNeededToScapeThisPirate = numberNeededToScapeThisPirate;
	}

	/**
	 * Generates the specific action when Trader encounters Pirate RandomEvent It
	 * calls the helper methods to the Trader play the dice and the consequences of
	 * each outcome. if Trader can escape, Trader keep all goods. if Trader cannot
	 * escape and pirate has to be happy to only take the goods or make the Trader
	 * walk the plank and ends the game.
	 */
	@Override
	public void randomEventSpecificAction(Trader trader) {
		setTraderDicePlayed();
		if (traderDicePlayed < numberNeededToScapeThisPirate) {
			this.traderCouldEscape = false;
			trader.getShipOwned().removeAllCargoTradables();
			setMakePirateHappyDicePlayed();
			if (makePirateHappyDicePlayed < pirateHappinessLevel) {
				this.isPirateHappy = false;
				this.walkThePlank(trader);
			} else {
				this.isPirateHappy = true;
			}

		} else {
			this.traderCouldEscape = true;
		}
	}

	/**
	 * sets the random dice played to try to convince the Pirate is happy enough
	 * with Trader goods.
	 */
	private void setMakePirateHappyDicePlayed() {
		this.makePirateHappyDicePlayed = playDice();
	}

	/**
	 * Trader did not convince the Pirate to be happy taking the goods, the pirate
	 * takes the money and make the Trader walk the plank.
	 *
	 * @param trader
	 */
	private void walkThePlank(Trader trader) {
		trader.cleanTraderAccountBalance();

	}

	/**
	 * Sets a random number for the Trader dice played.
	 */
	private void setTraderDicePlayed() {
		this.traderDicePlayed = playDice();
	}

	/**
	 * Represents a dice game
	 *
	 * @return a random number between 1 and 6.
	 */
	private int playDice() {
		Random random = new Random();
		int randomDiceNumber = random.nextInt(6) + 1;
		return randomDiceNumber;
	}

	/**
	 * Overrides the super encounterMessage from RandomEvent class to the Pirate
	 * encounter event
	 */

	@Override
	public String encounterMessage() {
		return "You meet " + this.name + "! " + this.description + "\nGood luck trying to scape from " + this.name
				+ "!";
	}

	/**
	 * Overrides the super resultOfEncounterMessage from RandomEvent class to the
	 * Pirate result of encounter event
	 */
	@Override
	public String resultOfEncounterMessage() {
		return "You needed a " + this.numberNeededToScapeThisPirate + " on the dice game to scape from " + this.name
				+ ". You played the dice and got a " + traderDicePlayed;
	}

	/**
	 * The message when the Pirate wins the dice game and takes all Trader'goods.
	 *
	 * @return PIRATE_TAKE_ALL_GOODS
	 */
	public String getMessagePirateTakeAllGoods() {
		return PIRATE_TAKE_ALL_GOODS;
	}

	/**
	 * The message used when the Pirate is happy with the goods
	 *
	 * @return MESSAGE_HAPPY_PIRATE and the value of the dice played.
	 */
	public String getMessagePirateHappy() {
		return String.format(MESSAGE_HAPPY_PIRATE, this.makePirateHappyDicePlayed, this.name);
	}

	/**
	 * The message used when the pirate is not happy with the goods
	 *
	 * @return MESSAGE_WALK_THE_PLANK and the value of the dice played.
	 */
	public String getMessageWalkPlank() {
		return String.format(MESSAGE_WALK_THE_PLANK, this.makePirateHappyDicePlayed, this.name);
	}

	/**
	 * The message when Trader escapes from Pirate
	 *
	 * @return DEFEATED_THE_PIRATE
	 */
	public String getMessageScapedFromPirate() {
		return String.format(DEFEATED_THE_PIRATE, this.name);
	}

	/**
	 * @return the isPirateHappy
	 */
	public boolean isPirateHappy() {
		return this.isPirateHappy;
	}

	/**
	 * @param isPirateHappy the isPirateHappy to set
	 */
	public void setPirateHappy(boolean isPirateHappy) {
		this.isPirateHappy = isPirateHappy;
	}

	/**
	 * @return the traderCouldEscape
	 */
	public boolean isTraderCouldEscape() {
		return this.traderCouldEscape;
	}

	/**
	 * Used to personalize the game for each event.
	 */
	@Override
	public String getEventSpecificName() {
		return "Pirate";
	}
}
