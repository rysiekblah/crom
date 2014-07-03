package com.rysiekblah.crom;

import android.database.Cursor;

/**
 * Created by tomek on 7/1/14.
 */
public abstract class FieldAbstract<T> {
    public abstract T getData(Cursor cursor, int index);
}
