package org.apache.hadoop.hive.serde2.columnar;

public class ColumnarStruct extends ColumnarStructBase{
    public ColumnarStruct(int total) {
        super(total);
    }

    @Override
    protected int getLength(int start, int length) {
        return 0;
    }
}
