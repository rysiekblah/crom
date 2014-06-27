package com.rysiekblah.crom.test;

import android.database.Cursor;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.CroBulk;
import com.rysiekblah.crom.Crom;
import com.rysiekblah.crom.test.pojo.Employee;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by tomek on 6/27/14.
 */
@RunWith(RobolectricTestRunner.class)
public class CromTest {

    @Test
    public void test() {
        List<Object[]> rows = Lists.newArrayList();
        rows.add(new Object[]{1, "Tomek", 12});

        Cursor cursor = CursorBuilder.create(new String[]{"_id", "name", "salaries"}, rows);
        cursor.moveToFirst();

        Crom crom = new Crom();
        Employee employee = crom.cursorToPojo(cursor, Employee.class);
        assertEquals("Tomek", employee.getName());
        assertEquals(12, employee.getSalary().intValue());
    }

    @Test
    public void testEmployeeTable() {
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
        //List<Employee> employees = CroBulk.convert(cursor, Employee.class);

        Crom crom = new Crom();
        List<Employee> employees = crom.cursorToPojoList(cursor, Employee.class);

        assertThat(employees).hasSize(4)
                .contains(tomek, ola, miki, magda);

    }

}
