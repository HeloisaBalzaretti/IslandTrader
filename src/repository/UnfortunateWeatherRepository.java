/**
 *
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.UnfortunateWeather;

/**
 * @author Maria
 *
 */
public class UnfortunateWeatherRepository extends BaseRepository {
	private final int ID_NUMBER = 0;
	private final int NAME = 1;
	private final int DESCRIPTION = 2;

	private final String UNFORTUNATE_WEATHER_CSV_FILEPATH = "csvFiles/unfortunateWeather.csv";

	@Override
	public ArrayList<UnfortunateWeather> getList() {
		List<List<String>> unfortunateWeatherList = getResultListfromFile(UNFORTUNATE_WEATHER_CSV_FILEPATH);
		unfortunateWeatherList.remove(0);
		return getUnfortunateWeather(unfortunateWeatherList);
	}

	private ArrayList<UnfortunateWeather> getUnfortunateWeather(List<List<String>> unfortunateWeatherList) {
		ArrayList<UnfortunateWeather> unfortunateWeather = new ArrayList<>();
		for (List<String> weather : unfortunateWeatherList) {
			int idNumber = Integer.parseInt(weather.get(ID_NUMBER));
			String name = weather.get(NAME);
			String description = weather.get(DESCRIPTION);

			UnfortunateWeather newunfortunateWeather = new UnfortunateWeather(idNumber, name, description);
			unfortunateWeather.add(newunfortunateWeather);
		}
		return unfortunateWeather;
	}
}
