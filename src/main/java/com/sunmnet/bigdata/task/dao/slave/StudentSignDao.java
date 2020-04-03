package com.sunmnet.bigdata.task.dao.slave;

import com.sunmnet.bigdata.task.entity.StudentSign;
import com.sunmnet.bigdata.task.vo.CheckInRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentSignDao {

    List<StudentSign> selectByExample(@Param("record") StudentSign record);

    int batchInsert(List<StudentSign> records);

    int batchInsertSelective(List<CheckInRecord> records);
}
