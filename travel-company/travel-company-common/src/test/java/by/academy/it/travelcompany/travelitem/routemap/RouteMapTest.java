package by.academy.it.travelcompany.travelitem.routemap;

import org.junit.Test;

import static org.junit.Assert.*;

public class RouteMapTest {

    @Test
    public void testEquals() {
        RouteMap r1 = new RouteMap("RY--VNO--BGY--Direct");
        RouteMap r2 = new RouteMap("RY--VNO--BGY--Direct");
        RouteMap r3 = new RouteMap("RY--WMI--BGY--Direct");
        assertEquals(r1,r2);
        assertNotEquals(r1,r3);
    }
}