package by.academy.it.travelcompany.travelitem.direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Direction {
    private Long id;
    private String directionName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return Objects.equals(directionName, direction.directionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directionName);
    }
}
