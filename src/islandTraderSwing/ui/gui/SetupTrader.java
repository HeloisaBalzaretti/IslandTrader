package islandTraderSwing.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import islandtrader.core.Ship;
import islandtrader.core.Trader;

public class SetupTrader {

	private JFrame windowSetupTrader;
	private JTextField textTraderName;

	private int durationSelected;
	private int txtLblNumDaysChoosen;
	private String traderName;
	private GameEnvironmentSwing game;

	public SetupTrader(GameEnvironmentSwing incomingPlayer) {
		game = incomingPlayer;
		initialize();
		windowSetupTrader.setVisible(true);
	}

	public void closeWindow() {
		windowSetupTrader.dispose();
	}

	public void finishedWindow() {
		game.closeSetupScreen(this);
	}

	/**
	 * Create the application.
	 */
	public SetupTrader() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		windowSetupTrader = new JFrame();
		windowSetupTrader.getContentPane().setForeground(new Color(51, 51, 153));
		windowSetupTrader.getContentPane().setBackground(SystemColor.control);
		windowSetupTrader.getContentPane().setFont(new Font("Serif", Font.PLAIN, 14));
		windowSetupTrader.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\heloi\\Heloisa\\javaGroupProject\\TraderProject\\src\\islandTraderSwing\\ui\\gui\\images\\weel2.jfif"));
		windowSetupTrader.setTitle("Island Trader Adventure");
		windowSetupTrader.setBounds(100, 100, 842, 527);
		windowSetupTrader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowSetupTrader.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Name | Speed | Cargo Capacity | Endurance | Sneakiness | Crew size");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(88, 295, 420, 13);
		windowSetupTrader.getContentPane().add(lblNewLabel_1);

		JLabel lblInformDuration = new JLabel("Inform how many days you want to travel:");
		lblInformDuration.setHorizontalAlignment(SwingConstants.LEFT);
		lblInformDuration.setFont(new Font("Serif", Font.BOLD, 14));
		lblInformDuration.setBounds(207, 125, 287, 25);
		windowSetupTrader.getContentPane().add(lblInformDuration);

		JLabel lblInformTraderName = new JLabel("Inform your name:");
		lblInformTraderName.setHorizontalAlignment(SwingConstants.LEFT);
		lblInformTraderName.setFont(new Font("Serif", Font.BOLD, 14));
		lblInformTraderName.setBounds(209, 88, 134, 25);
		windowSetupTrader.getContentPane().add(lblInformTraderName);

		JLabel lblSteeringWeelImg = new JLabel("captain");
		lblSteeringWeelImg.setVerticalAlignment(SwingConstants.TOP);
		lblSteeringWeelImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblSteeringWeelImg.setForeground(SystemColor.textHighlightText);
		lblSteeringWeelImg.setBounds(10, 14, 213, 186);
		lblSteeringWeelImg.setIcon(new ImageIcon(
				"C:\\Users\\heloi\\Heloisa\\javaGroupProject\\TraderProject\\src\\islandTraderSwing\\ui\\gui\\images\\SteeringWeelSmall.png"));
		windowSetupTrader.getContentPane().add(lblSteeringWeelImg);

