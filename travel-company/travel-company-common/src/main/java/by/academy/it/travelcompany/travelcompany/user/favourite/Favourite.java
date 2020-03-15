package by.academy.it.travelcompany.travelcompany.user.favourite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Favourite {
    private Long id;
    private String favouriteName;

    @Override
    public String toString() {
        return favouriteName;
    }
}
