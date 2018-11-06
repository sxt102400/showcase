package com.rats.orm.data;

/**
 * Anchor
 */
class Anchor {
    public static int[] mark(DataSet dataset, String fix, int fixType) {
        int[] marks = new int[dataset.size() + 1];
        int idx = 0;

        if (fixType == DataSet.TYPE_STRING) {
            String preValue = null;
            String curValue = null;
            for (int i = 0; i < dataset.size(); i++) {
                DataRow data = (DataRow) dataset.get(i);
                curValue = data.getString(fix);
                if (!curValue.equals(preValue)) {
                    marks[idx++] = i;
                }
                preValue = curValue;
            }
        } else if (fixType == DataSet.TYPE_INTEGER) {
            int preValue = Integer.MIN_VALUE;
            int curValue = Integer.MIN_VALUE;
            for (int i = 0; i < dataset.size(); i++) {
                DataRow data = (DataRow) dataset.get(i);
                curValue = data.getInt(fix);
                if (curValue != preValue) {
                    marks[idx++] = i;
                }
                preValue = curValue;
            }
        } else if (fixType == DataSet.TYPE_DOUBLE) {
            double preValue = Double.NaN;
            double curValue = Double.NaN;
            for (int i = 0; i < dataset.size(); i++) {
                DataRow data = (DataRow) dataset.get(i);
                curValue = data.getDouble(fix);
                if (curValue != preValue) {
                    marks[idx++] = i;
                }
                preValue = curValue;
            }
        }
        marks[idx] = dataset.size();
        return trimRight(marks);
    }

    private static int[] trimRight(int[] marks) {
        int tail = -1;
        for (int i = marks.length - 1; i >= 0; i--) {
            if (marks[i] != 0) {
                tail = i;
                break;
            }
        }

        int[] ms = new int[tail + 1];

        for (int i = 0; i < ms.length; i++) {
            ms[i] = marks[i];
        }

        return ms;
    }
}
