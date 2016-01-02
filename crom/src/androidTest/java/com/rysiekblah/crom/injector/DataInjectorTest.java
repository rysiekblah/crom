package com.rysiekblah.crom.injector;

import android.content.ContentResolver;
import android.test.ProviderTestCase2;

import com.rysiekblah.crom.Crom;

/**
 * Created by tomek on 1/2/16.
 */
public class DataInjectorTest extends ProviderTestCase2<MyContentProvider> {
    private ContentResolver contentResolver;
    private Crom crom = new Crom();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        contentResolver = getMockContentResolver();
    }

    public DataInjectorTest() {
        super(MyContentProvider.class, MyContract.AUTHORITY);

    }
}
