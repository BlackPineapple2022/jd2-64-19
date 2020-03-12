package by.academy.it;

import by.academy.it.impl.RYFlightScanner;
import lombok.Data;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RYFlightScannerTest {

    @Test
    public void parseFlightsRY1() {
        String routeMap1 = "RY--VNO--BVA--DIRECT";
        LocalDate startingDate1 = LocalDate.now().plusDays(10);
        Integer dayCount1 = 30;
        FlightScannerData flightScannerData1 = new FlightScannerData(routeMap1,dayCount1,startingDate1);
        RYFlightScanner ryFlightScanner1 = new RYFlightScanner(flightScannerData1);
        List<JSONObject> flightList1 = ryFlightScanner1.parse(2000L,2);
        System.err.println(flightList1);
        Assert.assertTrue(flightList1.size()>0);
    }

    @Test
    public void parseFlightsRY2(){
        String routeMap2 = "RY--BVA--VNO--RETURN";
        LocalDate startingDate2 = LocalDate.now().plusDays(15);
        Integer dayCount2 = 30;
        FlightScannerData flightScannerData2 = new FlightScannerData(routeMap2,dayCount2,startingDate2);
        RYFlightScanner ryFlightScanner2 = new RYFlightScanner(flightScannerData2);
        List<JSONObject> flightList2 = ryFlightScanner2.parse(2000L,2);
        System.err.println(flightList2);
        Assert.assertTrue(flightList2.size()>0);
    }

    @Test
    public void parseFlightRY3(){
        String routeMap1 = "RY--VNO--BVA--DIRECT";
        LocalDate startingDate1 = LocalDate.now().plusDays(10);
        Integer dayCount1 = 10;
        FlightScannerData flightScannerData1 = new FlightScannerData(routeMap1,dayCount1,startingDate1);
        String routeMap2 = "RY--BVA--VNO--RETURN";
        LocalDate startingDate2 = LocalDate.now().plusDays(15);
        Integer dayCount2 = 10;
        FlightScannerData flightScannerData2 = new FlightScannerData(routeMap2,dayCount2,startingDate2);
        List<FlightScannerData> flightScannerDataList = new ArrayList<>();
        flightScannerDataList.add(flightScannerData1);
        flightScannerDataList.add(flightScannerData2);

        FlightScanner fs = new RYFlightScanner(flightScannerDataList);
        List<JSONObject> jsonObjects = fs.parse(2000L, 2);
        System.err.println(jsonObjects);
        Assert.assertTrue(jsonObjects.size()>0);
    }





}