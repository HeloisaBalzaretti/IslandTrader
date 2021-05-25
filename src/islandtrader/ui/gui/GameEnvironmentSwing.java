/**
 * Package with the gui interface application
 */
package islandtrader.ui.gui;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

import islandtrader.core.GameEnvironment;
import islandtrader.core.Island;
import islandtrader.core.Pirate;
import islandtrader.core.Route;
import islandtrader.core.Ship;
import islandtrader.core.Trader;
import repository.IslandRepository;
import repository.ShipRepository;

/**
 * Creates the GUI interface application
 *
 * @author Maria Heloisa Balzaretti
 *
 */
@SuppressWarnings("unused")
public class GameEnvironmentSwing extends GameEnvironment {

	private ArrayList<Island> islands = new ArrayList<>();
	private ArrayList<Route> routesArray = new ArrayList<>();
	private ArrayList<Ship> ships = new ArrayList<>();
	private Island selectedIsland;
	private Ship ship;
	private MainWindow mainWindow;

	/**
	 * Creates a new game using gui interface application
	 */
	public GameEnvironmentSwing() {
		ShipRepository shipRepository = new ShipRepository();
		this.ships = shipRepository.getList();
		IslandRepository islandRepository = new IslandRepository();
		islands = islandRepository.getList();
		currentIsland = islands.get(0);

	}

	/**
	 * Launches the main window
	 *
	 * @param trader the Trader
	 */
	public void launchMainScreen(Trader trader) {
		mainWindow = new MainWindow(this);
	}

	/**
	 * Closes the main window
	 *
	 * @param mainWindow to be closed
	 */
	public void closeMainScreen(MainWindow mainWindow) {
		mainWindow.closeWindow();

	}

	/**
	 * Gets the main window
	 *
	 * @return mainWindow
	 */
	public MainWindow getMainScreen() {
		return mainWindow;

	}

	/**
	 * Closes the main window
	 */
	public void closeMainScreen() {
		mainWindow.finishedWindow();
	}

	/**
	 * Launch set up window
	 */
	public void launchSetupScreen() {
		SetupTraderWindow setupWindow = new SetupTraderWindow(this);
	}

	/**
	 * Closes the set up screen
	 *
	 * @param setupScreen to be closed
	 */
	public void closeSetupScreen(SetupTraderWindow setupScreen) {
		setupScreen.closeWindow();

	}

	/**
	 * Launches Routes information window
	 */
	public void launchRoutesInformation() {
		RouteInformationWindow routesInformation = new RouteInformationWindow(this, trader);
	}

	/**
	 * close routes information window
	 *
	 * @param routesInformationwindow to be closed
	 */
	public void closeRoutesInformation(RouteInformationWindow routesInformationwindow) {
		routesInformationwindow.closeWindow();

	}

	/**
	 * Launch the Archipelago window that represents a map
	 */
	public void launchArchipelagoWindow() {
		ArchipelagoWindow archipelagoWindowWindow = new ArchipelagoWindow(this);
	}

	/**
	 * Launches the window with the details about the selected Island from the
	 * Archipelago window.
	 *
	 * @param island the Island which to show the information
	 */
	public void launchIslandDetail(Island island) {
		IslandDetailWindow islandDetailWindow = new IslandDetailWindow(island);
	}

	/**
	 * Launches the Store window
	 */
	public void launchIslandStore() {
		IslandStoreWindow islandStore = new IslandStoreWindow(this);
	}

	/**
	 * Launches the current Cargo tradables window
	 */
	public void launchCargoStoreTradablesWindow() {
		CargoCurrentGoodsWindow cargoWindow = new CargoCurrentGoodsWindow(this);
	}

	/**
	 * Shows the slider value in the label
	 *
	 * @param slider         to take the value from
	 * @param lblToChangeTxt the label that shows the value
	 */
	public void showSliderValue(JSlider slider, JLabel lblToChangeTxt) {
		lblToChangeTxt.setText("" + slider.getValue());
	}

