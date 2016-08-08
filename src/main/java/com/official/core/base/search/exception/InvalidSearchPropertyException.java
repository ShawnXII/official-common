package com.official.core.base.search.exception;

/**
 * 无效的查询类型错误
 * @author ShawnXII
 * @Version 1.0
 */
public final class InvalidSearchPropertyException extends SearchException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7486649986355623605L;
	
	
	public InvalidSearchPropertyException(String searchProperty, String entityProperty) {
        this(searchProperty, entityProperty, null);
    }

    public InvalidSearchPropertyException(String searchProperty, String entityProperty, Throwable cause) {
        super("Invalid Search Property [" + searchProperty + "] Entity Property [" + entityProperty + "]", cause);
    }
}
