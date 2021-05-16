/**
 *
 */
package islandtrader.ui;

import java.util.ArrayList;

import islandtrader.core.GameEnvironment;
import islandtrader.core.Island;
import islandtrader.core.Pirate;
import islandtrader.core.RandomEvent;
import islandtrader.core.Route;
import islandtrader.core.Ship;
import islandtrader.core.StoreTradable;
import islandtrader.core.Trader;
import repository.IslandRepository;
import repository.ShipRepository;

/**
 * @author Maria
 *
 */

/**
 * Create the following based in UML use Cases setupTrader(): void
 * generateGameBoard(): void startGame():void viewGoods(): ArrayList<Item>
 * viewHistoryOfGoods(): ArrayList<Item> viewIslands(): ArrayList<Island>
 * viewRoutes(): ArrayList<Route> viewStore(): Store viewShip(): String
 * viewShipHealth(): String repairShip(): void sail(): //RandomEvent, pay to fix
 * etc
 */

public class GameEnvironmentCmdLine extends GameEnvironment {
	private final String YOU_ARE_HERE_MESSAGE = " - [%s - You are already here!]\n";
	private final String ROUTE_MENU_HEADING = "Choose one of the available routes leaving from %s:\n0 - [Go back]\nIslandId - Route information\n";
	private final String ROUTE_MENU_LINE_MESSAGE = " - %d days of sailing with a total cost of %.2f$ to get %s\n";
	private final String ARRIVED_DESTINY_MESSAGE = "You sailed %d days to %s and it cost you $ %.2f to get here!\n";
	private final String GO_BACK_MENU_OPTION = "0 - Go back\n";

	private GameEnvironmentStoreHelper storeHelper;
	private GameEnvironmentMessageHelper messageHelper;
	private final String INVALID_OPTION_MESSAGE = "Invalid Option!";

	public GameEnvironmentCmdLine() {
		IslandRepository islandRepository = new IslandRepository();
		this.availableIslands = islandRepository.getList();
		this.storeHelper = new GameEnvironmentStoreHelper();
		this.messageHelper = new GameEnvironmentMessageHelper();
	}

	/**
	 * Set trader up to start the game at island 1 - Phuket
	 */
	@Override
	public void startGame() {
		setupTrader();
		setDuration();
		currentIsland = getRandomIsland();
		messageHelper.printMessage("Game started!");
		showMainMenu();
	}

	private void showMainMenu() {
		while (canStillPlay()) {
			showTraderCurrentInformation();
			int option = messageHelper.readInputInteger(getMainMenuMessage());
			switch (option) {
			case 0:
				messageHelper.printMessage("Game finished!");
				return;
			case 1:
				storeHelper.viewHistoryTraderGoods(availableIslands);
				break;
			case 2:
				viewIslands();
				break;
			case 3:
				viewCurrentIslandRoutes();
				break;
			case 4:
				showStoreMenu();
				break;
			case 5:
				viewShip();
				break;
			case 6:
				viewTraderCurrentGoods();
				break;
			case 7:
				repairShip();
				break;
			case 8:
				showSailMenu();
				break;
			default:
				messageHelper.printMessage(INVALID_OPTION_MESSAGE);
			}
		}
	}

	private String getMainMenuMessage() {
		String menu = "Choose what you want to do next:\n";
		menu += "0 - Quit\n";
		menu += "1 - View History Trader Goods\n";
		menu += "2 - View Islands\n";
		menu += "3 - View Current Island Routes\n";
		menu += "4 - View Store\n";
		menu += "5 - View Ship\n";
		menu += "6 - View your goods\n";
		menu += "7 - Repair Ship\n";
		menu += "8 - Sail\n";
		return menu;
	}

	private void setDuration() {
		String durationMessage = String.format("Please provide the game duration between %d and %d:", MINIMUM_DURATION,
				MAXIMUM_DURATION);
		durationChosenInDays = messageHelper.readInputInteger(durationMessage);
		while (durationChosenInDays < MINIMUM_DURATION || durationChosenInDays > MAXIMUM_DURATION) {
			messageHelper.printMessage("Invalid!\n");
			durationChosenInDays = messageHelper.readInputInteger(durationMessage);
		}
	}

