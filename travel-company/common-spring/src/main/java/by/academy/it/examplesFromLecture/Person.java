package by.academy.it.examplesFromLecture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Component
public class Person {
    @Autowired
    @Qualifier("companyAddress")
    //@Qualifier("privateAddress")
    Address address;
    @Value("1")
    Long id;
    @Value("Uladzimir")
    String name;
    @Value("Dubouski")
    String surname;

    public void init(){
        log.info("Init method");
    }

    public void destroy(){
        log.info("Destroy method");
    }

}
