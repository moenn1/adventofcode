package org.advent.day1;

import org.advent.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This time, you'll need to figure out exactly how often each number from the left list appears in the right list.
 * Calculate a total similarity score by adding up each number in the left list after multiplying it
 * by the number of times that number appears in the right list.
 * Here are the same example lists again:
 * 3   4
 * 4   3
 * 2   5
 * 1   3
 * 3   9
 * 3   3
 * For these example lists, here is the process of finding the similarity score:
 * The first number in the left list is 3. It appears in the right list three times, so the similarity score increases by 3 * 3 = 9.
 * The second number in the left list is 4. It appears in the right list once, so the similarity score increases by 4 * 1 = 4.
 * The third number in the left list is 2. It does not appear in the right list, so the similarity score does not increase (2 * 0 = 0).
 * The fourth number, 1, also does not appear in the right list.
 * The fifth number, 3, appears in the right list three times; the similarity score increases by 9.
 * The last number, 3, appears in the right list three times; the similarity score again increases by 9.
 * So, for these example lists, the similarity score at the end of this process is 31 (9 + 4 + 0 + 0 + 9 + 9).
 *
 * My idea is to first construct a count map of the number of occurrences of each number of the left list in the right list
 * off that I can just do a simple multiplication of each key*value then sum them together
 */

/**
 * My idea is to first construct a count map of the number of occurrences of each number of the left list in the right list
 * off that I can just do a simple multiplication of each key*value then sum them together
 */
public class Day1Part2 {
    static int day1Part2(List<Integer> lList, List<Integer> rList) {
        Map<Integer, Integer> rightListCounts = new HashMap<>();
        for (Integer num : rList) {
            rightListCounts.put(num, rightListCounts.getOrDefault(num, 0) + 1);
        }

        return lList.stream()
                .mapToInt(num -> num * rightListCounts.getOrDefault(num, 0))
                .sum();
    }

    public static void main(String[] args) throws IOException {
        List<String> input = Util.readInput("C:\\Users\\Mohamed Enn\\Desktop\\adventofcode2024\\src\\main\\java\\org\\advent\\day1\\inputs.txt");
        List<Integer> lList = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        for(String s: input){
            String[] numbers = s.split("\\s+");
            lList.add(Integer.parseInt(numbers[0]));
            rList.add(Integer.parseInt(numbers[1]));
        }
        System.out.println("Answer: " + day1Part2(lList, rList));
    }
}
