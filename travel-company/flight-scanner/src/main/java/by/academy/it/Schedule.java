package by.academy.it;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {

    private String routeMap;
    private Set<LocalDate> scheduleSet = new TreeSet<>();

    public Schedule(String routeMap) {
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
