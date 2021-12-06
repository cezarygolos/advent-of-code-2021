package com.adventofcode.day6;

public class Lanternfish {

    private int timer;
    private boolean producedNewLanternFishToday = false;

    public Lanternfish() {
        this.timer = 8;
    }

    public Lanternfish(int timer) {
        this.timer = timer;
    }

    Lanternfish produceNewOne() {
        this.timer = 6;
        this.producedNewLanternFishToday = true;
        return new Lanternfish();
    }

    void decreaseTimer() {
        if (!producedNewLanternFishToday) {
            this.timer--;
        }
        this.producedNewLanternFishToday = false;
    }

    public int getTimer() {
        return timer;
    }

    @Override
    public String toString() {
        return "Lanternfish{" +
                "timer=" + timer +
                '}';
    }
}
