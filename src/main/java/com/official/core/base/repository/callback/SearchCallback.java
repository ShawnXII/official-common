package com.official.core.base.repository.callback;

import javax.persistence.Query;

import com.official.core.base.search.Search;
import com.official.core.base.search.support.Searchable;

public interface SearchCallback extends Search{

	public static final SearchCallback DEFAULT = new SearchCallbackImpl();

	/**
	 * 动态拼装HQL
	 * 
	 * @param ql
	 * @param search
	 */
	public void prepareQL(StringBuilder ql, Searchable search);

	/**
	 * 动态拼装 WHERE GROUP BY HAVING
	 * 
	 * @param ql
	 * @param search
	 */
	public void prepareOrder(StringBuilder ql, Searchable search);

	/**
	 * 根据Search 给Query赋值
	 * 
	 * @param query
	 * @param search
	 */
	public void setValues(Query query, Searchable search);

	/**
	 * 根据Search 给Query设置分页信息
	 * 
	 * @param query
	 * @param search
	 */
	public void setPageable(Query query, Searchable search);
}
