package com.adventofcode.day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    int basin;
    private Set<String> visited;
    private List<Integer> basins = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Hello Advent of Code - Day 9");
        Main main = new Main();
        List<String> input = new ArrayList<>();
        try {
            input = main.loadDataFromFile("/input9.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        main.process(input);
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

    private void process(List<String> l) {
        int risk = 0;
        for (int j = 0 ; j < l.size();j++) {
            for (int i = 0 ; i < l.get(j).length(); i++) {
                if (isLowPoint(l, i,j)) {
                    basin = 0;
                    visited = new HashSet<>();
                    countBasinSize(l, i,j);
                    basins.add(basin);
                }
                risk += getRisk(l, i, j);
            }
        }
        Collections.sort(basins, Collections.reverseOrder());

        System.out.println("Sum of the risk levels: " + risk);
        var top = basins.subList(0,3);
        System.out.println("Multipying size three largest basins " + top + ":" + top.stream().reduce(1, (a,b) -> a*b));
    }

    private String getKey(int i, int j) {
        return i + ":" + j;
    }

    private void countBasinSize(List<String> a, int i, int j) {
        String key = getKey(i,j);
        if (visited.contains(key)) return;
        visited.add(key);
        int me = getAt(a, i,j);
        if (me < 9) {
            basin++;
            countBasinSize(a,i,j-1);
            countBasinSize(a, i, j+1);
            countBasinSize(a, i-1, j);
            countBasinSize(a, i+1, j);
        }
    }

    private Integer getAt(List<String> a, int i, int j) {
        if (j < 0 || j >= a.size()) return 9;
        if (i < 0) return 9;
        String s = a.get(j);
        if (i >= s.length()) return 9;
        return s.charAt(i) -'0';
    }

    private int getRisk(List<String> a, int i, int j) {
        int me = getAt(a, i,j),
                n = getAt(a,i,j-1),
                s = getAt(a, i, j+1),
                w = getAt(a, i-1, j),
                e = getAt(a, i+1, j);
        if  (me < n && me < s && me < w && me < e) return me + 1;
        return 0;
    }

    private boolean isLowPoint(List<String> a, int i, int j) {
        int me = getAt(a, i,j),
                n = getAt(a,i,j-1),
                s = getAt(a, i, j+1),
                w = getAt(a, i-1, j),
                e = getAt(a, i+1, j);
        return me < n && me < s && me < w && me < e;
    }
}
