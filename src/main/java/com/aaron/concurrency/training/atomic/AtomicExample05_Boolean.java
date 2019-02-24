package com.aaron.concurrency.training.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Aaron.
 */
@Slf4j
public class AtomicExample05_Boolean {

    //并发线程数
    public static int threadTotal = 500;

    //普通计数
    public static boolean isHappened = false;

    //原子计数
    public static AtomicBoolean isAtomicHappened = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {

        final ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);

        for(int i = 0; i < threadTotal; i++) {

            executorService.execute(() -> {

                if(isHappened == false) {
                    isHappened = true;
                    log.info("execute1");
                }

                if(isAtomicHappened.compareAndSet(false, true)) {
                    log.info("execute2");
                }
                countDownLatch.countDown();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error("Exception", e);
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened: {}", isHappened);
        log.info("isAtomicHappened: {}", isAtomicHappened);
    }
}
