package islandtrader.ui.gui;

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

/**
 * Creates the Window that shows the Ship cargo current goods
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class CargoCurrentGoodsWindow {
	/**
	 * The window to show the current goods in the cargo of the Ship
	 */
	private JFrame cargoAllItemsWindow;
	/**
	 * The current game being played
	 */
	private GameEnvironmentSwing game;

	/**
	 * Create current Cargo goods window application.
	 *
	 * @param incomingGame the current game
	 */
	public CargoCurrentGoodsWindow(GameEnvironmentSwing incomingGame) {
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
		cargoAllItemsWindow.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CargoCurrentGoodsWindow.class.getResource("/islandtrader/images/capitain9.png")));
		cargoAllItemsWindow.setBounds(100, 100, 900, 439);
		cargoAllItemsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cargoAllItemsWindow.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("You current goods available for trade");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Serif", Font.BOLD, 18));
		lblTitle.setBounds(244, 10, 342, 30);
		cargoAllItemsWindow.getContentPane().add(lblTitle);

		JLabel lblShipImg = new JLabel("");
		lblShipImg.setIcon(
				new ImageIcon(CargoCurrentGoodsWindow.class.getResource("/islandtrader/images/shipWater1.png")));
		lblShipImg.setBounds(28, 60, 237, 250);
		cargoAllItemsWindow.getContentPane().add(lblShipImg);

		JLabel lblCurrentGoods = new JLabel("Current goods in my ship cargo:");
		lblCurrentGoods.setFont(new Font("Serif", Font.BOLD, 14));
		lblCurrentGoods.setBounds(281, 75, 263, 31);
		cargoAllItemsWindow.getContentPane().add(lblCurrentGoods);
		DefaultListModel<StoreTradable> tradablesListModel = new DefaultListModel<StoreTradable>();

		tradablesListModel.addAll(game.getTrader().getShipOwned().getCurrentCargoTradables());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 131, 581, 210);
		cargoAllItemsWindow.getContentPane().add(scrollPane);

		JList<StoreTradable> list_CurrentGoods = new JList<StoreTradable>(tradablesListModel);
		scrollPane.setViewportView(list_CurrentGoods);
		list_CurrentGoods.setFont(new Font("Serif", Font.PLAIN, 12));

		JLabel lblNameDescriptionPrice = new JLabel("Name | Description | Price | Weight | Quantity");
		lblNameDescriptionPrice.setFont(new Font("Serif", Font.BOLD, 14));
		lblNameDescriptionPrice.setBounds(275, 109, 343, 24);
		cargoAllItemsWindow.getContentPane().add(lblNameDescriptionPrice);

		DefaultListModel<StoreTradable> tradablesSoldListModel = new DefaultListModel<StoreTradable>();

		tradablesSoldListModel.addAll(game.getTrader().getShipOwned().getSoldCargoTradables());
	}

}
