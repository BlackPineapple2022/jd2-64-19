package by.academy.it.travelcompany;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component("address")
public class Address implements IAddress {

    String street;
    String country;
}
