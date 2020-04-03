package com.sunmnet.bigdata.task.service;


import com.sunmnet.bigdata.task.vo.PageRequest;
import com.sunmnet.bigdata.task.vo.PageResponse;

public interface IStudentInfoService {

    PageResponse<String> getStudent(PageRequest pageRequest);
}
