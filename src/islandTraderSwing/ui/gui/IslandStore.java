package islandTraderSwing.ui.gui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import islandtrader.core.Ship;
import islandtrader.core.StoreTradable;
import islandtrader.core.Trader;

public class IslandStore {

	private JFrame storeWindow;
	private GameEnvironmentSwing game;
	private ArrayList<StoreTradable> listBuy = new ArrayList<StoreTradable>();
	private ArrayList<StoreTradable> listSell = new ArrayList<StoreTradable>();

	/**
	 * Create the application.
	 */
	public IslandStore(GameEnvironmentSwing incomingGame) {
		game = incomingGame;
		initialize();
		storeWindow.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		storeWindow = new JFrame();
		storeWindow.getContentPane().setFont(new Font("Serif", Font.PLAIN, 14));
		storeWindow.getContentPane().setLayout(null);

		JLabel lblIslStore = new JLabel(game.getCurrentIsland().getName() + "'");
		lblIslStore.setVerticalAlignment(SwingConstants.TOP);
		lblIslStore.setFont(new Font("Serif", Font.BOLD, 20));
		lblIslStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblIslStore.setBounds(248, -1, 320, 32);
		storeWindow.getContentPane().add(lblIslStore);

		JLabel lblStoreImg = new JLabel("");
		lblStoreImg.setIcon(
				new ImageIcon(IslandStore.class.getResource("/islandTraderSwing/ui/gui/images/storeIsland.png")));
		lblStoreImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblStoreImg.setBounds(308, 10, 192, 150);
		storeWindow.getContentPane().add(lblStoreImg);

		JLabel lblGoodsYouBuy = new JLabel("Goods you can buy here:");
		lblGoodsYouBuy.setFont(new Font("Serif", Font.BOLD, 14));
		lblGoodsYouBuy.setBounds(10, 135, 192, 24);
		storeWindow.getContentPane().add(lblGoodsYouBuy);

		JLabel lblGoodsYouSell = new JLabel("Goods you can sell here:");
		lblGoodsYouSell.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGoodsYouSell.setFont(new Font("Serif", Font.BOLD, 14));
		lblGoodsYouSell.setBounds(595, 135, 210, 24);
		storeWindow.getContentPane().add(lblGoodsYouSell);

		JLabel lblTraderBalance = new JLabel(
				String.format("You have $ %.2f available.", game.getTrader().getTraderAccountBalance()));
		lblTraderBalance.setFont(new Font("Serif", Font.BOLD, 12));
		lblTraderBalance.setBounds(10, 10, 168, 17);
		storeWindow.getContentPane().add(lblTraderBalance);

		JLabel lblYourShipCan = new JLabel(String.format("Your ship can still carry: %.2f kgs.",
				game.getTrader().getShipOwned().getCargoCapacity()));
		lblYourShipCan.setFont(new Font("Serif", Font.BOLD, 12));
		lblYourShipCan.setBounds(10, 24, 192, 17);
		storeWindow.getContentPane().add(lblYourShipCan);

		DefaultListModel<StoreTradable> tradablesBuyListModel = new DefaultListModel<StoreTradable>();

		DefaultListModel<StoreTradable> tradablesSellListModel = new DefaultListModel<StoreTradable>();

		// Add the existing items to the ListModel
		tradablesBuyListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesToBuy());

		tradablesSellListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesToSell());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 158, 343, 96);
		storeWindow.getContentPane().add(scrollPane);

		JList<StoreTradable> list_TraderBuy = new JList<StoreTradable>(tradablesSellListModel);
		scrollPane.setViewportView(list_TraderBuy);
		list_TraderBuy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderBuy.setFont(new Font("Serif", Font.PLAIN, 12));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(462, 158, 343, 96);
		storeWindow.getContentPane().add(scrollPane_1);

		JList<StoreTradable> list_TraderSell = new JList<StoreTradable>(tradablesBuyListModel);
		scrollPane_1.setViewportView(list_TraderSell);
		list_TraderSell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderSell.setFont(new Font("Serif", Font.PLAIN, 12));

		JLabel lblYourPurchaseHistory = new JLabel("Your trade history in this island:");
		lblYourPurchaseHistory.setFont(new Font("Serif", Font.BOLD, 14));
		lblYourPurchaseHistory.setBounds(10, 363, 234, 24);
		storeWindow.getContentPane().add(lblYourPurchaseHistory);

		JLabel lblQtyBuy = new JLabel("0");
		lblQtyBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtyBuy.setFont(new Font("Serif", Font.BOLD, 14));
		lblQtyBuy.setBounds(302, 264, 53, 17);
		storeWindow.getContentPane().add(lblQtyBuy);

		JLabel lblQtySell = new JLabel("0");
		lblQtySell.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtySell.setFont(new Font("Serif", Font.BOLD, 14));
		lblQtySell.setBounds(750, 262, 55, 21);
		storeWindow.getContentPane().add(lblQtySell);

		JSlider slider_QtyBuy = new JSlider();
		slider_QtyBuy.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				game.showSliderValue(slider_QtyBuy, lblQtyBuy);
				slider_QtyBuy.setMaximum(list_TraderBuy.getSelectedValue().getQuantity());
			}
		});
		slider_QtyBuy.setBounds(117, 263, 175, 22);
		slider_QtyBuy.setMinimum(0);

		storeWindow.getContentPane().add(slider_QtyBuy);

		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = slider_QtyBuy.getValue();
				double price = list_TraderBuy.getSelectedValue().getPrice();
				double totalAmountToPay = quantity * price;
				Trader trader = game.getTrader();
				Ship ship = trader.getShipOwned();

				if (trader.getTraderAccountBalance() >= totalAmountToPay) {
					// trader.getShipOwned().getCargoCapacity();
					// see cargoavaiability and tradableweight? if not upgrade
					trader.removeAmountFromBalance(totalAmountToPay);
					ship.addTradable(list_TraderBuy.getSelectedValue());

					JOptionPane.showMessageDialog(new JFrame(),
							"You successfuly bought " + quantity + " " + list_TraderBuy.getSelectedValue().getName(),
							"Success!", JOptionPane.INFORMATION_MESSAGE);
					lblTraderBalance.setText(
							String.format("You have $ %.2f available.", game.getTrader().getTraderAccountBalance()));
					// change text of cargo capacity label too
				}

			}
		});
		btnBuy.setFont(new Font("Serif", Font.BOLD, 14));
		btnBuy.setBounds(10, 264, 85, 21);
		storeWindow.getContentPane().add(btnBuy);

		JSlider slider_QtySell = new JSlider();
		slider_QtySell.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				game.showSliderValue(slider_QtySell, lblQtySell);
				slider_QtySell.setMaximum(list_TraderSell.getSelectedValue().getQuantity());
			}
		});
		slider_QtySell.setBounds(572, 264, 168, 22);
		slider_QtySell.setMinimum(0);

		storeWindow.getContentPane().add(slider_QtySell);

		JButton btnSell = new JButton("Sell");
		btnSell.setFont(new Font("Serif", Font.BOLD, 14));
		btnSell.setBounds(459, 264, 85, 21);
		storeWindow.getContentPane().add(btnSell);

		DefaultListModel<StoreTradable> soldHistoryListModel = new DefaultListModel<StoreTradable>();
		soldHistoryListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesSold());

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 442, 341, 94);
		storeWindow.getContentPane().add(scrollPane_2);

		JList<StoreTradable> list_TraderHistoryBought = new JList<StoreTradable>(soldHistoryListModel);
		scrollPane_2.setViewportView(list_TraderHistoryBought);
		list_TraderHistoryBought.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderHistoryBought.setFont(new Font("Serif", Font.PLAIN, 12));

		DefaultListModel<StoreTradable> boughtHistoryListModel = new DefaultListModel<StoreTradable>();
		boughtHistoryListModel.addAll(game.getCurrentIsland().getStore().getItemsAndUpgradesBought());

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(459, 442, 341, 94);
		storeWindow.getContentPane().add(scrollPane_3);

		JList<StoreTradable> list_TraderHistorySold = new JList<StoreTradable>(boughtHistoryListModel);
		scrollPane_3.setViewportView(list_TraderHistorySold);
		list_TraderHistorySold.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_TraderHistorySold.setFont(new Font("Serif", Font.PLAIN, 12));

		JLabel lblNbought = new JLabel("Bought");
		lblNbought.setFont(new Font("Serif", Font.BOLD, 14));
		lblNbought.setBounds(10, 410, 85, 17);
		storeWindow.getContentPane().add(lblNbought);

		JLabel lblSold = new JLabel("Sold");
		lblSold.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSold.setFont(new Font("Serif", Font.BOLD, 14));
		lblSold.setBounds(715, 415, 85, 17);
		storeWindow.getContentPane().add(lblSold);

		JButton btnLeaveStore = new JButton("Leave Store");
		btnLeaveStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				storeWindow.dispose();
			}
		});
		btnLeaveStore.setFont(new Font("Serif", Font.BOLD, 14));
		btnLeaveStore.setBounds(345, 571, 119, 32);
		storeWindow.getContentPane().add(btnLeaveStore);

		storeWindow.setTitle("Store");
		storeWindow.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(IslandStore.class.getResource("/islandTraderSwing/ui/gui/images/storeIsland.png")));
		storeWindow.setBounds(100, 100, 825, 691);
		storeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
