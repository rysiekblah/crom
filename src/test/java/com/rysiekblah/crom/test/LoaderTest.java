package com.rysiekblah.crom.test;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.ContractData;
import com.rysiekblah.crom.CromLoader;
import com.rysiekblah.crom.test.pojo.Employee;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

/**
 * Created by tomek on 7/8/14.
 */
@RunWith(RobolectricTestRunner.class)
public class LoaderTest {
    private Application mApplication;
    @Before
    public void setUp() throws Exception {
        mApplication = Robolectric.application;
    }

    @Test
    public void test() {

        List<Object[]> rows = Lists.newArrayList();
        rows.add(new Object[]{1, "Tomek", 12});
        rows.add(new Object[]{2, "Ola", 13});
        rows.add(new Object[]{3, "Miki", 14});
        rows.add(new Object[]{4, "Magda", 15});

        Employee tomek = new Employee("Tomek", 12);
        Employee ola = new Employee("Ola", 13);
        Employee miki = new Employee("Miki", 14);
        Employee magda = new Employee("Magda", 15);

        Cursor cursor = CursorBuilder.create(new String[]{"_id", "name", "salaries"}, rows);

        Context context = mock(Context.class);
        ContentResolver resolver = mock(ContentResolver.class);

        when(resolver.query(any(Uri.class), any(String[].class), anyString(), any(String[].class), anyString())).thenReturn(cursor);
        when(context.getContentResolver()).thenReturn(resolver);

        CromLoader<Employee> loader = new CromLoader<Employee>(context, new ContractData(null, null, null, null, null), Employee.class);

        List<Employee> employees = loader.loadInBackground();

        assertThat(employees).hasSize(4)
                .contains(tomek, ola, miki, magda);

    }

}
