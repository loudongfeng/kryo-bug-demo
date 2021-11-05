package org.apache.hadoop.hive.serde2.columnar;

public abstract class ColumnarStructBase {
    class FieldInfo {
        int start;
        int length;
        public FieldInfo(int start, int length) {
            this.length = length;
            this.start = start;
        }

        protected Object uncheckedGetField() {
            if (getLength(start, length) != -1) {
                return null;
            }
            return this;
        }
    }

    private FieldInfo[] fields = null;

    public ColumnarStructBase(int total) {
        fields = new FieldInfo[total];
        for (int i = 0; i < total; i++) {
            fields[i] = new FieldInfo(i, i);
        }
    }

    protected abstract int getLength(int start, int length);

    public void randomPrint() {
        fields[0].uncheckedGetField();
    }
}
