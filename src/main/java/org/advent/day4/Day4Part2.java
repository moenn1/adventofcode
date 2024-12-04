package org.advent.day4;

import org.advent.util.Util;

import java.io.IOException;
import java.util.List;


public class Day4Part2 {


    static int day4part2(List<String> input){
        int res = 0;
        for(int i=1; i<input.size()-1; i++){
            for(int j=1; j<input.getFirst().length()-1; j++){
                if (input.get(i).charAt(j) == 'A'){
                    String mainDiag = input.get(i-1).charAt(j-1) + "A" + input.get(i+1).charAt(j+1);
                    String antiDiag = input.get(i-1).charAt(j+1) + "A" + input.get(i+1).charAt(j-1);
                    if ((mainDiag.equals("SAM") || mainDiag.equals("MAS") ||
                            mainDiag.equals("MAS") || mainDiag.equals("SAM")) &&
                            (antiDiag.equals("SAM") || antiDiag.equals("MAS") ||
                                    antiDiag.equals("MAS") || antiDiag.equals("SAM")))
                        res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = Util.readInput("C:\\Users\\Mohamed Enn\\Desktop\\adventofcode2024\\src\\main\\java\\org\\advent\\day4\\inputs.txt");
        System.out.println(day4part2(input));
    }

}
