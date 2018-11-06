package com.rats.orm.data;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataSetList<E> extends ArrayList<E> implements DataSet<E> {

    private static final long serialVersionUID = 1L;
    private String serializableId;
    private String serializablePath;
    private boolean serializable;
    private boolean batchSerializable;
    private int batchPageSize;
    private int batchPageCount;

    /**
     * construct function
     */
    public DataSetList() {
        super();
    }

    /**
     * construct function
     *
     * @param list
     */
    public DataSetList(List list) {
        addAll(list);
    }

    /**
     * get serializable id
     *
     * @return String
     */
    public String getSerializableId() {
        return serializableId;
    }

    /**
     * set serializable id
     *
     * @param serializableId
     */
    public void setSerializableId(String serializableId) {
        this.serializableId = serializableId;
    }

    /**
     * get serializable path
     *
     * @return String
     */
    public String getSerializablePath() {
        return serializablePath;
    }

    /**
     * set serializable path
     *
     * @param serializablePath
     */
    public void setSerializablePath(String serializablePath) {
        this.serializablePath = serializablePath;
    }

    /**
     * is serializable
     *
     * @return boolean
     */
    public boolean isSerializable() {
        return serializable;
    }

    /**
     * set serializable
     *
     * @param serializable
     */
    public void setSerializable(boolean serializable) {
        this.serializable = serializable;
    }

    /**
     * is batch serializable
     *
     * @return boolean
     */
    public boolean isBatchSerializable() {
        return batchSerializable;
    }

    /**
     * set batch serializable
     *
     * @param batchSerializable
     */
    public void setBatchSerializable(boolean batchSerializable) {
        this.batchSerializable = batchSerializable;
    }

    /**
     * set batch page size
     *
     * @return int
     */
    public int getBatchPageSize() {
        return batchPageSize;
    }

    /**
     * set batch page size
     *
     * @param batchPageSize
     */
    public void setBatchPageSize(int batchPageSize) {
        this.batchPageSize = batchPageSize;
    }

    /**
     * set batch page count
     *
     * @return int
     */
    public int getBatchPageCount() {
        return batchPageCount;
    }

    /**
     * set batch page count
     *
     * @param batchPageCount
     */
    public void setBatchPageCount(int batchPageCount) {
        this.batchPageCount = batchPageCount;
    }

    /**
     * get object
     *
     * @param index
     * @param name
     * @return Object
     */
    public Object get(int index, String name) {
        DataRow data = (DataRow) get(index);
        return data == null ? null : data.get(name);
    }

    /**
     * get object
     *
     * @param index
     * @param name
     * @param def
     * @return Object
     */
    public Object get(int index, String name, Object def) {
        Object value = get(index, name);
        return value == null ? def : value;
    }

    /**
     * get names
     *
     * @return String[]
     */
    public String[] getNames() {
        return size() > 0 ? ((DataRow) get(0)).getNames() : null;
    }

    /**
     * to data
     *
     * @return DataRow
     */
    public DataRow toData() throws Exception {
        DataRow data = new DataMap();
        Iterator it = iterator();
        while (it.hasNext()) {
            DataRow element = (DataRow) it.next();
            Iterator iterator = element.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (data.containsKey(key)) {
                    List list = (List) data.get(key);
                    list.add(element.get(key));
                } else {
                    List list = new ArrayList();
                    list.add(element.get(key));
                    data.put(key, list);
                }
            }
        }
        return data;
    }

    /**
     * get count
     *
     * @return int
     */
    public int count() {
        return size();
    }

    /**
     * sort single (default ascend)
     */
    public void sort(String key, int keyType) {
        sort(key, keyType, DataSet.ORDER_ASCEND);
    }

    /**
     * sort single
     */
    public void sort(String key, int keyType, int order) {
        Object[] datas = this.toArray();
        DataComparator c = new DataComparator(key, keyType, order);
        Arrays.sort(datas, c);

        List list = Arrays.asList(datas);

        this.clear();
        this.addAll(list);
    }

    /**
     * sort double (default ascend)
     */
    public void sort(String key1, int keyType1, String key2, int keyType2) {
        sort(key1, keyType1, DataSet.ORDER_ASCEND);
        sort(key2, keyType2, DataSet.ORDER_ASCEND, key1, keyType1);
    }

    /**
     * sort double
     */
    public void sort(String key1, int keyType1, int order1, String key2, int keyType2, int order2) {
        sort(key1, keyType1, order1);
        sort(key2, keyType2, order2, key1, keyType1);
    }

    /**
     * sort assistant
     */
    private void sort(String key, int type, int order, String fix, int fixType) {
        Object[] datas = this.toArray();

        DataComparator c = new DataComparator(key, type, order);
        if (fix == null) {
            Arrays.sort(datas, c);
        } else {
            int[] marks = Anchor.mark(this, fix, fixType);

            for (int pre = 0, i = 1; i < marks.length; i++) {
                Arrays.sort(datas, pre, marks[i], c);
                pre = marks[i];
            }
        }
        List list = Arrays.asList(datas);
        this.clear();
        this.addAll(list);
    }

    public DataRow getFirst() {
        if (null == this || this.size() == 0) {
            return null;
        }
        return (DataRow) this.get(0);
    }

    public DataRow getData(int index) {
        Object value = this.get(index);
        if (value == null) {
            return null;
        } else {
            return (DataRow) value;
        }
    }

    public DataSet getDataSet(int index) {
        Object value = this.get(index);
        if (value == null) {
            return null;
        } else {
            return (DataSet) value;
        }
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }

}
