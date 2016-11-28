package com.bullet.sweet.daedalus.base.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhanlan on 16/11/25.
 */
public class FormatUtil {
    private final static Logger logger = LoggerFactory.getLogger(FormatUtil.class);

    public static Map<String,String> xmlStr2Map(String msg) {
        Map<String, String> result = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(msg);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            elements.stream().forEach(element ->
                    result.put(element.getName(), element.getText())
            );
        } catch (Exception e) {
            logger.error("parse xml string to map error", e);
            return Collections.emptyMap();
        }
        return result;
    }
}
