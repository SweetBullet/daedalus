package com.bullet.sweet.daedalus.kern;

import com.bullet.sweet.daedalus.kern.router.RouterInitializer;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*****************************************************
 * Created by zhanlan on 16/11/20.
 * If there are more than one service in this project,
 * it can choose which one to start-up
 * (use in deploying)
 *****************************************************
 */
public class ApplicationInitializer implements ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

    private ApplicationContext applicationContext;

    @Setter
    private boolean can;

    public void init() {
        if (can) {
            logger.info("init of RouterInitializer");
            applicationContext.getBean(RouterInitializer.class).init();
            logger.info("router start!");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
