package by.academy.it.travelcompany.listener;

import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener()
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(se.getSession().getAttribute("user"));
        List<Long> searchIdList = (List<Long>)se.getSession().getAttribute("searchIdList");
        System.out.println(searchIdList);
        TripServiceImpl.getInstance().deleteAllBySearchIdButFavourite(searchIdList);
        System.out.println("deleted");
    }
}
