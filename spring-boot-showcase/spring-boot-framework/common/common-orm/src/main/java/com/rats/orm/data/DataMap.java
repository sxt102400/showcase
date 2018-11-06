package com.rats.orm.data;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zhonglj
 * 2015-03-23
 * 泛型支持
 * @param <K>
 * @param <V>
 */
public class DataMap<K,V> extends HashMap<K,V>  implements DataRow<K,V>, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * get object
	 * 
	 * @param name
	 * @param def
	 * @return Object
	 */
	public Object get(String name, Object def) {
		Object value = get(name);
		return value == null ? def : value;
	}

	/**
	 * get names
	 * 
	 * @return String[]
	 */
	public String[] getNames() {
		String[] names = keySet().toArray(new String[0]);
		Arrays.sort(names);
		return names;
	}

	/**
	 * get string
	 * 
	 * @param name
	 * @return String
	 */
	public String getString(String name) {
		Object value = get(name);
		return value == null ? null : value.toString();
	}

	/**
	 * get string
	 * 
	 * @param name
	 * @return String
	 */
	public String getString(String name, String defaultValue) {
		return get(name, defaultValue).toString();
	}
	


	/**
	 * get int
	 * 
	 * @param name
	 * @return int
	 */
	public int getInt(String name) {
		return getInt(name, 0);
	}

	/**
	 * get int
	 * 
	 * @param name
	 * @param defaultValue
	 * @return int
	 */
	public int getInt(String name, int defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Integer.parseInt(value);
	}

	/**
	 * get double
	 * 
	 * @param name
	 * @return double
	 */
	public double getDouble(String name) {
		return getDouble(name, 0);
	}

	/**
	 * get double
	 * 
	 * @param name
	 * @param defaultValue
	 * @return double
	 */
	public double getDouble(String name, double defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Double.parseDouble(value);
	}

	/**
	 * get boolean
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean getBoolean(String name) {
		return getBoolean(name, false);
	}

	/**
	 * get boolean
	 * 
	 * @param name
	 * @param defaultValue
	 * @return boolean
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Boolean.valueOf(value).booleanValue();
	}

	/**
	 * to json string
	 * 
	 * @return str
	 */
	public String toJSON() {
		return JSON.toJSONString(this);
	}

	/**
	 * get string
	 * 
	 * @param name
	 * @return String
	 */
	public String getStringNotEmpty(String name) {
		String value =  getString(name);
		if(StringUtils.isBlank(value)){
			throw new RuntimeException(name+" can not empty ");
		}
		return value;
	}
	
	public int getIntNotEmpty(String name) {
		String value =  getString(name);
		if(StringUtils.isBlank(value)){
			throw new RuntimeException(name+" can not empty ");
		}
		return getInt(name);
	}

	public double getDoubleNotEmpty(String name) {
		String value =  getString(name);
		if(StringUtils.isBlank(value)){
			throw new RuntimeException(name+" can not empty ");
		}
		return getDouble(name);
	}
}
