package com.adventofcode.day1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Advent of Code - Day 1");
        Main main = new Main();
        main.firstPart();
        main.secondPart();
    }

    private int[] loadDataFromFile(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner scanner = new Scanner(new FileInputStream(input));
        int[] data = new int[2000];
        int i = 0;
        for (int singleRecord : data) {
            if (scanner.hasNextLine()) {
                data[i] = scanner.nextInt();
                i++;
            }
        }
        return data;
    }

    private void firstPart() {
        int[] input = new int[2000];
        try {
            input = this.loadDataFromFile("input1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int counter = 0;
        int i = 1;
        while (i < input.length) {
            System.out.println(input[i]);
            if (input[i] > input[i-1]) {
                counter++;
            }
            i++;
        }
        System.out.println("Measurements larger than previous: " + counter);
    }

    private void secondPart() {
        int[] input = new int[2000];
        try {
            input = this.loadDataFromFile("input1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int counter = 0;
        int i = 3;
        int sumPrev = 0;
        int sumNext = 0;
        while (i < input.length) {
            sumPrev = input[i-1] + input[i-2] + input[i-3];
            sumNext = input[i] + input[i-1] + input[i-2];
            if (sumNext > sumPrev) {
                counter++;
            }
            i++;
        }
        System.out.println("Three-measurements larger than previous: " + counter);
    }
}