	private void setupTrader() {
		String nameMessage = "Please provide your name:";
		// validate valid selection
		String selectedName = messageHelper.readInput(nameMessage);

		ShipRepository shipRepository = new ShipRepository();
		ArrayList<Ship> ships = shipRepository.getList();
		String shipMessage = "Please select one of the following ships:\n";

		String shipMessageTemplate = "%d - %s. Speed %.1f. Endurance %d. Sneakiness %d \n";

		for (int indexShip = 0; indexShip < ships.size(); indexShip++) {
			Ship ship = ships.get(indexShip);
			shipMessage += String.format(shipMessageTemplate, indexShip, ship.getName(), ship.getSailSpeed(),
					ship.getEndurance(), ship.getSneakiness());
		}
		// validate valid selection
		int selectedShipInt = messageHelper.readInputInteger(shipMessage);
		while (selectedShipInt < 0 || selectedShipInt >= ships.size()) {
			messageHelper.printMessage("Invalid!\n");
			selectedShipInt = messageHelper.readInputInteger(shipMessage);
		}
		Ship selectedShip = ships.get(selectedShipInt);

		trader = new Trader(selectedName, "Very Brave Sailer", selectedShip);
	}

	private void showTraderCurrentInformation() {
		double shiphealth = trader.getShipOwned().getCurrentHealthStatus();
		double traderAccountBalance = trader.getTraderAccountBalance();
		String shipName = trader.getShipOwned().getName();
		String traderInformation = "%s, ";
		traderInformation += "you currently have  %.2f $ available.\n";
		traderInformation += "Your ship %s is at %.2f %% Health.\n";
		traderInformation += "You are docked at %s.\n";
		traderInformation += "You already traveled a total of %d days.\n";
		traderInformation += "You have %d remaining days to sail!\n";
		messageHelper.printMessage(String.format(traderInformation, trader.getName(), traderAccountBalance, shipName,
				shiphealth, currentIsland.getName(), daysElapsed, getDaysRemaining()));
	}

	private void viewTraderCurrentGoods() {
		if (trader.getShipOwned().getCurrentCargoTradables().size() > 0) {
			for (StoreTradable storeTradable : trader.getShipOwned().getCurrentCargoTradables()) {
				String template = "%d - %s, %s. Quantity: %d\n";
				messageHelper.printMessage(String.format(template, storeTradable.getIdNumber(), storeTradable.getName(),
						storeTradable.getDescription(), storeTradable.getQuantity()));
			}
		} else {
			messageHelper.printMessage("No items.");
		}
	}

	private void viewIslands() {
		messageHelper.printMessage(availableIslands.toString());

	}

	private void viewCurrentIslandRoutes() {
		messageHelper.printMessage("Available routes leaving from " + currentIsland.getName() + ":");
		messageHelper.printMessage(getRoutesToString());
	}

	private void showStoreMenu() {
		messageHelper.printMessage(currentIsland.getStore().getDescription());
		while (canStillPlay()) {
			int option = messageHelper.readInputInteger(storeHelper.getStoreMenuMessage());
			switch (option) {
			case 0:
				messageHelper.printMessage("Left store");
				return;
			case 1:
				buyItemsFromStore();
				break;
			case 2:
				sellItemsToStore();
				break;
			case 3:
				buyUpgradesFromStore();
				break;
			case 4:
				sellUpgradesToStore();
				break;
			case 5:
				storeHelper.showBuyHistory(currentIsland.getStore());
				break;
			case 6:
				storeHelper.showSellHistory(currentIsland.getStore());
				break;
			default:
				messageHelper.printMessage(INVALID_OPTION_MESSAGE);
			}
		}
	}

	private void buyUpgradesFromStore() {
		while (canStillPlay()) {
			int option = messageHelper.readInputInteger(storeHelper.getUpgradesTraderCanBuy(currentIsland));
			if (option == 0) {
				return;
			} else {
				StoreTradable storeTradable = storeHelper.getStoreTradableFromStoreTradableId(option,
						this.currentIsland.getStore().getItemsAndUpgradesToSell(), false);
				if (storeTradable == null) {
					messageHelper.printMessage("Upgrade not found!");
				} else {
					storeHelper.buyTradableFromStore(trader, currentIsland.getStore(), storeTradable);
				}
				return;
			}
		}
	}

	private void sellUpgradesToStore() {
		while (canStillPlay()) {
			int option = messageHelper.readInputInteger(storeHelper.getUpgradesTraderCanSell(currentIsland));
			if (option == 0) {
				return;
			} else {
				StoreTradable storeTradable = storeHelper.getStoreTradableFromStoreTradableId(option,
						this.currentIsland.getStore().getItemsAndUpgradesToBuy(), false);
				if (storeTradable == null) {
					messageHelper.printMessage("Upgrade not found!");
				} else {
					storeHelper.sellTradableToStore(trader, currentIsland.getStore(), storeTradable);
					return;
				}
			}
		}
	}

