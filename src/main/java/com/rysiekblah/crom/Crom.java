package com.rysiekblah.crom;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tomek on 4/28/14.
 */
public class Crom {

    private final HashMap<Class<?>, Cro<?>> cache = Maps.newHashMap();
    public final static ImmutableMap<Class<?>, FieldAbstract<?>> fieldTypes;

    static {
        FieldTypes.ShortField shortField = new FieldTypes.ShortField();
        FieldTypes.BlobField blobField = new FieldTypes.BlobField();
        FieldTypes.BooleanField booleanField = new FieldTypes.BooleanField();
        FieldTypes.DoubleField doubleField = new FieldTypes.DoubleField();
        FieldTypes.FloatField floatField = new FieldTypes.FloatField();
        FieldTypes.IntegerField integerField = new FieldTypes.IntegerField();
        FieldTypes.LongField longField = new FieldTypes.LongField();
        FieldTypes.StringField stringField = new FieldTypes.StringField();
        fieldTypes = new ImmutableMap.Builder<Class<?>, FieldAbstract<?>>()
                .put(short.class, shortField)
                .put(Short.class, shortField)
                .put(byte[].class, blobField)
                .put(boolean.class, booleanField)
                .put(Boolean.class, booleanField)
                .put(double.class, doubleField)
                .put(Double.class, doubleField)
                .put(float.class, floatField)
                .put(Float.class, floatField)
                .put(int.class, integerField)
                .put(Integer.class, integerField)
                .put(long.class, longField)
                .put(Long.class, longField)
                .put(String.class, stringField)
                .build();
    }

    public <T> T cursorToPojo(Cursor cursor, Class<T> clazz) {
        return getCro(clazz).populate(cursor);
    }

    public <T> List<T> cursorToPojoList(Cursor cursor, Class<T> clazz) {
        List<T> pojoList = Lists.newArrayList();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pojoList.add(cursorToPojo(cursor, clazz));
            cursor.moveToNext();
        }
        cursor.close();
        return pojoList;
    }

    public <T> List<ContentValues> toContentValuesList(List<T> pojoList) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public <T> ContentValues toContentValues(T pojo) {
        return getCro(pojo.getClass()).toContentValues(pojo);
    }

    @SuppressWarnings("unchecked")
    private <T> Cro<T> getCro(Class<T> clazz) {
        Cro<T> cro = (Cro<T>) cache.get(clazz);
        if (cro == null) {
            cro = new Cro<T>(clazz);
            cache.put(clazz, cro);
        }
        return cro;
    }

}
