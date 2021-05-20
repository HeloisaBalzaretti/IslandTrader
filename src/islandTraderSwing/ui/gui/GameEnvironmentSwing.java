package islandTraderSwing.ui.gui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JSlider;

import islandtrader.core.GameEnvironment;
import islandtrader.core.Island;
import islandtrader.core.Route;
import islandtrader.core.Ship;
import islandtrader.core.Trader;
import repository.IslandRepository;
import repository.ShipRepository;

@SuppressWarnings("unused")
public class GameEnvironmentSwing extends GameEnvironment {

	private ArrayList<Island> islands = new ArrayList<>();
	private ArrayList<Route> routesArray = new ArrayList<>();
	private ArrayList<Ship> ships = new ArrayList<>();
	private Island selectedIsland;
	private Ship ship;

	public GameEnvironmentSwing() {
		ShipRepository shipRepository = new ShipRepository();
		this.ships = shipRepository.getList();
		IslandRepository islandRepository = new IslandRepository();
		islands = islandRepository.getList();
		currentIsland = islands.get(0);

	}

	public void launchMainScreen(Trader trader) {
		MainWindow mainWindow = new MainWindow(this);
	}

	public void closeMainScreen(MainWindow mainWindow) {
		mainWindow.closeWindow();

	}

	public void launchSetupScreen() {
		SetupTrader setupWindow = new SetupTrader(this);
	}

	public void closeSetupScreen(SetupTrader setupScreen) {
		setupScreen.closeWindow();

	}

	public void launchRoutesInformation() {
		RouteInformation routesInformation = new RouteInformation(this, trader);
	}

	public void closeRoutesInformation(RouteInformation routesInformationwindow) {
		routesInformationwindow.closeWindow();

	}

	public void launchArchipelagoWindow() {
		ArchipelagoWindow archipelagoWindowWindow = new ArchipelagoWindow(this);
	}

	public void launchIslandDetail(Island island) {
		IslandDetail islandDetailWindow = new IslandDetail(island);
	}

	public void launchIslandStore() {
		IslandStore islandStore = new IslandStore(this);
	}

	public void launchCargoStoreTradablesWindow() {
		CargoAllStoreTradablesWindow cargoWindow = new CargoAllStoreTradablesWindow(this);
	}

	public void showSliderValue(JSlider slider, JLabel lblToChangeTxt) {
		lblToChangeTxt.setText("" + slider.getValue());
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

	public Ship getShip() {
		return ship;
	}

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
	 * @param currentIsland the currentIsland to set
	 */
	public void setCurrentIsland() {
		setCurrentIsland(getIslands().get(0));

	}

	public void setDurationChosenInDays(int durationSelected) {
		durationChosenInDays = durationSelected;
	}

	public int getDurationChosenInDays() {
		return durationChosenInDays;
	}

	@Override
	public void startGame() {

	}

	public void startMainWindowPanel() {

	}

	public static void main(String[] args) {

		GameEnvironmentSwing manager = new GameEnvironmentSwing();
		manager.launchSetupScreen();
	}

}
