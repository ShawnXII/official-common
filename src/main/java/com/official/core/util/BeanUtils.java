package com.official.core.util;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.google.common.collect.Maps;

/**
 * 扩展BeanUtils
 * 
 * @author ShawnXII
 * @Version 1.0
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;
		Object obj = beanClass.newInstance();
		populate(obj, map);
		return obj;
	}

	public static Map<Object, Object> objectToMap(Object obj) {
		if (obj == null)
			return null;
		return new org.apache.commons.beanutils.BeanMap(obj);
	}
	
	public static Map<String,Object> PageToMap(Page<?> page){
		Map<String,Object> map=Maps.newHashMap();
		if(page!=null){
			map.put("content", page.getContent());
			map.put("number", page.getNumber());
			map.put("numberOfElements", page.getNumberOfElements());
			map.put("size", page.getSize());
			map.put("totalElements", page.getTotalElements());
			map.put("totalPages", page.getTotalPages());
		}
		return map;
	}
	

}
