package islandtrader.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import islandtrader.core.Item;
import islandtrader.core.Ship;
import islandtrader.core.Store;
import islandtrader.core.StoreTradable;
import islandtrader.core.Trader;
import islandtrader.core.Upgrade;

public class IslandStoreWindow {

	private JFrame storeWindow;
	private GameEnvironmentSwing game;

	/**
	 * Create the application.
	 */
	public IslandStoreWindow(GameEnvironmentSwing incomingGame) {
		game = incomingGame;
		initialize();
		storeWindow.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		storeWindow = new JFrame();
		storeWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainWindow mainWindow = game.getMainScreen();
				mainWindow.closeWindow();
				game.launchMainScreen(game.getTrader());
			}
		});
		storeWindow.getContentPane().setFont(new Font("Serif", Font.PLAIN, 14));
		storeWindow.getContentPane().setLayout(null);

		JLabel lblIslStore = new JLabel(game.getCurrentIsland().getName() + "'s");
		lblIslStore.setForeground(new Color(0, 0, 51));
		lblIslStore.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblIslStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblIslStore.setBounds(198, 2, 393, 53);
		storeWindow.getContentPane().add(lblIslStore);

		JLabel lblStoreImg = new JLabel("");
		lblStoreImg.setIcon(new ImageIcon(IslandStoreWindow.class.getResource("/islandtrader/images/storeIsland.png")));
		lblStoreImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblStoreImg.setBounds(296, 41, 192, 150);
		storeWindow.getContentPane().add(lblStoreImg);

		JLabel lblGoodsYouBuy = new JLabel("Goods you can buy here:");
		lblGoodsYouBuy.setFont(new Font("Serif", Font.BOLD, 16));
		lblGoodsYouBuy.setBounds(10, 167, 192, 24);
		storeWindow.getContentPane().add(lblGoodsYouBuy);

		JLabel lblGoodsYouSell = new JLabel("Goods you can sell here:");
		lblGoodsYouSell.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGoodsYouSell.setFont(new Font("Serif", Font.BOLD, 16));
		lblGoodsYouSell.setBounds(577, 167, 210, 24);
		storeWindow.getContentPane().add(lblGoodsYouSell);

		JLabel lblQtyBuy = new JLabel("0");
		lblQtyBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtyBuy.setFont(new Font("Serif", Font.BOLD, 14));
		lblQtyBuy.setBounds(289, 334, 53, 17);
		storeWindow.getContentPane().add(lblQtyBuy);

		JLabel lblQtySell = new JLabel("0");
		lblQtySell.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtySell.setFont(new Font("Serif", Font.BOLD, 14));
		lblQtySell.setBounds(732, 332, 55, 21);
		storeWindow.getContentPane().add(lblQtySell);

		JLabel lblTraderBalance = new JLabel(
				String.format("You have $ %.2f available.", game.getTrader().getTraderAccountBalance()));
		lblTraderBalance.setFont(new Font("Serif", Font.BOLD, 13));
		lblTraderBalance.setBounds(10, 10, 168, 17);
		storeWindow.getContentPane().add(lblTraderBalance);

		JLabel lblYourShipCan = new JLabel(String.format("Your ship can still carry: %.2f kgs.",
				game.getTrader().getShipOwned().getCargoCapacity()));
		lblYourShipCan.setFont(new Font("Serif", Font.BOLD, 13));
		lblYourShipCan.setBounds(10, 38, 192, 17);
		storeWindow.getContentPane().add(lblYourShipCan);

		JSlider slider_QtyBuy = new JSlider();
		slider_QtyBuy.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				game.showSliderValue(slider_QtyBuy, lblQtyBuy);
			}
		});
		slider_QtyBuy.setBounds(105, 329, 175, 22);
		slider_QtyBuy.setMinimum(0);
		slider_QtyBuy.setMaximum(0);
		slider_QtyBuy.setValue(0);

		storeWindow.getContentPane().add(slider_QtyBuy);

		JSlider slider_QtySell = new JSlider();
		slider_QtySell.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				game.showSliderValue(slider_QtySell, lblQtySell);
			}
		});
		slider_QtySell.setBounds(557, 334, 168, 22);
		slider_QtySell.setMinimum(0);
		slider_QtySell.setMaximum(0);
		slider_QtySell.setValue(0);

		storeWindow.getContentPane().add(slider_QtySell);
		DefaultListModel<StoreTradable> tradablesBuyListModel = new DefaultListModel<StoreTradable>();

		DefaultListModel<StoreTradable> tradablesSellListModel = new DefaultListModel<StoreTradable>();

		tradablesBuyListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesToSell());

		tradablesSellListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesToBuy());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 343, 96);
		storeWindow.getContentPane().add(scrollPane);

		JList<StoreTradable> list_TraderBuy = new JList<StoreTradable>(tradablesBuyListModel);
		scrollPane.setViewportView(list_TraderBuy);
		list_TraderBuy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderBuy.setFont(new Font("Serif", Font.PLAIN, 12));

		list_TraderBuy.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				slider_QtyBuy.setValue(0);
				game.showSliderValue(slider_QtyBuy, lblQtyBuy);
				if (list_TraderBuy.getSelectedValue() != null) {
					slider_QtyBuy.setMaximum(list_TraderBuy.getSelectedValue().getQuantity());
				}
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(462, 224, 343, 96);
		storeWindow.getContentPane().add(scrollPane_1);

		JList<StoreTradable> list_TraderSell = new JList<StoreTradable>(tradablesSellListModel);
		scrollPane_1.setViewportView(list_TraderSell);
		list_TraderSell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderSell.setFont(new Font("Serif", Font.PLAIN, 12));

		list_TraderSell.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				slider_QtySell.setValue(0);
				game.showSliderValue(slider_QtySell, lblQtySell);
				if (list_TraderSell.getSelectedValue() != null) {
					slider_QtySell.setMaximum(list_TraderSell.getSelectedValue().getQuantity());
				}

			}
		});

		JLabel lblYourPurchaseHistory = new JLabel("Your trade history in this island:");
		lblYourPurchaseHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourPurchaseHistory.setFont(new Font("Serif", Font.BOLD, 14));
		lblYourPurchaseHistory.setBounds(292, 411, 217, 24);
		storeWindow.getContentPane().add(lblYourPurchaseHistory);

		JButton btnBuy = new JButton("Buy");
		btnBuy.setBackground(Color.WHITE);
		btnBuy.setFont(new Font("Serif", Font.BOLD, 14));
		btnBuy.setBounds(10, 330, 85, 21);
		storeWindow.getContentPane().add(btnBuy);

		JButton btnSell = new JButton("Sell");
		btnSell.setBackground(Color.WHITE);
		btnSell.setFont(new Font("Serif", Font.BOLD, 14));
		btnSell.setBounds(462, 332, 85, 21);
		storeWindow.getContentPane().add(btnSell);

		DefaultListModel<StoreTradable> boughtHistoryListModel = new DefaultListModel<StoreTradable>();
		boughtHistoryListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesBought());

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 486, 341, 94);
		storeWindow.getContentPane().add(scrollPane_2);

		JList<StoreTradable> list_TraderHistoryBought = new JList<StoreTradable>(boughtHistoryListModel);
		scrollPane_2.setColumnHeaderView(list_TraderHistoryBought);
		list_TraderHistoryBought.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderHistoryBought.setFont(new Font("Serif", Font.PLAIN, 12));

		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = slider_QtyBuy.getValue();
				if (list_TraderBuy.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: Select an item to buy!", "Error!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				StoreTradable storeTradable = getStoreTradableFromStoreTradableId(
						list_TraderBuy.getSelectedValue().getIdNumber(),
						game.getCurrentIsland().getStore().getItemsAndUpgradesToSell());

				float price = storeTradable.getPrice();
				float totalAmountToPay = quantity * price;
				Trader trader = game.getTrader();

				if (canBuyTradable(trader, totalAmountToPay, storeTradable, quantity)) {
					storeTradable.decreaseQuantity(quantity);
					trader.removeAmountFromBalance(totalAmountToPay);

					if (storeTradable.getTradable() instanceof Upgrade) {
						Upgrade upgrade = (Upgrade) storeTradable.getTradable();
						trader.getShipOwned().increaseSpeed(upgrade.getIncreaseSpeed() * quantity);
						trader.getShipOwned().increaseSneakiness(upgrade.getIncreaseSpeed() * quantity);
						trader.getShipOwned().increaseEndurance(upgrade.getIncreaseEndurance() * quantity);
						trader.getShipOwned().increaseCargoHoldCapacity(upgrade.getIncreaseCargoCapacity() * quantity);
					} else {
						Item item = (Item) storeTradable.getTradable();
						float totalWeight = quantity * item.getWeight();
						trader.getShipOwned().decreaseCargoHoldCapacity(totalWeight);
					}
					lblYourShipCan.setText(String.format("Your ship can still carry: %.2f kgs.",
							game.getTrader().getShipOwned().getCargoCapacity()));

					lblTraderBalance.setText(
							String.format("You have $ %.2f available.", game.getTrader().getTraderAccountBalance()));

					addStoreTradableToShip(storeTradable, totalAmountToPay, quantity, trader.getShipOwned());
					addStoreTradableBoughtHistory(storeTradable, totalAmountToPay, quantity,
							game.getCurrentIsland().getStore());

					list_TraderBuy.setListData(game.getCurrentIsland().getStore().getItemsAndUpgradesToSell()
							.toArray(StoreTradable[]::new));

					list_TraderHistoryBought.setListData(game.getCurrentIsland().getStore().getItemsAndUpgradesBought()
							.toArray(StoreTradable[]::new));
					slider_QtyBuy.setMaximum(0);
					JOptionPane.showMessageDialog(new JFrame(),
							"You successfuly bought " + quantity + " " + storeTradable.getName(), "Success!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		DefaultListModel<StoreTradable> soldHistoryListModel = new DefaultListModel<StoreTradable>();
		soldHistoryListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesSold());

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(464, 486, 341, 94);
		storeWindow.getContentPane().add(scrollPane_3);

		JList<StoreTradable> list_TraderHistorySold = new JList<StoreTradable>(soldHistoryListModel);
		scrollPane_3.setViewportView(list_TraderHistorySold);
		list_TraderHistorySold.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderHistorySold.setFont(new Font("Serif", Font.PLAIN, 12));

		btnSell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = slider_QtySell.getValue();
				if (list_TraderSell.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: Select an item to sell!", "Error!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				StoreTradable storeTradable = getStoreTradableFromStoreTradableId(
						list_TraderSell.getSelectedValue().getIdNumber(),
						game.getCurrentIsland().getStore().getItemsAndUpgradesToBuy());
				float price = storeTradable.getPrice();
				float totalPrice = quantity * price;
				Trader trader = game.getTrader();

				if (canSellTradable(trader, storeTradable, quantity, trader.getShipOwned())) {
					storeTradable.decreaseQuantity(quantity);
					trader.addAmountToBalance(totalPrice);
					if (storeTradable.getTradable() instanceof Upgrade) {
						Upgrade upgrade = (Upgrade) storeTradable.getTradable();
						trader.getShipOwned().decreaseSpeed(upgrade.getIncreaseSpeed() * quantity);
						trader.getShipOwned().decreaseSneakiness(upgrade.getIncreaseSpeed() * quantity);
						trader.getShipOwned().decreaseEndurance(upgrade.getIncreaseEndurance() * quantity);
						trader.getShipOwned().decreaseCargoHoldCapacity(upgrade.getIncreaseCargoCapacity() * quantity);
					} else {
						Item item = (Item) storeTradable.getTradable();
						float totalWeight = quantity * item.getWeight();
						trader.getShipOwned().increaseCargoHoldCapacity(totalWeight);
					}
					lblYourShipCan.setText(String.format("Your ship can still carry: %.2f kgs.",
							game.getTrader().getShipOwned().getCargoCapacity()));
					lblTraderBalance.setText(
							String.format("You have $ %.2f available.", game.getTrader().getTraderAccountBalance()));
					addStoreTradableSoldHistory(storeTradable, totalPrice, quantity,
							game.getCurrentIsland().getStore());
					removeStoreTradableFromShip(storeTradable, totalPrice, quantity, trader.getShipOwned());
					lblYourShipCan.setText(String.format("Your ship can still carry: %.2f kgs.",
							game.getTrader().getShipOwned().getCargoCapacity()));
					list_TraderSell.setListData(game.getCurrentIsland().getStore().getItemsAndUpgradesToBuy()
							.toArray(StoreTradable[]::new));

					list_TraderHistorySold.setListData(
							game.getCurrentIsland().getStore().getItemsAndUpgradesSold().toArray(StoreTradable[]::new));
					slider_QtySell.setMaximum(0);
					JOptionPane.showMessageDialog(new JFrame(),
							"You successfuly sold " + quantity + " " + storeTradable.getName(), "Success!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton btnLeaveStore = new JButton("Leave Store");
		btnLeaveStore.setBackground(Color.WHITE);
		btnLeaveStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				storeWindow.dispose();
			}
		});
		btnLeaveStore.setFont(new Font("Serif", Font.BOLD, 14));
		btnLeaveStore.setBounds(348, 600, 119, 32);
		storeWindow.getContentPane().add(btnLeaveStore);

		JButton btnViewCargoGoods = new JButton("View my cargo goods");
		btnViewCargoGoods.setBackground(Color.WHITE);
		btnViewCargoGoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchCargoStoreTradablesWindow();
			}
		});
		btnViewCargoGoods.setForeground(new Color(204, 51, 102));
		btnViewCargoGoods.setFont(new Font("Serif", Font.BOLD, 13));
		btnViewCargoGoods.setBounds(10, 56, 168, 21);
		storeWindow.getContentPane().add(btnViewCargoGoods);

		JLabel lblNameDescriptionPrice = new JLabel("Name | Description | Price | Weight | Quantity");
		lblNameDescriptionPrice.setFont(new Font("Serif", Font.BOLD, 14));
		lblNameDescriptionPrice.setBounds(10, 201, 343, 24);
		storeWindow.getContentPane().add(lblNameDescriptionPrice);

		JLabel lblNameDescriptionPrice_1 = new JLabel("Name | Description | Price | Weight | Quantity");
		lblNameDescriptionPrice_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNameDescriptionPrice_1.setBounds(464, 201, 341, 24);
		storeWindow.getContentPane().add(lblNameDescriptionPrice_1);

		JLabel lblNameDescriptionPrice_2 = new JLabel("Bought:    Name | Description | Price | Weight | Quantity");
		lblNameDescriptionPrice_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNameDescriptionPrice_2.setBounds(10, 460, 356, 24);
		storeWindow.getContentPane().add(lblNameDescriptionPrice_2);

		JLabel lblNameDescriptionPrice_3 = new JLabel("Sold:     Name | Description | Price | Weight | Quantity");
		lblNameDescriptionPrice_3.setFont(new Font("Serif", Font.BOLD, 14));
		lblNameDescriptionPrice_3.setBounds(462, 460, 343, 24);
		storeWindow.getContentPane().add(lblNameDescriptionPrice_3);

		DefaultListModel<StoreTradable> tradablesListModel = new DefaultListModel<StoreTradable>();

		tradablesListModel.addAll(game.getTrader().getShipOwned().getCurrentCargoTradables());

		storeWindow.setTitle("Store");
		storeWindow.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(IslandStoreWindow.class.getResource("/islandtrader/images/storeIsland.png")));
		storeWindow.setBounds(100, 100, 825, 691);
		storeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void removeStoreTradableFromShip(StoreTradable storeTradable, float totalPrice, int quantity, Ship ship) {
		StoreTradable storeTradableInShip = getStoreTradableFromTradableId(storeTradable.getTradable().getIdNumber(),
				ship.getCurrentCargoTradables());
		storeTradableInShip.decreaseQuantity(quantity);
		if (storeTradableInShip.getQuantity() == 0) {
			ship.removeTradable(storeTradableInShip);
		} else {
			storeTradableInShip.decreasePrice(totalPrice);
		}
	}

	private void addStoreTradableSoldHistory(StoreTradable storeTradable, float totalPrice, int quantity, Store store) {
		try {
			StoreTradable storeTradableHistory = (StoreTradable) storeTradable.clone();
			storeTradableHistory.setPrice(totalPrice);
			storeTradableHistory.setQuantity(quantity);
			store.addItemSoldHistory(storeTradableHistory);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public StoreTradable getStoreTradableFromStoreTradableId(int idNumber, ArrayList<StoreTradable> storeTradables) {
		for (StoreTradable storeTradable : storeTradables) {
			if (storeTradable.getIdNumber() == idNumber) {
				return storeTradable;
			}
		}
		return null;
	}

	private boolean canSellTradable(Trader trader, StoreTradable storeTradable, int quantity, Ship ship) {
		boolean canSell = true;
		if (quantity == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Error: Invalid quantity!", "Error!",
					JOptionPane.ERROR_MESSAGE);
			canSell = false;
		} else if (quantity > storeTradable.getQuantity()) {
			JOptionPane.showMessageDialog(new JFrame(), "Store doesn't buy that amount of quantity!", "Error!",
					JOptionPane.ERROR_MESSAGE);
			canSell = false;
		} else {
			StoreTradable storeTradableInShip = getStoreTradableFromTradableId(
					storeTradable.getTradable().getIdNumber(), ship.getCurrentCargoTradables());
			if (storeTradableInShip == null || storeTradableInShip.getQuantity() < quantity) {
				canSell = false;
				JOptionPane.showMessageDialog(new JFrame(), "You don't have tradables to sell to store!", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
			if (canSell && storeTradable.getTradable() instanceof Upgrade) {
				Upgrade upgrade = (Upgrade) storeTradable.getTradable();
				if (upgrade.getIncreaseCargoCapacity() > 0) {
					double totalCapacityToSell = upgrade.getIncreaseCargoCapacity() * quantity;
					if (trader.getShipOwned().getCargoCapacity() - totalCapacityToSell < 0) {
						JOptionPane.showMessageDialog(new JFrame(),
								"You can't sell the upgrade! You need to sell items first!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						canSell = false;
					}
				}
			}
		}
		return canSell;
	}

	private void addStoreTradableBoughtHistory(StoreTradable storeTradable, float totalPrice, int quantity,
			Store store) {
		try {
			StoreTradable storeTradableHistory = (StoreTradable) storeTradable.clone();
			storeTradableHistory.setPrice(totalPrice);
			storeTradableHistory.setQuantity(quantity);
			store.addItemBoughtHistory(storeTradableHistory);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	private boolean canBuyTradable(Trader trader, float totalPrice, StoreTradable storeTradable, int quantity) {
		boolean canBuy = true;
		if (quantity == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Error: Invalid quantity!", "Error!",
					JOptionPane.ERROR_MESSAGE);
			canBuy = false;
		} else if (trader.getTraderAccountBalance() < totalPrice) {
			canBuy = false;
			JOptionPane.showMessageDialog(new JFrame(), "Error: You don't have enough money to buy the products!",
					"Error!", JOptionPane.ERROR_MESSAGE);
		} else if (quantity > storeTradable.getQuantity()) {
			canBuy = false;
			JOptionPane.showMessageDialog(new JFrame(), "Store doesn't have the selected quantity to sell!", "Error!",
					JOptionPane.ERROR_MESSAGE);
		} else if (storeTradable.getTradable() instanceof Item) {
			Item item = (Item) storeTradable.getTradable();
			float totalWeight = quantity * item.getWeight();
			if (trader.getShipOwned().getCargoCapacity() < totalWeight) {
				canBuy = false;
				JOptionPane.showMessageDialog(new JFrame(),
						"You don't have available cargo capactity to buy the items!", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return canBuy;
	}

	private void addStoreTradableToShip(StoreTradable storeTradable, float totalPrice, int quantity, Ship ship) {
		StoreTradable storeTradableInShip = getStoreTradableFromTradableId(storeTradable.getTradable().getIdNumber(),
				ship.getCurrentCargoTradables());
		if (storeTradableInShip == null) {
			try {
				StoreTradable storeTradableToAdd = (StoreTradable) storeTradable.clone();
				storeTradableToAdd.setPrice(totalPrice);
				storeTradableToAdd.setQuantity(quantity);
				ship.addTradable(storeTradableToAdd);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		} else {
			storeTradableInShip.increaseQuantity(quantity);
			storeTradableInShip.increasePrice(totalPrice);
		}
	}

	public StoreTradable getStoreTradableFromTradableId(int idNumber, ArrayList<StoreTradable> storeTradables) {
		for (StoreTradable storeTradable : storeTradables) {
			if (storeTradable.getTradable().getIdNumber() == idNumber) {
				return storeTradable;
			}
		}
		return null;
	}
}
