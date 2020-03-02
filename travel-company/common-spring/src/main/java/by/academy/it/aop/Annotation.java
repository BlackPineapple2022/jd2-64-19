package by.academy.it.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Slf4j
@Service("annotation")
public class Annotation {

    @Pointcut("execution(* by.academy.it.aop.TaskService.performJob())")
    public void performance(){

    }

    @Pointcut("execution(* by.academy.it.aop.TaskService.performJob(String)) && args(name)")
    public void intercept(String name){

    }

    @Before("performance()")
    public void beforeWork(){
        log.info("Execution before work from Annotation");
    }

    @AfterReturning("performance()")
    public void afterWork(){
        log.info("Execution after work from Annotation");
    }

    @AfterThrowing("performance()")
    public void afterWorkProblems(){
        log.info("Execution after work problems from Annotation");
    }

    @Around("performance()")
    public void aroundWork(ProceedingJoinPoint point){
        try {
            log.info("Start from Annotation");
            log.info("Begin time from Annotation: " + System.currentTimeMillis());
            String result = (String) point.proceed();
            log.info("Finish time from Annotation: " + System.currentTimeMillis());
            log.info("Status from Annotation: " + result);
        }catch (Throwable throwable){
            log.error(String.valueOf(throwable));
        }
    }

    @Before("intercept(name)")
    public void interceptWorkName(String name){
        log.info("Intercepted from Annotation work is "+ name);
    }
}