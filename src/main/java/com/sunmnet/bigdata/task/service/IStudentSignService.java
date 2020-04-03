package com.sunmnet.bigdata.task.service;

import com.sunmnet.bigdata.task.entity.StudentSign;
import com.sunmnet.bigdata.task.vo.CheckInRecord;

import java.util.List;

public interface IStudentSignService {
    List<StudentSign> getRecord();

    int batchInsertSelective(List<CheckInRecord> records);
}
