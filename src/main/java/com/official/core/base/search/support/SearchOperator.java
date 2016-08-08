package com.official.core.base.search.support;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.official.core.base.search.Search;

/**
 * 查询操作符
 * @author ShawnXII
 * @Version 1.0
 */
public class SearchOperator implements Search{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2707454359756333208L;
	
	/**
     * 等于
     */
    public final static String eq = "=";
    /**
     *不等于 
     */
    public final static String ne = "!=";
    /**
     * 大于
     */
    public final static String gt = ">";
    /**
     * 大于等于
     */
    public final static String gte = ">=";
    /**
     * 小于
     */
    public final static String lt = "<";
    /**
     * 小于等于
     */
    public final static String lte = "<=";
    /**
     * 前缀模糊匹配
     */
    public final static String prefixLike = "like";
    /**
     * 前缀模糊不匹配
     */
    public final static String prefixNotLike = "not like";
    /**
     * 后缀模糊匹配
     */
    public final static String suffixLike = "like";
    /**
     * 后缀模糊不匹配
     */
    public final static String suffixNotLike = "not like";
    /**
     * 模糊匹配
     */
    public final static String like = "like";
    /**
     * 不匹配
     */
    public final static String notLike = "not like";
    /**
     * 空
     */
    public final static String isNull = "is null";
    /**
     * 非空
     */
    public final static String isNotNull = "is not null";
    /**
     * 包含
     */
    public final static String in = "in";
    /**
     * 不包含
     */
    public final static String notIn = "not in";
    /**
     * 自定义默认的
     */
    public final static String custom = "";
    
    private static Map<String,String> valueMap = Maps.newHashMap();
    static{
    	valueMap.put("eq", eq);
    	valueMap.put("ne", ne);
    	valueMap.put("gt", gt);
    	valueMap.put("gte", gte);
    	valueMap.put("lt", lt);
    	valueMap.put("lte", lte);
    	valueMap.put("prefixLike", prefixLike);
    	valueMap.put("prefixNotLike", prefixNotLike);
    	valueMap.put("suffixLike", suffixLike);
    	valueMap.put("suffixNotLike", suffixNotLike);
    	valueMap.put("like", like);
    	valueMap.put("notLike", notLike);
    	valueMap.put("isNull", isNull);
    	valueMap.put("isNotNull", isNotNull);
    	valueMap.put("in", in);
    	valueMap.put("notIn", notIn);
    	valueMap.put("custom", custom);
    }


    public SearchOperator(){
    	super();
    }

    public static String toStringAllOperator() {
    	return JSON.toJSONString(valueMap);
    }

    /**
     * 操作符是否允许为空
     * @param operator
     * @return
     */
    public static boolean isAllowBlankValue(final String operator) {
        return operator.equals(SearchOperator.isNotNull) || operator.equals(SearchOperator.isNull);
    }
    /***
     * 
     * @param string
     * @return
     */
	public static String valueOf(String string) {
		return valueMap.get(string);
	}
    
}