	private void buyItemsFromStore() {
		while (canStillPlay()) {
			int option = messageHelper.readInputInteger(storeHelper.getItemsTraderCanBuy(currentIsland));
			if (option == 0) {
				return;
			} else {
				StoreTradable storeTradable = storeHelper.getStoreTradableFromStoreTradableId(option,
						this.currentIsland.getStore().getItemsAndUpgradesToSell(), true);
				if (storeTradable == null) {
					messageHelper.printMessage("Item not found!");
				} else {
					storeHelper.buyTradableFromStore(trader, currentIsland.getStore(), storeTradable);
					return;
				}
			}
		}
	}

	private void sellItemsToStore() {
		while (canStillPlay()) {
			int option = messageHelper.readInputInteger(storeHelper.getItemsTraderCanSell(this.currentIsland));
			if (option == 0) {
				return;
			} else {
				StoreTradable storeTradable = storeHelper.getStoreTradableFromStoreTradableId(option,
						this.currentIsland.getStore().getItemsAndUpgradesToBuy(), true);
				if (storeTradable == null) {
					messageHelper.printMessage("Item not found!");
				} else {
					storeHelper.sellTradableToStore(trader, currentIsland.getStore(), storeTradable);
					return;
				}
			}
		}
	}

	private void viewShip() {
		messageHelper.printMessage(trader.getShipOwned().getName());
	}

	private void repairShip() {
		double shipHealth = trader.getShipOwned().getCurrentHealthStatus();
		double totalDiscount = 100 - shipHealth;
		String messageTemplate = "Repairing ship. Its current health is %.2f. It will cost you %.2f $ to repair.";
		messageHelper.printMessage(String.format(messageTemplate, shipHealth, totalDiscount));
		trader.getShipOwned().repair();
		trader.removeAmountFromBalance(totalDiscount);
	}

	private void sail(int selectedIslandId) {
		int selectedIslandIndex = getIslandIndexById(selectedIslandId);
		if (currentIsland.getRoutes()[selectedIslandIndex] == null) {
			messageHelper.printMessage(String.format(YOU_ARE_HERE_MESSAGE, currentIsland.getName()));
			return;
		} else {
			setSailHelper(selectedIslandIndex);
		}
	}

	/**
	 * @param selectedIslandIndex
	 */
	private void setSailHelper(int selectedIslandIndex) {
		Island selectedIsland = availableIslands.get(selectedIslandIndex);
		int days = currentIsland.distanceToInDays(selectedIslandIndex);
		double shipHealth = trader.getShipOwned().getCurrentHealthStatus();
		double costToSail = trader.getShipOwned().getCrewCostToSailByTotalDays(days);
		if (shipHealth < 100) {
			messageHelper.printMessage("You first need to repair your ship to sail!");
			return;
		} else if (trader.getTraderAccountBalance() < costToSail) {
			messageHelper.printMessage("You don't have enough money to pay your crew for this many days sailing!");

		} else if (currentIsland.distanceToInDays(selectedIslandIndex) > getDaysRemaining()) {
			messageHelper.printMessage("You don't have enough time to afford this many days sailing!");
		} else {
			randomEventHappenedHelper(selectedIslandIndex);
			successifullySailedToAnotherIsland(selectedIsland, days, costToSail);
		}
	}

	/**
	 * @param selectedIslandIndex
	 */
	private void randomEventHappenedHelper(int selectedIslandIndex) {
		RandomEvent randomEvent = getEventFromListOfPossibleEvents(
				currentIsland.getRoutes()[selectedIslandIndex].getPossibleEvents());
		if (randomEvent != null) {
			messageHelper.printMessage(randomEvent.encounterMessage());
			randomEvent.randomEventSpecificAction(trader);
			if (randomEvent instanceof Pirate) {
				randomEventIsPirateHelper(randomEvent);
			} else {
				messageHelper.printMessage(randomEvent.resultOfEncounterMessage());
			}

		} else {
			messageHelper.printMessage("You did not encounter any interesting things during your travel...");
		}
	}

