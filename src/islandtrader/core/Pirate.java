/**
 *
 */
package islandtrader.core;

import java.util.Random;

/**
 * @author Maria
 */
public class Pirate extends RandomEvent {

	private int traderDicePlayed;
	private int makePirateHappyDicePlayed;
	private int pirateHappinessLevel;
	private int numberNeededToScapeThisPirate;

	private boolean isPirateHappy;

	private boolean traderCouldEscape;

	private final String DEFEATED_THE_PIRATE = "You successfully scaped from the pirate and saved all your goods";
	private final String PIRATE_TAKE_ALL_GOODS = "It was not enough to defeat the pirate and you lost your goods!";
	private final String MESSAGE_HAPPY_PIRATE = "You played the dice again and got a %d and it was enough to convinced %s to be satisfied and happy taking only your goods,\n you can go on with your travels";
	private final String MESSAGE_WALK_THE_PLANK = "You played the dice again and got a %d but you could not convince %s to be satisfied with your goods and to let you go. You lost your ship and your money...Go walk the plank and swim with the sharks.";;

	public Pirate(int idNumber, String name, String description, int numberNeededToScapeThisPirate,
			int happinessLevel) {
		super(idNumber, name, description);
		this.pirateHappinessLevel = happinessLevel;
		this.numberNeededToScapeThisPirate = numberNeededToScapeThisPirate;
	}

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

	private void setMakePirateHappyDicePlayed() {
		this.makePirateHappyDicePlayed = playDice();
	}

	private void walkThePlank(Trader trader) {
		trader.cleanTraderAccountBalance();

	}

	private void setTraderDicePlayed() {
		this.traderDicePlayed = playDice();
	}

	/**
	 *
	 * @return a random number between 1 and 6.
	 */
	private int playDice() {
		Random random = new Random();
		int randomDiceNumber = random.nextInt(6) + 1;
		return randomDiceNumber;
	}

	@Override
	public String encounterMessage() {
		return "You meet " + this.name + "! " + this.description + "\nGood luck trying to scape from " + this.name
				+ "!";
	}

	@Override
	public String resultOfEncounterMessage() {
		return "You needed a " + this.numberNeededToScapeThisPirate + " on the dice game to scape from " + this.name
				+ ". You played the dice and got a " + traderDicePlayed;
	}

	public String getMessagePirateTakeAllGoods() {
		return PIRATE_TAKE_ALL_GOODS;
	}

	public String getMessagePirateHappy() {
		return String.format(MESSAGE_HAPPY_PIRATE, this.makePirateHappyDicePlayed, this.name);
	}

	public String getMessageWalkPlank() {
		return String.format(MESSAGE_WALK_THE_PLANK, this.makePirateHappyDicePlayed, this.name);
	}

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
	 * @param traderCouldEscape the traderCouldEscape to set
	 */
	public void setTraderCouldEscape(boolean traderCouldEscape) {
		this.traderCouldEscape = traderCouldEscape;
	}

	@Override
	public String getEventSpecificName() {
		return "Pirate";
	}
}
