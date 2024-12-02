package org.advent.day2;

import org.advent.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Part2 {

    static int day2part2(List<List<Integer>> input) {
        int safeCount = 0;
        for (List<Integer> l1 : input) {
            if (isSafeList(l1)) {
                safeCount++;
                continue;
            }

            // remove each level and check if list safe
            for (int removeIndex = 0; removeIndex < l1.size(); removeIndex++) {
                List<Integer> modifiedList = new ArrayList<>(l1);
                modifiedList.remove(removeIndex);

                if (isSafeList(modifiedList)) {
                    safeCount++;
                    break;
                }
            }
        }
        return safeCount;
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
