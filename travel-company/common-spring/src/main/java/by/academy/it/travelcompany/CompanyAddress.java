package by.academy.it.travelcompany;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component("companyAddress")
public class CompanyAddress implements IAddress{

    private String companyAddress;
}
