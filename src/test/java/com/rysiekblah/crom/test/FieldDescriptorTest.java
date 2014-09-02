package com.rysiekblah.crom.test;

import com.rysiekblah.crom.FieldDescriptor;
import com.rysiekblah.crom.annotation.OneToMany;
import com.rysiekblah.crom.test.pojo.Car;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 7/24/14.
 */
@RunWith(RobolectricTestRunner.class)
public class FieldDescriptorTest {

    private static class DummyClass {
        @OneToMany
        private List<Car> cars;
    }

    @Test
    public void testFieldList() {
        try {
            Field field = DummyClass.class.getDeclaredField("cars");
            FieldDescriptor fieldDescriptor = new FieldDescriptor(field, field.getAnnotation(OneToMany.class));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
