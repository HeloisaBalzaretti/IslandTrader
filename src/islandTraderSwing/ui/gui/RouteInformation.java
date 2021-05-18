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

import islandtrader.core.Route;
import islandtrader.core.Trader;

public class RouteInformation {

	private final String ROUTE_MENU_LINE_MESSAGE = " %s: <br> %d days and %d kms of sailing with a total cost of %.2f$ to get %s <br>";
	private JFrame routesInformationWindow;
	private GameEnvironmentSwing game;

	/**
	 * Create the application.
	 */
	public RouteInformation(GameEnvironmentSwing incomingGame, Trader trader) {
		game = incomingGame;

		initialize();
		routesInformationWindow.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		routesInformationWindow = new JFrame();
		routesInformationWindow.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(RouteInformation.class.getResource("/islandTraderSwing/ui/gui/images/shipWater1.png")));
		routesInformationWindow.setTitle("View routes information");
		routesInformationWindow.setBounds(100, 100, 842, 721);
		routesInformationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		routesInformationWindow.getContentPane().setLayout(null);

		String islandName = game.getCurrentIsland().getName();
		JLabel lblRoutesInformation = new JLabel(
				"Routes leaving from " + islandName + " and the random events you may encounter!");
		lblRoutesInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoutesInformation.setFont(new Font("Serif", Font.BOLD, 20));
		lblRoutesInformation.setBounds(10, 23, 808, 27);
		routesInformationWindow.getContentPane().add(lblRoutesInformation);

		String routeDetailTemplate = getRoutesToString(game.getTrader());

		JLabel lblRouteOne = new JLabel("<html><body style=\"text-align: justify;  text-justify: inter-word;\">"
				+ routeDetailTemplate + "</body></html>");
		lblRouteOne.setIcon(null);
		lblRouteOne.setVerticalAlignment(SwingConstants.TOP);
		lblRouteOne.setHorizontalAlignment(SwingConstants.LEFT);

		lblRouteOne.setFont(new Font("Serif", Font.PLAIN, 13));
		lblRouteOne.setBounds(20, 60, 774, 400);
		routesInformationWindow.getContentPane().add(lblRouteOne);

		JButton btnNewButton = new JButton("Go back to sailing options");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				routesInformationWindow.dispose();
				game.startMainWindowPanel();

			}
		});
		btnNewButton.setIcon(null);
		btnNewButton.setForeground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 14));
		btnNewButton.setBounds(309, 614, 208, 49);
		routesInformationWindow.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(
				new ImageIcon(RouteInformation.class.getResource("/islandTraderSwing/ui/gui/images/pirateT1.png")));
		lblNewLabel.setBounds(10, 470, 155, 204);
		routesInformationWindow.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(
				new ImageIcon(RouteInformation.class.getResource("/islandTraderSwing/ui/gui/images/sailorTom.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(673, 455, 155, 219);
		routesInformationWindow.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(
				RouteInformation.class.getResource("/islandTraderSwing/ui/gui/images/badWeatherSmlT.png")));
		lblNewLabel_2.setBounds(347, 459, 128, 162);
		routesInformationWindow.getContentPane().add(lblNewLabel_2);
	}

	public void closeWindow() {
		routesInformationWindow.dispose();

	}

	private Route[] getCurrentRoutes() {
		return game.getCurrentIsland().getRoutes();
	}

	private String getRoutesToString(Trader trader) {
		String routesToString = "";
		for (int routeIndex = 0; routeIndex < getCurrentRoutes().length; routeIndex++) {

			Route route = getCurrentRoutes()[routeIndex];

			if (route != null) {
				routesToString += "Route " + getCurrentRoutes()[routeIndex].getIdNumber();
				int durationDays = game.getCurrentIsland().distanceToInDays(routeIndex);
				int distanceKms = game.getCurrentIsland().distanceToInKm(routeIndex);

				double totalCostCrewToSail = trader.getShipOwned().getCrewCostToSailByTotalDays(durationDays);
				routesToString += String.format(ROUTE_MENU_LINE_MESSAGE, route.getName(), durationDays, distanceKms,
						totalCostCrewToSail, route);
			}
		}

		return routesToString;
	}
}
