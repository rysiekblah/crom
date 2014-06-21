package com.rysiekblah.crom;

import android.database.Cursor;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by tomek on 4/28/14.
 */
public class Crom {

    public <T> T cursorToPojo(Cursor cursor, Class<T> clazz) {
        T pojo = null;

        return pojo;
    }

    public <T> List<T> cursorToPojoList(Cursor cursor, Class<T> clazz) {
        List<T> pojoList = Lists.newArrayList();

        return pojoList;
    }

}
