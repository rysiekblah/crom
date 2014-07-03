package com.rysiekblah.crom.test.pojo;

import com.rysiekblah.crom.annotation.Column;

/**
 * Created by tomek on 7/1/14.
 */
public class MultiType {
    @Column
    private Short shortObjData;
    @Column
    private short shortData;

    @Column
    private Integer intObjData;
    @Column
    private int intData;

    @Column
    private Boolean boolObjData;
    @Column
    private boolean boolData;

//    @Column
//    private Double doubleObjData;
//    @Column
//    private double doubleData;
//
//    @Column
//    private Float floatObjData;
//    @Column
//    private float floatData;
//
//    @Column
//    private Long londObjData;
//    @Column
//    private long longData;
//
//    @Column
//    private String stringObjData;


    public Short getShortObjData() {
        return shortObjData;
    }

    public short getShortData() {
        return shortData;
    }

    public Integer getIntObjData() {
        return intObjData;
    }

    public int getIntData() {
        return intData;
    }

    public Boolean getBoolObjData() {
        return boolObjData;
    }

    public boolean isBoolData() {
        return boolData;
    }
}
