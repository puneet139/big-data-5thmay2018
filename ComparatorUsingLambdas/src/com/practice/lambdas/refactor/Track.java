package com.practice.lambdas.refactor;

public class Track {
    private String name;
    private int length;

    public Track(String name, int len)
    {
        this.name=name;
        length=len;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
