package com.sunmnet.bigdata.task.controller;

import com.sunmnet.bigdata.task.service.IStudentInfoService;
import com.sunmnet.bigdata.task.vo.PageRequest;
import com.sunmnet.bigdata.task.vo.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cxs")
public class TestController {
    @Autowired
    private IStudentInfoService studentInfoService;

    @GetMapping("test")
    public PageResponse<String> test(PageRequest pageRequest){
        PageResponse<String> student = studentInfoService.getStudent(pageRequest);
        return student;
    }

}
