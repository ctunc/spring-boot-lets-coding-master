package com.letscoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;

@SpringBootApplication
@EnableTask
public class SpringBootCronJobApp{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCronJobApp.class, args);
    }


    @BeforeTask
    public void start(TaskExecution taskExecution) {
        System.out.println("Task-Name : " + taskExecution.getTaskName() + " Execution Id : "
                + taskExecution.getExecutionId() + " cron job started...");

    }

    @AfterTask
    public void end(TaskExecution taskExecution) {
        System.out.println("Task-Name : " + taskExecution.getTaskName() + " Execution Id : "
                + taskExecution.getExecutionId() + " cron job completed...");

    }

    @FailedTask
    public void fail(TaskExecution taskExecution, Throwable throwable) {
        System.out.println("Task-Name : " + taskExecution.getTaskName() + " Execution Id : "
                + taskExecution.getExecutionId() + " failed...");

    }

}
