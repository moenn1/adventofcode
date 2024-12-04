package org.advent.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> readInput(String fileName) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> rotateMatrix(List<String> input) {
        int rows = input.size();
        int cols = input.getFirst().length();

        List<String> rotated = new ArrayList<>();

        for (int col = 0; col < cols; col++) {
            StringBuilder sb = new StringBuilder();
            for (int row = rows - 1; row >= 0; row--) {
                sb.append(input.get(row).charAt(col));
            }
            rotated.add(sb.toString());
        }

        return rotated;
    }

    public static void print2DCharArray(char[][] array) {
        for (char[] row : array) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
