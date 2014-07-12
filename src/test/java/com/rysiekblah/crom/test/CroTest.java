package com.rysiekblah.crom.test;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.Cro;
import com.rysiekblah.crom.CromException;
import com.rysiekblah.crom.test.pojo.Car;
import com.rysiekblah.crom.test.pojo.Employee;
import com.rysiekblah.crom.test.pojo.MultiType;
import com.rysiekblah.crom.test.pojo.UnsupportedTypes;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.math.BigDecimal;
import java.util.List;


import static org.fest.assertions.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testCarTable() {

        Cursor cursor = CursorBuilder.create(
                new String[] {"make", "model", "year", "milage"},
                new Object[] {"Toyota", "Yaris", 2000, 150000});
        cursor.moveToFirst();
        Cro cro = new Cro(Car.class);
        Car car = (Car) cro.populate(cursor);
        assertEquals("Toyota", car.getMake());
        assertEquals("Yaris", car.getModel());
        assertEquals(2000, car.getYear().intValue());
        assertEquals(150000, car.getMilage().intValue());

    }

    @Test
    public void testMultipleTypes() {
        Cursor cursor = CursorBuilder.create(
                new String[]{
                        "shortObjData", "shortData", "intObjData", "intData", "boolObjData", "boolData",
                        "doubleObjData", "doubleData", "floatObjData", "floatData", "longObjData", "longData",
                        "stringObjData", "blobData"
                },
                new Object[]{
                        1, 2, 333, 444, true, false,
                        12.33, 33.12, 22.11, 11.22, 123, 321,
                        "TESTDATA", new byte[] {1, 2, 'A', ' '}
                }
        );
        cursor.moveToFirst();
        Cro cro = new Cro(MultiType.class);
        MultiType multiType = (MultiType) cro.populate(cursor);

        assertNotNull(multiType);
        assertEquals(1, multiType.getShortObjData().intValue());
        assertEquals(2, (int) multiType.getShortData());
        assertEquals(333, multiType.getIntObjData().intValue());
        assertEquals(444, multiType.getIntData());
        assertTrue(multiType.getBoolObjData());
        assertFalse(multiType.isBoolData());
        assertEquals(Double.valueOf(12.33), multiType.getDoubleObjData());
        assertEquals(Double.valueOf(33.12), Double.valueOf(multiType.getDoubleData()));
        assertEquals(Float.valueOf((float)22.11), multiType.getFloatObjData());
        assertEquals(Float.valueOf((float)11.22), Float.valueOf(multiType.getFloatData()));
        assertEquals(Long.valueOf(123), multiType.getLondObjData());
        assertEquals(Long.valueOf(321), Long.valueOf(multiType.getLongData()));
        assertEquals("TESTDATA", multiType.getStringObjData());
        assertThat(multiType.getBlobData()).contains((byte)1, (byte)2, (byte)'A', (byte)' ');
    }

    @Test(expected = CromException.class)
    public void testUnsupportedTypes() {
        Cursor cursor = CursorBuilder.create(
                new String[]{ "bigDecimal" },
                new BigDecimal[]{ BigDecimal.ONE }
        );
        cursor.moveToFirst();
        Cro cro = new Cro(UnsupportedTypes.class);
        UnsupportedTypes unsupportedTypes = (UnsupportedTypes) cro.populate(cursor);
    }

    @Test
    public void testToContentValues() {
        Car car = new Car("make", "model", 1234, 9000);
        Cro cro = new Cro(Car.class);
        ContentValues contentValues = cro.toContentValues(car);
        assertEquals("model", contentValues.getAsString("model"));
        assertEquals("make", contentValues.getAsString("make"));
        assertEquals(1234, contentValues.getAsInteger("year").intValue());
        assertEquals(9000, contentValues.getAsInteger("milage").intValue());
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

        Cro cro = new Cro(MultiType.class);
        ContentValues cv = cro.toContentValues(multiType);

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

}
