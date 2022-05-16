package com.yeop.calendar.service;

import com.yeop.calendar.domain.CalendarMaker;
import com.yeop.calendar.domain.CalendarVO;
import com.yeop.calendar.domain.CalendarDTO;
import com.yeop.calendar.persistence.CalendarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarDAO dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CalendarVO proc(CalendarVO vo) throws Exception{
        /////
        // 선언
        /////
        List<CalendarDTO> calendarList = new ArrayList<>();
        List<CalendarDTO> holidayList = null;
        String holidayXML = null;
        int resultCnt = 0;
        CalendarMaker cm = null;
        CalendarDTO dto = null;
        LocalDate currentDate = null;

        /////
        // 로직
        /////

        // 해당 년도의 Holiday가 있는지 확인 없으면 HolidayAPI를 이용하여 DB에 값 적재
        if(dao.selectCount(vo.getYear()) < 1){
            // API를 이용하여 XML 받아오기
            holidayXML = getHoliday(vo.getYear());
            // 해당 XML을 parsing하여 holidayList 만들기
            holidayList = createHolidayList(holidayXML);

            // 해당 list를 DB에 insert 하기
            resultCnt = dao.create(holidayList);

            // 오류 발생 시 처리
            if(resultCnt < 0) throw new SQLException();
        }
        // 해당 월의 HolidayList 가져오기
        holidayList = dao.selectList(vo);

        // Calendar 생성

        // Calendar 생성을 위한 변수를 갖고 있는 CalendarMaker 생성
        cm = new CalendarMaker(vo);

        // 캘린더의 시작일부터, 마지막일까지 list에 넣기
        for(int i = 0; !cm.getLastDate().equals(currentDate);i++){
            dto = new CalendarDTO();
            // 현재(넣을) 일자를 1일씩 증가
            currentDate = cm.getStartDate().plusDays(i);
            // dto에 해당 일자 세팅
            dto.setDate(currentDate);
            // 해당 일자가 휴일 목록에 있는지 확인
            if(holidayList.contains(dto)){
                // 있으면, DTO를 해당 휴일 값으로 변경
                dto = holidayList.get(holidayList.indexOf(dto));
            }
            // dto를 리스트에 삽입
            calendarList.add(dto);
        }
        // 만들어진 리스트를 vo에 담기
        vo.setDateList(calendarList);

        /////
        // 반환
        /////
        return vo;
    }



    private String getHoliday(int year) throws Exception{
        /////
        // 선언
        /////
        StringBuilder sb = null;
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader br = null;
        String line = null;

        // API 호출
        sb = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        sb.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=HFZ7esAlPNwhEdbkFYSQSBpIpTrzsnPTiF%2FDS9WjP69UGXAvVaH8neE2AB1fLbvaTIjFEzPdzIr4236vzwyCYg%3D%3D"); /*Service Key*/
        sb.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(String.valueOf(year), "UTF-8")); /*연*/
        sb.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
        url = new URL(sb.toString());
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        // 에러코드 발생 시 에러 담기 아닐 시 결과 값 받기
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        // 반환 값을 위한 StringBuilder 초기화
        sb.setLength(0);

        // 결과 값 StringBuilder에 담기
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();

        /////
        // 반환
        /////
        return sb.toString();
    }

    private List<CalendarDTO> createHolidayList(String xml) throws Exception{
        /////
        // 선언
        /////
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder db = null;
        Document document = null;
        NodeList list = null;
        Node item = null;
        Element element = null;
        CalendarDTO dto = null;
        List<CalendarDTO> holidayList = null;

        /////
        // 로직
        /////
        System.out.println("xml = " + xml);
        // 받아온 XML parsing을 위한 세팅
        dbFactory = DocumentBuilderFactory.newInstance();
        db = dbFactory.newDocumentBuilder();
        document = db.parse(new InputSource(new StringReader(xml)));

        // item 가져오기(item에 휴일의 대한 정보가 담겨있음)
        list = document.getElementsByTagName("item");

        // 휴일 정보를 담을 List 세팅
        holidayList = new ArrayList<>();
        // item 내의 dateName과 localDate 값 가져오기
        for(int i = 0; i < list.getLength(); i++) {
            item = list.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                element = (Element) item;
                
                dto = new CalendarDTO();
                // 받아온 item의 값으로 DTO값 세팅
                // 이름 값을 가져와 세팅
                dto.setName(element.getElementsByTagName("dateName").item(0).getTextContent());
                // Date를 가져올 때, yyyyMMdd 패턴으로 입력되어 있기 때문에, 포매터를 이용하여 LocalDate 생성 후 삽입
                dto.setDate(LocalDate.parse(element.getElementsByTagName("locdate").item(0).getTextContent(),
                        DateTimeFormatter.ofPattern("yyyyMMdd")));

                dto.setYear(dto.getDate().getYear());

                holidayList.add(dto);
            }
        }
        /////
        // 반환
        /////
        return holidayList;
    }
}
