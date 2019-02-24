package com.aaron.concurrency.training.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Aaron.
 *
 * LongAdder在低并发的时候可以和AtomicLong的性能基本保持一致，而在高并发的时候提高了性能。
 * 缺点是LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
 */
@Slf4j
public class AtomicExample03_LongAdder {

    //并发线程数
    public static int threadTotal = 500;

    //普通计数
    public static Long count = 0L;

    //原子计数
    public static LongAdder atomicCount = new LongAdder();

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
        atomicCount.increment();
    }
}
