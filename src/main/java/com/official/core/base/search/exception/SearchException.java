package com.official.core.base.search.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Search 错误
 * @author ShawnXII
 * @Version 1.0
 */
public class SearchException extends NestedRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2554645635571308884L;

	public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
