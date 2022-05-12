package com.yeop.calendar.controller;

import com.yeop.calendar.domain.CalendarMaker;
import com.yeop.calendar.domain.CalendarVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CalendarController {

    @RequestMapping("/calendar")
    public String home(CalendarVO vo, Model m){
        /////
        // 선언
        /////
        LocalDate date;

        /////
        // 유효성 검사
        /////

        // 값이 없거나, month가 0~12가 아닌 경우 현재의 일자로 세팅
        if((vo.getYear() == null || vo.getMonth() == null) || (0 > vo.getMonth()) || ( 12 < vo.getMonth())){
            date = LocalDate.now();
            vo = new CalendarVO(date.getYear(), date.getMonth().getValue());
        }

        /////
        // 반환
        /////
        m.addAttribute(vo);
        return "calendar";
    }

    @ResponseBody
    @PostMapping("/proc")
    public CalendarVO proc(@RequestBody CalendarVO vo) throws Exception{

        CalendarMaker cm = null;
        /////
        // 유효성 검사
        /////

        // 값이 없거나 입력된 달이 0~12이 아닌 경우 에러 발생시키기
        if(vo.getYear() == null || vo.getMonth() == null){
            throw new IOException("잘못된 접근");
        }else if((0 > vo.getMonth()) || ( 12 < vo.getMonth())){
            throw new IOException("존재하지 않는 달");
        }

        /////
        // 로직
        /////
        cm = new CalendarMaker(vo);
        vo.setDateList(cm.createDateList());
        vo.setHolidayList(cm.createHolidayList());

        /////
        // 반환
        /////
        return vo;
    }

}