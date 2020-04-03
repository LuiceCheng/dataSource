package com.sunmnet.bigdata.task.vo;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = 4757917169505792603L;
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> row;

    public PageResponse(){}

    public PageResponse(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.total = pageInfo.getTotal();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.row = pageInfo.getList();
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getRow() {
        return row;
    }
}
