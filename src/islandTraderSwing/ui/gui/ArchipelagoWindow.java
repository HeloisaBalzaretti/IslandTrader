package islandTraderSwing.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ArchipelagoWindow {

	private JFrame frmArchipelagoMap;
	private GameEnvironmentSwing game;

	/**
	 * Create the application.
	 */
	public ArchipelagoWindow(GameEnvironmentSwing incomingGame) {
		game = incomingGame;
		initialize();
		frmArchipelagoMap.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArchipelagoMap = new JFrame();
		frmArchipelagoMap.setBackground(new Color(153, 204, 255));
		frmArchipelagoMap.getContentPane().setBackground(new Color(153, 204, 255));
		frmArchipelagoMap.setTitle("Archipelago Map");
		frmArchipelagoMap.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ArchipelagoWindow.class.getResource("/islandTraderSwing/ui/gui/images/shipWater1.png")));
		frmArchipelagoMap.setBounds(100, 100, 1021, 664);
		frmArchipelagoMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmArchipelagoMap.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Archipelago Map - Click on the Island to see more");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel.setBounds(109, 10, 727, 39);
		frmArchipelagoMap.getContentPane().add(lblNewLabel);

		JButton btnIslandOne = new JButton("");
		btnIslandOne.setBackground(new Color(204, 255, 255));
		btnIslandOne.setIcon(
				new ImageIcon(ArchipelagoWindow.class.getResource("/islandTraderSwing/ui/gui/images/Island1Sml.png")));
		btnIslandOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				game.launchIslandDetail(game.getIslands().get(0));
			}
		});
		btnIslandOne.setBounds(390, 72, 202, 178);
		frmArchipelagoMap.getContentPane().add(btnIslandOne);

		JButton btnIslandTwo = new JButton("");
		btnIslandTwo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchIslandDetail(game.getIslands().get(1));
			}
		});
		btnIslandTwo.setBackground(new Color(204, 255, 255));
		btnIslandTwo.setIcon(
				new ImageIcon(ArchipelagoWindow.class.getResource("/islandTraderSwing/ui/gui/images/island2Sml.png")));
		btnIslandTwo.setBounds(42, 147, 202, 218);
		frmArchipelagoMap.getContentPane().add(btnIslandTwo);

		JButton btnIslandThree = new JButton("");
		btnIslandThree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchIslandDetail(game.getIslands().get(2));
			}
		});
		btnIslandThree.setBackground(new Color(204, 255, 255));
		btnIslandThree.setIcon(
				new ImageIcon(ArchipelagoWindow.class.getResource("/islandTraderSwing/ui/gui/images/island3Sml.png")));
		btnIslandThree.setBounds(194, 409, 202, 178);
		frmArchipelagoMap.getContentPane().add(btnIslandThree);

		JButton btnIslandFour = new JButton("");
		btnIslandFour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchIslandDetail(game.getIslands().get(3));
			}
		});
		btnIslandFour.setBackground(new Color(204, 255, 255));
		btnIslandFour.setIcon(
				new ImageIcon(ArchipelagoWindow.class.getResource("/islandTraderSwing/ui/gui/images/island4Sml.png")));
		btnIslandFour.setBounds(567, 409, 202, 178);
		frmArchipelagoMap.getContentPane().add(btnIslandFour);

		JButton btnIslandFive = new JButton("");
		btnIslandFive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.launchIslandDetail(game.getIslands().get(4));
			}
		});
		btnIslandFive.setBackground(new Color(204, 255, 255));
		btnIslandFive.setIcon(
				new ImageIcon(ArchipelagoWindow.class.getResource("/islandTraderSwing/ui/gui/images/island5Sml.png")));
		btnIslandFive.setBounds(696, 147, 235, 191);
		frmArchipelagoMap.getContentPane().add(btnIslandFive);
	}
}
