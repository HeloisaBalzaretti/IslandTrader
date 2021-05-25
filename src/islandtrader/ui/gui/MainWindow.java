package islandtrader.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import islandtrader.core.Island;
import islandtrader.core.Pirate;
import islandtrader.core.RandomEvent;
import islandtrader.core.Ship;
import islandtrader.core.Trader;

public class MainWindow {

	private JFrame mainWindowTraderAdventure;
	private GameEnvironmentSwing game;
	private String currentIslandDescription;

	/**
	 * Create the application.
	 */
	public MainWindow(GameEnvironmentSwing incomingGame) {
		game = incomingGame;

		initialize();
		mainWindowTraderAdventure.setVisible(true);

	}

	public void closeWindow() {
		mainWindowTraderAdventure.dispose();
	}

	public void finishedWindow() {
		game.closeMainScreen(this);
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
		mainWindowTraderAdventure.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MainWindow.class.getResource("/islandtrader/images/shipWater1.png")));
		mainWindowTraderAdventure.setTitle("Island Trader Adventure");
		mainWindowTraderAdventure.setBounds(100, 100, 945, 608);
		mainWindowTraderAdventure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindowTraderAdventure.getContentPane().setLayout(null);

		JLabel lblDaysLeftToTravel = new JLabel("Days left to travel :  " + game.getDaysRemaining());
		lblDaysLeftToTravel.setVerticalAlignment(SwingConstants.TOP);
		lblDaysLeftToTravel.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysLeftToTravel.setFont(new Font("Serif", Font.BOLD, 14));
		lblDaysLeftToTravel.setBounds(768, 10, 153, 19);
		mainWindowTraderAdventure.getContentPane().add(lblDaysLeftToTravel);

		JLabel lblDaysElapsedTxt = new JLabel("Days elapsed: " + game.getDaysElapsed());
		lblDaysElapsedTxt.setVerticalAlignment(SwingConstants.TOP);
		lblDaysElapsedTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysElapsedTxt.setFont(new Font("Serif", Font.BOLD, 14));
		lblDaysElapsedTxt.setBounds(768, 39, 153, 19);
		mainWindowTraderAdventure.getContentPane().add(lblDaysElapsedTxt);

		JLabel lblTraderImg = new JLabel("");
		lblTraderImg.setVerticalAlignment(SwingConstants.TOP);
		lblTraderImg.setIcon(new ImageIcon(MainWindow.class.getResource("/islandtrader/images/capitain9.png")));
		lblTraderImg.setBounds(0, 10, 130, 90);
		mainWindowTraderAdventure.getContentPane().add(lblTraderImg);

		JLabel lblTraderInfo = new JLabel(game.getTrader().toString());
		lblTraderInfo.setFont(new Font("Serif", Font.BOLD, 14));
		lblTraderInfo.setBounds(158, 7, 497, 24);

		mainWindowTraderAdventure.getContentPane().add(lblTraderInfo);

		JLabel lblShipDescription = new JLabel(game.getTrader().getShipOwned().getDescription());
		lblShipDescription.setBackground(Color.WHITE);
		lblShipDescription.setIcon(null);
		lblShipDescription.setVerticalAlignment(SwingConstants.TOP);
		lblShipDescription.setFont(new Font("Serif", Font.BOLD, 14));
		lblShipDescription.setBounds(140, 42, 678, 24);
		mainWindowTraderAdventure.getContentPane().add(lblShipDescription);

		JLabel lblIslandImg = new JLabel("");
		lblIslandImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblIslandImg.setIcon(new ImageIcon(MainWindow.class.getResource(changeIslandImg())));
		lblIslandImg.setBounds(10, 223, 253, 201);
		mainWindowTraderAdventure.getContentPane().add(lblIslandImg);

		JLabel lblCurrentDocked = new JLabel("Currently docket at " + game.getCurrentIsland().getName());
		lblCurrentDocked.setForeground(new Color(0, 0, 102));
		lblCurrentDocked.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblCurrentDocked.setBounds(273, 223, 561, 58);
		mainWindowTraderAdventure.getContentPane().add(lblCurrentDocked);

		JButton btnViewMyItems = new JButton("View my cargo items");
		btnViewMyItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchCargoStoreTradablesWindow();

			}
		});
		btnViewMyItems.setForeground(new Color(51, 51, 102));
		btnViewMyItems.setFont(new Font("Serif", Font.BOLD, 14));
		btnViewMyItems.setBackground(Color.WHITE);
		btnViewMyItems.setBounds(649, 116, 169, 24);
		mainWindowTraderAdventure.getContentPane().add(btnViewMyItems);

		JLabel lbCurrentlIslandDescription = new JLabel(game.getCurrentIsland().getDescription());
		lbCurrentlIslandDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lbCurrentlIslandDescription.setVerticalAlignment(SwingConstants.TOP);
		lbCurrentlIslandDescription.setFont(new Font("Serif", Font.PLAIN, 17));
		lbCurrentlIslandDescription.setBounds(289, 291, 545, 37);
		mainWindowTraderAdventure.getContentPane().add(lbCurrentlIslandDescription);

		JButton btnViewCurrentStore = new JButton("Visit Island Store");
		btnViewCurrentStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchIslandStore();
			}
		});
		btnViewCurrentStore.setForeground(new Color(0, 153, 255));
		btnViewCurrentStore.setFont(new Font("Serif", Font.BOLD, 16));
		btnViewCurrentStore.setBackground(Color.WHITE);
		btnViewCurrentStore.setBounds(10, 434, 171, 35);
		mainWindowTraderAdventure.getContentPane().add(btnViewCurrentStore);

		JProgressBar progressBarShipHealth = new JProgressBar();
		progressBarShipHealth.setForeground(new Color(102, 255, 51));
		progressBarShipHealth.setValue((int) game.getTrader().getShipOwned().getCurrentHealthStatus());
		progressBarShipHealth.setBounds(115, 124, 130, 16);
		mainWindowTraderAdventure.getContentPane().add(progressBarShipHealth);

		JLabel lblShipHealth = new JLabel("Ship Health");
		lblShipHealth.setFont(new Font("Serif", Font.BOLD, 14));
		lblShipHealth.setBounds(23, 121, 85, 19);
		mainWindowTraderAdventure.getContentPane().add(lblShipHealth);

		JButton btnViewArchipelagoMap = new JButton("View Archipelago Map Information");
		btnViewArchipelagoMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchArchipelagoWindow();
			}
		});
		btnViewArchipelagoMap.setForeground(new Color(51, 51, 102));
		btnViewArchipelagoMap.setFont(new Font("Serif", Font.BOLD, 16));
		btnViewArchipelagoMap.setBackground(Color.WHITE);
		btnViewArchipelagoMap.setBounds(10, 526, 300, 35);
		mainWindowTraderAdventure.getContentPane().add(btnViewArchipelagoMap);

		JLabel lblChooseIsland = new JLabel("Select Island to Sail:");
		lblChooseIsland.setFont(new Font("Serif", Font.BOLD, 14));
		lblChooseIsland.setBounds(598, 364, 136, 24);
		mainWindowTraderAdventure.getContentPane().add(lblChooseIsland);

		DefaultListModel<Island> islandListModel = new DefaultListModel<Island>();
		islandListModel.addAll(game.getIslands());
		islandListModel.removeElement(game.getCurrentIsland());

		JButton btnViewRoutesLeavingCurrentIsland = new JButton("View All Routes Information");
		btnViewRoutesLeavingCurrentIsland.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewRoutesLeavingCurrentIsland.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchRoutesInformation();
			}
		});
		btnViewRoutesLeavingCurrentIsland.setForeground(new Color(51, 51, 102));
		btnViewRoutesLeavingCurrentIsland.setFont(new Font("Serif", Font.BOLD, 16));
		btnViewRoutesLeavingCurrentIsland.setBackground(Color.WHITE);
		btnViewRoutesLeavingCurrentIsland.setBounds(10, 479, 230, 37);
		mainWindowTraderAdventure.getContentPane().add(btnViewRoutesLeavingCurrentIsland);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 398, 497, 97);
		mainWindowTraderAdventure.getContentPane().add(scrollPane);

		JList<Island> islandsList = new JList<Island>(islandListModel);
		scrollPane.setViewportView(islandsList);
		islandsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				game.getTrader().getShipOwned().setAbleToSail(true);
			}
		});
		islandsList.setFont(new Font("Serif", Font.PLAIN, 14));

		islandsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnFixShip = new JButton("Repair Ship");
		btnFixShip.setForeground(new Color(0, 153, 153));
		btnFixShip.setBackground(Color.WHITE);
		btnFixShip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Trader trader = game.getTrader();
				Ship traderShip = trader.getShipOwned();
				double shipHealth = traderShip.getCurrentHealthStatus();
				double totalDiscount = 100 - shipHealth;
				totalDiscount = totalDiscount * 10;
				String shipFullHealthMessage = "Your ship " + traderShip.getName()
						+ " is at full health.\nYou do not need to fix it now!";
				String messageTemplate = "Repairing ship. Its current health is %.2f. It will cost you %.2f $ to repair.\n";

				if (trader.getShipOwned().getCurrentHealthStatus() == 100) {
					JOptionPane.showMessageDialog(new JFrame(), shipFullHealthMessage,
							traderShip.getName() + " at 100%", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (trader.getTraderAccountBalance() < totalDiscount) {
					messageTemplate += "You do not have enough money to pay the repair";
				} else {
					traderShip.repair();
					trader.removeAmountFromBalance(totalDiscount);
					progressBarShipHealth.setValue((int) traderShip.getCurrentHealthStatus());
					traderShip.setAbleToSail(true);
				}
				JOptionPane.showMessageDialog(new JFrame(), String.format(messageTemplate, shipHealth, totalDiscount),
						"Repairing Ship", JOptionPane.YES_NO_CANCEL_OPTION);

			}
		});
		btnFixShip.setFont(new Font("Serif", Font.BOLD, 12));
		btnFixShip.setBounds(258, 119, 108, 21);
		mainWindowTraderAdventure.getContentPane().add(btnFixShip);

		JButton btnSail = new JButton("Sail!");
		btnSail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String errorMessageTemplate = "You cannot sail! \n";
				String errors = "";

				if (islandsList.getSelectedValue() == null) {

					errors += "You need to choose the Island to visit!";
					JOptionPane.showMessageDialog(new JFrame(), errorMessageTemplate + errors, "Oops",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Trader trader = game.getTrader();
				game.setSelectedIsland(islandsList.getSelectedValue());

				Ship traderShip = game.getTrader().getShipOwned();
				Island islandToGo = game.getSelectedIsland();
				int daysNeededToTravel = game.getCurrentIsland()
						.getDistanceToAnotherIslandInDays(islandToGo.getIdNumber() - 1);

				int daysRemaining = game.getDaysRemaining();
				int daysElapsed = game.getDaysElapsed();
				double shipHealth = traderShip.getCurrentHealthStatus();
				double costToSail = traderShip.getCrewCostToSailByTotalDays(daysNeededToTravel);

				if (shipHealth < 100) {
					traderShip.setAbleToSail(false);
					errors += "You must repair you ship before you sail again!\n";
				}
				if (trader.getTraderAccountBalance() < costToSail) {
					traderShip.setAbleToSail(false);
					errors += "You do not have enough money to afford this many days sailing!\n";
				}
				if (daysNeededToTravel > daysRemaining) {
					traderShip.setAbleToSail(false);
					errors += "You do not have enough days remaining go this far!\n";
				}

				if (traderShip.isAbleToSail()) {
					errorMessageTemplate = "You sailed during %d days to %s \nThe travel cost you: %.2f $\n";

					JOptionPane.showMessageDialog(new JFrame(),
							String.format(errorMessageTemplate + errors, daysNeededToTravel,
									game.getSelectedIsland().getName(), costToSail),
							"Sailing...", JOptionPane.INFORMATION_MESSAGE);

					randomEventHappenedHelper(game.getSelectedIsland().getIdNumber() - 1, btnSail);
					trader.removeAmountFromBalance(costToSail);
					game.setCurrentIsland(islandToGo);
					game.setDaysElapsed(daysElapsed + daysNeededToTravel);
					game.launchMainScreen(game.getTrader());
					mainWindowTraderAdventure.dispose();

				} else {
					JOptionPane.showMessageDialog(new JFrame(), errorMessageTemplate + errors, "Sailing...",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnSail.setForeground(new Color(0, 153, 102));
		btnSail.setFont(new Font("Serif", Font.BOLD, 16));
		btnSail.setBackground(Color.WHITE);
		btnSail.setBounds(649, 505, 85, 35);
		mainWindowTraderAdventure.getContentPane().add(btnSail);

		JLabel lblDdd = new JLabel(game.getTrader().getShipOwned().toString());
		lblDdd.setVerticalAlignment(SwingConstants.TOP);
		lblDdd.setFont(new Font("Serif", Font.BOLD, 14));
		lblDdd.setBackground(Color.WHITE);
		lblDdd.setBounds(140, 76, 678, 24);
		mainWindowTraderAdventure.getContentPane().add(lblDdd);

		JLabel lblShipProperties = new JLabel((String) null);
		lblShipProperties.setVerticalAlignment(SwingConstants.TOP);
		lblShipProperties.setFont(new Font("Serif", Font.BOLD, 14));
		lblShipProperties.setBackground(Color.WHITE);
		lblShipProperties.setBounds(140, 96, 678, 24);
		mainWindowTraderAdventure.getContentPane().add(lblShipProperties);
		mainWindowTraderAdventure.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				game.changeLabelText(lblTraderInfo, game.getTrader().toString());
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				game.changeLabelText(lblTraderInfo, game.getTrader().toString());
			}

		});

	}

	private void randomEventHappenedHelper(int selectedIslandIndex, JButton btnSail) {
		String eventEncounterMessage = "";
		String eventResultOfEncounterMessage = "";
		RandomEvent randomEvent = game.getEventFromListOfPossibleEvents(
				game.getCurrentIsland().getRoutes()[selectedIslandIndex].getPossibleEvents());

		if (randomEvent != null) {

			eventEncounterMessage += randomEvent.encounterMessage() + "\n";
			randomEvent.randomEventSpecificAction(game.getTrader());
			JOptionPane.showMessageDialog(new JFrame(), eventEncounterMessage, randomEvent.getName(),
					JOptionPane.WARNING_MESSAGE);

			if (randomEvent instanceof Pirate) {
				randomEventIsPirateHelper(randomEvent, btnSail);
			} else {
				eventResultOfEncounterMessage += randomEvent.resultOfEncounterMessage() + "\n";
				JOptionPane.showMessageDialog(new JFrame(), eventResultOfEncounterMessage, randomEvent.getName(),
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else {
			eventEncounterMessage = "You did not encounter any interesting things during your travel...\n";
			JOptionPane.showMessageDialog(new JFrame(), eventEncounterMessage, "All goody", JOptionPane.DEFAULT_OPTION);
		}
	}

	private void randomEventIsPirateHelper(RandomEvent randomEvent, JButton btnSail) {

		Pirate pirate = (Pirate) randomEvent;

		if (pirate.isTraderCouldEscape()) {
			JOptionPane.showMessageDialog(new JFrame(), pirate.getMessageScapedFromPirate(), pirate.getName(),
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), randomEvent.resultOfEncounterMessage(), pirate.getName(),
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(new JFrame(), pirate.getMessagePirateTakeAllGoods(), randomEvent.getName(),
					JOptionPane.INFORMATION_MESSAGE);

			if (pirate.isPirateHappy()) {
				game.getTrader().getShipOwned().setAbleToSail(true);

				JOptionPane.showMessageDialog(new JFrame(), pirate.getMessagePirateHappy(), pirate.getName(),
						JOptionPane.INFORMATION_MESSAGE);
			} else {

				game.pirateMadeTraderWalkThePlank(pirate, btnSail);

			}
		}
	}

	public String changeIslandImg() {
		String sourcePath;
		int indexIsland = game.getCurrentIsland().getIdNumber() - 1;
		switch (indexIsland) {

		case 0:
			sourcePath = "/islandtrader/images/island1Sml.png";
			break;
		case 1:
			sourcePath = "/islandtrader/images/island2Sml.png";
			break;
		case 2:
			sourcePath = "/islandtrader/images/island3Sml.png";
			break;
		case 3:
			sourcePath = "/islandtrader/images/island4Sml.png";
			break;
		case 4:
			sourcePath = "/islandtrader/images/island5Sml.png";
			break;
		default:
			sourcePath = "/islandtrader/images/island0Sml.png";
			break;

		}
		return sourcePath;
	}
}
