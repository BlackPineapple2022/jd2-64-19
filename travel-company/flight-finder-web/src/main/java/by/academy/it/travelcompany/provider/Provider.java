package by.academy.it.travelcompany.provider;

import java.util.ResourceBundle;

public interface Provider {

    String apiAddress = ResourceBundle.getBundle("flightfinder").getString("main.web.project.path");

}
