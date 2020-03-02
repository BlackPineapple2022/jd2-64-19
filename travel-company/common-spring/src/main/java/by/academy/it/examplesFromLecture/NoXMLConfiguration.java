package by.academy.it.examplesFromLecture;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.academy.it.examplesFromLecture")
public class NoXMLConfiguration {
    @Bean(value ="personBean", autowire = Autowire.NO, initMethod = "init", destroyMethod = "destroy")
    public Person getPerson(){
        return new Person();
    }
}
