package by.academy.it.travelcompany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Schedule {

    private Set<LocalDate> scheduleSet = new TreeSet<>();

    public LocalDate getNextDate(LocalDate currentDay) {
        for (LocalDate l: scheduleSet) {
            if (l.isAfter(currentDay)){
                return l;
            }
        }
        return currentDay.plusDays(1);
    }
}
