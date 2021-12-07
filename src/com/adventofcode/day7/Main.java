package com.adventofcode.day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Advent of Code - Day 6");
        Main main = new Main();
        List<String> input = new ArrayList<>();
        try {
            input = main.loadDataFromFile("/input7.txt");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        main.firstPart(input);
    }

    private List<String> loadDataFromFile(String fileName) throws IOException, URISyntaxException {
        Stream<String> input = Files.lines(Path.of(this.getClass().getResource(fileName).toURI()));
        Iterator<String> it = input.iterator();
        List<String> data = new ArrayList<>();
        while (it.hasNext()) {
            data.add(it.next());
        }
        data.forEach(System.out::println);
        return Arrays.asList(data.get(0).split(","));
    }

    private void firstPart(List<String> input) {
        List<Integer> crabs = new ArrayList<>();
        input.forEach(crab -> crabs.add(Integer.parseInt(crab)));
        crabs.forEach(System.out::println);
        int leastFuelUse = Integer.MAX_VALUE;
        int bestPosition = 0;
        for (int focusPosition = 0; focusPosition < crabs.size(); focusPosition++) {
            int usedFuel = 0;
            for (int crabIndex = 0; crabIndex < crabs.size(); crabIndex++) {
                usedFuel += calculateFuel(Math.abs(focusPosition - crabs.get(crabIndex)));
            }
            if (usedFuel < leastFuelUse) {
                leastFuelUse = usedFuel;
                bestPosition = focusPosition;
            }
        }
        System.out.println("Best position to dig: " + bestPosition + ", used fuel: " + leastFuelUse);
    }

    private int calculateFuel(int positionsMoved) {
        int fuelUsed = 0;
        for (int i = 0; i < positionsMoved; i++) {
            int j = i;
            fuelUsed += ++j;
        }
        return fuelUsed;
    }
}
