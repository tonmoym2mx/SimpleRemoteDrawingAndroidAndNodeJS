package com.example.socketgameserver.customview;

public class XY_ {
    private float X_;
    private float Y_;

    public XY_(float x_, float y_) {
        X_ = x_;
        Y_ = y_;
    }
    public XY_() {

    }

    public float getX_() {
        return X_;
    }

    public void setX_(float x_) {
        X_ = x_;
    }

    public float getY_() {
        return Y_;
    }

    public void setY_(float y_) {
        Y_ = y_;
    }

    @Override
    public String toString() {
        return "XY_{" +
                "X_=" + X_ +
                ", Y_=" + Y_ +
                '}';
    }
}
