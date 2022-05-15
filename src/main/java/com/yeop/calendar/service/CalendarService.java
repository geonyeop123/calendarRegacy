package com.yeop.calendar.service;

import com.yeop.calendar.domain.CalendarVO;
import org.springframework.transaction.annotation.Transactional;

public interface CalendarService {
    @Transactional(rollbackFor = Exception.class)
    CalendarVO proc(CalendarVO vo) throws Exception;
}
