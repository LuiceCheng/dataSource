package com.sunmnet.bigdata.task.entity;

import java.io.Serializable;

public class StudentInfo implements Serializable {
    private static final long serialVersionUID = 4704108565226162590L;
    private String studentCode;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}
