package com.aaron.concurrency.training.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by Aaron.
 */
@Slf4j
public class AQSExample09_FutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do something in task");
            Thread.sleep(5000);
            return "Done";
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        String result = futureTask.get();
        log.info("{}", result);
    }
}
