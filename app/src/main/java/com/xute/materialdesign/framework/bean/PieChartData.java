package com.xute.materialdesign.framework.bean;

/**
 * Created by xute on 2017/1/3.
 */

public class PieChartData {
    //用户关心数据
    private String name;
    private float value;
    private float percentage;

    //非用户关心数据
    private int color = 0;
    private float angle = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
