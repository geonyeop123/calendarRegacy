package com.yeop.calendar.controller;

import com.yeop.calendar.domain.CalendarVO;
import com.yeop.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CalendarController {

    @Autowired
    CalendarService service;

    @RequestMapping("/calendar")
    public String home(Model m){
        /////
        // 반환
        /////

        // VO에 현재일을 담아서 Model에 전달
        m.addAttribute(new CalendarVO(LocalDate.now()));

        return "calendar";
    }

    @ResponseBody
    @PostMapping("/proc")
    public CalendarVO proc(@RequestBody CalendarVO vo) throws Exception{
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
        // 반환
        /////
        return service.proc(vo);
    }
}