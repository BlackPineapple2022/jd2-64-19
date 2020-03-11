package by.academy.it;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
