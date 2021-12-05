package com.adventofcode.day5;

public class Vent {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    Vent(String input) {
        this.x1 = Integer.parseInt(input.split(" -> ")[0].split(",")[0]);
        this.y1 = Integer.parseInt(input.split(" -> ")[0].split(",")[1]);
        this.x2 = Integer.parseInt(input.split(" -> ")[1].split(",")[0]);
        this.y2 = Integer.parseInt(input.split(" -> ")[1].split(",")[1]);
    }

    int getX1() {
        return x1;
    }

    int getY1() {
        return y1;
    }

    int getX2() {
        return x2;
    }

    int getY2() {
        return y2;
    }

    @Override
    public String toString() {
        return "Vent{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }
}
