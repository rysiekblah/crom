package com.rysiekblah.crom;

import android.database.Cursor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by tomek on 4/28/14.
 */
public class CroBulk {

    private static final Map<Class<?>, Cro<?>> CACHED_CROS = Maps.newHashMap();

    @SuppressWarnings("unchecked")
    private static synchronized  <T> Cro<T> getCroFor(Class<T> cro) {
        Cro<T> croObj = (Cro<T>) CACHED_CROS.get(cro);
        if(croObj == null) {
            croObj = new Cro<T>(cro);
            CACHED_CROS.put(cro, croObj);
        }
        return croObj;
    }

    public static <T> List<T> convert(Cursor cursor, Class<T> clazz) {
        List<T> rows = Lists.newArrayList();
        Cro<T> cro = getCroFor(clazz);
        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {
            rows.add(cro.populate(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return rows;
    }

}
