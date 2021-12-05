package com.adventofcode.day5;

public class Map {

    private int[][] coordinates;

    Map() {
        this.coordinates = new int[999][999];
        for (int y = 0; y < this.coordinates.length; y++) {
            for (int x = 0; x < this.coordinates.length; x++) {
                this.coordinates[x][y] = 0;
            }
        }
    }

    void putVent(Vent vent) {
        if (vent.getX1() == vent.getX2()) {
            int x = vent.getX1();
            int y1 = vent.getY1();
            int y2 = vent.getY2();
            if (y2 > y1) {
                for (int y = y1; y < y2+1; y++) {
                    this.coordinates[x][y]+=1;
                }
            } else if (y1 > y2) {
                for (int y = y2; y < y1+1; y++) {
                    this.coordinates[x][y]+=1;
                }
            } else {
                this.coordinates[x][y1]+=1;
            }
        }
        if (vent.getY1() == vent.getY2()) {
            int y = vent.getY1();
            int x1 = vent.getX1();
            int x2 = vent.getX2();
            if (x2 > x1) {
                for (int x = x1; x < x2+1; x++) {
                    this.coordinates[x][y]+=1;
                }
            } else if (x1 > x2){
                for (int x = x2; x < x1+1; x++) {
                    this.coordinates[x][y]+=1;
                }
            } else {
                this.coordinates[x1][y]+=1;
            }
        }
    }

    void putDiagonal(Vent vent) {
        int x1 = vent.getX1();
        int y1 = vent.getY1();
        int x2 = vent.getX2();
        int y2 = vent.getY2();
        if ((x2 > x1) && (y2 > y1)) {
            if ((x2-x1) == (y2-y1)) {
                int i = 0;
                int x = x1;
                int y = y1;
                while(i < (x2-x1)+1) {
                    this.coordinates[x][y]+=1;
                    x++;
                    y++;
                    i++;
                }
            }
        }
        if ((x2 > x1) && (y1 > y2)) {
            if ((x2-x1) == (y1-y2)) {
                int i = 0;
                int x = x1;
                int y = y1;
                while(i < (x2-x1)+1) {
                    this.coordinates[x][y]+=1;
                    x++;
                    y--;
                    i++;
                }
            }
        }
        if ((x1 > x2) && (y2 > y1)) {
            if ((x1-x2) == (y2-y1)) {
                int i = 0;
                int x = x1;
                int y = y1;
                while(i < (x1-x2)+1) {
                    this.coordinates[x][y]+=1;
                    x--;
                    y++;
                    i++;
                }
            }
        }
        if ((x1 > x2) && (y1 > y2)) {
            if ((x1-x2) == (y1-y2)) {
                int i = 0;
                int x = x1;
                int y = y1;
                while(i < (x1-x2)+1) {
                    this.coordinates[x][y]+=1;
                    x--;
                    y--;
                    i++;
                }
            }
        }
    }

    int countDangerousSpot() {
        int spots = 0;
        for (int y = 0; y < this.coordinates.length; y++) {
            for (int x = 0; x < this.coordinates.length; x++) {
                if (this.coordinates[x][y] > 1) {
                    ++spots;
                }
            }
        }
        return spots;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();
        StringBuilder row = new StringBuilder();
        for (int y = 0; y < this.coordinates.length; y++) {
            for (int x = 0; x < this.coordinates.length; x++) {
                row.append(this.coordinates[x][y]);
            }
            matrix.append("\n").append(row);
            row = new StringBuilder();
        }
        System.out.println(matrix);
        return "";
    }
}
