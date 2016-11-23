package com.bullet.sweet.daedalus.kern.processor;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanlan on 16/11/21.
 */
@AllArgsConstructor
public class HttpContext {

    @Getter
    private final HttpServerRequest request;
    @Getter
    private final HttpServerResponse response;
    @Getter
    @Setter
    private final String rawBody;

    private final Map<String, Object> attributes = new HashMap<>();

    public <T> T getAttribute(String key ,Class<T> clazz) {
        if (attributes.get(key) == null) {
            return null;
        }
        return (T) attributes.get(key);
    }

    public void addAttribute(String key, Object val) {
        attributes.putIfAbsent(key, val);
    }

}
