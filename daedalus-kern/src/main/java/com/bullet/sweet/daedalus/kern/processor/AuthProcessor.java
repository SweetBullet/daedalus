package com.bullet.sweet.daedalus.kern.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhanlan on 16/11/23.
 */
public class AuthProcessor implements HttpAspectProcessor {

    private final static Logger logger = LoggerFactory.getLogger(AuthProcessor.class);

    @Override
    public void process(HttpContext ctx, HttpProcessor chain) {
        logger.info("AuthProcessor process request");
        ctx.getResponse().end("successful!");
        chain.process(ctx);
    }
}
