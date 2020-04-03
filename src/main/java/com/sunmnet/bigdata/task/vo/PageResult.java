package com.sunmnet.bigdata.task.vo;


import java.util.List;

/**
 * 封装分页结果集
 * 
 */
public class PageResult<T> {
	
	private Integer page;// 要查找第几页
	private Integer pageSize;// 每页显示多少条
	private Long totalPage = 0L;// 总页数
	private Long totalRows;// 总记录数

    public PageResult() {}

    public PageResult(List  queryData, Long queryTotal, int page, int pageSize) {
        this.rows = queryData;
        this.total = queryTotal;
        this.totalRows = queryTotal;
        this.page = page;
        this.pageSize = pageSize;
    }
	
	@SuppressWarnings("rawtypes")
	private List<T> rows;// 结果集
	
	/**
	 * FIMAX 设置 datagrid 的 pageNumber, total
	 */
	private Integer pageNumber; // 要查找第几页
	private Long total;      // 总记录数

/*	private Long total = 0L;

	public Long getTotal() {
		return totalPage;
	}

	public void setTotal(Long total) {
		this.total = total;
	}*/

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
		this.pageNumber = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPage() {
		if (this.getPageSize() != null) {
			if(this.getPageSize()==0){
				return 0L;
			}
			return (this.getTotalRows() + this.getPageSize() - 1) / this.getPageSize();// 总页数的算法
		} else {
			return totalPage;
		}
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
		this.total = totalRows;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	@SuppressWarnings("rawtypes")
	public List<T> getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		this.page = pageNumber;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
		this.totalRows = total;
	}

	@Override
	public String toString() {
		return "PageResult{" +
				"当前页page=" + this.getPage() +
				", 总行数totalRows=" + this.getTotalRows() +
				", 页宽度pageSize=" + this.getPageSize() +
				", 总页数totalPage=" + this.getTotalPage() +
				", 当前页pageNumber=" + this.getPageNumber() +
				", 总行数total=" + this.getTotal() +
				'}';
	}
}
