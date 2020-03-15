package by.academy.it.travelcompany.travelcompany.servlet.user;

import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;
import by.academy.it.travelcompany.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelcompany.travelitem.trip.Trip;
import by.academy.it.travelcompany.travelcompany.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/user/favourite/add")
public class addTripToFavouriteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        Long flightDirectId = Long.parseLong(req.getParameter("flightDirectId"));
        Long flightReturnId = Long.parseLong(req.getParameter("flightReturnId"));
        Double price = Double.parseDouble(req.getParameter("price"));

        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(flightDirectId));
        flights.add(new Flight(flightReturnId));
        Trip trip = new Trip(flights,price,null);
        TripServiceImpl.getInstance().addTrip(trip);
        FavouriteServiceImpl.getInstance().addTripToFavourite(user.getId(),user.getUserName()+"_favourite",trip.getId());

    }
}
