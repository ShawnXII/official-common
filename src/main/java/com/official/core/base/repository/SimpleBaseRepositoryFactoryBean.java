package com.official.core.base.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.official.core.base.search.support.Searchable;

/**
 * 抽象DAO层基类 提供一些简便方法<br>
 * 想要使用该接口需要在spring配置文件的jpa:repositories中添加 
 * factory-class="com.official.core.base.repository.SimpleBaseRepositoryFactoryBean"
 * @author ShawnXII
 * @Version 1.0
 * @param <M>
 * @param <ID>
 */
public interface SimpleBaseRepositoryFactoryBean<M, ID extends Serializable>
		extends CrudRepository<M, ID>, Serializable {
	/**
	 * 根据主键删除
	 * @param ids
	 */
	public void delete(ID[] ids);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public List<M> findAll();
	/**
	 * 排序查询
	 * @param sort
	 * @return
	 */
	public List<M> findAll(Sort sort);
	/**
	 * 分页排序查询
	 * @param pageable
	 * @return
	 */
	public Page<M> findAll(Pageable pageable);
	/**
	 * 根据条件分页排序查询
	 * @param searchable
	 * @return
	 */
	public Page<M> findAll(Searchable searchable);
	/**
	 * 自定义SQL查询
	 * @param definedSql
	 * @return
	 */
	public List<M> findAll(String definedSql);
	/**
	 * 自定义SQL查询
	 * @param definedSql
	 * @param params
	 * @return
	 */
	public List<M> findAll(String definedSql,Object params);
	/**
	 * 根据条件统计所有记录数
	 * @param searchable
	 * @return
	 */
	public long count(Searchable searchable);
	/**
	 * 查询
	 * @param query
	 * @param params
	 * @param begin
	 * @param max
	 * @return
	 */
	public List<M> query(String query, Map<String,Object> params, int begin, int max);
	/**
	 * 获取
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public M getBy(Class<M> clazz, final String propertyName, final Object value);
	/**
	 * 获取
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public M getBy(String propertyName, String value);
	/**
	 * 
	 * @param condition
	 * @param params
	 * @param begin
	 * @param max
	 * @return
	 */
	public List<M> find(String condition, Map<String,Object> params, int begin, int max);
	/**
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public M getBy(String propertyName, Object value);
	/**
	 * 
	 * @param params
	 * @return
	 */
	public List<M> findByProperty(Map<String, String> params);
	/**
	 * 
	 * @param params
	 * @param sqlCondition
	 * @return
	 */
	public List<M> findByPropertyAndCondition(Map<String, String> params, String sqlCondition);
	/**
	 * 释放
	 */
	public void flush();
}
