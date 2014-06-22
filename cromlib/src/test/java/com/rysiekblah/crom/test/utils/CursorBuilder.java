package com.rysiekblah.crom.test.utils;

import android.database.Cursor;
import android.database.MatrixCursor;

import java.util.List;

/**
 * Created by tomek on 6/21/14.
 */
public class CursorBuilder {

    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";
    public static final String NAME3 = "name3";
    public static final String NAME4 = "name4";
    public static final String NAME5 = "name5";
    public static final String NAME6 = "name6";

    public static Cursor createBasic() {
        MatrixCursor cursor = new MatrixCursor(new String[]{"_id", "name"});
        cursor.addRow(new String[] {"1", NAME1});
        cursor.addRow(new String[] {"2", NAME2});
        cursor.addRow(new String[] {"3", NAME3});
        cursor.addRow(new String[] {"4", NAME4});
        cursor.addRow(new String[] {"5", NAME5});
        cursor.addRow(new String[] {"6", NAME6});
        return cursor;
    }

    public static Cursor createTableNames() {
        MatrixCursor cursor = new MatrixCursor(new String[]{"_id", "name"});
        cursor.addRow(new String[] {"1", NAME1});
        cursor.addRow(new String[] {"2", NAME2});
        cursor.addRow(new String[] {"3", NAME2});
        cursor.addRow(new String[] {"4", NAME3});
        cursor.addRow(new String[] {"5", NAME3});
        cursor.addRow(new String[] {"6", NAME3});
        cursor.addRow(new String[] {"7", NAME4});
        cursor.addRow(new String[] {"8", NAME4});
        cursor.addRow(new String[] {"9", NAME4});
        cursor.addRow(new String[] {"10", NAME4});
        return cursor;
    }

    public static Cursor createEmployeeTable() {
        MatrixCursor cursor = new MatrixCursor(new String[]{"_id", "name", "salaries"});
        cursor.addRow(new Object[] {1, "Tomek", 123});
        cursor.addRow(new Object[] {2, "Ola", 321});
        return cursor;
    }

    public static Cursor create(String[] columns, List<Object[]> rows) {
        MatrixCursor cursor = new MatrixCursor(columns);
        for (Object[] row : rows) {
            cursor.addRow(row);
        }
        return cursor;
    }

    public static Cursor create(String[] columns, Object[] row) {
        MatrixCursor cursor = new MatrixCursor(columns);
        cursor.addRow(row);
        return cursor;
    }

}
