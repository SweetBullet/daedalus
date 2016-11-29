package com.bullet.sweet.daedalus.kern.mapper;

import com.baidu.unbiz.easymapper.MapperFactory;
import com.baidu.unbiz.easymapper.util.SystemPropertyUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pudongxu on 16/11/25.
 */
public class MapperTest {

    private final static Logger logger = LoggerFactory.getLogger(MapperTest.class);

    @Before
    public void init() {
        System.setProperty(SystemPropertyUtil.ENABLE_WRITE_SOURCE_FILE, "true");
        System.setProperty(SystemPropertyUtil.ENABLE_WRITE_CLASS_FILE, "true");
    }

    @Test
    public void testMapper() {
        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(11);
        People people = new People();
        MapperFactory.getCopyByRefMapper()
                .mapClass(Person.class, People.class)
                .field("name","trickName").field("age","realAge")
                .registerAndMap(person, people);
        logger.info("person:{}", person);
        logger.info("people:{}", people);

    }


//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public class Person {
//        private String name;
//        private int age;
//    }
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public class People {
//        private String trickName;
//        private int realAge;
//    }

}

