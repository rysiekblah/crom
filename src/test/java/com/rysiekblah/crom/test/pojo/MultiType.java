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

    @Column
    private Double doubleObjData;
    @Column
    private double doubleData;

    @Column
    private Float floatObjData;
    @Column
    private float floatData;

    @Column
    private Long longObjData;
    @Column
    private long longData;

    @Column
    private String stringObjData;

    @Column
    private byte[] blobData;


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

    public Double getDoubleObjData() {
        return doubleObjData;
    }

    public double getDoubleData() {
        return doubleData;
    }

    public Float getFloatObjData() {
        return floatObjData;
    }

    public float getFloatData() {
        return floatData;
    }

    public Long getLondObjData() {
        return longObjData;
    }

    public long getLongData() {
        return longData;
    }

    public String getStringObjData() {
        return stringObjData;
    }

    public byte[] getBlobData() {
        return blobData;
    }
}
