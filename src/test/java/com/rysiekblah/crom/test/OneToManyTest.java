package com.rysiekblah.crom.test;

import android.database.Cursor;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.rysiekblah.crom.Cro;
import com.rysiekblah.crom.annotation.Column;
import com.rysiekblah.crom.annotation.OneToMany;
import com.rysiekblah.crom.test.utils.CursorBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by tomek on 9/1/14.
 */
@RunWith(RobolectricTestRunner.class)
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
    }

    @Test
    public void test() {
        List<Object[]> rows = Lists.newArrayList();
        rows.add(new Object[]{1, "Tomek", "12", 111, "fff"});

        Cursor cursor = CursorBuilder.create(new String[]{"customerId", "name", "country", "orderId", "orderDesc"}, rows);
        cursor.moveToFirst();
        Cro cro = new Cro(AllClientsOrders.class);

        AllClientsOrders o = (AllClientsOrders) cro.populate(cursor);
        System.out.printf(o.toString());
    }

}
