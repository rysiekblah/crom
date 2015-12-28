package com.rysiekblah.crom;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Field;

/**
 * Created by tomek on 7/1/14.
 */
public abstract class FieldAbstract<T> {
    public abstract T getData(Cursor cursor, int index);

    public abstract <D> void decorate(Field field, D object, ContentValues contentValues, String fieldName) throws IllegalAccessException;

}
