package pl.n2god.data_source;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author n2god on 19/12/2019
 * @project DataSource
 * Rekord do bazy wstawiamy na podstawie wszystkich przekazanych parametrów z formularza, natomiast usuwanie odbywa się
 * na podstawie nazwy miasta. Zauważ, że przy usuwaniu najpierw odpytujemy bazę, czy dany rekord w ogóle istnieje.
 * Do konkretnych kolumn w obiekcie ResultSet możemy się odwoływać nie tylko poprzez nazwy kolumn, czyli np. resultSet.getInt("ID"),
 * ale również po indeksach kolumn, co wykorzystaliśmy w naszym przykładzie -  resultSet.getInt(1).W odróżnieniu od
 * większości rzeczy w Javie, kolumny w ResultSet indeksowane są od 1 a nie od 0.
 * Po zakończeniu operacji wstawiania lub usuwania wszystkie zasoby są zwalniane dzięki wykorzystaniu bloku try-with-resources.
 */
public class DbUtil {

	public static void insert(String name, String country, String district, int population) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
		     Statement statement = connection.createStatement();) {
			String query = String
					.format("INSERT INTO city(Name, CountryCode, District, Population) VALUES ('%s', '%s', '%s', %d)",
							name, country, district, population);
			statement.executeUpdate(query);
		}
	}

	public static void delete(String name) throws SQLException {
		String selectQuery = String.format("SELECT ID FROM city WHERE Name='%s'", name);
		try (Connection connection = ConnectionProvider.getConnection();
		     Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(selectQuery);) {
			if (resultSet.next()) {
				String deleteQuery = String.format("DELETE FROM city WHERE ID=%d", resultSet.getInt(1));
				statement.executeUpdate(deleteQuery);
			}
		}
	}
}
