package com.bullet.sweet.daedalus.kern;


import io.vertx.core.Vertx;

/**
 * Created by Zhanlan on 16/11/8.
 */
public class VertxFactory {

    private volatile Vertx vertx;

    public Vertx build() {
        if (null == vertx) {
            synchronized (this) {
                if (null == vertx) {
                    vertx = Vertx.vertx();
                }
            }
        }
        return this.vertx;
    }


}
