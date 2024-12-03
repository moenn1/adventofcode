package org.advent.day3;

import org.advent.util.Util;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * There are two new instructions you'll need to handle:
 *
 * The do() instruction enables future mul instructions.
 * The don't() instruction disables future mul instructions.
 * Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.
 *
 * For example:
 *
 * xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
 * This corrupted memory is similar to the example from before, but this time the mul(5,5) and mul(11,8) instructions are disabled because there is a don't() instruction before them. The other mul instructions function normally, including the one at the end that gets re-enabled by a do() instruction.
 *
 * This time, the sum of the results is 48 (2*4 + 8*5).
 *
 * Handle the new instructions; what do you get if you add up all of the results of just the enabled multiplications?
 */
public class Day3Part2 {
    static int day3part2(String input){
        int result = 0;
        final String regex = "(mul\\((-?\\d+),(-?\\d+)\\)|do\\(\\)|don't\\(\\))";
        final Matcher m = Pattern.compile(regex).matcher(input);
        // to track do and dont next iteration
        boolean enabled = true;
        while (m.find()) {
            String match = m.group(1);
            if (match.equals("do()"))
                enabled = true;
            else if (match.equals("don't()"))
                enabled = false;
            else if(enabled)
                result += Integer.parseInt(m.group(2))*Integer.parseInt(m.group(3));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String input = String.join("", Util.readInput("inputs.txt"));
        System.out.println(day3part2(input));
    }
}
