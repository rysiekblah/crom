package com.rysiekblah.crom.test;

import android.database.Cursor;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.Cro;
import com.rysiekblah.crom.test.pojo.Employee;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by tomek on 6/21/14.
 */
@RunWith(RobolectricTestRunner.class)
public class CroTest {

    @Test
    public void testEmployeeTable() {

        List<Object[]> rows = Lists.newArrayList();
        rows.add(new Object[]{1, "Tomek", 12});

        Cursor cursor = CursorBuilder.create(new String[]{"_id", "name", "salaries"}, rows);
        cursor.moveToFirst();
        Cro cro = new Cro(Employee.class);
        Employee employee = (Employee) cro.populate(cursor);
        System.out.println(employee.getName() + ", " + employee.getSalary());
        assertEquals("Tomek", employee.getName());
        assertEquals(12, employee.getSalary().intValue());
    }

}
