package com.rysiekblah.crom;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.util.Pair;

import com.google.common.collect.Maps;
import com.rysiekblah.crom.annotation.Column;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by tomek on 4/28/14.
 */
public class Cro<T> {

    private Class<T> clazz;
    private Map<Field, FieldDescriptor> fileds = Maps.newHashMap();

    public Cro(Class<T> clazz) {
        this.clazz = clazz;
        init();
    }

    private void init() {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                fileds.put(field, obtainColumnName(field));
            }
        }
    }

    public T populate(Cursor cursor) {

        try {
            T obj = clazz.newInstance();

            for (Map.Entry<Field, FieldDescriptor> fieldStringEntry : fileds.entrySet()) {
                Field field = fieldStringEntry.getKey();
                field.setAccessible(true);
                field.set(obj, assignValue(cursor, fieldStringEntry.getValue()));
            }

            return obj;
        } catch (InstantiationException e) {
            throw new CromException("InstantiationException", e);
        } catch (IllegalAccessException e) {
            throw new CromException("IllegalAccessException", e);
        }

    }

    private Object assignValue(Cursor cursor, FieldDescriptor fieldDescriptor) throws CromException {
        int index = cursor.getColumnIndex(fieldDescriptor.getName());
        if (index == -1) {
            throw new CromException("Column not exists. " + fieldDescriptor.getName());
        }
        if (fieldDescriptor.getFieldAbstract() == null) {
            throw new CromException("Type not supported: " + fieldDescriptor.getType());
        }
        return fieldDescriptor.getFieldAbstract().getData(cursor, index);

//        switch (fieldDescriptor.getType()) {
//            case Types.COLUMN_TYPE_INTEGER:
//                int value = cursor.getInt(index);
//                return new Integer(value);
//            case Types.COLUMN_TYPE_STRING:
//                return new String(cursor.getString(index));
//        }
//        return null;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private FieldDescriptor obtainColumnName(Field field) {
        Column column = field.getAnnotation(Column.class);
        typing(field);
        return new FieldDescriptor(field, column);
//
//        if(column.name().isEmpty()){
//            return new FieldDescriptor(field.getName(), column.type());
//        }
//        return new FieldDescriptor(column.name(), column.type());
    }

    private void typing(Field field) {
        System.out.println("Type: " + field.getType() + ", isString: " + field.getType().isAssignableFrom(String.class));

        Class<?> clazz = field.getType();
        if (clazz.isAssignableFrom(String.class)) {
            System.out.println("String");
        } else if (clazz.isAssignableFrom(Integer.class)) {
            System.out.println("Integer");
        } else if (clazz.isAssignableFrom(int.class)) {
            System.out.println("int");
        } else {
            System.out.println("type unknown");
        }



    }

}
