package com.yeop.calendar.persistence;

import com.yeop.calendar.domain.CalendarVO;
import com.yeop.calendar.domain.CalendarDTO;
import com.yeop.calendar.domain.HolidayAPIDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalendarDAOImpl implements CalendarDAO{

    @Autowired
    SqlSession sqlSession;

    private static final String namespace = "com.yeop.calendar.mapper.HolidayMapper.";

    @Override
    public CalendarDTO select() throws Exception {
        return sqlSession.selectOne(namespace + "select");
    }

    @Override
    public List<CalendarDTO> selectList(CalendarVO vo) throws Exception {
        return sqlSession.selectList(namespace + "selectList", vo);
    }

    @Override
    public int selectCount(int year) throws Exception {
        return sqlSession.selectOne(namespace + "count", year);
    }

    @Override
    public int create(List<HolidayAPIDTO> list) throws Exception {
        return sqlSession.insert(namespace + "createHoliday", list);
    }

}
