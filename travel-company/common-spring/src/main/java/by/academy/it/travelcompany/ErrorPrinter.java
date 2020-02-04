package by.academy.it.travelcompany;

/**
 * Created by User on 04.02.2020.
 */
public class ErrorPrinter implements Printer {


    @Override
    public void print(String message) {
        System.err.println(message);
    }
}
