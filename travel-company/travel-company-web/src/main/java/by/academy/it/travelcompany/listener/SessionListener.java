package by.academy.it.travelcompany.listener;

import by.academy.it.travelcompany.service.global.FlightService;
import by.academy.it.travelcompany.service.global.TripService;
import by.academy.it.travelcompany.service.global.imp.FlightServiceImpl;
import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener()
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
