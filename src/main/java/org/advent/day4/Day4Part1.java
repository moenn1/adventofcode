package org.advent.day4;

import org.advent.util.Util;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other words. It's a little unusual, though, as you don't merely need to find one instance of XMAS - you need to find all of them. Here are a few ways XMAS might appear, where irrelevant characters have been replaced with .:
 *
 *
 * ..X...
 * .SAMX.
 * .A..A.
 * XMAS.S
 * .X....
 * The actual word search will be full of letters instead. For example:
 *
 * MMMSXXMASM
 * MSAMXMSMSA
 * AMXSXMAAMM
 * MSAMASMSMX
 * XMASAMXAMM
 * XXAMMXXAMA
 * SMSMSASXSS
 * SAXAMASAAA
 * MAMMMXMMMM
 * MXMXAXMASX
 * In this word search, XMAS occurs a total of 18 times; here's the same word search again, but where letters not involved in any XMAS have been replaced with .:
 *
 * ....XXMAS.
 * .SAMXMS...
 * ...S..A...
 * ..A.A.MS.X
 * XMASAMX.MM
 * X.....XA.A
 * S.S.S.S.SS
 * .A.A.A.A.A
 * ..M.M.M.MM
 * .X.X.XMASX
 * Take a look at the little Elf's word search. How many times does XMAS appear?
 */


public class Day4Part1 {
/**
 * MMMSXXMASM
 * MSAMXMSMSA
 * AMXSXMAAMM
 * MSAMASMSMX
 * XMASAMXAMM
 * XXAMMXXAMA
 * SMSMSASXSS
 * SAXAMASAAA
 * MAMMMXMMMM
 * MXMXAXMASX
 *
 * ....XXMAS.
 * .SAMXMS...
 * ...S..A...
 * ..A.A.MS.X
 * XMASAMX.MM
 * X.....XA.A
 * S.S.S.S.SS
 * .A.A.A.A.A
 * ..M.M.M.MM
 * .X.X.XMASX
 */

//     static int day4part1(List<String> input)
//     {
//         int result = 0;
//         // horizontal
//         String regex = "(?:XMAS|SAMX)";
//         Pattern p = Pattern.compile(regex);
//         for (String s : input){
//             final Matcher m = p.matcher(s);
//             while (m.find()) {
//                 System.out.println("match in horizontal");
//                 result++;
//             }
//         }
//
//         // vertical
//         List<String> rotated = Util.rotateMatrix(input);
//         System.out.println(rotated);
//         for (String s : rotated){
//             final Matcher m = p.matcher(s);
//             while (m.find()) {
//                 System.out.println("match in vertical");
//                 result++;
//             }
//         }
//
//         for (int i=0; i<input.size()-3; i++){
//             for (int j=0; j<input.getFirst().length()-3; j++){
//                 // construct matrix of 4x4 to check diagonal and antidiagonal for XMAS
//                 char[][] matrixflux = new char[4][4];
//                 for (int k=0; k<4; k++){
//                     for (int q=0; q<4; q++){
//                         matrixflux[k][q] = input.get(i+k).charAt(j+q);
//                     }
//                 }
//                 // main diagonal
//                 StringBuilder mainDiag = new StringBuilder();
//                 StringBuilder antiDiag = new StringBuilder();
//                 for (int diag = 0; diag<4; diag++){
//                     mainDiag.append(matrixflux[diag][diag]);
//                 }
//                 for (int diag = 3; diag >= 0; diag--){
//                     antiDiag.append(matrixflux[3-diag][diag]);
//                 }
//                 for (char[] c: matrixflux){
//                     System.out.println(Arrays.toString(c));
//                 }
//                 System.out.println("mainDiag: " + mainDiag);
//                 System.out.println("antiDiag: " + antiDiag);
//                 // NOW WE CHECK IF THE DIAG AND ANTIDIAG is XMAS or SAMX
//
//                 Matcher m1 = p.matcher(mainDiag);
//                 Matcher m2 = p.matcher(antiDiag);
//                 while (m1.find()) {
//                     System.out.println("match in maindiag");
//                     result++;
//                 }
//                 while (m2.find()) {
//                     System.out.println("match in antiDiag");
//                     result++;
//                 }
//             }
//         }
//
//         return result;
//    }


    static int day4part1(List<String> input) {
        int totalTimes = 0;
        Pattern pattern = Pattern.compile("XMAS");

        // horizontal
        for (String row : input) {
            String rowStr = row;
            totalTimes += countMatches(pattern, rowStr);
            totalTimes += countMatches(pattern, new StringBuilder(rowStr).reverse().toString());
        }

        // vertical
        for (int j = 0; j < input.get(0).length(); j++) {
            StringBuilder column = new StringBuilder();
            for (String row : input) {
                column.append(row.charAt(j));
            }
            String colStr = column.toString();
            totalTimes += countMatches(pattern, colStr);
            totalTimes += countMatches(pattern, new StringBuilder(colStr).reverse().toString());
        }

        // diagonal
        for (int i = 0; i <= input.size() - 4; i++) {
            for (int j = 0; j <= input.get(0).length() - 4; j++) {
                char[][] miniTable = new char[4][4];
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                        miniTable[x][y] = input.get(i+x).charAt(j+y);
                    }
                }

                StringBuilder mainDiag = new StringBuilder();
                StringBuilder antiDiag = new StringBuilder();
                for (int k = 0; k < 4; k++) {
                    mainDiag.append(miniTable[k][k]);
                    antiDiag.append(miniTable[k][3-k]);
                }

                String mainDiagStr = mainDiag.toString();
                String antiDiagStr = antiDiag.toString();

                totalTimes += countMatches(pattern, mainDiagStr);
                totalTimes += countMatches(pattern, new StringBuilder(mainDiagStr).reverse().toString());
                totalTimes += countMatches(pattern, antiDiagStr);
                totalTimes += countMatches(pattern, new StringBuilder(antiDiagStr).reverse().toString());
            }
        }

        return totalTimes;
    }

    static int countMatches(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = Util.readInput("C:\\Users\\Mohamed Enn\\Desktop\\adventofcode2024\\src\\main\\java\\org\\advent\\day4\\inputs.txt");
        System.out.println(day4part1(input));
    }

}
