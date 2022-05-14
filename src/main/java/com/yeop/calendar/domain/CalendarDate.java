package com.yeop.calendar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CalendarDate {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String name;

    CalendarDate(){}

    CalendarDate(LocalDate date, String name){
        this.date = date;
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CalendarDate{" +
                "date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}