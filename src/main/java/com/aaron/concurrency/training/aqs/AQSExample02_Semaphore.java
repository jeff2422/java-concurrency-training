package com.aaron.concurrency.training.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by Aaron.
 */
@Slf4j
public class AQSExample02_Semaphore {

    //并发线程数
    public static int threadTotal = 20;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(10);

        for(int i = 1; i <= threadTotal; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();// 获取一个许可
                    //semaphore.acquire(2);
                    test(threadNum);
                    semaphore.release(); //释放一个许可
                    //semaphore.release(2);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
            });
        }

        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}", threadNum);
    }
}
