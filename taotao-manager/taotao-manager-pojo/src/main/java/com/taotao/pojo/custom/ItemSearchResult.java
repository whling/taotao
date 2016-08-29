package com.taotao.pojo.custom;

import java.util.List;
/**
 * 自定义的索引库搜索返回结果
 * @author Administrator
 *
 */
public class ItemSearchResult {
	//商品信息
	private List<ItemSearchCustom> itemSearchCustoms;
	//总记录数
	private long totalRecord;
	//总页数
	private long totalPage;
	//当前页
	private long pageNow;

	public List<ItemSearchCustom> getItemSearchCustoms() {
		return itemSearchCustoms;
	}

	public void setItemSearchCustoms(List<ItemSearchCustom> itemSearchCustoms) {
		this.itemSearchCustoms = itemSearchCustoms;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getPageNow() {
		return pageNow;
	}

	public void setPageNow(long pageNow) {
		this.pageNow = pageNow;
	}
}
