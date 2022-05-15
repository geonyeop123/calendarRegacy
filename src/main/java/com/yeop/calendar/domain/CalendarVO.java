package com.yeop.calendar.domain;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class CalendarVO {
    private Integer year;
    private Integer month;

    private List<CalendarDTO> dateList;


    private int todayYear;

    public CalendarVO(){}

    public CalendarVO(int year, int month){
        this.todayYear = LocalDate.now().getYear();
        this.year = year;
        this.month = month;
    }

    public CalendarVO(LocalDate today){
        this.todayYear = today.getYear();
        this.year = today.getYear();
        this.month = today.getMonth().getValue();
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

    public List<CalendarDTO> getDateList() {
        return dateList;
    }

    public void setDateList(List<CalendarDTO> dateList) {
        this.dateList = dateList;
    }


    public int getTodayYear() {
        return todayYear;
    }


    public void setTodayYear(int todayYear) {
        this.todayYear = todayYear;
    }

    @Override
    public String toString() {
        return "CalendarVO{" +
                "year=" + year +
                ", month=" + month +
                ", dateList=" + dateList +
                ", todayYear=" + todayYear +
                '}';
    }
}