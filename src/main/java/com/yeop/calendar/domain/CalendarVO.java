package com.yeop.calendar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class CalendarVO {
    private Integer year;
    private Integer month;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> dateList;
    private List<CalendarDate> holidayList;

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

    public List<CalendarDate> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<CalendarDate> holidayList) {
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