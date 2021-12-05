package com.adventofcode.day4;

import java.util.Arrays;
import java.util.List;

public class Board {

    private int[][] matrix;
    private int rowOver10k = 99;
    private int columnOver10k = 99;

    Board() {
        this.matrix = new int[5][5];
    }

    void fillMatrixRow(int rowIndex, List<Integer> values) {
        for (int i = 0; i < 5; i++) {
            this.matrix[rowIndex][i] = values.get(i);
        }
    }

    boolean checkIfNumberExists(int number) {
        boolean atLeastOneFound = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.matrix[i][j] == number) {
                    this.matrix[i][j]+=2000;
                    atLeastOneFound = true;
                }
            }
        }
        return atLeastOneFound;
    }

    boolean checkIfAnyColumnHasValueOver10k() {
        for (int col = 0; col < 5; col++) {
            int value = 0;
            int row = 0;
            for (row = 0; row < 5; row++) {
                value+=this.matrix[row][col];
                if (value > 10000) {
                    this.columnOver10k = col;
                    return true;
                }
            }
        }
        return false;
    }

    boolean checkIfAnyRowHasValueOver10k() {
        for (int row = 0; row < 5; row++) {
            int value = 0;
            int col = 0;
            for (col = 0; col < 5; col++) {
                value+=this.matrix[row][col];
                if (value > 10000) {
                    this.rowOver10k = row;
                    return true;
                }
            }
        }
        return false;
    }

    int getSumOfNonMarkedValues() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.matrix[i][j] < 100) {
                    sum+=this.matrix[i][j];
                }
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        return this.matrix[0][0] + " " + this.matrix[0][1] + " " + this.matrix[0][2] + " " + this.matrix[0][3] + " " + this.matrix[0][4] + "\n"
              +this.matrix[1][0] + " " + this.matrix[1][1] + " " + this.matrix[1][2] + " " + this.matrix[1][3] + " " + this.matrix[1][4] + "\n"
              +this.matrix[2][0] + " " + this.matrix[2][1] + " " + this.matrix[2][2] + " " + this.matrix[2][3] + " " + this.matrix[2][4] + "\n"
              +this.matrix[3][0] + " " + this.matrix[3][1] + " " + this.matrix[3][2] + " " + this.matrix[3][3] + " " + this.matrix[3][4] + "\n"
              +this.matrix[4][0] + " " + this.matrix[4][1] + " " + this.matrix[4][2] + " " + this.matrix[4][3] + " " + this.matrix[4][4] + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (rowOver10k != board.rowOver10k) return false;
        if (columnOver10k != board.columnOver10k) return false;
        return Arrays.deepEquals(matrix, board.matrix);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(matrix);
        result = 31 * result + rowOver10k;
        result = 31 * result + columnOver10k;
        return result;
    }
}
