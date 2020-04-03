package com.sunmnet.bigdata.task.controller;

import com.sunmnet.bigdata.task.service.IStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cxs")
public class TestController {
    @Autowired
    private IStudentInfoService studentInfoService;

    @GetMapping("test")
    public List<String> test(){
        List<String> student = studentInfoService.getStudent(0, 1);
        return student;
    }

}
