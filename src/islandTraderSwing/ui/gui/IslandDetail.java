package islandTraderSwing.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import islandtrader.core.Island;
import islandtrader.core.StoreTradable;

public class IslandDetail {

	private JFrame islandDetailsWindow;

	private Island islandSelected;

	/**
	 * Create the application.
	 */
	public IslandDetail(Island island) {
		islandSelected = island;

		initialize();
		islandDetailsWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		islandDetailsWindow = new JFrame();
		islandDetailsWindow.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(IslandDetail.class.getResource("/islandTraderSwing/ui/gui/images/Island1Sml.png")));
		islandDetailsWindow.setTitle(islandSelected.getName() + " details");
		islandDetailsWindow.setBounds(100, 100, 741, 540);
		islandDetailsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		islandDetailsWindow.getContentPane().setLayout(null);

		JLabel lblIslandImg = new JLabel("");
		lblIslandImg.setHorizontalAlignment(SwingConstants.CENTER);
		changeIslandImg();
		lblIslandImg.setIcon(new ImageIcon(IslandDetail.class.getResource(changeIslandImg())));
		lblIslandImg.setBounds(10, 10, 276, 234);
		islandDetailsWindow.getContentPane().add(lblIslandImg);

		JLabel lblIslandDescription = new JLabel(
				"<html><body style=\"text-align: justify;  text-justify: inter-word;\">"
						+ islandSelected.getDescription() + "</body></html>");
		lblIslandDescription.setVerticalAlignment(SwingConstants.TOP);
		lblIslandDescription.setFont(new Font("Serif", Font.PLAIN, 15));
		lblIslandDescription.setBounds(296, 68, 421, 143);
		islandDetailsWindow.getContentPane().add(lblIslandDescription);

		JLabel lblIslandName = new JLabel(islandSelected.getName());
		lblIslandName.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblIslandName.setBounds(306, 28, 393, 26);
		islandDetailsWindow.getContentPane().add(lblIslandName);
		DefaultListModel<StoreTradable> tradablesBuyListModel = new DefaultListModel<StoreTradable>();

		// Add the existing items to the ListModel
		tradablesBuyListModel.addAll(islandSelected.getStore().getItemsAndUpgradesToBuy());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 290, 337, 115);
		islandDetailsWindow.getContentPane().add(scrollPane);

		JList<StoreTradable> list_Buy = new JList<StoreTradable>(tradablesBuyListModel);
		list_Buy.setFont(new Font("Serif", Font.PLAIN, 12));
		scrollPane.setViewportView(list_Buy);
		list_Buy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultListModel<StoreTradable> tradablesSellListModel = new DefaultListModel<StoreTradable>();

		// Add the existing items to the ListModel
		tradablesSellListModel.addAll(islandSelected.getStore().getItemsAndUpgradesToSell());

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(372, 290, 345, 115);
		islandDetailsWindow.getContentPane().add(scrollPane_1);
		JList<StoreTradable> list_Sell = new JList<StoreTradable>(tradablesSellListModel);
		list_Sell.setFont(new Font("Serif", Font.PLAIN, 12));
		scrollPane_1.setViewportView(list_Sell);
		list_Sell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblBuy = new JLabel("Items this Island Buys");
		lblBuy.setFont(new Font("Serif", Font.BOLD, 13));
		lblBuy.setBounds(10, 254, 217, 26);
		islandDetailsWindow.getContentPane().add(lblBuy);

		JLabel lblBSell = new JLabel("Items this Island Sells");
		lblBSell.setFont(new Font("Serif", Font.BOLD, 13));
		lblBSell.setBounds(372, 254, 217, 26);
		islandDetailsWindow.getContentPane().add(lblBSell);

		JLabel lblNewLabel = new JLabel(
				"To be able to buy or sell items you must be in the island! Let's go sailing!!");
		lblNewLabel.setForeground(new Color(255, 102, 51));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 438, 707, 42);
		islandDetailsWindow.getContentPane().add(lblNewLabel);
	}

	public String changeIslandImg() {
		String sourcePath;
		int indexIsland = islandSelected.getIdNumber() - 1;
		switch (indexIsland) {

		case 0:
			sourcePath = "/islandTraderSwing/ui/gui/images/Island1Sml.png";
			break;
		case 1:
			sourcePath = "/islandTraderSwing/ui/gui/images/Island2Sml.png";
			break;
		case 2:
			sourcePath = "/islandTraderSwing/ui/gui/images/Island3Sml.png";
			break;
		case 3:
			sourcePath = "/islandTraderSwing/ui/gui/images/Island4Sml.png";
			break;
		case 4:
			sourcePath = "/islandTraderSwing/ui/gui/images/Island5Sml.png";
			break;
		default:
			sourcePath = "/islandTraderSwing/ui/gui/images/Island0Sml.png";
			break;

		}
		return sourcePath;

	}
}
