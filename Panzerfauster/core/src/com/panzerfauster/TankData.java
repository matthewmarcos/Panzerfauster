package com.panzerfauster;

/**
 * Created by matt on 11/22/16.
 */

public class TankData {

    String username;
    int x, y;
    float angle;


    public TankData(String username, int x, int y, float angle) {
        this.username = username;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }


    public String getUsername() {
        return username;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public float getAngle() {
        return angle;
    }
}
