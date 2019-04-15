package cn.m.u.page;

public class Page {

	private Integer totalCount;
	private Integer totalPageCount;
	private Integer currenPage;
	private Integer pageSize;
	
	
	public Page(Integer totalCount, Integer totalPageCount, Integer currenPage, Integer pageSize) {
		super();
		this.totalCount = totalCount;
		this.totalPageCount = totalPageCount;
		this.currenPage = currenPage;
		this.pageSize = pageSize;
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public Integer getCurrenPage() {
		return currenPage;
	}
	public void setCurrenPage(Integer currenPage) {
		this.currenPage = currenPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
