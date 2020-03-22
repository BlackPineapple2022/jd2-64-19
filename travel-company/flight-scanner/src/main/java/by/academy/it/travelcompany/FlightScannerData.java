package by.academy.it.travelcompany;

import by.academy.it.travelcompany.serializer.LocalDateDeserializer;
import by.academy.it.travelcompany.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FlightScannerData {

    private String routeMap;
    private Integer dayCount;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startingDate;

}
