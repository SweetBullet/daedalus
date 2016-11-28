package com.bullet.sweet.daedalus.kern.processor;

/**
 * Created by Zhanlan on 16/11/23.
 */
public interface HttpAspectProcessor {
    void process(HttpContext ctx,HttpProcessor chain);
}
