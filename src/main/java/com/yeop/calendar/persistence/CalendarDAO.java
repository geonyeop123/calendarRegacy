package com.yeop.calendar.persistence;

import com.yeop.calendar.domain.CalendarVO;
import com.yeop.calendar.domain.CalendarDTO;

import java.util.List;

public interface CalendarDAO {
    CalendarDTO select() throws Exception;

    List<CalendarDTO> selectList(CalendarVO vo) throws Exception;

    int selectCount(int year) throws Exception;

    int create(List<CalendarDTO> list) throws Exception;
}
