package by.academy.it.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("taskService")
public class TaskService {

    public void performJob(){
        log.info("Perform Job from TaskService");
    }

    public void performExceptionJob() throws Exception{
        log.info("Throws exception in Job from TaskService");
        throw new Exception("Exception JOB from TaskService");
    }

    public String performJobAround(){
        log.info("Perform around Job fromTaskService");
        return "Success from TaskService";
    }

}