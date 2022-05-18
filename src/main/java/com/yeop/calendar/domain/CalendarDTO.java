package com.yeop.calendar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

public class CalendarDTO {
    private int holidayIdx;
    private int year;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private boolean isHoliday;

    public CalendarDTO(){}

    public CalendarDTO(int year, String name, LocalDate date){
        this.year = year;
        this.name = name;
        this.date = date;
    }

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDate() {
        return date;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    @Override
    public String toString() {
        return "CalendarDTO{" +
                "holidayIdx=" + holidayIdx +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", isHoliday=" + isHoliday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarDTO that = (CalendarDTO) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
