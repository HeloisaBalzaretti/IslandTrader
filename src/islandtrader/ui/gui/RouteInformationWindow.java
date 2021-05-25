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

import islandtrader.core.Route;
import islandtrader.core.Trader;

/**
 * The window with a detailed description about the available routes for other
 * Islands
 *
 * @author Maria Heloisa Balzaretti
 *
 */

public class RouteInformationWindow {

	private final String ROUTE_MENU_LINE_MESSAGE = " %s: <br> %d days and %d kms of sailing with a total cost of %.2f$ to get %s <br>";
	private JFrame routesInformationWindow;
	private GameEnvironmentSwing game;

	/**
	 * Creates the route information window application.
	 *
	 * @param incomingGame the current game
	 * @param trader       the Trader
	 */
	public RouteInformationWindow(GameEnvironmentSwing incomingGame, Trader trader) {
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
				.getImage(RouteInformationWindow.class.getResource("/islandtrader/images/shipWater1.png")));
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

		JLabel lblRouteInformation = new JLabel("<html><body style=\"text-align: justify;  text-justify: inter-word;\">"
				+ routeDetailTemplate + "</body></html>");
		lblRouteInformation.setIcon(null);
		lblRouteInformation.setVerticalAlignment(SwingConstants.TOP);
		lblRouteInformation.setHorizontalAlignment(SwingConstants.LEFT);

		lblRouteInformation.setFont(new Font("Serif", Font.PLAIN, 13));
		lblRouteInformation.setBounds(20, 60, 774, 400);
		routesInformationWindow.getContentPane().add(lblRouteInformation);

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

		JLabel lblPirateImg = new JLabel("");
		lblPirateImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblPirateImg
				.setIcon(new ImageIcon(RouteInformationWindow.class.getResource("/islandtrader/images/pirateT1.png")));
		lblPirateImg.setBounds(10, 470, 155, 204);
		routesInformationWindow.getContentPane().add(lblPirateImg);

		JLabel lblSailorImg = new JLabel("");
		lblSailorImg
				.setIcon(new ImageIcon(RouteInformationWindow.class.getResource("/islandtrader/images/sailorTom.png")));
		lblSailorImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblSailorImg.setBounds(673, 455, 155, 219);
		routesInformationWindow.getContentPane().add(lblSailorImg);

		JLabel lblBadWeatherImg = new JLabel("");
		lblBadWeatherImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblBadWeatherImg.setIcon(
				new ImageIcon(RouteInformationWindow.class.getResource("/islandtrader/images/badWeatherSmlT.png")));
		lblBadWeatherImg.setBounds(347, 459, 128, 162);
		routesInformationWindow.getContentPane().add(lblBadWeatherImg);
	}

	/**
	 * Closes the routes window
	 */
	public void closeWindow() {
		routesInformationWindow.dispose();

	}

	/**
	 * Gets the current routes from current Island
	 *
	 * @return routes list
	 */
	private Route[] getCurrentRoutes() {
		return game.getCurrentIsland().getRoutes();
	}

	/**
	 * Get the Routes to string with information to be shown in the labels
	 *
	 * @param trader the Trader/ player
	 * @return string with a description of the Routes
	 */
	private String getRoutesToString(Trader trader) {
		String routesToString = "";
		for (int routeIndex = 0; routeIndex < getCurrentRoutes().length; routeIndex++) {

			Route route = getCurrentRoutes()[routeIndex];

			if (route != null) {
				routesToString += "Route " + getCurrentRoutes()[routeIndex].getIdNumber();
				int durationDays = game.getCurrentIsland().getDistanceToAnotherIslandInDays(routeIndex);
				int distanceKms = game.getCurrentIsland().getDistanceToAnotherIslandInKm(routeIndex);

				double totalCostCrewToSail = trader.getShipOwned().getCrewCostToSailByTotalDays(durationDays);
				routesToString += String.format(ROUTE_MENU_LINE_MESSAGE, route.getName(), durationDays, distanceKms,
						totalCostCrewToSail, route);
			}
		}

		return routesToString;
	}
}
