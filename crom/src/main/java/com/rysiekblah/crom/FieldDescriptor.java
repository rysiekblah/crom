package com.rysiekblah.crom;

/**
 * Created by tomek on 4/28/14.
 */
public class FieldDescriptor {

    private String name;
    private int type;

    public FieldDescriptor(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
