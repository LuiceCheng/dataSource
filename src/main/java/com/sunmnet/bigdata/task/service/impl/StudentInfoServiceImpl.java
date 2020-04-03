package com.sunmnet.bigdata.task.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunmnet.bigdata.task.dao.master.StudentInfoDao;
import com.sunmnet.bigdata.task.service.IStudentInfoService;
import com.sunmnet.bigdata.task.vo.PageRequest;
import com.sunmnet.bigdata.task.vo.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements IStudentInfoService {
    @Autowired
    private StudentInfoDao studentInfoDao;

    @Override
    public PageResponse<String> getStudent(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getPageSize());
        List<String> strings = studentInfoDao.selectStudentCode();
        PageResponse<String> pageResponse = new PageResponse<>(strings);
        return pageResponse;
    }
}
