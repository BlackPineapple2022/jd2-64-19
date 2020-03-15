package by.academy.it.travelcompany.travelcompany.travelitem.schedule;

import by.academy.it.travelcompany.travelcompany.travelitem.routemap.RouteMap;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

@NoArgsConstructor
@Data
public class Schedule {
    private RouteMap routeMap;
    private Set<LocalDate> scheduleSet = new TreeSet<>();

    public Schedule(RouteMap routeMap) {
        this.routeMap = routeMap;
    }

    public LocalDate getNextDate(LocalDate currentDay) {
        for (LocalDate l: scheduleSet) {
            if (l.isAfter(currentDay)){
                return l;
            }
        }
        //danger using when base is empty
        //for example first search 01.05.2020-15.05.2020
        //second search 20.04.2020 - 01.05.2020 getting only one day: 01.05.2020

        return currentDay.plusDays(1);
    }

}




