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

    public MultiType() {

    }

    public MultiType(Short shortObjData, short shortData, Integer intObjData, int intData, Boolean boolObjData, boolean boolData, Double doubleObjData, double doubleData, Float floatObjData, float floatData, Long longObjData, long longData, String stringObjData, byte[] blobData) {
        this.shortObjData = shortObjData;
        this.shortData = shortData;
        this.intObjData = intObjData;
        this.intData = intData;
        this.boolObjData = boolObjData;
        this.boolData = boolData;
        this.doubleObjData = doubleObjData;
        this.doubleData = doubleData;
        this.floatObjData = floatObjData;
        this.floatData = floatData;
        this.longObjData = longObjData;
        this.longData = longData;
        this.stringObjData = stringObjData;
        this.blobData = blobData;
    }

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
