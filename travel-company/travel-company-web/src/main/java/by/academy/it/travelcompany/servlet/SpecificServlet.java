package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.user.User;
import by.academy.it.travelcompany.user.favourite.Favourite;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/specific")
public class SpecificServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Airport> allStartedAirports = AirportServiceImpl.getInstance().getAll();
        allStartedAirports.removeIf(a -> a.getCode().equals("VNO") || a.getCode().equals("KUN") ||
                a.getCode().equals("WAW") || a.getCode().equals("WMI"));
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            List<Favourite> favourites = FavouriteServiceImpl.getInstance().getAllFavouriteByUserId(user.getId());
            if (favourites != null) {
                req.setAttribute("favourites", favourites);
            }
        }
        req.setAttribute("allStartedAirports", allStartedAirports);

        Integer defaultDay = LocalDate.now().getDayOfMonth();
        Integer defaultMonth = LocalDate.now().getMonthValue();
        Integer defaultYear = LocalDate.now().getYear();

        Integer defaultDayFinish = LocalDate.now().plusDays(15).getDayOfMonth();
        Integer defaultMonthFinish = LocalDate.now().plusDays(15).getMonthValue();
        Integer defaultYearFinish = LocalDate.now().plusDays(15).getYear();

        req.setAttribute("defaultStartDate",""+defaultMonth+"/"+defaultDay+"/"+defaultYear);
        req.setAttribute("defaultFinishDate",""+defaultMonthFinish+"/"+defaultDayFinish+"/"+defaultYearFinish);

        req.setAttribute("checkedVNOStart", "checked");
        req.setAttribute("checkedKUNStart", "checked");
        req.setAttribute("checkedWMIStart", "checked");
        req.setAttribute("checkedWAWStart", "checked");

        req.setAttribute("checkedVNOFinish", "checked");
        req.setAttribute("checkedKUNFinish", "checked");
        req.setAttribute("checkedWMIFinish", "checked");
        req.setAttribute("checkedWAWFinish", "checked");

        req.setAttribute("checkedNoFilter", "checked");
        req.setAttribute("checkedNoFilterR", "checked");

        req.setAttribute("displayDiv2", "none");
        req.setAttribute("displayDiv3", "none");
        req.setAttribute("displayDiv4", "none");
        req.setAttribute("displayDiv5", "none");

        req.setAttribute("displayDivR2", "none");
        req.setAttribute("displayDivR3", "none");
        req.setAttribute("displayDivR4", "none");
        req.setAttribute("displayDivR5", "none");

        req.setAttribute("minDay", "10");
        req.setAttribute("maxDay", "15");







        req.getRequestDispatcher("/WEB-INF/jsp/specific.jsp").forward(req, resp);
    }


}
