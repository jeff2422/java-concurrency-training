package com.aaron.concurrency.training.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Aaron on 2019/3/2.
 */
@Slf4j
public class Example03_Condition {

    public static void main(String[] args) {

        Lock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("Thread-1 start");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
            log.info("Thread-1 end");
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("Thread-2 start");
            try {
                condition.signalAll();
            }finally {
                reentrantLock.unlock();
            }
            log.info("Thread-2 end");

        }).start();
    }
}
