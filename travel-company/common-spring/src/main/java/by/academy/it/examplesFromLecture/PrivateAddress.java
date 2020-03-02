package by.academy.it.examplesFromLecture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component("privateAddress")
public class PrivateAddress implements Address {
    @Value("1")
    private Long id;
    @Value("Belarus")
    private String country;
    @Value("Minsk")
    private String city;
    @Value("Puskina sq.")
    private String street;


}
