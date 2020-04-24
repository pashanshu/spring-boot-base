package com.zc.springbootbase.controller;

import java.time.LocalDate;
import java.util.Date;

import com.zc.springbootbase.bean.Programmer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class BackendController {
    private HttpServletResponse response;

    @RequestMapping("/api")
    public String printDate(@RequestHeader(name = "user-name", required = false) String username) {
        if (username != null) {
            return new Date().toString() + " " + username;
        }
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return new Date().toString();
        return "hello";
    }


    @RequestMapping("/api2")
    public ResponseEntity<String> printDate2(@RequestHeader(name = "user-name", required = false) String username) {

        return new ResponseEntity("hello", HttpStatus.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));

    }

/**
 * @Description :
 * @Author: zc
 * @Date : 2020/4/9 16:44
*/
    @RequestMapping("/programmer")
    public Programmer getProgrammer() {
        Programmer programmer = new Programmer();
        programmer.setAge(20);
        programmer.setBirthday(LocalDate.now());
        programmer.setSalary(100.0f);
        programmer.setName("zhanchang");
        return programmer;
    }


}