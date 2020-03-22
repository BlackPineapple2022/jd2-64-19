package by.academy.it.travelcompany;

import by.academy.it.travelcompany.scanner.impl.WIZZFlightScanner;
import by.academy.it.travelcompany.scanner.FlightScanner;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WIZZFlightScannerTest {

    @Test
    public void parseFlightsWIZZ1() {
        String routeMap1 = "WIZZ--VNO--BVA--DIRECT";
        LocalDate startingDate1 = LocalDate.now().plusDays(10);
        Integer dayCount1 = 10;
        FlightScannerData flightScannerData1 = new FlightScannerData(routeMap1,dayCount1,startingDate1);
        FlightScanner wizzFlightScanner1 = new WIZZFlightScanner(flightScannerData1);
        List<JSONObject> flightList1 = wizzFlightScanner1.parse(2000L,2);
        System.err.println(flightList1);
        Assert.assertTrue(flightList1.size()>0);
    }

    @Test
    public void parseFlightsWIZZ2(){
        String routeMap2 = "WIZZ--BVA--VNO--RETURN";
        LocalDate startingDate2 = LocalDate.now().plusDays(15);
        Integer dayCount2 = 10;
        FlightScannerData flightScannerData2 = new FlightScannerData(routeMap2,dayCount2,startingDate2);
        FlightScanner wizzFlightScanner2 = new WIZZFlightScanner(flightScannerData2);
        List<JSONObject> flightList2 = wizzFlightScanner2.parse(2000L,2);
        System.err.println(flightList2);
        Assert.assertTrue(flightList2.size()>0);
    }

    @Test
    public void parseFlightWIZZ3(){
        String routeMap1 = "WIZZ--VNO--BVA--DIRECT";
        LocalDate startingDate1 = LocalDate.now().plusDays(10);
        Integer dayCount1 = 10;
        FlightScannerData flightScannerData1 = new FlightScannerData(routeMap1,dayCount1,startingDate1);
        String routeMap2 = "WIZZ--BVA--VNO--RETURN";
        LocalDate startingDate2 = LocalDate.now().plusDays(15);
        Integer dayCount2 = 10;
        FlightScannerData flightScannerData2 = new FlightScannerData(routeMap2,dayCount2,startingDate2);
        List<FlightScannerData> flightScannerDataList = new ArrayList<>();
        flightScannerDataList.add(flightScannerData1);
        flightScannerDataList.add(flightScannerData2);

        FlightScanner fs = new WIZZFlightScanner(flightScannerDataList);
        List<JSONObject> jsonObjects = fs.parse(2000L, 2);
        System.err.println(jsonObjects);
        Assert.assertTrue(jsonObjects.size()>0);
    }

}