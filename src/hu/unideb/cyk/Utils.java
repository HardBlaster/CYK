package hu.unideb.cyk;

import java.util.Arrays;
import java.util.Comparator;

public class Utils {

    public static String[] getAllCombinations(String[] from, String[] to) {
        int length = from.length * to.length;
        int counter = 0;
        String[] combinations = new String[length];

        if (length == 0) {
            return combinations;
        }

        for (String s : from) {
            for (String value : to) {

                combinations[counter] = s + value;
                counter++;

            }
        }

        return combinations;
    }

    public static void printTable(String[][] table) {
        int l = longestString(table) + 2;

        String formatString = "| %-" + l + "s ";
        String s = "";
        StringBuilder sb = new StringBuilder();

        sb.append("+");
        for (int x = 0; x <= l + 2; x++) {

            if (x == l + 2) {
                sb.append("+");
            } else {
                sb.append("-");
            }
        }

        String low = sb.toString();
        sb.delete(0, 1);
        String lowRight = sb.toString();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j <= table[i].length; j++) {
                System.out.print((j == 0) ? low : (i <= 1 && j == table[i].length - 1) ? "" : lowRight);
            }

            System.out.println();

            for (int j = 0; j < table[i].length; j++) {

                s = (table[i][j].isEmpty()) ? "-" : table[i][j];
                System.out.format(formatString, s.replaceAll("\\s", ","));

                if (j == table[i].length - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();
        }
    }

    public static String manageWord(String word, int position) {
        return Character.toString(word.charAt(position));
    }

    public static String toString(String[] input) {
        return Arrays.toString(input).replaceAll("[\\[\\],]", "");
    }

    public static String[] toArray(String input) {
        return input.split("\\s");
    }

    private static int longestString(String[][] table) {
        return Arrays.stream(table).flatMap(Arrays::stream).max(Comparator.comparing(String::length)).get().length();
    }

}
