package com.yeop.calendar.persistence;

import com.yeop.calendar.domain.HolidayDTO;

public interface CalendarDAO {
    HolidayDTO select() throws Exception;
}
