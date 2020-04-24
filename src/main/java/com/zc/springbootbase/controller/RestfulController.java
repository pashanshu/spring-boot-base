package com.zc.springbootbase.controller;

import com.zc.springbootbase.bean.Programmer;
import com.zc.springbootbase.bean.ProgrammerPlus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : heibaiying
 * @description : restful 控制器
 */
@RestController
@RequestMapping("restful")
public class RestfulController {

    @RequestMapping("programmers")
    public List<Programmer> getProgrammers() {
        List<Programmer> programmers = new ArrayList<>();
        programmers.add(new Programmer("xiaoming", 12, 100000.00f, LocalDate.of(2019, Month.AUGUST, 2)));
        programmers.add(new Programmer("xiaohong", 23, 900000.00f, LocalDate.of(2013, Month.FEBRUARY, 2)));
        return programmers;
    }


    @RequestMapping("test")
    public ProgrammerPlus getProgrammer(){
        ProgrammerPlus p = new ProgrammerPlus("xiaoming", 12, 100000.00f, LocalDate.of(2019, Month.AUGUST, 2),"woman");
        return p;
    }
}
