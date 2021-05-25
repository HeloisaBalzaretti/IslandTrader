package islandtrader.ui.gui;

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

/**
 * Creates the Archipelago window that represents a map between the Islands
 *
 * @author heloi
 *
 */
public class ArchipelagoWindow {

	/**
	 * the Archipelago window
	 */
	private JFrame frmArchipelagoMap;

	/**
	 * The current game
	 */
	private GameEnvironmentSwing game;

	/**
	 * Create the Archipelago window that represents a map.
	 *
	 * @param incomingGame the current game
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
				.getImage(ArchipelagoWindow.class.getResource("/islandtrader/images/shipWater1.png")));
		frmArchipelagoMap.setBounds(100, 100, 1021, 664);
		frmArchipelagoMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmArchipelagoMap.getContentPane().setLayout(null);

		JLabel lblMap = new JLabel("Archipelago Map - Click on the Island to see more");
		lblMap.setHorizontalAlignment(SwingConstants.CENTER);
		lblMap.setFont(new Font("Serif", Font.BOLD, 20));
		lblMap.setBounds(109, 10, 727, 39);
		frmArchipelagoMap.getContentPane().add(lblMap);

		JButton btnIslandOne = new JButton("");
		btnIslandOne.setBackground(new Color(204, 255, 255));
		btnIslandOne.setIcon(new ImageIcon(ArchipelagoWindow.class.getResource("/islandtrader/images/island1Sml.png")));
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
		btnIslandTwo.setIcon(new ImageIcon(ArchipelagoWindow.class.getResource("/islandtrader/images/island2Sml.png")));
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
		btnIslandThree
				.setIcon(new ImageIcon(ArchipelagoWindow.class.getResource("/islandtrader/images/island3Sml.png")));
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
		btnIslandFour
				.setIcon(new ImageIcon(ArchipelagoWindow.class.getResource("/islandtrader/images/island4Sml.png")));
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
		btnIslandFive
				.setIcon(new ImageIcon(ArchipelagoWindow.class.getResource("/islandtrader/images/island5Sml.png")));
		btnIslandFive.setBounds(696, 147, 235, 191);
		frmArchipelagoMap.getContentPane().add(btnIslandFive);

		JLabel lblPhuket = new JLabel("Phuket Island");
		lblPhuket.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhuket.setFont(new Font("Serif", Font.BOLD, 14));
		lblPhuket.setBounds(410, 241, 167, 31);
		frmArchipelagoMap.getContentPane().add(lblPhuket);

		JLabel lblAztecs = new JLabel("Aztecs Island");
		lblAztecs.setHorizontalAlignment(SwingConstants.CENTER);
		lblAztecs.setFont(new Font("Serif", Font.BOLD, 14));
		lblAztecs.setBounds(52, 368, 167, 31);
		frmArchipelagoMap.getContentPane().add(lblAztecs);

		JLabel lblShetland = new JLabel("Shetland Islands");
		lblShetland.setHorizontalAlignment(SwingConstants.CENTER);
		lblShetland.setFont(new Font("Serif", Font.BOLD, 14));
		lblShetland.setBounds(216, 587, 167, 30);
		frmArchipelagoMap.getContentPane().add(lblShetland);

		JLabel lblBermuda = new JLabel("Bermuda Triangle");
		lblBermuda.setHorizontalAlignment(SwingConstants.CENTER);
		lblBermuda.setFont(new Font("Serif", Font.BOLD, 14));
		lblBermuda.setBounds(591, 587, 167, 31);
		frmArchipelagoMap.getContentPane().add(lblBermuda);

		JLabel lblTrazmoz = new JLabel("Trazmoz");
		lblTrazmoz.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrazmoz.setFont(new Font("Serif", Font.BOLD, 14));
		lblTrazmoz.setBounds(737, 331, 167, 31);
		frmArchipelagoMap.getContentPane().add(lblTrazmoz);
	}
}
