package com.rysiekblah.crom.injector;

import android.net.Uri;

/**
 * Created by tomek on 1/2/16.
 */
public class MyContract {
    public static final String AUTHORITY = "com.domain.app.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
}
