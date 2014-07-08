package com.rysiekblah.crom;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by tomek on 7/8/14.
 */
public class CromLoader<T> extends AsyncTaskLoader<List<T>> {

    private Uri uri;
    private String[] projection;
    private String selection;
    private String[] selectionArgs;
    private String sortOrder;
    private Class<T> clazz;
    private Context context;

    public CromLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, Class<T> clazz) {
        super(context);
        this.context = context;
        this.uri = uri;
        this.projection = projection;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
        this.sortOrder = sortOrder;
        this.clazz = clazz;
    }

    @Override
    public List<T> loadInBackground() {
        Crom crom = new Crom();
        Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        return crom.cursorToPojoList(cursor, clazz);
    }
}
