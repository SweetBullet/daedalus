package com.bullet.sweet.daedalus.base.http;


import com.bullet.sweet.daedalus.base.Constants;
import lombok.val;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Zhanlan
 */
public class HttpClientFactory {

    private static final int DEFAULT_MAX_TOTAL = 512;

    private static final int DEFAULT_MAX_PER_ROUTE = 64;

    private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;

    private static final int DEFAULT_SOCKET_TIMEOUT = 5000;

    public CloseableHttpClient build() {
        val config = ConnectionConfig.custom().setCharset(Constants.DEFAULT_CHARSET).build();
        val defaultRequestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
            .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).build();
        return HttpClients.custom().setMaxConnPerRoute(DEFAULT_MAX_PER_ROUTE).setMaxConnTotal(DEFAULT_MAX_TOTAL)
            .setDefaultConnectionConfig(config).setDefaultRequestConfig(defaultRequestConfig).build();
    }
}
