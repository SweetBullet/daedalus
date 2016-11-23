package com.bullet.sweet.daedalus.kern.processor;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by zhanlan on 16/11/23.
 */
public class AsyncProcessor implements HttpAspectProcessor {

    private final static Logger logger = LoggerFactory.getLogger(AsyncProcessor.class);

    private final static int DEFAULT_THREAD_NUMBER = 16;

    private final ExecutorService executorService = new ThreadPoolExecutor(DEFAULT_THREAD_NUMBER, DEFAULT_THREAD_NUMBER
            , 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
            , new BasicThreadFactory.Builder().namingPattern("processor-executor-%d").build());

    @Override
    public void process(HttpContext ctx, HttpProcessor chain) {
        executorService.execute(() -> {
            try {
                logger.info("async processor try to execute");
                chain.process(ctx);
            } catch (Exception e) {
                logger.error("unexpected execute error:", e);
            }
        });
    }
}
