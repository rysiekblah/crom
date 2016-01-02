package com.rysiekblah.crom.injector;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.util.Pair;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.Crom;

import java.util.List;
import java.util.Map;

/**
 * Created by tomek on 12/6/15.
 */
public class DataInjector {

    private Crom crom = new Crom();
    private ContentResolver contentResolver;

        public DataInjector(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public List<Uri> inject(Feed data) {
        data.toModel(crom);
        return insert(data.getModel());
    }

    private List<Uri> insert(Map<Class<?>, Pair<Uri, List<ContentValues>>> model) {
        List<Uri> uriList = Lists.newArrayList();
        for (Map.Entry<Class<?>, Pair<Uri, List<ContentValues>>> entry : model.entrySet()) {
            Uri uri = entry.getValue().first;
            for (ContentValues values : entry.getValue().second) {
                uriList.add(contentResolver.insert(uri, values));
            }
        }
        return uriList;
    }

    public List<Uri> injectUpdate(Feed feed) {

        return null;
    }

}
