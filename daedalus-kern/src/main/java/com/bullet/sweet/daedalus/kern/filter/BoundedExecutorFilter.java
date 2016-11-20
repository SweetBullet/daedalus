package com.bullet.sweet.daedalus.kern.filter;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


/**
 * Created by zhanlan on 16/11/20.
 */
public class BoundedExecutorFilter<T> implements Filter<T>, AutoCloseable {

    private final static int DEFAULT_WORKER_THREAD_NUM = 16;

    private final static Logger logger = LoggerFactory.getLogger(BoundedExecutorFilter.class);

    private ExecutorService executor;

    private Filter filter;

    public BoundedExecutorFilter(Filter filter) {
        executor = new ThreadPoolExecutor(DEFAULT_WORKER_THREAD_NUM, DEFAULT_WORKER_THREAD_NUM, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new BasicThreadFactory.Builder().namingPattern("filter-executor-%d").build());
        this.filter = filter;
    }


    @Override
    public void filter(T t) {
        try {
            executor.execute(() -> {
                try {
                    filter.filter(t);
                } catch (Exception e) {
                    logger.error("error occur while executing filter task:", e);
                }
            });
        } catch (Exception e) {
            logger.error("error occur while submitting the task:", e);
        }

    }

    @Override
    public void close() throws Exception {
        executor.shutdown();
        boolean isShutDown = executor.awaitTermination(60, TimeUnit.SECONDS);
        if (!isShutDown) {
            logger.warn("waiting executor shutdown timeout!");
            executor.shutdownNow();
        }
    }
}
