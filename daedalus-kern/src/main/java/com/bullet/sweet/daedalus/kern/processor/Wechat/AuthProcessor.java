package com.bullet.sweet.daedalus.kern.processor.Wechat;

import com.bullet.sweet.daedalus.kern.SignatureFactory;
import com.bullet.sweet.daedalus.kern.processor.HttpAspectProcessor;
import com.bullet.sweet.daedalus.kern.processor.HttpContext;
import com.bullet.sweet.daedalus.kern.processor.HttpProcessor;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Zhanlan on 16/11/20.
 * validate the token and url
 * check whether the msg is from wechat or not
 */


public class AuthProcessor implements HttpAspectProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AuthProcessor.class);

    @Value("${daedalus.token}")
    private String token;

    @Override
    public void process(HttpContext ctx, HttpProcessor chain) {
        logger.info("AuthProcessor try to process:{}", ctx.getRawBody());
        HttpServerRequest request = ctx.getRequest();
        String signature = request.getParam("signature");
        String timestamp = request.getParam("timestamp");
        String nonce = request.getParam("nonce");
        try {
            String sha = SignatureFactory.createSignature(token, timestamp, nonce);
            if (!signature.equals(sha)) return;
            if (request.rawMethod().equals(HttpMethod.GET.name())) {
                logger.warn("Receive msg not from wechat discard! expected signature is {},actual is {}", signature, sha);
                ctx.getResponse().end(request.getParam("echostr"));
                return;
            }
            chain.process(ctx);
        } catch (Exception e) {
            logger.error("error occurs while computing and comparing signature discard msg error:", e);
        }
    }
}
