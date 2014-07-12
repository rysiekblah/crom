package com.rysiekblah.crom;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.rysiekblah.crom.annotation.Column;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by tomek on 4/28/14.
 */
public class Cro<T> {

    private Class<T> clazz;
    private Map<Field, FieldDescriptor> fields = Maps.newHashMap();

    public Cro(Class<T> clazz) {
        this.clazz = clazz;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                fields.put(field, obtainColumnName(field));
            }
        }
    }

    public <T> ContentValues toContentValues(T object) {
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<Field, FieldDescriptor> fieldDescriptorEntry : fields.entrySet()) {
            Field field = fieldDescriptorEntry.getKey();
            field.setAccessible(true);

            try {
                FieldAbstract fieldAbstract = fieldDescriptorEntry.getValue().getFieldAbstract();
                fieldAbstract.decorate(field, object, contentValues);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return contentValues;
    }

    public T populate(Cursor cursor) {
        try {
            T obj = clazz.newInstance();
            for (Map.Entry<Field, FieldDescriptor> fieldStringEntry : fields.entrySet()) {
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
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private FieldDescriptor obtainColumnName(Field field) {;
        return new FieldDescriptor(field, field.getAnnotation(Column.class));
    }

}
