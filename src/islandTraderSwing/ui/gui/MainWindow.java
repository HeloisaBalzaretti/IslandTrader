package islandTraderSwing.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import islandtrader.core.Island;
import islandtrader.core.Trader;

public class MainWindow {

	private JFrame mainWindowTraderAdventure;
	private GameEnvironmentSwing game;
	private Island selectedIsland;
	private String currentIslandDescription;
	private Trader trader;

	/**
	 * Create the application.
	 */
	public MainWindow(GameEnvironmentSwing incomingGame, Trader newTrader) {
		game = incomingGame;
		setTrader(newTrader);
		initialize();
		mainWindowTraderAdventure.setVisible(true);

	}

	public void closeWindow() {
		mainWindowTraderAdventure.dispose();
	}

	public void finishedWindow() {
		game.closeMainScreen(this);
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
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
	public void setSelectedIsland(Island selectedIsland) {
		this.selectedIsland = selectedIsland;
	}

	/**
	 * @return the currentIslandDescription
	 */
	public String getCurrentIslandDescription() {
		return currentIslandDescription;
	}

	/**
	 * @param currentIslandDescription the currentIslandDescription to set
	 */
	public void setCurrentIslandDescription(String currentIslandDescription) {
		this.currentIslandDescription = currentIslandDescription;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainWindowTraderAdventure = new JFrame();
		mainWindowTraderAdventure.getContentPane().setFont(new Font("Serif", Font.PLAIN, 12));
		mainWindowTraderAdventure.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\heloi\\Heloisa\\javaGroupProject\\TraderProject\\src\\islandTraderSwing\\ui\\gui\\images\\weel2.jfif"));
		mainWindowTraderAdventure.setTitle("Island Trader Adventure");
		mainWindowTraderAdventure.setBounds(100, 100, 842, 527);
		mainWindowTraderAdventure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindowTraderAdventure.getContentPane().setLayout(null);

		JLabel lblDaysToTravel = new JLabel("35");
		lblDaysToTravel.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysToTravel.setFont(new Font("Serif", Font.BOLD, 14));
		lblDaysToTravel.setBounds(691, 10, 14, 19);
		mainWindowTraderAdventure.getContentPane().add(lblDaysToTravel);

		JLabel lblDaysLeftToTravel = new JLabel("Days left to travel");
		lblDaysLeftToTravel.setVerticalAlignment(SwingConstants.TOP);
		lblDaysLeftToTravel.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysLeftToTravel.setFont(new Font("Serif", Font.BOLD, 14));
		lblDaysLeftToTravel.setBounds(708, 10, 110, 19);
		mainWindowTraderAdventure.getContentPane().add(lblDaysLeftToTravel);

		JLabel lblDaysElapsed = new JLabel("10");
		lblDaysElapsed.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysElapsed.setFont(new Font("Serif", Font.BOLD, 14));
		lblDaysElapsed.setBounds(691, 39, 14, 19);
		mainWindowTraderAdventure.getContentPane().add(lblDaysElapsed);

		JLabel lblDaysElapsedTxt = new JLabel("Days elapsed");
		lblDaysElapsedTxt.setVerticalAlignment(SwingConstants.TOP);
		lblDaysElapsedTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysElapsedTxt.setFont(new Font("Serif", Font.BOLD, 14));
		lblDaysElapsedTxt.setBounds(708, 39, 110, 19);
		mainWindowTraderAdventure.getContentPane().add(lblDaysElapsedTxt);

		JLabel lblTraderImg = new JLabel("");
		lblTraderImg.setVerticalAlignment(SwingConstants.TOP);
		lblTraderImg.setIcon(new ImageIcon(
				"C:\\Users\\heloi\\Heloisa\\javaGroupProject\\TraderProject\\src\\islandTraderSwing\\ui\\gui\\images\\capitain9.png"));
		lblTraderImg.setBounds(0, 10, 130, 90);
		mainWindowTraderAdventure.getContentPane().add(lblTraderImg);

		JLabel lblTraderName = new JLabel(trader.getName() + " " + trader.getDescription());
		lblTraderName.setFont(new Font("Serif", Font.BOLD, 14));
		lblTraderName.setBounds(158, 7, 326, 24);
		mainWindowTraderAdventure.getContentPane().add(lblTraderName);

		JLabel lblNewLabel = new JLabel(trader.getTraderAccountBalance() + " $");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(321, 39, 62, 19);
		mainWindowTraderAdventure.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Current account balance:");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 42, 171, 13);
		mainWindowTraderAdventure.getContentPane().add(lblNewLabel_1);

		JLabel lblShip = new JLabel("Ship: " + trader.getShipOwned().getName());
		lblShip.setVerticalAlignment(SwingConstants.TOP);
		lblShip.setFont(new Font("Serif", Font.BOLD, 14));
		lblShip.setBounds(158, 62, 105, 22);
		mainWindowTraderAdventure.getContentPane().add(lblShip);

		JLabel lblShipProp = new JLabel(trader.getShipOwned().getDescription());
		lblShipProp.setBackground(Color.WHITE);
		lblShipProp.setIcon(null);
		lblShipProp.setVerticalAlignment(SwingConstants.TOP);
		lblShipProp.setFont(new Font("Serif", Font.BOLD, 14));
		lblShipProp.setBounds(260, 62, 558, 24);
		mainWindowTraderAdventure.getContentPane().add(lblShipProp);

		JLabel lblIslandImg = new JLabel("");
		lblIslandImg.setIcon(new ImageIcon(
				"C:\\Users\\heloi\\Heloisa\\javaGroupProject\\TraderProject\\src\\islandTraderSwing\\ui\\gui\\images\\Island8Sml.png"));
		lblIslandImg.setBounds(10, 154, 189, 184);
		mainWindowTraderAdventure.getContentPane().add(lblIslandImg);

		JLabel lblCurrentDocked = new JLabel("Currently docket at " + game.getCurrentIsland().getName());
		lblCurrentDocked.setFont(new Font("Serif", Font.BOLD, 14));
		lblCurrentDocked.setBounds(10, 127, 224, 24);
		mainWindowTraderAdventure.getContentPane().add(lblCurrentDocked);

		JButton btnViewRoutesLeavingCurrentIsland = new JButton("View Route Information");
		btnViewRoutesLeavingCurrentIsland.setForeground(new Color(51, 51, 102));
		btnViewRoutesLeavingCurrentIsland.setFont(new Font("Serif", Font.BOLD, 16));
		btnViewRoutesLeavingCurrentIsland.setBackground(Color.WHITE);
		btnViewRoutesLeavingCurrentIsland.setBounds(382, 419, 204, 37);
		mainWindowTraderAdventure.getContentPane().add(btnViewRoutesLeavingCurrentIsland);

		JButton btnViewMyItems = new JButton("View my cargo items");
		btnViewMyItems.setForeground(new Color(51, 51, 102));
		btnViewMyItems.setFont(new Font("Serif", Font.BOLD, 14));
		btnViewMyItems.setBackground(Color.WHITE);
		btnViewMyItems.setBounds(486, 10, 169, 35);
		mainWindowTraderAdventure.getContentPane().add(btnViewMyItems);

		JButton btnSail = new JButton("Sail!");
		btnSail.setForeground(new Color(0, 153, 102));
		btnSail.setFont(new Font("Serif", Font.BOLD, 16));
		btnSail.setBackground(Color.WHITE);
		btnSail.setBounds(646, 419, 69, 37);
		mainWindowTraderAdventure.getContentPane().add(btnSail);

		JLabel lbCurrentlIslandDescription = new JLabel(game.getCurrentIsland().getDescription());
		lbCurrentlIslandDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lbCurrentlIslandDescription.setVerticalAlignment(SwingConstants.TOP);
		lbCurrentlIslandDescription.setFont(new Font("Serif", Font.PLAIN, 16));
		lbCurrentlIslandDescription.setBounds(170, 185, 648, 37);
		mainWindowTraderAdventure.getContentPane().add(lbCurrentlIslandDescription);

		JButton btnViewCurrentStore = new JButton("Visit Island Store");
		btnViewCurrentStore.setForeground(new Color(51, 51, 102));
		btnViewCurrentStore.setFont(new Font("Serif", Font.BOLD, 16));
		btnViewCurrentStore.setBackground(Color.WHITE);
		btnViewCurrentStore.setBounds(10, 366, 171, 43);
		mainWindowTraderAdventure.getContentPane().add(btnViewCurrentStore);

		JProgressBar progressBarShipHealth = new JProgressBar();
		progressBarShipHealth.setValue(100);
		progressBarShipHealth.setBounds(236, 87, 130, 16);
		mainWindowTraderAdventure.getContentPane().add(progressBarShipHealth);

		JLabel lblShipHealth = new JLabel("Ship Health");
		lblShipHealth.setFont(new Font("Serif", Font.BOLD, 14));
		lblShipHealth.setBounds(158, 86, 85, 19);
		mainWindowTraderAdventure.getContentPane().add(lblShipHealth);

		JButton btnViewArchipelagoMap = new JButton("View Archipelago");
		btnViewArchipelagoMap.setForeground(new Color(51, 51, 102));
		btnViewArchipelagoMap.setFont(new Font("Serif", Font.BOLD, 16));
		btnViewArchipelagoMap.setBackground(Color.WHITE);
		btnViewArchipelagoMap.setBounds(9, 413, 172, 43);
		mainWindowTraderAdventure.getContentPane().add(btnViewArchipelagoMap);

		JLabel lblChooseIsland = new JLabel("Choose Island to Sail:");
		lblChooseIsland.setFont(new Font("Serif", Font.BOLD, 14));
		lblChooseIsland.setBounds(474, 257, 146, 24);
		mainWindowTraderAdventure.getContentPane().add(lblChooseIsland);

		DefaultListModel<Island> islandListModel = new DefaultListModel<Island>();
		islandListModel.addAll(game.getIslands());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(317, 284, 501, 125);
		mainWindowTraderAdventure.getContentPane().add(scrollPane);

		JList<Island> islandsList = new JList<Island>(islandListModel);
		scrollPane.setViewportView(islandsList);
		islandsList.setFont(new Font("Serif", Font.PLAIN, 14));

		islandsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

}
