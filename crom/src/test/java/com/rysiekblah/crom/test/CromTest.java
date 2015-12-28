package com.rysiekblah.crom.test;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.BuildConfig;
import com.rysiekblah.crom.Crom;
import com.rysiekblah.crom.test.pojo.Employee;
import com.rysiekblah.crom.test.pojo.MultiType;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tomek on 6/27/14.
 */
@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(RobolectricGradleTestRunner.class)
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

    @Test
    public void testToContentValueeAllTypes() {
        Short shortObjData = 1;
        short shortData    = 2;
        Integer intObjData = 3;
        int intData        = 4;
        Boolean boolObjData= true;
        boolean boolData   = false;
        Double doubleObjData = 1.2345;
        double doubleData    = 2.3456;
        Float floatObjData   = new Float(3.4567);
        float floatData      = (float)4.5678;
        Long longObjData    = new Long(5);
        long longData       = (long)6;
        String stringObjData = "qwerty";
        byte[] blobData = new byte[] {1, 2, 'A', ' '};

        MultiType multiType = new MultiType(
                shortObjData, shortData, intObjData, intData, boolObjData, boolData, doubleObjData, doubleData, floatObjData, floatData,
                longObjData, longData, stringObjData, blobData
        );

        Crom crom = new Crom();

        ContentValues cv = crom.toContentValues(multiType);

        assertNotNull(cv);
        assertEquals(1, cv.getAsShort("shortObjData").shortValue());
        assertEquals(2, cv.getAsShort("shortData").shortValue());
        assertEquals(3, cv.getAsInteger("intObjData").intValue());
        assertEquals(4, cv.getAsInteger("intData").intValue());
        assertEquals(true, cv.getAsBoolean("boolObjData"));
        assertEquals(false, cv.getAsBoolean("boolData"));
        assertEquals(Double.valueOf(1.2345), cv.getAsDouble("doubleObjData"));
        assertEquals(Double.valueOf(2.3456), cv.getAsDouble("doubleData"));
        assertEquals(Float.valueOf((float)3.4567), cv.getAsFloat("floatObjData"));
        assertEquals(Float.valueOf((float)4.5678), cv.getAsFloat("floatData"));
        assertEquals(Long.valueOf(5), cv.getAsLong("longObjData"));
        assertEquals(Long.valueOf(6), cv.getAsLong("longData"));
        assertEquals("qwerty", cv.getAsString("stringObjData"));
        byte[] blob = cv.getAsByteArray("blobData");
        assertThat(blob).contains((byte)1, (byte)2, (byte)'A', (byte)' ');
    }

    @Test
    public void testToContentValuesList() {
        Employee employee1 = new Employee("A", 1);
        Employee employee2 = new Employee("B", 2);

        List<Employee> employees = Lists.newArrayList();
        employees.add(employee1);
        employees.add(employee2);

        Crom crom = new Crom();
        ContentValues[] values = crom.toContentValuesList(employees);

        assertNotNull(values);
        assertThat(values).hasSize(2);
        assertEquals("A", values[0].getAsString("name"));
        assertEquals(1, values[0].getAsInteger("salaries").intValue());

        assertEquals("B", values[1].getAsString("name"));
        assertEquals(2, values[1].getAsInteger("salaries").intValue());

    }

}
