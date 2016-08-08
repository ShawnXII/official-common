package com.official.core.base.search.filter;

import java.util.List;

import com.google.common.collect.Lists;
import com.official.core.base.search.SearchFilter;

/**
 * and 条件
 * @author ShawnXII
 * @Version 1.0
 */
public class AndCondition implements SearchFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296448348335071197L;
	//使用list保证连接顺序
	private List<SearchFilter> andFilters=Lists.newArrayList();
	
	public AndCondition(){
		super();
	}
	/**
	 * 添加And连接查询
	 * @param searchFilter
	 * @return
	 */
	public AndCondition add(SearchFilter searchFilter){
		andFilters.add(searchFilter);
		return this;
	}
	
	public List<SearchFilter> getAndFilters() {
		return andFilters;
	}
	
	@Override
	public String toString() {
		return "AndCondition [andFilters=" + andFilters + "]";
	}	
}
