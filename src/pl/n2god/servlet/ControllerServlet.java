package pl.n2god.servlet;

import pl.n2god.data_source.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author n2god on 19/12/2019
 * @project DataSource
 * W klasie ControllerServlet obsługujemy żądanie POST odebrane z formularza. Najpierw odczytujemy przesłane parametry,
 * a następnie w zależności od tego, czy użytkownik wybrał opcję "Dodaj", czy "Usuń" próbujemy dodać lub usunąć rekord
 * z bazy danych wywołując odpowiednią metodę klasy DbUtil. Po drodze może pojawić się wiele wyjątków - np. użytkownik
 * mógł nie wypełnić wszystkich pól, a kolumny w bazie danych mają zabezpieczenie przed wstawianiem wartości NULL co
 * spowoduje błąd. Ze względu na czytelność przykładu pominęliśmy jednak obsługę tych specyficznych sytuacji, ograniczając
 * się jedynie do weryfikacji wyjątku NumberFormatException - zachęcam potraktować obsługę pozostałych jako ćwiczenie.
 *
 * Po przetworzeniu zapytania do bazy danych ustawiamy komunikat, czy rekord został dodany, czy usunięty z bazy i
 * przekazujemy go do strony message.jsp.
 */
@WebServlet(name = "ControllerServlet", urlPatterns = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("city");
        String country = request.getParameter("country");
        String district = request.getParameter("district");
        String populationString = request.getParameter("population");
        int population = 0;
        if (populationString != null && !populationString.isEmpty()) {
            population = Integer.parseInt(populationString);
        }

        String option = request.getParameter("option");
        String message = null;
        if ("add".equals(option)) {
            try {
                DbUtil.insert(name, country, district, population);
                message = "Do bazy dodano miasto " + name;
            } catch (SQLException e) {
                message = "Nie udało się dodać miasta " + name;
                e.printStackTrace();
            }

        } else if ("delete".equals(option)) {
            try {
                DbUtil.delete(name);
                message = "Z bazy usunięto miasto " + name;
            } catch (SQLException e) {
                e.printStackTrace();
                message = "Nie udało się usunąć miasta " + name;
            }
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
}
