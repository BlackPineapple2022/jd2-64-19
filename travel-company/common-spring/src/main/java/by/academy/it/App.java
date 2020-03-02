package by.academy.it;

import by.academy.it.examplesFromLecture.NoXMLConfiguration;
import by.academy.it.examplesFromLecture.Person;
import by.academy.it.examplesFromLecture.PersonService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Slf4j
@Getter
@Component("app")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class App implements BeanNameAware {

    private String beanName;

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("by.academy.it.examplesFromLecture");
        Person p = context.getBean("person", Person.class);
        log.info("Person with address :" + p);
        ((ConfigurableApplicationContext)context).close();

        context = new AnnotationConfigApplicationContext(App.class);
        App app = context.getBean("app",App.class);
        log.info("bean name: "+app.getBeanName());
        log.info("class: "+app.getClass());
        ((ConfigurableApplicationContext)context).close();

        context = new AnnotationConfigApplicationContext();
        ((AnnotationConfigApplicationContext)context).register(NoXMLConfiguration.class);
        ((AnnotationConfigApplicationContext) context).refresh();
        ((AnnotationConfigApplicationContext) context).start();

        PersonService personService = context.getBean(PersonService.class);
        log.info("Name from personService: " +personService.getPersonName());

    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }
}