	/**
	 * @param randomEvent
	 */
	private void randomEventIsPirateHelper(RandomEvent randomEvent) {
		messageHelper.printMessage(randomEvent.resultOfEncounterMessage());
		Pirate pirate = (Pirate) randomEvent;
		if (pirate.isTraderCouldEscape()) {
			messageHelper.printMessage(pirate.getMessageScapedFromPirate());
		} else {
			messageHelper.printMessage(pirate.getMessagePirateTakeAllGoods());
			if (pirate.isPirateHappy()) {
				messageHelper.printMessage(pirate.getMessagePirateHappy());
			} else {
				pirateMadeTraderWalkThePlank(pirate);
			}
		}
	}

	/**
	 * @param pirate
	 */
	private void pirateMadeTraderWalkThePlank(Pirate pirate) {
		messageHelper.printMessage(pirate.getMessageWalkPlank());
		isGameOver = true;
		messageHelper.printMessage("Game over");
	}

	/**
	 * @param selectedIsland
	 * @param days
	 * @param costToSail
	 */
	private void successifullySailedToAnotherIsland(Island selectedIsland, int days, double costToSail) {
		trader.removeAmountFromBalance(costToSail);
		daysElapsed += days;
		currentIsland = selectedIsland;
		messageHelper.printMessage(String.format(ARRIVED_DESTINY_MESSAGE, days, currentIsland.getName(), costToSail));
	}

	private void showSailMenu() {
		while (canStillPlay()) {
			int option = messageHelper.readInputInteger(getSailMenuMessage());

			switch (option) {
			case 0:
				return;
			case 1:
				repairShip();
				break;
			case 2:
				messageHelper.printMessage(getRoutesToString());
				break;
			case 3:
				if (showRoutesMenuAndLeave()) {
					return;
				}
				break;
			default:
				messageHelper.printMessage("Invalid message");
				return;
			}
		}
	}

	private String getSailMenuMessage() {
		String menu = "Prepare to sail:\n" + GO_BACK_MENU_OPTION;
		menu += "1 - Repair Ship\n";
		menu += "2 - View Current Island Routes\n";
		menu += "3 - Sail! By choosing which route to take leaving from " + currentIsland.getName() + "\n";
		return menu;
	}

	private int getIslandIndexById(int idNumberIsland) {
		for (int islandIndex = 0; islandIndex < availableIslands.size(); islandIndex++) {
			Island island = availableIslands.get(islandIndex);
			if (island.getIdNumber() == idNumberIsland) {
				return islandIndex;
			}
		}
		return -1;
	}

	private boolean showRoutesMenuAndLeave() {
		while (canStillPlay()) {
			int option = messageHelper.readInputInteger(getRoutesMenuMessage());

			if (option == 0) {
				return false;
			} else if (option < 0 || option > availableIslands.size()) {
				messageHelper.printMessage("Island not found in the archipelago!");
			} else {
				messageHelper.printMessage("Chosen island " + option);
				sail(option);
				return true;
			}
		}
		return true;
	}

	private String getRoutesMenuMessage() {
		String menu = String.format(ROUTE_MENU_HEADING, currentIsland.getName());
		menu += getRoutesToString();
		return menu;
	}

	private String getRoutesToString() {
		String routesToString = "";
		for (int routeIndex = 0; routeIndex < currentIsland.getRoutes().length; routeIndex++) {

			Route route = currentIsland.getRoutes()[routeIndex];
			routesToString += availableIslands.get(routeIndex).getIdNumber();
			if (route != null) {
				int durationDays = currentIsland.distanceToInDays(routeIndex);
				double totalCostCrewToSail = trader.getShipOwned().getCrewCostToSailByTotalDays(durationDays);
				routesToString += String.format(ROUTE_MENU_LINE_MESSAGE, durationDays, totalCostCrewToSail, route);
			} else {
				routesToString += String.format(YOU_ARE_HERE_MESSAGE, currentIsland.getName());
			}
		}

		return routesToString;
	}

	public static void main(String args[]) {
		GameEnvironmentCmdLine game = new GameEnvironmentCmdLine();

		ShipRepository shipRepository = new ShipRepository();
		ArrayList<Ship> ships = shipRepository.getList();

		Ship selectedShip = ships.get(0);
		Trader trader = new Trader("testName", "Very Brave Sailer", selectedShip);
		game.trader = trader;

		IslandRepository islandRepository = new IslandRepository();
		game.availableIslands = islandRepository.getList();
		game.durationChosenInDays = 20;
		game.currentIsland = game.availableIslands.get(0);
		game.showMainMenu();
	}

}
