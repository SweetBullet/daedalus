package com.bullet.sweet.daedalus.kern.filter;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhanlan on 16/11/20.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestContext {

    private HttpServerResponse response;

    private HttpServerRequest request;

    private String body;

}
