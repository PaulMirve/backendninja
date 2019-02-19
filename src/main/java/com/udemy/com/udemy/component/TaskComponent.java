package com.udemy.com.udemy.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("taskComponent")
public class TaskComponent {
    private static final Log log = LogFactory.getLog(TaskComponent.class);

    @Scheduled(fixedDelay = 5000)
    public void doTask(){
        log.info("TIME IS: "+ new Date());


    }
}
