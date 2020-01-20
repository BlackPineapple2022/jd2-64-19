package by.academy.it.travelcompany.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
