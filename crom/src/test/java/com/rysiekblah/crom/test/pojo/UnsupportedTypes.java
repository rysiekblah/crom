package com.rysiekblah.crom.test.pojo;

import com.rysiekblah.crom.annotation.Column;

import java.math.BigDecimal;

/**
 * Created by tomek on 7/5/14.
 */
public class UnsupportedTypes {
    @Column
    private BigDecimal bigDecimal;
}
