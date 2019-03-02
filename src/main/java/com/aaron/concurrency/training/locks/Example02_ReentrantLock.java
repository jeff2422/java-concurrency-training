package com.aaron.concurrency.training.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Aaron on 2019/3/2.
 */
@Slf4j
public class Example02_ReentrantLock {


    //请求数
    public static int requestTotal = 5000;

    //并发线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static CountDownLatch countDownLatch = new CountDownLatch(requestTotal);

    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(threadTotal);

        for(int i = 0; i < requestTotal; i++) {
            executorService.execute(() -> {
                try {
                    add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        log.info("{}", count);
        executorService.shutdown();
    }

    public static void add() throws InterruptedException {
        lock.lock();
        try {
            count++;
        }finally {
           lock.unlock();
        }

    }
}
