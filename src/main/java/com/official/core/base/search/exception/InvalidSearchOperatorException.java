package com.official.core.base.search.exception;

/**
 * 无效的查询连接参数错误
 * @author ShawnXII
 * @Version 1.0
 */
public final class InvalidSearchOperatorException extends SearchException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1548250343258420280L;

	public InvalidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }

    public InvalidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
        super("无效的查询连接操作符: [" + searchProperty + "], " +
                "操作符: [" + operatorStr + "], 必须是其中一个 :[]"  /*SearchOperator.toStringAllOperator()*/, cause);
    }
}
