package com.rysiekblah.crom.injector;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.util.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rysiekblah.crom.Crom;

import java.util.List;
import java.util.Map;

/**
 * Created by tomek on 12/8/15.
 */
public abstract class Feed {

    protected Map<Class<?>, Pair<Uri, List<ContentValues>>> model = Maps.newHashMap();

    public Feed(Pair<Class<?>, Uri> contractData[]) {
        for (Pair<Class<?>, Uri> pair : contractData) {
            model.put(pair.first, Pair.<Uri, List<ContentValues>>create(pair.second, Lists.<ContentValues>newArrayList()));
        }
    }

    public abstract void toModel(Crom crom) throws DataInjectException;

    public List<ContentValues> getContentValues(Class<?> klass) {
        return model.get(klass).second;
    }

    public Uri getUri(Class<?> klass) {
        return model.get(klass).first;
    }

    public Map<Class<?>, Pair<Uri, List<ContentValues>>> getModel() {
        return model;
    }

}
