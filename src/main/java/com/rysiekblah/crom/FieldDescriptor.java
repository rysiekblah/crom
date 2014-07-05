package com.rysiekblah.crom;

import android.database.Cursor;

import com.google.common.collect.Maps;
import com.rysiekblah.crom.annotation.Column;

import java.lang.reflect.Field;
import java.nio.DoubleBuffer;
import java.util.Map;

/**
 * Created by tomek on 4/28/14.
 */
public class FieldDescriptor {

    private String name;
    private Class<?> type;
    private FieldAbstract fieldAbstract;
    private final static Map<Class<?>, FieldAbstract<?>> fieldCache;

    static {
        fieldCache = Maps.newHashMap();
        fieldCache.put(short.class, new ShortField());
        fieldCache.put(Short.class, new ShortField());
        fieldCache.put(long.class, new LongField());
        fieldCache.put(Long.class, new LongField());
        fieldCache.put(double.class, new DoubleField());
        fieldCache.put(Double.class, new DoubleField());
        fieldCache.put(float.class, new FloatField());
        fieldCache.put(Float.class, new FloatField());
        fieldCache.put(int.class, new IntegerField());
        fieldCache.put(Integer.class, new IntegerField());
        fieldCache.put(String.class, new StringField());
        fieldCache.put(byte[].class, new BlobField());
        fieldCache.put(boolean.class, new BooleanField());
        fieldCache.put(Boolean.class, new BooleanField());
    }

    public FieldDescriptor(Field field, Column column) {
        if (column.name().isEmpty()) {
            this.name = field.getName();
        } else {
            this.name = column.name();
        }
        type = field.getType();
        fieldAbstract = fieldCache.get(field.getType());
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public FieldAbstract getFieldAbstract() {
        return fieldAbstract;
    }

    private static class ShortField extends FieldAbstract<Short> {
        @Override
        public Short getData(Cursor cursor, int index) {
            return cursor.getShort(index);
        }
    }

    private static class LongField extends FieldAbstract<Long> {
        @Override
        public Long getData(Cursor cursor, int index) {
            return cursor.getLong(index);
        }
    }

    private static class DoubleField extends FieldAbstract<Double> {
        @Override
        public Double getData(Cursor cursor, int index) {
            return cursor.getDouble(index);
        }
    }

    private static class FloatField extends FieldAbstract<Float> {
        @Override
        public Float getData(Cursor cursor, int index) {
            return cursor.getFloat(index);
        }
    }

    private static class BooleanField extends FieldAbstract<Boolean> {
        @Override
        public Boolean getData(Cursor cursor, int index) {
            return cursor.getString(index).equals("true");
        }
    }

    private static class IntegerField extends FieldAbstract<Integer> {
        @Override
        public Integer getData(Cursor cursor, int index) {
            return cursor.getInt(index);
        }
    }

    private static class StringField extends FieldAbstract<String> {
        @Override
        public String getData(Cursor cursor, int index) {
            return cursor.getString(index);
        }
    }

    private static class BlobField extends FieldAbstract<byte[]> {
        @Override
        public byte[] getData(Cursor cursor, int index) {
            return cursor.getBlob(index);
        }
    }
}
