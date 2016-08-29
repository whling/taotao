package com.taotao.common.pojo;

/**
 * easyUI的基本模型对象
 * 
 * @author Administrator
 *
 */
public class EUTreeData {
	private long id;
	private String text;
	/*
	 *  这个字段为open，表示为叶子节点
	 *  这个字段为closed，表示为父节点
	 */
	private String state;
	//父亲节点ID
	private long parentId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
}