	/**
	 * Updates the information about the trader in the main window
	 *
	 * @param lblTraderInfo label to be updated
	 */
	public void updateLblTraderInfo(JLabel lblTraderInfo) {
		lblTraderInfo.setText(trader.toString());
	}

	/**
	 * @return the trader
	 */
	public Trader getTrader() {
		return trader;
	}

	/**
	 * @param trader the trader to set
	 */
	public void setTrader(Trader trader) {

		this.trader = trader;
	}

	/**
	 * @return the ships
	 */
	public ArrayList<Ship> getShips() {
		return ships;
	}

	/**
	 * @param ships the ships to set
	 */
	public void setShips(ArrayList<Ship> ships) {
		this.ships = ships;
	}

	/**
	 * Get Ship used in the game
	 *
	 * @return ship
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * Set the ship to the game
	 *
	 * @param ship to be captained by Trader
	 */
	public void setShip(Ship ship) {
		this.ship = ship;
	}

	/**
	 * @param selectedIsland the selectedIsland to set
	 */

	public void setSelectedIsland(Island selectedIsland) {
		this.selectedIsland = selectedIsland;
	}

	/**
	 * @return the selectedIsland
	 */
	public Island getSelectedIsland() {
		return selectedIsland;
	}

	/**
	 * @return the islands
	 */
	public ArrayList<Island> getIslands() {
		return islands;
	}

	/**
	 * @param islands the islands to set
	 */
	public void setIslands(ArrayList<Island> islands) {
		this.islands = islands;
	}

	/**
	 * @return the routes
	 */
	public ArrayList<Route> getRoutes() {
		return routesArray;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(ArrayList<Route> routes) {

		this.routesArray = routes;
	}

	/**
	 * @return the currentIsland
	 */
	public Island getCurrentIsland() {
		return currentIsland;
	}

	/**
	 * @param currentIsland the currentIsland to set
	 */
	public void setCurrentIsland(Island currentIsland) {

		this.currentIsland = currentIsland;
	}

	/**
	 * currentIsland the currentIsland to set
	 */
	public void setCurrentIsland() {
		setCurrentIsland(getIslands().get(0));

	}

	/**
	 * Sets the duration for the game
	 *
	 * @param durationSelected the number of days to play the game
	 */
	public void setDurationChosenInDays(int durationSelected) {
		durationChosenInDays = durationSelected;
	}

	/**
	 * the chosen duration for the game
	 *
	 * @return durationChosenInDays
	 */
	public int getDurationChosenInDays() {
		return durationChosenInDays;
	}

	/**
	 * Entry point to Start the game in the gui interface
	 */
	@Override
	public void startGame() {
		launchSetupScreen();

	}

	/**
	 * Starts the main window
	 */
	public void startMainWindowPanel() {
		launchMainScreen(trader);

	}

	/**
	 * Helper method for the Sail button, when the random event is Pirate and it
	 * makes the Trader walk the plank
	 *
	 * @param pirate
	 * @param btnToChange
	 */
	protected void pirateMadeTraderWalkThePlank(Pirate pirate, JButton btnToChange) {
		isGameOver = true;
		JOptionPane.showMessageDialog(new JFrame(), pirate.getMessageWalkPlank(), "Walk the plank",
				JOptionPane.OK_OPTION);
		getTrader().getShipOwned().setAbleToSail(false);
		makeBtnInvisible(btnToChange);
	}

	/**
	 * Helper method to change button visibility
	 *
	 * @param btnToChange the button to be set invisible
	 */
	public void makeBtnInvisible(JButton btnToChange) {
		btnToChange.setVisible(false);
	}

	/**
	 * Changes the labelToChangeTxt with the given text
	 *
	 * @param labelToChangeTxt label to change
	 * @param text             to be used in the label
	 */
	public void changeLabelText(JLabel labelToChangeTxt, String text) {
		labelToChangeTxt.setText(text);
	}

}
