package com.bullet.sweet.daedalus.kern.processor.Wechat;

import com.bullet.sweet.daedalus.base.util.FormatUtil;
import com.bullet.sweet.daedalus.kern.processor.HttpAspectProcessor;
import com.bullet.sweet.daedalus.kern.processor.HttpContext;
import com.bullet.sweet.daedalus.kern.processor.HttpProcessor;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanlan on 16/11/25.
 */

public class EventMsgProcessor implements HttpAspectProcessor {

    private final static Logger logger = LoggerFactory.getLogger(EventMsgProcessor.class);

    private final static String MST_TYPE_EVENT = "event";
    private final static String NONE = "none";
    @Setter
    private String toUser;

    private Map<EventType, EventMsgWorker> workerMap;


    @PostConstruct
    public void init() {
        workerMap = new HashMap<>();
        this.register(EventType.subscribe, this::workForSubscribe);
        this.register(EventType.unsubscribe, this::workForUnsubscribe);
        this.register(EventType.location, this::workForLocation);
        this.register(EventType.click,this::workForClick);
        this.register(EventType.scan, this::workForScan);
        this.register(EventType.view, this::workForView);
    }


    @Override
    public void process(HttpContext ctx, HttpProcessor chain) {
        String rawString = ctx.getRawBody();
        logger.info("EventMsgProcessor try to process:{}", rawString);
        Map<String, String> rawMap = FormatUtil.xmlStr2Map(rawString);
        if (rawMap.get("ToUserName").equals(toUser) && rawMap.get("MsgType").equals(MST_TYPE_EVENT)) {
            String eventType = rawMap.get("Event");
            workerMap.get(EventType.valueOf(eventType)).work(this.createEventParam(rawMap));
        }
        chain.process(ctx);

    }

    public void register(EventType event, EventMsgWorker worker) {
        this.workerMap.put(event, worker);
    }


    //关注:手动/扫码
    private void workForSubscribe(EventMsgParam param) {
        String fromOpenId = param.getFromOpenId();
        if (param.getEventKey() != NONE) {

        }
        //// TODO: 16/11/28 save user

    }

    //取关
    private void workForUnsubscribe(EventMsgParam param) {
        String fromOpenId = param.getFromOpenId();
        //// TODO: 16/11/28  remove user
    }

    private void workForScan(EventMsgParam param) {
        String qrCode=param.getEventKey();
        String ticket=param.getTicket();

    }

    private void workForLocation(EventMsgParam param) {
        String latitude = param.getLatitude();
        String longitude = param.getLongitude();
        String precision = param.getPrecision();
        String location = String.format("%s,%s,%s", latitude, longitude, precision);
        //// TODO: 16/11/28 save location
    }

    private void workForClick(EventMsgParam param) {
        String eventKey = param.getEventKey();
        //// TODO: 16/11/28 concrete action
    }

    private void workForView(EventMsgParam param) {
        String url = param.getEventKey();  //设置的跳转url
    }


    private EventMsgParam createEventParam(Map<String, String> params) {
        EventMsgParam param = new EventMsgParam(params.get("FromUserName")
                , params.getOrDefault("EventKey", NONE)
                , params.getOrDefault("Ticket", NONE)
                , params.getOrDefault("Latitude", NONE)
                , params.getOrDefault("Longitude", NONE)
                , params.getOrDefault("Precision", NONE)
                , params.get("Event"));
        return param;
    }



    private enum EventType {
        subscribe, unsubscribe, scan, location, click, view
    }

    @Value
    @Builder
    public static class EventMsgParam {
        private String fromOpenId;
        private String eventKey;
        private String ticket;
        private String latitude;
        private String longitude;
        private String precision;
        private String event;
    }


}

