package by.academy.it.travelcompany.travelitem.schedule;

import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
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
        return currentDay.plusDays(1);
    }

}




