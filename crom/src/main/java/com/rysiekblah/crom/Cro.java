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
    private Map<Field, Pair<String, Integer>> fileds = Maps.newHashMap();

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

            for (Map.Entry<Field, Pair<String, Integer>> fieldStringEntry : fileds.entrySet()) {
                Field field = fieldStringEntry.getKey();
                field.setAccessible(true);
                field.set(obj,assignValue(cursor, fieldStringEntry.getValue()));
            }

            return obj;
        } catch (InstantiationException e) {
            throw new CromException("InstantiationException", e);
        } catch (IllegalAccessException e) {
            throw new CromException("IllegalAccessException", e);
        }

    }

    private Object assignValue(Cursor cursor, Pair<String, Integer> pair) throws CromException {
        int index = cursor.getColumnIndex(pair.first);
        if (index == -1) {
            throw new CromException("Column not exists.");
        }

        switch (pair.second) {
            case Types.COLUMN_TYPE_INTEGER:
                int value = cursor.getInt(index);
                return new Integer(value);
            case Types.COLUMN_TYPE_STRING:
                return new String(cursor.getString(index));
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private Pair<String, Integer> obtainColumnName(Field field) {
        Column column = field.getAnnotation(Column.class);

        if(column.name().isEmpty()){
            return Pair.create(field.getName(), column.type());
        }
        return Pair.create(column.name(), column.type());
    }

}
