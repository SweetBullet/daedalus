package com.bullet.sweet.daedalus.kern;

import com.bullet.sweet.daedalus.base.Constants;
import com.google.gson.Gson;
import lombok.Setter;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanlan on 16/11/28.
 */
public class WechatApi {

    private final static Logger logger = LoggerFactory.getLogger(WechatApi.class);

    private Gson gson = new Gson();
    @Setter
    private CloseableHttpClient httpClient;

    //发送客服消息
    public void sendMsg(String accessToken, String toUser, String msgType, String... content) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s", accessToken);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
        Map<String, Object> request = new HashMap<>(3);
        request.put("touser", toUser);
        request.put("msgtype", msgType);
        Map<String, Object> inner = new HashMap<>(5);
        switch (NormalMsgType.valueOf(msgType)) {
            case text:
                inner.put("content", content);
                break;
            case image:
                inner.put("media_id", content);
                break;
            case voice:
                inner.put("media_id", content);
                break;
            case video:
                inner.put("media_id", content[0]);
                inner.put("thumb_media_id", content[1]);
                inner.put("title", content[2]);
                inner.put("description", content[3]);
                break;
            case music:
                inner.put("title", content[0]);
                inner.put("description", content[1]);
                inner.put("musicurl", content[2]);
                inner.put("hqmusicurl", content[3]);
                inner.put("thumb_media_id", content[4]);
                break;
            case news:
                break;
            case mpnews:
                inner.put("media_id", content);
                break;
            case wxcard:
                inner.put("card_id", content[0]);
                inner.put("card_ext", content[1]);
                break;
            default:
                break;
        }

        request.put(msgType, inner);
        String requestString = gson.toJson(request, Map.class);
        StringEntity entity = new StringEntity(requestString, Constants.DEFAULT_CHARSET);
        httpPost.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            if (response.getStatusLine().getStatusCode() == Constants.HTTP_SUCCESS_CODE) {
                String respEntity = EntityUtils.toString(response.getEntity());
                logger.info("resp:{}", respEntity);
                return;
            }
            logger.info("unexpected get access token error with result:{}", response);
        } catch (Exception ex) {
            logger.error("unexpected error:", ex);
        }
    }


    private enum NormalMsgType {
        text, image, voice, video, music, news, mpnews, wxcard
    }

}