		JLabel lblNewLabel = new JLabel("Welcome to the Island trader adventure!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel.setBounds(201, 0, 361, 45);
		windowSetupTrader.getContentPane().add(lblNewLabel);

		textTraderName = new JTextField();
		textTraderName.setHorizontalAlignment(SwingConstants.CENTER);
		textTraderName.setFont(new Font("Serif", Font.PLAIN, 12));
		textTraderName.setBounds(342, 93, 343, 19);
		windowSetupTrader.getContentPane().add(textTraderName);
		textTraderName.setColumns(10);

		JLabel lblNumDaysMin = new JLabel("20");
		lblNumDaysMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumDaysMin.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNumDaysMin.setBounds(472, 145, 28, 25);
		windowSetupTrader.getContentPane().add(lblNumDaysMin);
		JLabel lblNumDaysMax = new JLabel("50");
		lblNumDaysMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumDaysMax.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNumDaysMax.setBounds(670, 145, 28, 25);
		windowSetupTrader.getContentPane().add(lblNumDaysMax);

		JLabel lblNumDaysChoosen = new JLabel("35");

		lblNumDaysChoosen.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumDaysChoosen.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNumDaysChoosen.setBounds(573, 145, 28, 25);
		windowSetupTrader.getContentPane().add(lblNumDaysChoosen);

		JSlider sliderDurationDays = new JSlider();
		sliderDurationDays.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				showSliderValue(sliderDurationDays, lblNumDaysChoosen);

			}
		});
		sliderDurationDays.setValue(35);
		sliderDurationDays.setMaximum(50);
		sliderDurationDays.setMinimum(20);
		sliderDurationDays.setFont(new Font("Serif", Font.BOLD, 14));
		sliderDurationDays.setBounds(486, 125, 200, 22);
		windowSetupTrader.getContentPane().add(sliderDurationDays);

		JLabel lblChooseShip = new JLabel("Choose a ship to captain:");
		lblChooseShip.setFont(new Font("Serif", Font.BOLD, 15));
		lblChooseShip.setBounds(253, 263, 164, 22);
		windowSetupTrader.getContentPane().add(lblChooseShip);

		JLabel lblShipImg = new JLabel("");
		lblShipImg.setHorizontalAlignment(SwingConstants.LEFT);
		lblShipImg.setIcon(new ImageIcon(
				"C:\\Users\\heloi\\Heloisa\\javaGroupProject\\TraderProject\\src\\islandTraderSwing\\ui\\gui\\images\\shipWater1.png"));
		lblShipImg.setBounds(605, 218, 213, 204);
		windowSetupTrader.getContentPane().add(lblShipImg);
		// Create a ListModel to store the items in the JList
		DefaultListModel<Ship> shipListModel = new DefaultListModel<Ship>();

		// Add the existing items to the ListModel
		shipListModel.addAll(game.getShips());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 318, 416, 92);
		windowSetupTrader.getContentPane().add(scrollPane);

		// Create the actual JList, notice that we put the astronautListModel in as an
		// argument to new JList

		JList<Ship> list_ShipToChoose = new JList<Ship>(shipListModel);
		scrollPane.setViewportView(list_ShipToChoose);
		list_ShipToChoose.setFont(new Font("Serif", Font.PLAIN, 14));
		list_ShipToChoose.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// get the selected ship.

		JButton btnStart = new JButton("Start!");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Trader trader = new Trader(textTraderName.getText(), ", a Very Brave Sailer!",
						getChoosenShip(list_ShipToChoose));
				game.setTrader(trader);
				game.launchMainScreen(trader);
				finishedWindow();

			}
		});
		btnStart.setForeground(new Color(51, 51, 102));
		btnStart.setBackground(new Color(255, 255, 255));
		btnStart.setFont(new Font("Serif", Font.BOLD, 16));
		btnStart.setBounds(653, 432, 134, 37);
		windowSetupTrader.getContentPane().add(btnStart);

	}

	public void showSliderValue(JSlider sliderDurationDays, JLabel lblNumDaysChoosen) {
		lblNumDaysChoosen.setText("" + sliderDurationDays.getValue());
	}

	public void setTraderName() {
		traderName = textTraderName.getText();
	}

	/**
	 * @return the traderName
	 */
	public String getTraderName() {
		return traderName;
	}

	public void setDurationSelected(JSlider sliderDurationDays) {
		durationSelected = sliderDurationDays.getValue();
	}

	/**
	 * @return the durationSelected
	 */
	public int getDurationSelected() {
		return durationSelected;
	}

	public void setChoosenShip(JList<Ship> list_ShipToChoose) {
		game.setShip(list_ShipToChoose.getSelectedValue());
	}

	public Ship getChoosenShip(JList<Ship> list_ShipToChoose) {
		return list_ShipToChoose.getSelectedValue();
	}

	/**
	 * @return the textTraderName
	 */
	public JTextField getTextTraderName() {
		return textTraderName;
	}

}
