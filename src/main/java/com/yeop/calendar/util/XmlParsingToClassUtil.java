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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParsingToClassUtil {
    private String xml;
    private Class clazz;
    private Map<String, String> map;

    public XmlParsingToClassUtil(){}
    public XmlParsingToClassUtil(String xml, Class clazz){
        this.xml = xml;
        this.clazz = clazz;
    }

    public Map<String, String> getMap(String xml) throws Exception{
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder db = null;
        Document document = null;
        NodeList list = null;
        Node item = null;
        Element element = null;
        CalendarDTO dto = null;
        List<CalendarDTO> holidayList = null;

        dbFactory = DocumentBuilderFactory.newInstance();
        db = dbFactory.newDocumentBuilder();
        document = db.parse(new InputSource(new StringReader(xml)));
        System.out.println(xml);
        element = document.getDocumentElement();
        list = element.getChildNodes();

        for(int i = 0; i < list.getLength(); i++){
            item = list.item(i);
            System.out.println("item name =" + item.getNodeName());
        }
        map = new HashMap<String, String>();


        return map;
    }
}
