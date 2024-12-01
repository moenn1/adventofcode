package org.advent.day1;


import org.advent.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Day1Part1{
    /**
     * The idea is to sort the two lists, and add up the differences of each lList1[i]-rList1[i]
     */
    static int day1Part1(List<Integer> lList, List<Integer> rList){
        Collections.sort(lList);
        Collections.sort(rList);
        return IntStream.range(0, rList.size())
                .map((i)->Math.abs(lList.get(i)-rList.get(i)))
                .sum();
    }

    public static void main(String... args) throws IOException {
        List<String> input = Util.readInput("inputs.txt");
        List<Integer> lList = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        // split and add to respective lists
        for(String s: input){
            String[] numbers = s.split("\\s+");
            lList.add(Integer.parseInt(numbers[0]));
            rList.add(Integer.parseInt(numbers[1]));
        }
        System.out.println("Answer: " + day1Part1(lList, rList));
    }
}