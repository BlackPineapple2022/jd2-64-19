package by.academy.it.travelcompany.listener;

import by.academy.it.travelcompany.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class SessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        User user = (User)(se.getSession().getAttribute("user"));
        logger.info("Session created by " + user.getUserName());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        User user = (User)(se.getSession().getAttribute("user"));
        logger.info("Session ended by "+user.getUserName());
    }
}
