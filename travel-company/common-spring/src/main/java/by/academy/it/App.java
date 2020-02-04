package by.academy.it;


import by.academy.it.travelcompany.PrintService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        PrintService printService = (PrintService)context.getBean("printService");

        printService.printMessage("Hello Ulala");
    }
}
