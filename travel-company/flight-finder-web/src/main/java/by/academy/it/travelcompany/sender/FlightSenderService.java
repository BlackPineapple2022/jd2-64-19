package by.academy.it.travelcompany.sender;

import org.json.JSONObject;

import java.util.List;

public interface FlightSenderService extends SenderService {

    void sendData(List<JSONObject> data);

}
