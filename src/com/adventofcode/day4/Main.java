package com.adventofcode.day4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Advent of Code - Day 4");
        Main main = new Main();
        List<Integer> numbers = null;
        List<Board> boardsFirstPart = null;
        List<Board> boardsSecondPart = null;
        try {
            numbers = main.loadNumbersFromFile("input4a.txt");
            System.out.println("------------------");
            boardsFirstPart = main.loadBoardsFromFile("input4b.txt");
            boardsSecondPart = main.loadBoardsFromFile("input4b.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        System.out.println("=============================");

        String winnerPair = main.findFirstBoardAndNumber(numbers, boardsFirstPart);
        int winnerBoardIndex = Integer.parseInt(winnerPair.split(",")[0]);
        int lastNumberIndex = Integer.parseInt(winnerPair.split(",")[1]);
        Board winnerBoard = boardsFirstPart.get(winnerBoardIndex);
        int lastNumber = numbers.get(lastNumberIndex);
        int sumOfNonMarkedValuesWinBoard = winnerBoard.getSumOfNonMarkedValues();
        System.out.println("Winner board index: " + winnerBoardIndex);
        System.out.println("Winner board: \n" + boardsFirstPart.get(winnerBoardIndex).toString());
        System.out.println("Last number: " + lastNumber);
        System.out.println("Sum of non marked values: " + sumOfNonMarkedValuesWinBoard);
        System.out.println("Multiplying with last number: " + sumOfNonMarkedValuesWinBoard * lastNumber);

        System.out.println("=============================");

        int lastNumberIndexBeforeLosing = main.findLastBoardAndNumber(numbers, boardsSecondPart);
        int sumOfNonMarkedValuesLostBoard = boardsSecondPart.get(0).getSumOfNonMarkedValues();
        System.out.println("Last board: \n" + boardsSecondPart.get(0));
        System.out.println("Last number before losing: " + numbers.get(lastNumberIndexBeforeLosing));
        System.out.println("Sum of non marked values: " + sumOfNonMarkedValuesLostBoard);
        System.out.println("Multiplying with last number: " + sumOfNonMarkedValuesLostBoard * numbers.get(lastNumberIndexBeforeLosing));

    }

    private List<Integer> loadNumbersFromFile(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner scanner = new Scanner(new FileInputStream(input));
        List<Integer> data = new ArrayList<>();
        if (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            for (int i = 0; i < row.split(",").length; i++) {
                data.add(Integer.valueOf(row.split(",")[i]));
            }
            data.forEach(System.out::println);
        }
        return data;
    }

    private List<Board> loadBoardsFromFile(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner scanner = new Scanner(new FileInputStream(input));
        List<Board> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (scanner.hasNextLine()) {
                System.out.println("adding row");
                data.add(this.loadSingleBoard(scanner));
                try {
                    if (scanner.nextLine().equals("")) {
                        continue;
                    }
                } catch (NoSuchElementException e2) {

                }
            }
        }

        return data;
    }

    private Board loadSingleBoard(Scanner scanner) throws FileNotFoundException {
        Pattern p = Pattern.compile("\\d+");
        Board singleBoard = new Board();
        for (int i = 0; i < 5; i++) {
            String row = scanner.nextLine();
            System.out.println(row);
            Matcher matcher = p.matcher(row);
            List<Integer> values = new ArrayList<>();
            while (matcher.find()) {
                values.add(Integer.parseInt(matcher.group()));
            }
            singleBoard.fillMatrixRow(i, values);
        }
        return singleBoard;
    }

    private String findFirstBoardAndNumber(List<Integer> numbers, List<Board> boards) {
        String winnerPair = "";
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < boards.size(); j++) {
                if (boards.get(j).checkIfNumberExists(numbers.get(i))) {
                    if (boards.get(j).checkIfAnyColumnHasValueOver10k() ||
                            boards.get(j).checkIfAnyRowHasValueOver10k()) {
                        winnerPair += j + ",";
                        winnerPair += i;
                        return winnerPair;
                    }
                }
            }
        }
        return winnerPair;
    }

    private int findLastBoardAndNumber(List<Integer> numbers, List<Board> boards) {
        int i = 0;
        for (i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < boards.size(); j++) {
                if (boards.get(j).checkIfNumberExists(numbers.get(i))) {
                    if (boards.get(j).checkIfAnyColumnHasValueOver10k() ||
                            boards.get(j).checkIfAnyRowHasValueOver10k()) {
                        boards.remove(j);
                        j = 0;
                        if (boards.size() == 1) {
                            for (int k = 0; k < numbers.size(); k++) {
                                boards.get(0).checkIfNumberExists(numbers.get(k));
                                if (boards.get(j).checkIfAnyColumnHasValueOver10k() ||
                                        boards.get(j).checkIfAnyRowHasValueOver10k()) {
                                    return k;
                                }
                            }
                        }
                    }
                }
            }
        }
        return i;
    }
}
