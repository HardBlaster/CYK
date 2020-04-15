package hu.unideb.cyk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class WordGenerator {

    Grammar grammar;

    public WordGenerator() {
    }

    public WordGenerator(Grammar grammar) {
        this.grammar = grammar;
    }

    public boolean isGeneratable(String word) {
        String[][] cykTable = doCyk(createCYKTable(word), word);
        Utils.printTable(cykTable);

        return cykTable[cykTable.length-1][cykTable[cykTable.length-1].length-1].contains("" + grammar.getStartSymbol());
    }

    public String[][] createCYKTable(String word) {
        int length = word.length();

        String[][] cykTable = new String[length + 1][];
        cykTable[0] = new String[length];

        for (int i = 1; i < cykTable.length; i++) {
            cykTable[i] = new String[length - (i - 1)];
        }

        for (int i = 1; i < cykTable.length; i++) {
            Arrays.fill(cykTable[i], "");
        }

        return cykTable;
    }

    public String[][] doCyk(String[][] cykTable, String word) {
        for (int i = 0; i < cykTable[0].length; i++) {
            cykTable[0][i] = Utils.manageWord(word, i);
        }

        for (int i = 0; i < cykTable[1].length; i++) {

            String[] validCombinations = checkIfProduces(new String[]{cykTable[0][i]});
            cykTable[1][i] = Utils.toString(validCombinations);

        }
        if (word.length() <= 1) {
            return cykTable;
        }

        for (int i = 0; i < cykTable[2].length; i++) {

            String[] downwards = Utils.toArray(cykTable[1][i]);
            String[] diagonal = Utils.toArray(cykTable[1][i + 1]);
            String[] validCombinations = checkIfProduces(Utils.getAllCombinations(downwards, diagonal));

            cykTable[2][i] = Utils.toString(validCombinations);
        }

        if (word.length() <= 2) {
            return cykTable;
        }

        TreeSet<String> currentValues = new TreeSet<>();

        for (int i = 3; i < cykTable.length; i++) {
            for (int j = 0; j < cykTable[i].length; j++) {
                for (int compareFrom = 1; compareFrom < i; compareFrom++) {

                    String[] downwards = cykTable[compareFrom][j].split("\\s");
                    String[] diagonal = cykTable[i - compareFrom][j + compareFrom].split("\\s");
                    String[] combinations = Utils.getAllCombinations(downwards, diagonal);
                    String[] validCombinations = checkIfProduces(combinations);

                    if (cykTable[i][j].isEmpty()) {
                        cykTable[i][j] = Utils.toString(validCombinations);
                    } else {

                        String[] oldValues = Utils.toArray(cykTable[i][j]);
                        ArrayList<String> newValues = new ArrayList<String>(Arrays.asList(oldValues));
                        newValues.addAll(Arrays.asList(validCombinations));
                        currentValues.addAll(newValues);
                        cykTable[i][j] = Utils.toString(currentValues.toArray(new String[0]));

                    }
                }
                currentValues.clear();
            }
        }

        return cykTable;
    }

    public String[] checkIfProduces(String[] toCheck) {
        ArrayList<String> storage = new ArrayList<>();

        for (String s : grammar.getRules().keySet()) {
            for (String current : toCheck) {
                if (grammar.getRules().get(s).contains(current)) {
                    storage.add(s);
                }
            }
        }

        if (storage.size() == 0) {
            return new String[]{};
        }

        return storage.toArray(new String[0]);
    }
}
