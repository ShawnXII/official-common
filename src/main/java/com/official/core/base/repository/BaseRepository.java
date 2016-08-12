package com.official.core.base.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.official.core.base.entity.Entity;
import com.official.core.base.search.support.Searchable;
/**
 * BaseDao接口
 * 提供简单的增删改方法
 * @author ShawnXII
 * @Version 1.0
 * @param <M>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<M extends Entity<ID>,ID extends java.io.Serializable> extends Repository<M, ID>,java.io.Serializable{
	/**
	 * 主键查询	
	 * @param id
	 * @return
	 */
	public M findOne(ID id);
	/**
	 * 保存或者修改
	 * @param entity
	 * @return
	 */
	public M save(M entity);
	/**
	 * 保存多个
	 * @param entitys
	 * @return
	 */
	public List<M> save(Collection<M> entitys);
	/**
	 * 保存或者修改 并清空
	 * @param entity
	 * @return
	 */
	public M saveAndFlush(M entity);
	/**
	 * 清空
	 */
	public void flush();
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public void delete(ID id);
	/**
	 * 删除实体类
	 * @param entity
	 * @return
	 */
	public void delete(M entity);
	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	public void delete(ID[] ids);
	/**
	 * 删除多个
	 * @param entitys
	 * @return
	 */
	public void delete(Collection<M> entitys);
	/**
	 * 主键是否存在
	 * @param id
	 * @return
	 */
	public boolean exists(ID id);
	/**
	 * 查询全部 不分页 不排序
	 * @return
	 */
	public List<M> findAll();
	/**
	 * 查询全部记录数
	 * @return
	 */
	public long count();
	/**
	 * 按照条件查询记录数
	 * @param searchable
	 * @return
	 */
	public long count(final Searchable searchable);
	/**
	 * 自定义条件查询
	 * @param searchable
	 * @return
	 */
	public Page<M> findAll(final Searchable searchable);
	/**
	 * 按照条件排序查询 (不分页)
	 * @param searchable
	 * @return
	 */
	public List<M> findAllWithSort(final Searchable searchable);
	/**
	 * 按照条件分页查询(不排序)
	 * @param searchable
	 * @return
	 */
	public Page<M> findAllWithPage(final Searchable searchable);
	/**
	 * 按照条件不分页不排序查询
	 * @param searchable
	 * @return
	 */
	public List<M> findAllWithNoPageNoSort(final Searchable searchable);
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 */
	public Page<M> findAll(final Pageable pageable);
	/**
	 * 排序查询
	 * @param sort
	 * @return
	 */
	public List<M> findAll(final Sort sort);
	/**
	 * 自己组装sql
	 * @param definedSql
	 * @param params
	 * @return
	 */
	public List<M> findAll(String definedSql, Object... params);
	/**
	 * 自己组装sql
	 * @param definedSql
	 * @param params
	 * @return
	 */
	public long count(String definedSql, Object... params);
}
