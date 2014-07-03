package com.rysiekblah.crom;

import android.database.Cursor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tomek on 4/28/14.
 */
public class Crom {

    private final HashMap<Class<?>, Cro<?>> cache = Maps.newHashMap();

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
