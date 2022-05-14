package com.yeop.calendar.persistence;

import com.yeop.calendar.domain.HolidayDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDAOImpl implements CalendarDAO{

    @Autowired
    SqlSession sqlSession;

    private static final String namespace = "com.yeop.calendar.mapper.HolidayMapper.";

    @Override
    public HolidayDTO select() throws Exception {
        return sqlSession.selectOne(namespace + "select");
    }
}
