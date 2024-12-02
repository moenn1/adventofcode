package org.advent.day2;

import org.advent.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * The unusual data (your puzzle input) consists of many reports, one report per line. Each report is a list of numbers called levels that are separated by spaces. For example:
 *
 * 7 6 4 2 1
 * 1 2 7 8 9
 * 9 7 6 2 1
 * 1 3 2 4 5
 * 8 6 4 4 1
 * 1 3 6 7 9
 * This example data contains six reports each containing five levels.
 *
 * The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety systems can only tolerate levels that are either gradually increasing or gradually decreasing. So, a report only counts as safe if both of the following are true:
 *
 * The levels are either all increasing or all decreasing.
 * Any two adjacent levels differ by at least one and at most three.
 * In the example above, the reports can be found safe or unsafe by checking those rules:
 *
 * 7 6 4 2 1: Safe because the levels are all decreasing by 1 or 2.
 * 1 2 7 8 9: Unsafe because 2 7 is an increase of 5.
 * 9 7 6 2 1: Unsafe because 6 2 is a decrease of 4.
 * 1 3 2 4 5: Unsafe because 1 3 is increasing but 3 2 is decreasing.
 * 8 6 4 4 1: Unsafe because 4 4 is neither an increase or a decrease.
 * 1 3 6 7 9: Safe because the levels are all increasing by 1, 2, or 3.
 * So, in this example, 2 reports are safe.
 */

public class Day2Part1 {

    static int day2part1(List<List<Integer>> input) {
        return (int) input
                .stream()
                .filter(Day2Part1::isSafeList)
                .count();
    }

    static boolean isSafeList(List<Integer> list) {
        if (list.size() < 3) return true;

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < list.size(); i++) {
            int diff = list.get(i) - list.get(i-1);

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }
            if (diff > 0) decreasing = false;
            if (diff < 0) increasing = false;
        }

        return increasing || decreasing;
    }


    public static void main(String[] args) throws IOException {
        List<String> input = Util.readInput("C:\\Users\\Mohamed Enn\\Desktop\\adventofcode2024\\src\\main\\java\\org\\advent\\day2\\inputs.txt");
        List<List<Integer>> inputList = new ArrayList<>();
        for(String s: input){
            String[] numbers = s.split("\\s+");
            inputList.add(Arrays.stream(numbers).map(Integer::parseInt).toList());
        }
        System.out.println(day2part1(inputList));
    }
}
