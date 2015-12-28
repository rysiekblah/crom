package com.rysiekblah.crom.test;

import android.database.Cursor;

import com.google.common.collect.Lists;
import com.rysiekblah.crom.BuildConfig;
import com.rysiekblah.crom.Cro;
import com.rysiekblah.crom.annotation.Column;
import com.rysiekblah.crom.annotation.OneToMany;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by tomek on 9/1/14.
 */
@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(RobolectricGradleTestRunner.class)
public class OneToManyTest {

//    private static class Order {
//        private int orderId;
//        private int customerId;
//        private String orderDesc;
//    }
//
//    private static class Customer {
//        private int customerId;
//        private String name;
//        private String country;
//    }

    public static class ClientOrders {
        @Column
        private int orderId;
        @Column
        private String orderDesc;

        public ClientOrders() {

        }

        @Override
        public String toString() {
            return "ClientOrders{" +
                    "orderId=" + orderId +
                    ", orderDesc='" + orderDesc + '\'' +
                    '}';
        }

        public int getOrderId() {
            return orderId;
        }

        public String getOrderDesc() {
            return orderDesc;
        }
    }

    public static class AllClientsOrders {
        @Column
        private int customerId;
        @Column
        private String name;
        @Column
        private String country;
        @OneToMany
        private List<ClientOrders> orders = Lists.newArrayList();

        public AllClientsOrders() {

        }

        @Override
        public String toString() {
            return "AllClientsOrders{" +
                    "customerId=" + customerId +
                    ", name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    ", orders=" + orders.toString() +
                    '}';
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public List<ClientOrders> getOrders() {
            return orders;
        }
    }

    @Test
    public void test() {
        List<Object[]> rows = Lists.newArrayList();
        rows.add(new Object[]{1, "Tomek", "Krakow", 111, "beer"});

        Cursor cursor = CursorBuilder.create(new String[]{"customerId", "name", "country", "orderId", "orderDesc"}, rows);
        cursor.moveToFirst();
        Cro cro = new Cro(AllClientsOrders.class);

        AllClientsOrders o = (AllClientsOrders) cro.populate(cursor);

        assertEquals(1, o.getCustomerId());
        assertEquals("Tomek", o.getName());
        assertEquals("Krakow", o.getCountry());
        assertEquals(1, o.getOrders().size());
        assertEquals(111, o.getOrders().get(0).getOrderId());
        assertEquals("beer", o.getOrders().get(0).getOrderDesc());
    }

    //@Test
    public void testBulkJoin() {
        List<Object[]> rows = Lists.newArrayList();
        rows.add(new Object[]{1, "Tomek", "Krakow", 111, "beer"});
        rows.add(new Object[]{1, "Tomek", "Krakow", 112, "book"});
        rows.add(new Object[]{1, "Tomek", "Krakow", 113, "bread"});

        Cursor cursor = CursorBuilder.create(new String[]{"customerId", "name", "country", "orderId", "orderDesc"}, rows);
        cursor.moveToFirst();
        Cro cro = new Cro(AllClientsOrders.class);

        AllClientsOrders o = (AllClientsOrders) cro.populate(cursor);

        assertEquals(1, o.getCustomerId());
        assertEquals("Tomek", o.getName());
        assertEquals("Krakow", o.getCountry());
        assertEquals(3, o.getOrders().size());
        //assertEquals(111, o.getOrders().get(0).getOrderId());
        //assertEquals("beer", o.getOrders().get(0).getOrderDesc());
    }

}
