package com.bullet.sweet.daedalus.deploy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhanlan on 16/11/23.
 */
public class ApplicationLauncher {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationLauncher.class);

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:*/spring-daedalus.xml"});
        context.start();
        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
