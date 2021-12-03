package com.adventofcode.day3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Advent of Code - Day 3");
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
                data[i] = scanner.nextLine().trim();
                i++;
            }
        }
        return data;
    }

    private void firstPart() {
        String[] binary = new String[1000];
        try {
            binary = this.loadDataFromFile("input3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder gammaRate = new StringBuilder();
        int epsilonRate;
        StringBuilder bitsAtPosition = new StringBuilder();
        int i = 0;
        int j = 0;
        while (j < binary[0].trim().length()) {
            i = 0;
            bitsAtPosition = new StringBuilder();
            while (i < binary.length) {
                bitsAtPosition.append(binary[i].charAt(j));
                i++;
            }
            long zeros = bitsAtPosition.chars().filter(c -> c == '0').count();
            if (zeros > 500) {
                gammaRate.append('0');
            } else {
                gammaRate.append('1');
            }
            System.out.println(zeros);
            j++;
        }
        System.out.println("Binary format gamma rate: " + gammaRate);
        epsilonRate = ~Integer.parseInt(gammaRate.toString(), 2) & 0xFF;
        String binaryEpsilonRate = Integer.toBinaryString(epsilonRate);
        System.out.println("Binary format epsilon rate: " + binaryEpsilonRate);
        int decimalGammaRate = Integer.parseInt(gammaRate.toString(), 2);
        System.out.println("Decimal format gamma rate: " + decimalGammaRate);
        System.out.println("Decimal format epsilon rate: " + epsilonRate);
        System.out.println("Multiplying them: " + decimalGammaRate * epsilonRate);
    }

    private void secondPart() {
        List<String> data = new ArrayList<>();
        try {
            data = Arrays.asList(this.loadDataFromFile("input3.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> oxygenGeneratorRating = this.findOxygenGeneratorRating(data);
        oxygenGeneratorRating.forEach(System.out::println);
        List<String> co2ScrubberRating = this.findCO2ScrubberRating(data);
        co2ScrubberRating.forEach(System.out::println);
        System.out.println("Multiplying: " + Integer.parseInt(oxygenGeneratorRating.get(0), 2) * Integer.parseInt(co2ScrubberRating.get(0), 2));
    }

    private List<String> findOxygenGeneratorRating(List<String> data) {
        for (int i = 0; i < 12; i++) {
            final int j = i;
            long zeroCount = data.stream().filter(b -> b.charAt(j) == '0').count();
            long oneCount = data.stream().filter(b -> b.charAt(j) == '1').count();
            if (zeroCount > oneCount) {
                data = data.stream().filter(b -> b.charAt(j) == '0').collect(Collectors.toList());
            }
            else if (oneCount > zeroCount) {
                data = data.stream().filter(b -> b.charAt(j) == '1').collect(Collectors.toList());
            } else {
                data = data.stream().filter(b -> b.charAt(j) == '1').collect(Collectors.toList());
            }
            if (data.size() == 1) {
                return data;
            }
        }
        return data;
    }

    private List<String> findCO2ScrubberRating(List<String> data) {
        for (int i = 0; i < 12; i++) {
            final int j = i;
            long zeroCount = data.stream().filter(b -> b.charAt(j) == '0').count();
            long oneCount = data.stream().filter(b -> b.charAt(j) == '1').count();
            if (zeroCount < oneCount) {
                data = data.stream().filter(b -> b.charAt(j) == '0').collect(Collectors.toList());
            }
            else if (oneCount < zeroCount) {
                data = data.stream().filter(b -> b.charAt(j) == '1').collect(Collectors.toList());
            } else {
                data = data.stream().filter(b -> b.charAt(j) == '0').collect(Collectors.toList());
            }
            if (data.size() == 1) {
                return data;
            }
        }
        return data;
    }
}
