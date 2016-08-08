package com.official.core.base.search.filter;

import java.util.List;

import com.google.common.collect.Lists;
import com.official.core.base.search.SearchFilter;
/**
 * or连接参数
 * @author ShawnXII
 * @Version 1.0
 */
public class OrCondition implements SearchFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5836833633406105999L;
	
	private List<SearchFilter> orFilters = Lists.newArrayList();
	
	public OrCondition(){
		super();
	}
	/**
	 * 添加Or连接
	 * @param searchFilter
	 * @return
	 */
	public OrCondition add(SearchFilter searchFilter){	
		orFilters.add(searchFilter);
		return this;
	}
	/**
	 * 获取or连接
	 * @return
	 */
	public List<SearchFilter> getOrFilters() {
		return orFilters;
	}
	
	@Override
	public String toString() {
		return "OrCondition [orFilters=" + orFilters + "]";
	}	
}
