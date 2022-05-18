<%--
  Created by IntelliJ IDEA.
  User: duq14
  Date: 2022-05-04
  Time: 오후 6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calendar</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/reset.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div class="main_container">
    <div class="title">
        <div><button class="prev_btn">&lt</button></div>
        <div>
            <h2><span class="title_year">${calendarVO.year}</span>년 <span class="title_month">${calendarVO.month}</span>월</h2>
        </div>
        <div><button class="next_btn">&gt</button></div>
    </div>
    <table class="calendar_table">
        <thead>
        <tr>
            <th scope="cols">일</th>
            <th scope="cols">월</th>
            <th scope="cols">화</th>
            <th scope="cols">수</th>
            <th scope="cols">목</th>
            <th scope="cols">금</th>
            <th scope="cols">토</th>
        </tr>
        </thead>
    </table>
</div>
<script>
    $(function(){
        /////
        // 변수 선언
        /////
        let tbody;
        let dateJSON = {};
        let year = parseInt('${calendarVO.year}');
        let month = parseInt('${calendarVO.month}');
        let todayYear = parseInt('${calendarVO.todayYear}');
        let listMonth = 0;
        let listDay = 0;
        let listName = null;
        let isHoliday = false;
        let tbodyHTML = "";

        // calendar를 만드는 ajax 함수
        let makeCalendar = function(year, month){
            dateJSON.year = year;
            dateJSON.month = month;
            $.ajax({
                type : "POST",
                url : "proc" ,
                headers : { "content-type": "application/json"},
                data : JSON.stringify(dateJSON),
                success : function(result){
                    drawCalendar(result);
                },
                error: function(){
                    alert("알 수 없는 에러가 발생했습니다!!");
                    location.href='error.jsp';
                },
            })
        }
        // calendar를 그리는 함수
        let drawCalendar = function(vo){
            let holiday = {};
            // 현재 그려진 tbody가 있는지 체크, 있으면 지움
            tbody = $('.calendar_body');
            if(tbody != null) tbody.remove();

            // 그릴 tbody 생성
            tbodyHTML = "<tbody class='calendar_body'>";

            // Holiday 객체 생성
            // key = date
            // value = name

            $.each(vo.dateList, function(index, item){
                listMonth = parseInt(item.date.substring(5,7));
                listDay = item.date.substring(8,10);
                listName = item.name;
                isHoliday = item.holiday;
                // 최초와 7번째마다 tr태그로 구분
                if( index == 0 || index % 7 == 0) tbodyHTML += "<tr>";
                // 표시 해줄 일자의 달이 현재 달과 다를 경우 클래스 추가
                if(listMonth != month){
                    tbodyHTML += "<td class='anotherMonth'><p>"+listDay+"</p></td>";
                }else{
                    // 휴일의 경우, name 값을 가지고 있으며, 그럴 경우 휴일 표시
                    if(isHoliday){
                        tbodyHTML += "<td class='holiday'><p>"+listDay+"</p><p class='holiday_name'>"+listName+"</p></td>";
                    }else{
                        tbodyHTML += "<td><p>"+listDay+"</p></td>";
                    }
                }
            });

            // tbody를 table에 삽입
            tbodyHTML += "</tbody>";
            $('table').append(tbodyHTML);

            // calendar의 타이틀 변경
            $('.title_year').html(vo.year);
            $('.title_month').html(vo.month);
        }

        // button을 클릭 시 수행될 함수
        let buttonFunction = function(mode){
            // 법정 공휴일 API가 현재년도 기준 다음년까지만 제공하므로, 그럴 시 막기
            if((year == todayYear+1) && month == 12){
                alert(year + "년 12월까지만 조회가 가능합니다.");
                return;
            }
            // mode에 따라 분기 처리
            month = mode == 'N' ? month + 1 : month - 1;
            if(month > 12){
                month = 1;
                year++;
            }else if(month < 1){
                month = 12;
                year--;
            }
            makeCalendar(year, month);
        }

        /////
        // 이벤트
        /////

        $('.next_btn').on("click", ()=> buttonFunction('N'));

        $('.prev_btn').on("click", ()=> buttonFunction('P'));

        // 최초 접속 시 캘린더를 그리기 위해 ajax 호출
        makeCalendar(year, month);
    })
</script>
</body>
</html>
