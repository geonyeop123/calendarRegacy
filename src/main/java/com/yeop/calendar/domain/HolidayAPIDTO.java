package com.yeop.calendar.domain;

import java.time.LocalDate;

public class HolidayAPIDTO {
    private String dateName;
    private boolean isHoliday;
    private LocalDate locdate;

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public LocalDate getLocdate() {
        return locdate;
    }

    public void setLocdate(LocalDate locdate) {
        this.locdate = locdate;
    }

    @Override
    public String toString() {
        return "HolidayAPIDTO{" +
                "dateName='" + dateName + '\'' +
                ", isHoliday=" + isHoliday +
                ", locdate=" + locdate +
                '}';
    }
}
