package by.academy.it;

import by.academy.it.impl.WIZZFlightScanner;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@Slf4j
public class WIZZThreadTest extends Thread {

    private Integer testNumber;

    private static String routeMap1 = "WIZZ--VNO--BVA--DIRECT";
    private static LocalDate startingDate1 = LocalDate.now().plusDays(10);
    private static Integer dayCount1 = 10;
    private static FlightScannerData flightScannerData1 = new FlightScannerData(routeMap1, dayCount1, startingDate1);
    private static FlightScanner wizzFlightScanner1 = new WIZZFlightScanner(flightScannerData1);

    private static String routeMap2 = "WIZZ--BVA--VNO--RETURN";
    private static LocalDate startingDate2 = LocalDate.now().plusDays(15);
    private static Integer dayCount2 = 10;
    private static FlightScannerData flightScannerData2 = new FlightScannerData(routeMap2, dayCount2, startingDate2);
    private static FlightScanner wizzFlightScanner2 = new WIZZFlightScanner(flightScannerData2);

    public void parseFlightsWIZZ1() {
        List<JSONObject> flightList1 = wizzFlightScanner1.parse(2000L, 2);
        System.err.println(flightList1);
    }

    public void parseFlightsWIZZ2() {
        List<JSONObject> flightList2 = wizzFlightScanner2.parse(2000L, 2);
        System.err.println(flightList2);
    }

    @Override
    public void run() {
        if (testNumber.equals(1)) {
            parseFlightsWIZZ1();
        }
        if (testNumber.equals(2)) {
            parseFlightsWIZZ2();
        }
    }

    @Test
    public void multiThreadRequestTimeoutTest() throws Exception {
        WIZZThreadTest test1 = new WIZZThreadTest();
        test1.setTestNumber(1);
        WIZZThreadTest test2 = new WIZZThreadTest();
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

        Set<Long> test1TimeStampSet = ((WIZZFlightScanner)wizzFlightScanner1).getTimeStampFlightFindSet();
        Set<Long> test2TimeStampSet = ((WIZZFlightScanner)wizzFlightScanner2).getTimeStampFlightFindSet();

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
