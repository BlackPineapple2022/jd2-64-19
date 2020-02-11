package by.academy.it;


import by.academy.it.travelcompany.MyConfig;
import by.academy.it.travelcompany.Person;
import by.academy.it.travelcompany.PersonService;
import by.academy.it.travelcompany.PrintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Slf4j
public class App {
    public static void main(String[] args) {
        /*System.out.println("Hello World!");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        PrintService printService = (PrintService)context.getBean("printService");

        printService.printMessage("Hello Ulala");*/

       /* ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");*/


       /* AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("by.academy.it.travelcompany");

       */

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        context.register(MyConfig.class);

        context.refresh();
        context.start();

        PersonService bean = context.getBean(PersonService.class);

        log.info("Person bean : ", bean.getName());
    }
}
