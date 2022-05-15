package com.yeop.calendar.domain;


import java.time.LocalDate;

public class CalendarMaker {
    private int yoil;

    private LocalDate date;

    private int lastDayYoil;

    private LocalDate startDate;

    private LocalDate lastDate;

    public CalendarMaker(){}

    public CalendarMaker(CalendarVO vo){
        // Date 값 세팅
        date = LocalDate.of(vo.getYear(), vo.getMonth(), 1);
        // n년 n월 1일의 요일 값 세팅
        yoil = date.getDayOfWeek().getValue();
        // n년 n월의 전달의 마지막 일의 요일 세팅
        lastDayYoil = date.plusDays(date.lengthOfMonth() - 1).getDayOfWeek().getValue();
        // 시작일
        startDate = (yoil == 7) ? date : date.minusDays(yoil);
        // 종료일
        lastDate = lastDayYoil == 6 ? date.plusDays(date.lengthOfMonth() - 1)
                : date.plusDays(date.lengthOfMonth() - 1 + (lastDayYoil == 7 ? 6 : 6 - lastDayYoil));
    }

    public int getYoil() {
        return yoil;
    }

    public void setYoil(int yoil) {
        this.yoil = yoil;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLastDayYoil() {
        return lastDayYoil;
    }

    public void setLastDayYoil(int lastDayYoil) {
        this.lastDayYoil = lastDayYoil;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }



    @Override
    public String toString() {
        return "CalendarMaker{" +
                "yoil=" + yoil +
                ", date=" + date +
                ", lastDayYoil=" + lastDayYoil +
                ", startDate=" + startDate +
                ", lastDate=" + lastDate +
                '}';
    }
}