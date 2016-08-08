package com.official.core.base.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有的数据库实体类都需要继承该类 并实现主键设计
 * @author ShawnXII
 * @Version 1.0
 * @param <ID>
 */
@MappedSuperclass
public abstract class AbstractEntity<ID extends java.io.Serializable> implements Entity<ID>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5526256384705921481L;
	
	@Id
	@Column(name="id",unique=true,nullable=false)
	private ID id;
	
	/**
	 * 获取主键<p>
	 * 数据库设计应该有主键
	 * @return
	 */
	public ID getId(){
		return id;
	}
	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(ID id){
		this.id=id;
	}
	/**
	 * 是否为新增
	 */
	public boolean isNew() {
		return getId() == null;
	}
	
	
	
}
