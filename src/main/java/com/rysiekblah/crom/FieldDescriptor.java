package com.rysiekblah.crom;

import com.google.common.base.Predicate;
import com.rysiekblah.crom.annotation.Column;
import com.rysiekblah.crom.annotation.OneToMany;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by tomek on 4/28/14.
 */
public class FieldDescriptor {

    private String name;
    private Class<?> type;
    private FieldAbstract fieldAbstract;
    private boolean isJoined;
    private Class<?> joinedClass;
    private static final Predicate IS_LIST = new Predicate<Class<?>>() {
        @Override
        public boolean apply(Class<?> input) {
            return input == List.class;
        }
    };

    public FieldDescriptor(Field field, Column column) {
        if (column.name().isEmpty()) {
            this.name = field.getName();
        } else {
            this.name = column.name();
        }
        type = field.getType();
        fieldAbstract = Crom.fieldTypes.get(field.getType());
    }

    public FieldDescriptor(Field field, OneToMany columnList) {
        if (IS_LIST.apply(field.getType())) {
            joinedClass = (Class<?>)(((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);
            isJoined = true;
        } else {
            throw new CromException("Annotated field by @OneToMany is not a List.");
        }
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public FieldAbstract getFieldAbstract() {
        return fieldAbstract;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public Class<?> getJoinedClass() {
        return joinedClass;
    }
}
