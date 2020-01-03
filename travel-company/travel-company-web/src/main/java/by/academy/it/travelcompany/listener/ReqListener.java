/*
package by.academy.it.travelcompany.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReqListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(ReqListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
       if (sre.getServletRequest().getParameter("userName") != null) {
            logger.info("req initialized by user: " + sre.getServletRequest().getParameter("userName"));
       }
    }
}
*/
