package com.sunmnet.bigdata.task.dao.master;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentInfoDao {

//    List<String> selectStudentCode(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
    List<String> selectStudentCode();
}
