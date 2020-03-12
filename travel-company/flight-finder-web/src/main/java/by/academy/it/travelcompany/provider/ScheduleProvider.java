package by.academy.it.travelcompany.provider;

import by.academy.it.travelcompany.Schedule;

public interface ScheduleProvider extends Provider {

    Schedule getScheduleByRouteMapString(String routeMapString);

}
