package com.aaron.concurrency.training.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Aaron.
 */
@Slf4j
public class AtomicExample02_Long {

    //并发线程数
    public static int threadTotal = 500;

    //普通计数
    public static Long count = 0L;

    //原子计数
    public static AtomicLong atomicCount = new AtomicLong(0);

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
