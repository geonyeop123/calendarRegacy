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

    public List<Map<String, String>> getList(String xml, Map<String, String> fieldMap) throws Exception{
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder db = null;
        Document document = null;
        NodeList currentList = null;
        NodeList nextList = null;
        Node item = null;
        Element element = null;
        CalendarDTO dto = null;
        List<CalendarDTO> holidayList = null;
        ArrayList<Map<String, String>> list = new ArrayList<>();
        Map currentMap = null;
        Map rootMap = null;
        Map nextMap = null;

        dbFactory = DocumentBuilderFactory.newInstance();
        db = dbFactory.newDocumentBuilder();
        document = db.parse(new InputSource(new StringReader(xml)));

        // root Element 얻기
        element = document.getDocumentElement();

        item = element.cloneNode(true);
        if(item.hasChildNodes()){
            currentMap = new HashMap();
            nextMap = new HashMap();
            currentMap.put(item.getNodeName(), nextMap);
            rootMap = currentMap;
            currentList = item.getChildNodes();
            
        }

        // {{response = {getChildNode}}}

//        for(int i = 0; i < currentList.getLength(); i++){
//            item = currentList.item(i);
//            nextList = item.getChildNodes();
//            map = new HashMap<String, String>();
//            for(int j = 0; j < nextList.getLength(); j++){
//                item = nextList.item(j);
//                if(item.getNodeType() == Node.ELEMENT_NODE && fieldMap.containsKey(item.getNodeName())){
//                    map.put(fieldMap.get(item.getNodeName()), item.getTextContent());
//                }
//            }
//            list.add(map);
//        }
        return list;
    }
}
