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

}
