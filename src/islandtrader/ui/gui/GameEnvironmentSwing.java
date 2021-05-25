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

@SuppressWarnings("unused")
public class GameEnvironmentSwing extends GameEnvironment {

	private ArrayList<Island> islands = new ArrayList<>();
	private ArrayList<Route> routesArray = new ArrayList<>();
	private ArrayList<Ship> ships = new ArrayList<>();
	private Island selectedIsland;
	private Ship ship;
	private MainWindow mainWindow;

	public GameEnvironmentSwing() {
		ShipRepository shipRepository = new ShipRepository();
		this.ships = shipRepository.getList();
		IslandRepository islandRepository = new IslandRepository();
		islands = islandRepository.getList();
		currentIsland = islands.get(0);

	}

	public void launchMainScreen(Trader trader) {
		mainWindow = new MainWindow(this);
	}

	public void closeMainScreen(MainWindow mainWindow) {
		mainWindow.closeWindow();

	}

	public MainWindow getMainScreen() {
		return mainWindow;

	}

	public void closeMainScreen() {
		mainWindow.finishedWindow();
	}

	public void launchSetupScreen() {
		SetupTraderWindow setupWindow = new SetupTraderWindow(this);
	}

	public void closeSetupScreen(SetupTraderWindow setupScreen) {
		setupScreen.closeWindow();

	}

	public void launchRoutesInformation() {
		RouteInformationWindow routesInformation = new RouteInformationWindow(this, trader);
	}

	public void closeRoutesInformation(RouteInformationWindow routesInformationwindow) {
		routesInformationwindow.closeWindow();

	}

	public void launchArchipelagoWindow() {
		ArchipelagoWindow archipelagoWindowWindow = new ArchipelagoWindow(this);
	}

	public void launchIslandDetail(Island island) {
		IslandDetailWindow islandDetailWindow = new IslandDetailWindow(island);
	}

	public void launchIslandStore() {
		IslandStoreWindow islandStore = new IslandStoreWindow(this);
	}

	public void launchCargoStoreTradablesWindow() {
		CargoCurrentGoodsWindow cargoWindow = new CargoCurrentGoodsWindow(this);
	}

	public void showSliderValue(JSlider slider, JLabel lblToChangeTxt) {
		lblToChangeTxt.setText("" + slider.getValue());
	}

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
		launchSetupScreen();

	}

	public void startMainWindowPanel() {
		launchMainScreen(trader);

	}

	protected void pirateMadeTraderWalkThePlank(Pirate pirate, JButton btnToChange) {
		isGameOver = true;
		JOptionPane.showMessageDialog(new JFrame(), pirate.getMessageWalkPlank(), "Walk the plank",
				JOptionPane.OK_OPTION);
		getTrader().getShipOwned().setAbleToSail(false);
		makeBtnInvisible(btnToChange);

	}

	public void makeBtnInvisible(JButton btnToChange) {
		btnToChange.setVisible(false);
	}

	public void changeLabelText(JLabel labelToChangeTxt, String text) {
		labelToChangeTxt.setText(text);
	}

}
