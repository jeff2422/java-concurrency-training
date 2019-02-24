package com.aaron.concurrency.training.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by Aaron.
 */
@Slf4j
public class AQSExample08_Future {

    static class FutureExample implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in FutureExample");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = executorService.submit(new FutureExample());
        log.info("do something in main");

        String result = future.get();
        log.info("{}", result);
    }
}
