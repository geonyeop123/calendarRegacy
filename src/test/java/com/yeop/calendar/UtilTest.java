package com.yeop.calendar;


import com.yeop.calendar.domain.CalendarDTO;
import com.yeop.calendar.domain.HolidayAPIDTO;
import com.yeop.calendar.util.XmlParsingToDTOUtil;
import org.junit.Test;

import java.lang.reflect.Method;

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
    public void utilTest123() throws Exception{
        HolidayAPIDTO dto = XmlParsingToDTOUtil.xmlToDto("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                "</response>", HolidayAPIDTO.class, "item");
        System.out.println(dto);

    }
    
}
