package com.rysiekblah.crom;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.common.collect.Maps;
import com.rysiekblah.crom.annotation.Column;
import com.rysiekblah.crom.annotation.Embedded;
import com.rysiekblah.crom.annotation.OneToMany;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
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
            } else if (field.isAnnotationPresent(OneToMany.class)) {
                fields.put(field, new FieldDescriptor(field, field.getAnnotation(OneToMany.class)));
            } else if (field.isAnnotationPresent(Embedded.class)) {
                throw new CromException("Embedded annotation not implemented yet");
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
                if (fieldStringEntry.getValue().isJoined()) {
                    Object joinedObj = fieldStringEntry.getValue().getJoinedClass().newInstance();
                    Field ff = fieldStringEntry.getKey();
                    ff.setAccessible(true);
                    List l = (List)ff.get(obj);
                    Method method = ff.getType().getDeclaredMethod("add", Object.class);
                    method.invoke(l, joinedObj);
                    for (Map.Entry<Field, FieldDescriptor> entry : fieldStringEntry.getValue().getJoinedFields().entrySet()) {
                        Field field1 = entry.getKey();
                        field1.setAccessible(true);
                        field1.set(joinedObj, assignValue(cursor, entry.getValue()));
                    }

                } else {
                    Field field = fieldStringEntry.getKey();
                    field.setAccessible(true);
                    field.set(obj, assignValue(cursor, fieldStringEntry.getValue()));
                }
            }
            return obj;
        } catch (Exception e) {
            throw new CromException("Cursor to object population failed", e);
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

    private FieldDescriptor obtainColumnName(Field field) {
        return new FieldDescriptor(field, field.getAnnotation(Column.class));
    }

}
