package com.bullet.sweet.daedalus.kern.router;

import com.bullet.sweet.daedalus.base.Constants;
import com.bullet.sweet.daedalus.kern.HealthChecker;
import com.bullet.sweet.daedalus.kern.processor.HttpContext;
import com.bullet.sweet.daedalus.kern.processor.HttpProcessor;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Zhanlan on 16/11/20.
 */
public class RouterInitializer {

    private final static Logger logger = LoggerFactory.getLogger(RouterInitializer.class);

    @Setter
    private Vertx vertx;
    @Setter
    private int port;
    @Setter
    private HttpProcessor processor;
    @Setter
    private HealthChecker healthChecker;


    public void init() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route().path("/path").handler(ctx -> {
            String body = ctx.getBodyAsString(Constants.DEFAULT_CHARSET.name());
            logger.info("receive a request,method:{},body:{}", ctx.request().rawMethod(), body);
            processor.process(new HttpContext(ctx.request(), ctx.response(), body));
        });
        router.route("/_HB_").handler(ctx -> {
            String online = ctx.request().getParam("online");
            String offline = ctx.request().getParam("offline");
            logger.info("request -> online:{},offline{}");
            if (!StringUtils.isEmpty(online)) {
                healthChecker.online();
                ctx.response().setStatusCode(200).end("");
                return;
            }
            if (!StringUtils.isEmpty(offline)) {
                healthChecker.offline();
                ctx.response().setStatusCode(200).end("");
                return;
            }
            if (!healthChecker.isServed()) {
                ctx.response().setStatusCode(400).end("");
                return;
            }

        });
        port = (port == 0) ? 8888 : port;
        vertx.createHttpServer().requestHandler(router::accept).listen(port);
        logger.info("daedalus start at port:{}!", port);

    }


}
