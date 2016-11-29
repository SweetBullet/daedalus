package com.bullet.sweet.daedalus.kern;

import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanlan on 16/11/28.
 */
public class WechatApiTest {

    private final static Logger logger = LoggerFactory.getLogger(WechatApiTest.class);

    @Test
    public void testSendMsg() {
        this.send("nihao", "hello");
        Map<String, Object> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        Map<String, String> map1 = new HashMap<>();
        map1.put("3", "c");
        map.put("4", map1);
        logger.info(map2JsonString(map));
    }

    private void send(String... content) {
        logger.info("content:{}", content);
    }

    private String map2JsonString(Map<String, Object> map) {
        Gson gson = new Gson();
        return gson.toJson(map, Map.class);
    }


    @Test
    public void test() {
        int i = 65535;
        byte[] b = new byte[4];
        b[3] = (byte) (0xff & (i >>> 0));
        b[2] = (byte) (0xff & (i >>> 8));
        b[1] = (byte) (0xff & (i >>> 16));
        b[0] = (byte) (0xff & (i >>> 24));
        for (int j = 0; j < 4; j++) {
            System.out.println(b[j]);
        }
        int i1 = b[3] + (b[2] << 8) + (b[1] << 16) + (b[0] << 24);
        System.out.println(i1);


    }

}
