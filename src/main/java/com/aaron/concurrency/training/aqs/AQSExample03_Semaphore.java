package com.aaron.concurrency.training.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Aaron.
 */
@Slf4j
public class AQSExample03_Semaphore {

    //并发线程数
    public static int threadTotal = 20;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(10);

        for(int i = 1; i <= threadTotal; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    if(semaphore.tryAcquire()) { // 尝试获取一个许可
                        test(threadNum);
                        semaphore.release(); //释放一个许可
                    }
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
