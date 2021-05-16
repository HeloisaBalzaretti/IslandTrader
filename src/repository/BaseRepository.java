package repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import islandtrader.core.Entity;

/**
 * an Abstract class that represents a Basic Repository.It helps mocking a
 * Database to create the Entities used in the project(Ship, Island, Store, Item
 * and so on). Used to read the CSV files, which contains information to build
 * these Entities. Each line in the CSV file is used to build an object, with
 * each String in the file representing a property of some object in the
 * project, i.e Item or Store.
 *
 * @author heloisa
 */
public abstract class BaseRepository {
	/**
	 * DELIMITER is the character used to delimiter the strings in the CSV files,
	 *
	 */
	private final static String DELIMITER = "\\|";

	/**
	 * This method is used to read the CSV files.
	 *
	 * @param filePath is the filepath of the CSV files with the relevant
	 *                 information to build the objects.
	 * @return a resulting list that contains sublists of Strings that are separated
	 *         by a comma.
	 */
	public List<List<String>> getResultListfromFile(String filePath) {
		List<List<String>> resultList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(DELIMITER);
				resultList.add(Arrays.asList(values));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * Method that will force every classRepository that uses the baseRepository to
	 * create its own arrayList of type Entity and return the Array with the proper
	 * type, i.e ItemRepository will be used to read the files and return an
	 * ArrayList<Item>of all Items in the database, to be added to the Stores later,
	 *
	 * @return
	 */
	public abstract ArrayList<? extends Entity> getList();

}
