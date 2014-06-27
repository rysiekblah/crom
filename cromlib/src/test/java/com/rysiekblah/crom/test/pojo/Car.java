package com.rysiekblah.crom.test.pojo;

import com.rysiekblah.crom.Types;
import com.rysiekblah.crom.annotation.Column;

/**
 * Created by tomek on 6/27/14.
 */
public class Car {

    @Column(type = Types.COLUMN_TYPE_STRING)
    private String make;

    @Column(type = Types.COLUMN_TYPE_STRING)
    private String model;

    @Column(type = Types.COLUMN_TYPE_INTEGER)
    private Integer year;

    @Column(type = Types.COLUMN_TYPE_INTEGER)
    private Integer milage;

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMilage() {
        return milage;
    }
}
