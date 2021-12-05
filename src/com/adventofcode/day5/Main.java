package com.adventofcode.day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Advent of Code - Day 5");
        Main main = new Main();
        List<String> input = new ArrayList<>();
        try {
            input = main.loadDataFromFile("/input5.txt");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        Map map = new Map();
        List<Vent> vents = main.firstPart(map, input);
        System.out.println("======================");
        main.secondPart(map, vents);
    }

    private List<String> loadDataFromFile(String fileName) throws IOException, URISyntaxException {
        Stream<String> input = Files.lines(Path.of(this.getClass().getResource(fileName).toURI()));
        Iterator<String> it = input.iterator();
        List<String> data = new ArrayList<>();
        while (it.hasNext()) {
            data.add(it.next());
        }
        data.forEach(System.out::println);
        return data;
    }

    private List<Vent> firstPart(Map map, List<String> input) {
        List<Vent> vents = new ArrayList<>();
        Iterator<String> iterator = input.iterator();
        while(iterator.hasNext()) {
            vents.add(new Vent(iterator.next()));
        }
        vents.forEach(System.out::println);
        vents.forEach(map::putVent);
        System.out.println(map.toString());
        System.out.println(map.countDangerousSpot());
        return vents;
    }

    private void secondPart(Map map, List<Vent> vents) {
        vents.forEach(map::putDiagonal);
        System.out.println(map.toString());
        System.out.println(map.countDangerousSpot());
    }
}
