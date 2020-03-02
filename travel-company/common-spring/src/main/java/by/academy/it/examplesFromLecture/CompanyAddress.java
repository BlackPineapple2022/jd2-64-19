package by.academy.it.examplesFromLecture;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component("companyAddress")
public class CompanyAddress implements Address {
    @Value("1")
    private Long id;
    @Value("Belarus")
    private String country;
    @Value("Minsk")
    private String city;
    @Value("Kozlova")
    private String street;

}
