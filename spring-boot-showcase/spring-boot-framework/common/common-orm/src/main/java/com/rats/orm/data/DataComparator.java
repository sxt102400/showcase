package com.rats.orm.data;

import java.util.Comparator;

/**
 * DataRow Comparator
 * 
 */
public class DataComparator implements Comparator {
	private String key;

	private int keyType;

	private int order;

	public DataComparator(String key, int keyType, int order) {
		this.key = key;
		this.keyType = keyType;
		this.order = order;
	}

	public int compare(Object o1, Object o2) {
		DataRow data1 = (DataRow) o1;
		DataRow data2 = (DataRow) o2;

		if (order == DataSet.ORDER_ASCEND) {
			if (keyType == DataSet.TYPE_STRING) {
				String value1 = data1.getString(key);
				String value2 = data2.getString(key);
				return value1.compareTo(value2);
			} else if (keyType == DataSet.TYPE_INTEGER) {
				int value1 = data1.getInt(key);
				int value2 = data2.getInt(key);
				return value1 < value2 ? -1 : (value1 == value2 ? 0 : 1);
			} else if (keyType == DataSet.TYPE_DOUBLE) {
				double value1 = data1.getDouble(key);
				double value2 = data2.getDouble(key);
				return value1 < value2 ? -1 : (value1 == value2 ? 0 : 1);
			}
		} else {
			if (keyType == DataSet.TYPE_STRING) {
				String value1 = data1.getString(key);
				String value2 = data2.getString(key);
				return value2.compareTo(value1);
			} else if (keyType == DataSet.TYPE_INTEGER) {
				int value1 = data1.getInt(key);
				int value2 = data2.getInt(key);
				return value1 > value2 ? -1 : (value1 == value2 ? 0 : 1);
			} else if (keyType == DataSet.TYPE_DOUBLE) {
				double value1 = data1.getDouble(key);
				double value2 = data2.getDouble(key);
				return value1 > value2 ? -1 : (value1 == value2 ? 0 : 1);
			}
		}
		return 0;
	}
}
