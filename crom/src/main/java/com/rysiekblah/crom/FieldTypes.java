package com.rysiekblah.crom;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Field;

/**
 * Created by tomek on 7/7/14.
 */
public class FieldTypes {
    public static class ShortField extends FieldAbstract<Short> {
        @Override
        public Short getData(Cursor cursor, int index) {
            return cursor.getShort(index);
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (Short) field.get(object));
        }
    }

    public static class LongField extends FieldAbstract<Long> {
        @Override
        public Long getData(Cursor cursor, int index) {
            return cursor.getLong(index);
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (Long) field.get(object));
        }
    }

    public static class DoubleField extends FieldAbstract<Double> {
        @Override
        public Double getData(Cursor cursor, int index) {
            return cursor.getDouble(index);
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (Double) field.get(object));
        }
    }

    public static class FloatField extends FieldAbstract<Float> {
        @Override
        public Float getData(Cursor cursor, int index) {
            return cursor.getFloat(index);
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (Float) field.get(object));
        }
    }

    public static class BooleanField extends FieldAbstract<Boolean> {
        @Override
        public Boolean getData(Cursor cursor, int index) {
            return cursor.getString(index).equals("true");
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (Boolean) field.get(object));
        }
    }

    public static class IntegerField extends FieldAbstract<Integer> {
        @Override
        public Integer getData(Cursor cursor, int index) {
            return cursor.getInt(index);
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (Integer) field.get(object));
        }
    }

    public static class StringField extends FieldAbstract<String> {
        @Override
        public String getData(Cursor cursor, int index) {
            return cursor.getString(index);
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (String) field.get(object));
        }
    }

    public static class BlobField extends FieldAbstract<byte[]> {
        @Override
        public byte[] getData(Cursor cursor, int index) {
            return cursor.getBlob(index);
        }

        @Override
        public <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException {
            contentValues.put(fieldName, (byte[]) field.get(object));
        }
    }
}
