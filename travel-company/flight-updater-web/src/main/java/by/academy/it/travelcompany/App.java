package by.academy.it.travelcompany;

import java.util.ResourceBundle;

public class App {

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("flightupdater.properties");
        String hello = resourceBundle.getString("hello");
        System.out.println(hello);
    }
}
