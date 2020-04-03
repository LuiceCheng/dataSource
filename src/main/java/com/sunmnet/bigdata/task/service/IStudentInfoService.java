package com.sunmnet.bigdata.task.service;


import java.util.List;

public interface IStudentInfoService {

    List<String> getStudent(int pageNum, int pageSize);
}
