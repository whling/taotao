package com.taotao.common.pojo;

import java.util.List;

public class EUDatagridResult {
	private long total;
	//？表示任意类型 
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
