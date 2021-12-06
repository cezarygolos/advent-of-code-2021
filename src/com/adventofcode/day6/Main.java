package com.adventofcode.day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Advent of Code - Day 6");
        Main main = new Main();
        List<String> input = new ArrayList<>();
        try {
            input = main.loadDataFromFile("/input6.txt");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        main.firstPart(input);
        main.secondPart(input);
        main.secondPartEfficientWay(input);
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
        long start = System.currentTimeMillis();
        System.out.println("Initial state: " + input);
        List<Lanternfish> fishes = new ArrayList<>();
        input.forEach(timer -> fishes.add(new Lanternfish(Integer.parseInt(timer))));
        for (int day = 1; day < 81; day++) {
            List<Lanternfish> newFishes = new ArrayList<>();
            fishes.stream().filter(fish -> fish.getTimer() == 0).forEach(fish -> newFishes.add(fish.produceNewOne()));
            fishes.forEach(Lanternfish::decreaseTimer);
            fishes.addAll(newFishes);
            System.out.println(day + ", fishes: " + fishes.size());
        }
        System.out.println("Total fish: " + fishes.size());
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end-start) + "ms");
    }

    private void secondPart(List<String> input) {
        System.out.println("Initial state: " + input);
        List<Integer> lanternfishes = new ArrayList<>();
        input.forEach(i -> lanternfishes.add(Integer.parseInt(i)));
        List<Integer> fishes = lanternfishes;
        for (int day = 1; day < 258; day++) {
            System.out.println("Day: " + day + ", fishes: " + fishes.size());
            fishes = processDay(fishes);
        }
        System.out.println("Total fish: " + fishes.size());
    }

    private List<Integer> processDay(List<Integer> lanternfishes) {
        long a = System.currentTimeMillis();
        long parents = lanternfishes.stream().filter(fish -> fish==0).count();
        System.out.println("parents: " +parents);
        long b = System.currentTimeMillis();
        System.out.println("b-a:"+(b-a)+"ms");
        List<Integer> decreasedTimer = new ArrayList<>();
        long c = System.currentTimeMillis();
        System.out.println("c-b:"+(c-b)+"ms");

        for (Integer fish: lanternfishes) {
            if (fish > 0) {
                decreasedTimer.add(--fish);
            }
        }
        long d = System.currentTimeMillis();
        System.out.println("d-c:"+(d-c)+"ms");
        for (int i = 0; i < parents; i++) {
            decreasedTimer.add(8);
            decreasedTimer.add(6);
        }
        long e = System.currentTimeMillis();
        System.out.println("e-d:"+(e-d)+"ms");
        return decreasedTimer;
    }

    private void secondPartEfficientWay(List<String> input) {
        System.out.println("Initial state: " + input);
        List<Integer> lanternfishes = new ArrayList<>();
        input.forEach(i -> lanternfishes.add(Integer.parseInt(i)));
        long replicating = 0;
        long timer0 = lanternfishes.stream().filter(fish -> fish == 0).count();
        long timer1 = lanternfishes.stream().filter(fish -> fish == 1).count();
        long timer2 = lanternfishes.stream().filter(fish -> fish == 2).count();
        long timer3 = lanternfishes.stream().filter(fish -> fish == 3).count();
        long timer4 = lanternfishes.stream().filter(fish -> fish == 4).count();
        long timer5 = lanternfishes.stream().filter(fish -> fish == 5).count();
        long timer6 = lanternfishes.stream().filter(fish -> fish == 6).count();
        long timer7 = lanternfishes.stream().filter(fish -> fish == 7).count();
        long timer8 = lanternfishes.stream().filter(fish -> fish == 8).count();
        for (int day = 1; day < 256; day++) {
            timer0 = timer1;
            timer1 = timer2;
            timer2 = timer3;
            timer3 = timer4;
            timer4 = timer5;
            timer5 = timer6;
            timer6 = timer7+replicating;
            timer7 = timer8;
            timer8 = replicating;
            replicating = timer0;
            long allFish = replicating+timer0+timer1+timer2+timer3+timer4+timer5+timer6+timer7+timer8;
            System.out.println("Day: " + day + ", fish: " + allFish);
        }
    }

    private String printSimulation(List<Lanternfish> fishes) {
        StringBuilder line = new StringBuilder();
        fishes.forEach(fish -> line.append(fish.getTimer()).append(","));
        return line.toString();
    }
}
