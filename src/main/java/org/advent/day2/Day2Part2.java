package org.advent.day2;

import org.advent.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day2Part2 {

    static int day2part2(List<List<Integer>> input) {
        return (int) input.stream()
                .filter(list -> isSafeList(list) || // if the list is safe
                        IntStream.range(0, list.size())         // if not we heuristically remove elements and check if the list becomes safe
                                .anyMatch(removeIndex -> {
                                    List<Integer> modifiedList = new ArrayList<>(list);
                                    modifiedList.remove(removeIndex);
                                    return isSafeList(modifiedList);
                                }))
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
        List<String> input = Util.readInput("inputs.txt");
        List<List<Integer>> inputList = new ArrayList<>();
        for(String s: input){
            String[] numbers = s.split("\\s+");
            inputList.add(Arrays.stream(numbers).map(Integer::parseInt).toList());
        }
        System.out.println(day2part2(inputList));
    }

}
