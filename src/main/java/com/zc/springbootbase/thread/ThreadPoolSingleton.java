package com.zc.springbootbase.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/4/23 17:57
 */
public class ThreadPoolSingleton {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolSingleton.class);


    private ExecutorService executorService;
    private final int availableProcessor = Runtime.getRuntime().availableProcessors();
    private ThreadPoolSingleton() {
        if (executorService == null) {
            int coreNum = availableProcessor / 2;
            // 用单例模式创建线程池，保留2个核心线程，最多线程为CPU个数的2n+1的两倍.
            int maxProcessor = (availableProcessor * 2 + 1) * 2 ;
            executorService = new ThreadPoolExecutor(coreNum > 2 ? 2 : coreNum, maxProcessor,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
        }
    }
/*
    private static ThreadPoolSingleton instance;

    public static ThreadPoolSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadPoolSingleton();
        }
        return instance;
    }*/

    private  static ThreadPoolSingleton instance = new ThreadPoolSingleton();
    public static ThreadPoolSingleton getInstance() {
        return instance;
    }

    int executeThreadNum = 1;
    public void executeTask(Runnable runnable) {
        executorService.execute(runnable);
        logger.info("ThreadPoolSingleton {}", String.format("异步线程执行了%d次。", executeThreadNum++));
    }
}
