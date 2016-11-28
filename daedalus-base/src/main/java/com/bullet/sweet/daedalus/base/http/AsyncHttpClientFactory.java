package com.bullet.sweet.daedalus.base.http;

import lombok.Setter;
import lombok.val;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;

import java.io.IOException;

/**
 * @author Zhanlan
 *
 */
public class AsyncHttpClientFactory {

    private static final int DEFAULT_MAX_TOTAL = 512;

    private static final int DEFAULT_MAX_PER_ROUTE = 64;

    @Setter
    private int maxTotal = DEFAULT_MAX_TOTAL;

    @Setter
    private int defultMaxPerRoute = DEFAULT_MAX_PER_ROUTE;

    private static final int DEFAULT_CONNECTION_TIMEOUT = 1000;

    private static final int DEFAULT_SOCKET_TIMEOUT = 1000;

    public CloseableHttpAsyncClient build() throws IOException {
        val ioReactor = new DefaultConnectingIOReactor(IOReactorConfig.custom().setSoKeepAlive(true).build());
        val cm = new PoolingNHttpClientConnectionManager(ioReactor);
        val defaultRequestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).build();
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(defultMaxPerRoute);
        return HttpAsyncClients.custom()
                .setThreadFactory(new BasicThreadFactory.Builder().namingPattern("AysncHttp-%d").build())
                .setConnectionManager(cm).setDefaultRequestConfig(defaultRequestConfig).build();
    }

}
