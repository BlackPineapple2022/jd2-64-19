package by.academy.it.travelcompany;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.academy.it.travelcompany")
public class MyConfig {

    @Bean(initMethod = "init")
    public Person getPerson(){
        Person person = new Person();
        person.setName("From config");
        return person;
    }

}
