package by.academy.it.travelcompany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Slf4j
public class Person {
    @Value("1")
    private Long id;

    @Value("Vova")
    private String name;
    @Autowired
    @Qualifier("companyAddress")
    private IAddress addr;

    public void init(){
        log.info("init person method");
    }
}
