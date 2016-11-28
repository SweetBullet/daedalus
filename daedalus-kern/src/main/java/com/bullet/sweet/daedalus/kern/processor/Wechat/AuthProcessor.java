package com.bullet.sweet.daedalus.kern.processor.Wechat;

import com.bullet.sweet.daedalus.kern.processor.HttpAspectProcessor;
import com.bullet.sweet.daedalus.kern.processor.HttpContext;
import com.bullet.sweet.daedalus.kern.processor.HttpProcessor;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Zhanlan on 16/11/20. 微信签名校验处理器,检测消息是不是来自微信
 */
public class AuthProcessor implements HttpAspectProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AuthProcessor.class);

    @Override
    public void process(HttpContext ctx, HttpProcessor chain) {
        logger.info("AuthProcessor try to process:{}", ctx.getRawBody());
    }

//
//    private
//    @Value("${vanguard.component.token}")
//    String token;
//
//    @Override
//    public void process(VanguardHttpContext vanguardHttpContext, HttpProcessor chain) {
//        logger.info("begin auth:{}", vanguardHttpContext);
//        HttpServerRequest request = vanguardHttpContext.getRequest();
//        String timestamp = request.getParam("timestamp");
//        String nonce = request.getParam("nonce");
//        String wechatSignature = request.getParam("signature");//sha1 签名验证
//        HttpServerResponse response = vanguardHttpContext.getResponse();
//        try {
//            String signature = SignatureFactory.createSignature(token, timestamp, nonce);
//            if (!signature.equals(wechatSignature)) {
//                logger.warn("Receive msg not from wechat discard! expected signature is {},actual is {}", wechatSignature,
//                          signature);
//                response.putHeader(HttpHeaders.CONTENT_TYPE, Constants.CONTENT_TYPE_XML).end("");
//                return;
//            }
//        } catch (Exception e) {
//            logger.error("error occurs while computing and comparing signature discard msg error:", e);
//            response.putHeader(HttpHeaders.CONTENT_TYPE, Constants.CONTENT_TYPE_XML).end("");
//            return;
//        }
//        logger.info("AuthProcessor do next");
//        chain.process(vanguardHttpContext);
//    }
}
