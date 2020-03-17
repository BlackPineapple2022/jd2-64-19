package by.academy.it.travelcompany.controller;

import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RoundTrip;
import by.academy.it.travelcompany.entity.User;
import by.academy.it.travelcompany.service.CurrencyService;
import by.academy.it.travelcompany.service.RoundTripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
public class FavouriteController {

    @Autowired
    private RoundTripService roundTripService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/favourite", method = RequestMethod.POST)
    public void add(@RequestParam String id){
        User user = (User)httpSession.getAttribute("user");
        List<RoundTrip> trips = (List<RoundTrip>)httpSession.getAttribute("trips");
        RoundTrip r = trips.get(Integer.parseInt(id));
        roundTripService.addToFavourite(r,user);
    }

    @RequestMapping(value = "/favourite", method = RequestMethod.GET)
    public String favourite(){
        Object userObj = httpSession.getAttribute("user");
        if (userObj==null){
            return "login";
        }
        User user = (User)userObj;

        ArrayList<String> attributeList = Collections.list(httpSession.getAttributeNames());

        for (String a : attributeList) {
            if (!a.equals("user")&&(!a.equals("allStartedAirports"))){
                httpSession.removeAttribute(a);
            }
        }

        List<RoundTrip> favouriteRoundTripList = roundTripService.getFavouriteRoundTripList(user);

        for (RoundTrip trip : favouriteRoundTripList) {
            Flight returnFlight = trip.getReturnFlight();
            Flight directFlight = trip.getDirectFlight();
            Double price = returnFlight.getPrice()*currencyService.getEURMultiplier(returnFlight.getCurrency());
            price += directFlight.getPrice()*currencyService.getEURMultiplier(directFlight.getCurrency());
            trip.setPrice(price);
        }

        httpSession.setAttribute("trips",favouriteRoundTripList);
        return "favourite";
    }

    @RequestMapping(value = "/favourite/delete", method = RequestMethod.POST)
    public void delete(@RequestParam String id){
        User user = (User)httpSession.getAttribute("user");
        List<RoundTrip> trips = (List<RoundTrip>)httpSession.getAttribute("trips");
        RoundTrip r= null;
        for (RoundTrip trip : trips) {
            if(trip.getId()==Integer.parseInt(id)){
                r=trip;
            }
        }
        roundTripService.removeFromFavourite(r,user);
    }

}
