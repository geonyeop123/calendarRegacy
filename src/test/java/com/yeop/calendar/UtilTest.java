package com.yeop.calendar;


import com.yeop.calendar.domain.CalendarDTO;
import com.yeop.calendar.util.XmlParsingToClassUtil;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilTest {

    @Test
    public void test(){
        Class clazz = CalendarDTO.class;
        System.out.println("clazz = " + clazz);
        Method[] method = clazz.getDeclaredMethods();
        for(int i = 0; i < method.length; i++){
            if(method[i].getName().startsWith("set")) System.out.println(method[i].getName());
        }
    }

    @Test
    public void test1() throws Exception{
        List<Map<String, String>> list = null;
        XmlParsingToClassUtil parsing = new XmlParsingToClassUtil();
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("dateName", "name");
        fieldMap.put("locdate", "date");
        list = parsing.xmlToList("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<response>\n" +
                    "<header>\n" +
                        "<resultCode>00</resultCode>\n" +
                        "<resultMsg>NORMAL SERVICE.</resultMsg>\n" +
                    "</header>\n" +
                    "<body>\n" +
                        "<items>\n" +
                            "<item>\n" +
                                "<dateKind>01</dateKind>\n" +
                                "<dateName>신정</dateName>\n" +
                                "<isHoliday>Y</isHoliday>\n" +
                                "<locdate>20150101</locdate>\n" +
                                "<seq>1</seq>\n" +
                            "</item>\n" +
                            "<item>\n" +
                                "<dateKind>01</dateKind>\n" +
                                "<dateName>설날</dateName>\n" +
                                "<isHoliday>Y</isHoliday>\n" +
                                "<locdate>20150218</locdate>\n" +
                                "<seq>1</seq>\n" +
                            "</item>\n" +
                            "<item>\n" +
                                "<dateKind>01</dateKind>\n" +
                                "<dateName>설날</dateName>\n" +
                                "<isHoliday>Y</isHoliday>\n" +
                                "<locdate>20150219</locdate>\n" +
                                "<seq>1</seq>\n" +
                            "</item>\n" +
                            "<item>\n" +
                                "<dateKind>01</dateKind>\n" +
                                "<dateName>설날</dateName>\n" +
                                "<isHoliday>Y</isHoliday>\n" +
                                "<locdate>20150220</locdate>\n" +
                                "<seq>1</seq>\n" +
                            "</item>\n" +
                        "</items>\n" +
                    "<numOfRows>10</numOfRows>\n" +
                    "<pageNo>1</pageNo>\n" +
                    "<totalCount>16</totalCount>\n" +
                "</body>\n" +
            "</response>", fieldMap, "item");
        System.out.println(list);

    }
}
