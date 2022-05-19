package com.yeop.calendar.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParsingToClassUtil {

    // xml을 받아 Map을 담은 List로 반환하는 메서드
    public List<Map<String, String>> xmlToList(String xml, Map<String, String> fieldMap, String startPoint) throws Exception{

        /////
        // 선언
        /////
        NodeList rootNodeList = null;
        NodeList childNodeList = null;
        Map<String, String> dataMap = null;
        ArrayList<Map<String, String>> list = new ArrayList<>();

        /////
        // 로직
        /////

        // Point 지점의 NodeList 획득
        rootNodeList = getNodeList(xml, startPoint);

        // List 얻기
        for(int i = 0; i < rootNodeList.getLength(); i++){
            // item을 돌면서 Map 획득
            childNodeList = rootNodeList.item(i).getChildNodes();
            dataMap = getMap(childNodeList, fieldMap);
            list.add(dataMap);
        }

        /////
        // 반환
        /////
        return list;
    }

    // xml을 받아 Map으로 반환하는 메서드
    public Map<String, String> xmlToMap(String xml, Map<String, String> fieldMap, String startPoint) throws Exception{
        /////
        // 선언
        /////

        NodeList rootNodeList = null;
        NodeList childNodeList = null;
        Map<String, String> dataMap = null;

        /////
        // 로직
        /////

        // Point 지점의 NodeList 획득
        rootNodeList = getNodeList(xml, startPoint);
        // 해당 지점의 첫번째 노드 획득
        childNodeList = rootNodeList.item(0).getChildNodes();

        /////
        // 반환
        /////
        return getMap(childNodeList, fieldMap);
    }

    // 들어온 NodeList를 fieldMap에 맞게 Map으로 Parsing하는 메서드
    private Map<String, String> getMap(NodeList nodeList, Map<String, String> fieldMap){
        /////
        // 선언
        /////
        Node item = null;
        Map<String, String> dataMap = new HashMap<>();

        /////
        // 로직
        /////
        for(int j = 0; j < nodeList.getLength(); j++){
            // 들어온 nodeList의 item를 한개씩 꺼내옴
            item = nodeList.item(j);
            // 해당 item이 Node이면서, fieldMap에 포함한 필드인지 확인 후 Map에 담기
            if(item.getNodeType() == Node.ELEMENT_NODE && fieldMap.containsKey(item.getNodeName())){
                dataMap.put(fieldMap.get(item.getNodeName()), item.getTextContent());
            }
        }

        /////
        // 반환
        /////
        return dataMap;
    }

    // Point지점의 Node를 가져오는 메서드
    private NodeList getNodeList(String xml, String startPoint) throws Exception{

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
}
