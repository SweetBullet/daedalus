package com.bullet.sweet.daedalus.kern.router;

import com.bullet.sweet.daedalus.base.Constants;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhanlan on 16/11/20.
 */
public class RouterInitializer {

    private final static Logger logger = LoggerFactory.getLogger(RouterInitializer.class);


    @Setter
    private Vertx vertx;


    public void inti() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.patch("/path").handler(ctx -> {
            String body = ctx.getBodyAsString(Constants.DEFAULT_CHARSET.name());
            String method = ctx.request().rawMethod();
            MultiMap params = ctx.request().params();



        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        logger.info("daedalus start!");

    }
}
