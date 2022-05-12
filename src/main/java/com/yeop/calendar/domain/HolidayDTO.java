package com.yeop.calendar.domain;

import java.time.LocalDate;

public class HolidayDTO {
    private int holidayIdx;
    private int year;
    private String name;
    private LocalDate date;

    public int getHolidayIdx() {
        return holidayIdx;
    }

    public void setHolidayIdx(int holidayIdx) {
        this.holidayIdx = holidayIdx;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HolidayDTO{" +
                "holidayIdx=" + holidayIdx +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
