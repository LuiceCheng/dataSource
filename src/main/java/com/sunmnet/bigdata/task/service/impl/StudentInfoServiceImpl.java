package com.sunmnet.bigdata.task.service.impl;

import com.github.pagehelper.PageHelper;
import com.sunmnet.bigdata.task.dao.master.StudentInfoDao;
import com.sunmnet.bigdata.task.service.IStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements IStudentInfoService {
    @Autowired
    private StudentInfoDao studentInfoDao;

    @Override
    public List<String> getStudent(int pageNum, int pageSize) {
        PageHelper.startPage(1,2);
        List<String> strings = studentInfoDao.selectStudentCode();
        return strings;
    }
}
