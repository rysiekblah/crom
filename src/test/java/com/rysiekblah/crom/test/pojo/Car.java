package com.rysiekblah.crom.test.pojo;

import com.rysiekblah.crom.annotation.Column;

/**
 * Created by tomek on 6/27/14.
 */
public class Car {

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private int year;

    @Column
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
