package com.aaron.concurrency.training.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Aaron.
 */
@Slf4j
public class AtomicExample01_Integer {

    //并发线程数
    public static int threadTotal = 500;

    //普通计数
    public static Integer count = 0;

    //原子计数
    public static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        final ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);

        for(int i = 0; i < threadTotal; i++) {

            executorService.execute(() -> {
                add();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}", count);
        log.info("atomicCount: {}", atomicCount);
    }

    private static void add() {
        count++;
        atomicCount.incrementAndGet();
        //atomicCount.getAndIncrement();
    }
}
