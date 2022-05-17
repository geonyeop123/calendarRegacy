package com.yeop.calendar.util;

import com.yeop.calendar.domain.CalendarDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    public List<Map<String, String>> xmlToList(String xml, Map<String, String> fieldMap, String startPoint) throws Exception{
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder db = null;
        Document document = null;
        NodeList rootNodeList = null;
        NodeList childNodeList = null;
        Node item = null;
        ArrayList<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;

        dbFactory = DocumentBuilderFactory.newInstance();
        db = dbFactory.newDocumentBuilder();
        document = db.parse(new InputSource(new StringReader(xml)));

        // root Element 얻기
        rootNodeList = document.getElementsByTagName(startPoint);


        for(int i = 0; i < rootNodeList.getLength(); i++){
            item = rootNodeList.item(i);
            childNodeList = item.getChildNodes();
            map = new HashMap<String, String>();
            for(int j = 0; j < childNodeList.getLength(); j++){
                item = childNodeList.item(j);
                if(item.getNodeType() == Node.ELEMENT_NODE && fieldMap.containsKey(item.getNodeName())){
                    map.put(fieldMap.get(item.getNodeName()), item.getTextContent());
                }
            }
            list.add(map);
        }
        return list;
    }
}
