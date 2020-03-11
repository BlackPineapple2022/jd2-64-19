package by.academy.it;

import org.json.JSONObject;

import java.util.List;

public interface FlightScanner {

    List<JSONObject> parse(Long timeOut, Integer multiplier);
}
