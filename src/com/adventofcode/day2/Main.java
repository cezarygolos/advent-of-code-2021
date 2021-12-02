package com.adventofcode.day2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Advent of Code - Day 2");
        Main main = new Main();
        main.firstPart();
        main.secondPart();
    }

    private String[] loadDataFromFile(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner scanner = new Scanner(new FileInputStream(input));
        String[] data = new String[1000];
        int i = 0;
        while (i < data.length) {
            if (scanner.hasNextLine()) {
                data[i] = scanner.nextLine();
                i++;
            }
        }
        return data;
    }

    private void firstPart() {
        String[] data = new String[1000];
        try {
            data = this.loadDataFromFile("input2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        int horizontalPostion = 0;
        int depthPosition = 0;
        String direction;
        int value;
        while (i < data.length) {
            direction = data[i].trim().split(" ")[0];
            value = Integer.valueOf(data[i].trim().split(" ")[1]);
            if (direction.equals("up")) {
                depthPosition-=value;
            } else if (direction.equals("down")) {
                depthPosition+=value;
            } else {
                horizontalPostion+=value;
            }
            i++;
        }
        System.out.println("Final depth position: " + depthPosition + " and forward position: " + horizontalPostion);
        System.out.println("Multiplying both: " + depthPosition*horizontalPostion);
    }

    private void secondPart() {
        String[] data = new String[1000];
        try {
            data = this.loadDataFromFile("input2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        int horizontalPostion = 0;
        int depthPosition = 0;
        int aim = 0;
        String direction;
        int value;
        while (i < data.length) {
            direction = data[i].trim().split(" ")[0];
            value = Integer.valueOf(data[i].trim().split(" ")[1]);
            if (direction.equals("up")) {
                aim-=value;
            } else if (direction.equals("down")) {
                aim+=value;
            } else {
                horizontalPostion+=value;
                depthPosition+=(value*aim);
            }
            i++;
        }
        System.out.println("Final depth position: " + depthPosition + " and forward position: " + horizontalPostion);
        System.out.println("Multiplying both: " + depthPosition*horizontalPostion);
    }
}
