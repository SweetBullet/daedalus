package com.bullet.sweet.daedalus.kern.processor.Wechat;


/**
 * Created by Zhanlan on 16/11/25.
 */
public interface EventMsgWorker {
    void work(EventMsgProcessor.EventMsgParam params);
}
