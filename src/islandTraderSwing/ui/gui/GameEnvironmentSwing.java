package islandTraderSwing.ui.gui;

import java.util.ArrayList;

import javax.swing.JList;

import islandtrader.core.GameEnvironment;
import islandtrader.core.Island;
import islandtrader.core.Route;
import islandtrader.core.Ship;
import islandtrader.core.Trader;
import repository.IslandRepository;
import repository.ShipRepository;

public class GameEnvironmentSwing extends GameEnvironment {

	private ArrayList<Island> islands = new ArrayList<>();
	private ArrayList<Route> routes = new ArrayList<>();
	private ArrayList<Ship> ships = new ArrayList<>();
	private Island selectedIsland;
	private Ship ship;

	/**
	 *
	 */
	public GameEnvironmentSwing() {
		ShipRepository shipRepository = new ShipRepository();
		this.ships = shipRepository.getList();
		IslandRepository islandRepository = new IslandRepository();
		islands = islandRepository.getList();
		currentIsland = islands.get(0);
	}

	public void launchMainScreen(Trader trader) {
		MainWindow mainWindow = new MainWindow(this, trader);
	}

	public void closeMainScreen(MainWindow mainWindow) {
		mainWindow.closeWindow();
		;
	}

	public void launchSetupScreen() {
		SetupTrader setupWindow = new SetupTrader(this);
	}

	public void closeSetupScreen(SetupTrader setupScreen) {
		setupScreen.closeWindow();

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
		return routes;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(ArrayList<Route> routes) {
		this.routes = routes;
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
	 * @return the selectedIsland
	 */
	public Island getSelectedIsland() {
		return selectedIsland;
	}

	/**
	 * @param selectedIsland the selectedIsland to set
	 */
	public void setSelectedIsland(JList<Island> islandsList) {
		this.selectedIsland = islandsList.getSelectedValue();
	}

	/**
	 * @param currentIsland the currentIsland to set
	 */
	public void setCurrentIsland() {
		setCurrentIsland(getIslands().get(0));

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
