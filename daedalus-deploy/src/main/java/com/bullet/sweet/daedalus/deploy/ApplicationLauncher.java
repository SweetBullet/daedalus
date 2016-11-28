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
        logger.info("main method");
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    new String[]{"classpath:spring/spring-daedalus.xml"});
            context.start();
            logger.info("start!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
