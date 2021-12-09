package com.adventofcode.day8;

import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

import static java.util.stream.Collectors.toSet;

public class Main {

    static final Set<String> ALL_SEGMENTS = Set.of("a", "b", "c", "d", "e", "f", "g");

    public static void main(String[] args) throws Exception {
        int total = 0;
        int total2 = 0;
        for (String line : Files.readAllLines(Path.of(Main.class.getResource("/input8.txt").toURI()))) {
            String[] parts = line.split(" \\| ");

            List<Set<String>> possibles = Arrays.stream(parts[0].split(" ")).map(s -> Arrays.stream(s.split("")).collect(toSet())).collect(toList());
            List<Set<String>> digits = Arrays.stream(parts[1].split(" ")).map(s -> Arrays.stream(s.split("")).collect(toSet())).collect(toList());

            Map<Integer, List<Set<String>>> bySegCount = possibles.stream().collect(groupingBy(Set::size));

            Set<Set<String>> uniques = Stream.of(2, 3, 4, 7).map(c -> bySegCount.get(c).get(0)).collect(toSet());
            total += digits.stream().filter(uniques::contains).count();

            Map<String, Long> segFrequencies = Arrays.stream(parts[0].replace(" ", "").split("")).collect(groupingBy(x -> x, Collectors.counting()));
            Map<Long, String> frequencyToSeg = segFrequencies.entrySet().stream().collect(toMap(Entry::getValue, Entry::getKey, (a, b) -> null));
            String wireForSegB = frequencyToSeg.get(6L);
            String wireForSegE = frequencyToSeg.get(4L);
            String wireForSegF = frequencyToSeg.get(9L);

            Set<String> wiresFor9 = new HashSet<>(ALL_SEGMENTS);
            wiresFor9.remove(wireForSegE);

            Set<String> wiresFor2 = new HashSet<>(ALL_SEGMENTS);
            wiresFor2.remove(wireForSegB);
            wiresFor2.remove(wireForSegF);

            Set<String> wiresFor3 = new HashSet<>(ALL_SEGMENTS);
            wiresFor3.remove(wireForSegB);
            wiresFor3.remove(wireForSegE);

            Set<Set<String>> possibleWiresFor5 = new HashSet<>(bySegCount.get(5));
            possibleWiresFor5.remove(wiresFor2);
            possibleWiresFor5.remove(wiresFor3);
            Set<String> wiresFor5 = possibleWiresFor5.iterator().next();

            Set<String> wiresFor6 = new HashSet<>(wiresFor5);
            wiresFor6.add(wireForSegE);

            Set<Set<String>> possibleWiresFor0 = new HashSet<>(bySegCount.get(6));
            possibleWiresFor0.remove(wiresFor6);
            possibleWiresFor0.remove(wiresFor9);
            Set<String> wiresFor0 = possibleWiresFor0.iterator().next();

            Map<Set<String>, String> decoder = new LinkedHashMap<>(Map.ofEntries(
                    Map.entry(wiresFor0, "0"),
                    Map.entry(bySegCount.get(2).get(0), "1"),
                    Map.entry(wiresFor2, "2"),
                    Map.entry(wiresFor3, "3"),
                    Map.entry(bySegCount.get(4).get(0), "4"),
                    Map.entry(wiresFor5, "5"),
                    Map.entry(wiresFor6, "6"),
                    Map.entry(bySegCount.get(3).get(0), "7"),
                    Map.entry(bySegCount.get(7).get(0), "8"),
                    Map.entry(wiresFor9, "9")
            ));

            total2 += Integer.parseInt(digits.stream().map(decoder::get).collect(Collectors.joining()));

        }
        System.out.println(total);
        System.out.println(total2);
    }
}
