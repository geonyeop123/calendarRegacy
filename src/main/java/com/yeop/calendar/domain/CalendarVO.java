package com.yeop.calendar.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CalendarVO {
    private Integer year;
    private Integer month;
    private List<LocalDate> dateList;
    private List<Holiday> holidayList;

    public CalendarVO(){}

    public CalendarVO(Integer year, Integer month){
        this.year = year;
        this.month = month;
    }



    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<LocalDate> getDateList() {
        return dateList;
    }

    public List<Holiday> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<Holiday> holidayList) {
        this.holidayList = holidayList;
    }

    public void setDateList(List<LocalDate> dateList) {
        this.dateList = dateList;
    }

    @Override
    public String toString() {
        return "CalendarVO{" +
                "year=" + year +
                ", month=" + month +
                ", dateList=" + dateList +
                ", holidayList=" + holidayList +
                '}';
    }
}