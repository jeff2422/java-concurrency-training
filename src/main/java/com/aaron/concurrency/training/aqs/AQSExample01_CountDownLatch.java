package com.aaron.concurrency.training.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aaron.
 */
@Slf4j
public class AQSExample01_CountDownLatch {

    //并发线程数
    public static int threadTotal = 500;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(threadTotal);

        for(int i = 1; i <= threadTotal; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await(1000, TimeUnit.MILLISECONDS); //超时等待，先输出
        log.info("finished1");

        countDownLatch.await(); //最后输出
        log.info("finished2");

        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(2000);
        log.info("{}", threadNum);
    }
}
