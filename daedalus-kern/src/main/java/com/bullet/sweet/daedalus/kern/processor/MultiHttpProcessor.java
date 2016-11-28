package com.bullet.sweet.daedalus.kern.processor;

import lombok.AllArgsConstructor;

/**
 * Created by Zhanlan on 16/11/23.
 */
@AllArgsConstructor
public class MultiHttpProcessor implements HttpProcessor {

    private HttpAspectProcessor concurrent;
    private HttpProcessor chain;

    @Override
    public void process(HttpContext ctx) {
        concurrent.process(ctx, chain);
    }
}
