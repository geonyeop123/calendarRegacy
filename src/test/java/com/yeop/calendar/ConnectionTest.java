package com.yeop.calendar;

import com.yeop.calendar.domain.CalendarVO;
import com.yeop.calendar.domain.CalendarDTO;
import com.yeop.calendar.persistence.CalendarDAO;
import com.yeop.calendar.service.CalendarService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"}
)
public class ConnectionTest {
    @Autowired
    SqlSession session;

    @Autowired
    CalendarDAO dao;

    @Autowired
    CalendarService service;

    @Test
    public void test(){
        System.out.println(session);
    }

    @Test
    public void selectTest()throws Exception{
        List<CalendarDTO> list;
        CalendarVO vo = new CalendarVO(2022, 05);
            list = dao.selectList(vo);

        System.out.println("list = " + list);
        CalendarDTO dto = new CalendarDTO();
        dto.setDate(LocalDate.of(2022,05,05));
        System.out.println("contains?" + list.contains(dto));
        System.out.println(list.get(list.indexOf(dto)));
    }

    @Test
    public void selectCountTest() throws Exception{
        int count = dao.selectCount(2022);
        System.out.println(count);
    }

    @Test
    public void createTest()throws Exception{
        CalendarDTO dto = null;
        List<CalendarDTO> list = new ArrayList<>();

//        dto = new CalendarDTO(2022, "어버이 날", LocalDate.of(2022, 5, 8));
//        System.out.println("dto = " + dto);
//        list.add(dto);
//        dto = new CalendarDTO(2022, "스승의 날", LocalDate.of(2022, 5, 5));
//        list.add(dto);
        System.out.println("list=" + list);
//        dao.create(list);
    }

    @Test
    public void procTest() throws Exception{
        CalendarVO vo = new CalendarVO(2024, 1);
        service.proc(vo);
        System.out.println("vo = " + vo);
    }
}
