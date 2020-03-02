package by.academy.it;

import by.academy.it.aop.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


@Slf4j
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        TaskService taskService = context.getBean("taskService", TaskService.class);
        taskService.performJob();
        taskService.performJobAround();
        try {
            taskService.performExceptionJob();
        } catch (Exception e) {
            log.error("Error", e);
        }
        context.close();

        ClassPathXmlApplicationContext contextAnnotation = new ClassPathXmlApplicationContext("spring-config-no-aop.xml");
        TaskService taskServiceAnnotation = contextAnnotation.getBean("taskService", TaskService.class);
        taskServiceAnnotation.performJob();
        taskServiceAnnotation.performJobAround();
        try {
            taskServiceAnnotation.performExceptionJob();
        } catch (Exception e) {
            log.error("Error", e);
        }
        contextAnnotation.close();

    }
}
