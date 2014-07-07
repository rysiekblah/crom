package com.rysiekblah.crom;

import android.database.Cursor;

/**
 * Created by tomek on 7/7/14.
 */
public class FieldTypes {
    public static class ShortField extends FieldAbstract<Short> {
        @Override
        public Short getData(Cursor cursor, int index) {
            return cursor.getShort(index);
        }
    }

    public static class LongField extends FieldAbstract<Long> {
        @Override
        public Long getData(Cursor cursor, int index) {
            return cursor.getLong(index);
        }
    }

    public static class DoubleField extends FieldAbstract<Double> {
        @Override
        public Double getData(Cursor cursor, int index) {
            return cursor.getDouble(index);
        }
    }

    public static class FloatField extends FieldAbstract<Float> {
        @Override
        public Float getData(Cursor cursor, int index) {
            return cursor.getFloat(index);
        }
    }

    public static class BooleanField extends FieldAbstract<Boolean> {
        @Override
        public Boolean getData(Cursor cursor, int index) {
            return cursor.getString(index).equals("true");
        }
    }

    public static class IntegerField extends FieldAbstract<Integer> {
        @Override
        public Integer getData(Cursor cursor, int index) {
            return cursor.getInt(index);
        }
    }

    public static class StringField extends FieldAbstract<String> {
        @Override
        public String getData(Cursor cursor, int index) {
            return cursor.getString(index);
        }
    }

    public static class BlobField extends FieldAbstract<byte[]> {
        @Override
        public byte[] getData(Cursor cursor, int index) {
            return cursor.getBlob(index);
        }
    }
}
