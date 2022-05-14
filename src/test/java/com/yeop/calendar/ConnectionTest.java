package com.yeop.calendar;

import com.yeop.calendar.domain.HolidayDTO;
import com.yeop.calendar.persistence.CalendarDAO;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"}
)
public class ConnectionTest {
    @Autowired
    SqlSession session;

    @Autowired
    CalendarDAO dao;

    @Test
    public void test(){
        System.out.println(session);
    }

    @Test
    public void selectTest()throws Exception{
        HolidayDTO dto = dao.select();
        System.out.println(dto);
    }
}
