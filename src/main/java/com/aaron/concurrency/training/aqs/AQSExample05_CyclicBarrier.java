package com.aaron.concurrency.training.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by Aaron.
 */
@Slf4j
public class AQSExample05_CyclicBarrier {

    //并发线程数
    public static int threadTotal = 100;

    public static final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 1; i <= threadTotal; i++) {
            Thread.sleep(1000);
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("Exception", e);
                }

            });
        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException, BrokenBarrierException {
        //log.info("{}", threadNum);
        log.info("{} is waiting", threadNum);
        cyclicBarrier.await();
        log.info("{} is done", threadNum);
    }
}
