package islandTraderSwing.ui.gui;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import islandtrader.core.StoreTradable;

public class CargoAllStoreTradablesWindow {

	private JFrame cargoAllItemsWindow;
	private GameEnvironmentSwing game;

	/**
	 * Create the application.
	 */
	public CargoAllStoreTradablesWindow(GameEnvironmentSwing incomingGame) {
		game = incomingGame;
		initialize();
		cargoAllItemsWindow.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cargoAllItemsWindow = new JFrame();
		cargoAllItemsWindow.setTitle("See my goods");
		cargoAllItemsWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(
				CargoAllStoreTradablesWindow.class.getResource("/islandTraderSwing/ui/gui/images/capitain9.png")));
		cargoAllItemsWindow.setBounds(100, 100, 900, 439);
		cargoAllItemsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cargoAllItemsWindow.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("All goods purchased:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel.setBounds(244, 10, 342, 30);
		cargoAllItemsWindow.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				CargoAllStoreTradablesWindow.class.getResource("/islandTraderSwing/ui/gui/images/shipWater1.png")));
		lblNewLabel_1.setBounds(28, 60, 237, 250);
		cargoAllItemsWindow.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Current goods in my ship cargo:");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_2.setBounds(281, 75, 263, 31);
		cargoAllItemsWindow.getContentPane().add(lblNewLabel_2);
		DefaultListModel<StoreTradable> tradablesListModel = new DefaultListModel<StoreTradable>();

		// Add the existing items to the ListModel
		tradablesListModel.addAll(game.getTrader().getShipOwned().getCurrentCargoTradables());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 104, 581, 113);
		cargoAllItemsWindow.getContentPane().add(scrollPane);

		JList<StoreTradable> list_CurrentGoods = new JList<StoreTradable>(tradablesListModel);
		scrollPane.setViewportView(list_CurrentGoods);
		list_CurrentGoods.setFont(new Font("Serif", Font.PLAIN, 12));

		JLabel lblNewLabel_2_1 = new JLabel("Sold goods:");
		lblNewLabel_2_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(281, 227, 263, 31);
		cargoAllItemsWindow.getContentPane().add(lblNewLabel_2_1);

		DefaultListModel<StoreTradable> tradablesSoldListModel = new DefaultListModel<StoreTradable>();

		tradablesSoldListModel.addAll(game.getTrader().getShipOwned().getSoldCargoTradables());

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(281, 256, 581, 125);
		cargoAllItemsWindow.getContentPane().add(scrollPane_1);

		JList<StoreTradable> list_SoldGoods = new JList<StoreTradable>(tradablesSoldListModel);
		scrollPane_1.setViewportView(list_SoldGoods);
		list_SoldGoods.setFont(new Font("Serif", Font.PLAIN, 12));
	}

}
