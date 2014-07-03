package com.rysiekblah.crom.annotation;

import com.rysiekblah.crom.Types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tomek on 4/28/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    public String name() default "";
    public int type() default Types.TYPE_UNKNOWN;
}
