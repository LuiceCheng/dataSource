package com.sunmnet.bigdata.task.service.impl;

import com.sunmnet.bigdata.task.dao.slave.StudentSignDao;
import com.sunmnet.bigdata.task.entity.StudentSign;
import com.sunmnet.bigdata.task.service.IStudentSignService;
import com.sunmnet.bigdata.task.util.DateUtils;
import com.sunmnet.bigdata.task.vo.CheckInRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class StudentSignSerivceImpl implements IStudentSignService {

    @Autowired
    private StudentSignDao studentSignDao;

    @Override
    public List<StudentSign> getRecord() {
        StudentSign studentSign = new StudentSign();
        List<StudentSign> studentSigns = studentSignDao.selectByExample(studentSign);
        return studentSigns;
    }

    @Override
    public int batchInsertSelective(List<CheckInRecord> records) {
        Date now =new Date();
        for (CheckInRecord record: records) {
            record.setId(UUID.randomUUID().toString());
            record.setCreateAt(now);
        }
        return studentSignDao.batchInsertSelective(records);
    }
}
