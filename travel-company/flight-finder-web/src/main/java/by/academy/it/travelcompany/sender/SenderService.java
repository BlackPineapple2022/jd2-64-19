package by.academy.it.travelcompany.sender;

import java.util.ResourceBundle;

public interface SenderService {

    String apiAddress = ResourceBundle.getBundle("flightfinder").getString("main.web.project.path");

}
