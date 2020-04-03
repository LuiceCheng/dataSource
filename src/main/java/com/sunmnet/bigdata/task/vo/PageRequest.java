package com.sunmnet.bigdata.task.vo;


import java.util.List;

/**
 * 封装分页结果集
 * 
 */
public class PageRequest<T> {
	
	private Integer page;// 要查找第几页
	private Integer pageSize;// 每页显示多少条

    public PageRequest() {}

	public PageRequest(Integer page, Integer pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
