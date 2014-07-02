package com.rysiekblah.crom;

import android.database.Cursor;

import com.rysiekblah.crom.annotation.Column;

import java.lang.reflect.Field;

/**
 * Created by tomek on 4/28/14.
 */
public class FieldDescriptor {

    private String name;
    private int type;
    private FieldAbstract fieldAbstract;

    public FieldDescriptor(Field field, Column column) {
        if (column.name().isEmpty()) {
            this.name = field.getName();
        } else {
            this.name = column.name();
        }

        fieldAbstract = createFieldAbstract(field.getType());
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public FieldAbstract getFieldAbstract() {
        return fieldAbstract;
    }

    private FieldAbstract createFieldAbstract(Class<?> clazz) {
        if (clazz.isAssignableFrom(String.class)) {
            return new FieldAbstract<String>() {
                @Override
                public String getData(Cursor cursor, int index) {
                    return cursor.getString(index);
                }
            };
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return new FieldAbstract<Integer>() {
                @Override
                public Integer getData(Cursor cursor, int index) {
                    return cursor.getInt(index);
                }
            };
        } else if (clazz.isAssignableFrom(int.class)) {
            return new FieldAbstract<Integer>() {
                @Override
                public Integer getData(Cursor cursor, int index) {
                    return cursor.getInt(index);
                }
            };
        }
        return null;
    }
}
