package com.rysiekblah.crom.test;

import android.database.Cursor;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.CroBulk;
import com.rysiekblah.crom.test.pojo.Employee;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by tomek on 6/21/14.
 */
@RunWith(RobolectricTestRunner.class)
public class CroBulkTest {

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
        //CroBulk croBulk = new CroBulk(cursor);
        List<Employee> employees = CroBulk.convert(cursor, Employee.class);

        assertThat(employees).hasSize(4)
                .contains(tomek, ola, miki, magda);

    }

}