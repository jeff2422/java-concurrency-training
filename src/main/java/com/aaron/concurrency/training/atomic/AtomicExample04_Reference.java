package com.aaron.concurrency.training.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Aaron.
 */
@Slf4j
public class AtomicExample04_Reference {

    public static void main(String[] args) throws Exception {

        AtomicReference<Integer> count = new AtomicReference<>(0);

        count.compareAndSet(0, 10);
        count.compareAndSet(0, 20);
        count.compareAndSet(10, 20);
        count.compareAndSet(10, 30);
        log.info("count: {}", count);
    }
}
