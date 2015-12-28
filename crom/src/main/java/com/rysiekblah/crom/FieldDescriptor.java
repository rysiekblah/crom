package com.rysiekblah.crom;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.rysiekblah.crom.annotation.Column;
import com.rysiekblah.crom.annotation.Embedded;
import com.rysiekblah.crom.annotation.OneToMany;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by tomek on 4/28/14.
 */
public class FieldDescriptor {

    private String name;
    private Class<?> type;
    private FieldAbstract fieldAbstract;
    private boolean isJoined;
    private Class<?> joinedClass;
    private Map<Field, FieldDescriptor> fields;
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

    public FieldDescriptor(Field field1, OneToMany columnList) {
        fields = Maps.newHashMap();
        if (IS_LIST.apply(field1.getType())) {
            joinedClass = (Class<?>) (((ParameterizedType) field1.getGenericType()).getActualTypeArguments()[0]);
            isJoined = true;
            for (Field field : joinedClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    fields.put(field, new FieldDescriptor(field, field.getAnnotation(Column.class)));
                } else if (field.isAnnotationPresent(OneToMany.class)) {
                    throw new CromException("Nested OneToMany not supported");
                } else if (field.isAnnotationPresent(Embedded.class)) {
                    throw new CromException("Embedded annotation not implemented yet");
                }
            }
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

    public Map<Field, FieldDescriptor> getJoinedFields() {
        return fields;
    }
}
