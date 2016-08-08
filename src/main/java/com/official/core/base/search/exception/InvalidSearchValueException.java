package com.official.core.base.search.exception;

/**
 * 无效的查询值
 * @author ShawnXII
 * @Version 1.0
 */
public final class InvalidSearchValueException extends SearchException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5522295578374867394L;

	public InvalidSearchValueException(String searchProperty, String entityProperty, Object value) {
        this(searchProperty, entityProperty, value, null);
    }

    public InvalidSearchValueException(String searchProperty, String entityProperty, Object value, Throwable cause) {
        super("Invalid Search Value, searchProperty [" + searchProperty + "], " +
                "entityProperty [" + entityProperty + "], value [" + value + "]", cause);
    }

}
