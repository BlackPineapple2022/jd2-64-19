package by.academy.it;

import by.academy.it.impl.RYFlightScanner;
import lombok.Data;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
public class RYThreadTest extends Thread {

    private Integer testNumber;

    private static String routeMap1 = "RY--VNO--BVA--DIRECT";
    private static LocalDate startingDate1 = LocalDate.now().plusDays(10);
    private static Integer dayCount1 = 10;
    private static FlightScannerData flightScannerData1 = new FlightScannerData(routeMap1, dayCount1, startingDate1);
    private static RYFlightScanner ryFlightScanner1 = new RYFlightScanner(flightScannerData1);

    private static String routeMap2 = "RY--BVA--VNO--RETURN";
    private static LocalDate startingDate2 = LocalDate.now().plusDays(15);
    private static Integer dayCount2 = 10;
    private static FlightScannerData flightScannerData2 = new FlightScannerData(routeMap2, dayCount2, startingDate2);
    private static RYFlightScanner ryFlightScanner2 = new RYFlightScanner(flightScannerData2);

    public void parseFlightsRY1() {
        List<JSONObject> flightList1 = ryFlightScanner1.parse(2000L, 2);
        System.err.println(flightList1);
    }

    public void parseFlightsRY2() {
        List<JSONObject> flightList2 = ryFlightScanner2.parse(2000L, 2);
        System.err.println(flightList2);
    }

    @Override
    public void run() {
        if (testNumber.equals(1)) {
            parseFlightsRY1();
        }
        if (testNumber.equals(2)) {
            parseFlightsRY2();
        }
    }

    @Test
    public void multiThreadRequestTimeoutTest() throws Exception {
        RYThreadTest test1 = new RYThreadTest();
        test1.setTestNumber(1);
        RYThreadTest test2 = new RYThreadTest();
        test2.setTestNumber(2);
        test1.start();
        test2.start();

        while (true) {
            Thread.sleep(1000);
            if (test1.isAlive()) {
                continue;
            }
            if (test2.isAlive()) {
                continue;
            }
            break;
        }

        Set<Long> test1TimeStampSet = ryFlightScanner1.getTimeStampFlightFindSet();
        Set<Long> test2TimeStampSet = ryFlightScanner2.getTimeStampFlightFindSet();

        test1TimeStampSet.addAll(test2TimeStampSet);

        List<Long> intervals = new ArrayList<>();
        Long startingTimeStamp = ((TreeSet<Long>) test1TimeStampSet).pollFirst();
        for (Long timeStamp : test1TimeStampSet) {
            Long interval = timeStamp - startingTimeStamp;
            Assert.assertTrue(interval>2000L);
            intervals.add(interval);
            startingTimeStamp = timeStamp;
        }
        System.err.println(intervals);
    }


}
