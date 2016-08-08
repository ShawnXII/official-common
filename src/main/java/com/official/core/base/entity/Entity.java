package com.official.core.base.entity;
/**
 * 所有实体类的接口
 * @author ShawnXII
 * @Version 1.0
 */
public interface Entity<ID extends java.io.Serializable> extends java.io.Serializable{
	
	public boolean isNew();
	/**
	 * 
	 * @param id
	 */
	public void setId(ID id);
	/**
	 * 获取ID
	 * @return
	 */
	public ID getId();
	
}
