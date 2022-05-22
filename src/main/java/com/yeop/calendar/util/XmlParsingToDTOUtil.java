package com.yeop.calendar.util;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class XmlParsingToDTOUtil {

    // xml을 받아 DTO를 반환하는 메서드
    public static <T> T xmlToDto(String xml, Class<?> clazz, String startPoint) throws Exception{
        /////
        // 선언
        /////
        Constructor constructor = null;

        constructor = clazz.getDeclaredConstructor();
        if(!constructor.isAccessible()) constructor.setAccessible(true);

        return  (T) getDTO(getNodeList(xml, startPoint).item(0).getChildNodes()
                            , constructor.newInstance()
                            , clazz);
    }

    // xml을 받아 DTO를 담은 List로 반환하는 메서드
    public static List xmlToDtoList(String xml, Class<?> clazz, String startPoint) throws Exception{
        /////
        // 선언
        /////
        NodeList rootNodeList = null;
        NodeList childNodeList = null;
        Object dto = null;
        Constructor constructor = null;
        ArrayList list = new ArrayList<>();

        /////
        // 로직
        /////

        // constructor 세팅(기본 생성자)
        constructor = clazz.getDeclaredConstructor();
        if(!constructor.isAccessible()) constructor.setAccessible(true);

        // Point 지점의 NodeList 획득
        rootNodeList = getNodeList(xml, startPoint);

        // List 얻기
        for(int i = 0; i < rootNodeList.getLength(); i++){
            // item을 돌면서 NodeList 획득
            childNodeList = rootNodeList.item(i).getChildNodes();
            // 해당 NodeList로 DTO 생성
            dto = getDTO(childNodeList, constructor.newInstance(), clazz);
            // 생성된 dto를 list에 추가
            list.add(dto);
        }

        /////
        // 반환
        /////
        return list;
    }

    // NodeList를 받아 DTO를 생성하는 메서드
    public static Object getDTO(NodeList childNodeList, Object dto, Class<?> clazz) throws Exception{
        /////
        // 선언
        /////
        Method method = null;
        Field field = null;
        Class<?> fieldType = null;
        Node item = null;

        for(int i = 0; i < childNodeList.getLength(); i++){
            // 들어온 nodeList의 item를 한개씩 꺼내옴
            item = childNodeList.item(i);
            // 해당 item이 Node인지 확인
            if(item.getNodeType() == Node.ELEMENT_NODE){
                try{
                    // 해당 Node의 이름으로 field 불러오기
                    field = clazz.getDeclaredField(item.getNodeName());
                    // 없다면, for문 통과
                }catch(NoSuchFieldException ne){
                    continue;
                }
                // fieldType 가져오기
                fieldType = field.getType();
                // 가져온 field를 set + FieldName으로 변경 후 메서드 가져오기
                method = clazz.getDeclaredMethod(getSetterName(field.getName()), fieldType);
                // item의 값을 해당 field의 type으로 변경 후 해당 메서드 호출
                method.invoke(dto, convertTo(item.getTextContent(), fieldType));
            }
        }

        /////
        // 반환
        /////
        return clazz.cast(dto);
    }

    // Point지점의 Node를 가져오는 메서드
    private static NodeList getNodeList(String xml, String startPoint) throws Exception{

        /////
        // 선언
        /////
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder db = null;
        Document document = null;

        /////
        // 로직
        /////
        dbFactory = DocumentBuilderFactory.newInstance();
        db = dbFactory.newDocumentBuilder();
        document = db.parse(new InputSource(new StringReader(xml)));
        document.normalize();

        /////
        // 반환
        /////
        return document.getElementsByTagName(startPoint);
    }

    // fieldName을 받아 해당 setter 메서드로 변환해주는 함수
    private static String getSetterName(String fieldName){
        return "set" + StringUtils.capitalize(fieldName);
    }

    // XML에서 받아온 Node의 값(String)을 메서드의 반환 타입에 맞게 변환해주는 함수
    private static Object convertTo(String value, Class<?> type){
        if(value == null || type == null || type.isInstance(value)){
            return value;
        }else if(type == Integer.class){
            return Integer.valueOf(value);
        }else if(type == Character.class){
            return Character.valueOf(value.charAt(0));
        }else if(type == Double.class){
            return Double.valueOf(value);
        }else if(type == Long.class){
            return Long.valueOf(value);
        }else if(type == BigDecimal.class){
            return BigDecimal.valueOf(Long.valueOf(value));
        }else if(type == Short.class){
            return Short.valueOf(value);
        }else if(type == Float.class) {
            return Float.valueOf(value);
        // isHoliday의 경우 Y / N으로 들어오기 때문에 별도 처리
        } else if(type == boolean.class){
            return value.equals("Y") ? true : false;
        // locDate의 경우 yyyyMMdd 형식으로 들어오기 때문에 별도 처리
        }else if(type == LocalDate.class){
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        return value;
    }
}
