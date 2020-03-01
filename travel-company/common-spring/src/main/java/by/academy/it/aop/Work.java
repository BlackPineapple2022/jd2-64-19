package by.academy.it.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("work")
public class Work {

    public void beforeWork() {
        log.info("Execute before work from Work");
    }

    public void afterWork() {
        log.info("Execute after work from Work");
    }

    public void afterWorkProblems() {
        log.info("Execution problems from Work");
    }

}

