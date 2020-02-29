package by.academy.it;

import by.academy.it.examplesFromLecture.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Slf4j
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("by.academy.it.examplesFromLecture");
        Person p = context.getBean("person", Person.class);
        log.info("Person with address :" + p);
    }


}
