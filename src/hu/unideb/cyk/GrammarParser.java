package hu.unideb.cyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class GrammarParser {

    public Grammar readFromFile(String path) {
        Grammar grammar = new Grammar();

        try (Scanner sc = new Scanner(new File(path))) {

            Character startSymbol = sc.nextLine().charAt(0);
            List<Character> terminals = new LinkedList<>();
            List<Character> nonTerminals = new LinkedList<>();
            TreeMap<String, ArrayList<String>> rules = new TreeMap<>();
            List<String> tmp = new ArrayList<>();

            Stream.of(sc.nextLine().split(" ")).forEach(s -> terminals.add(s.charAt(0)));
            Stream.of(sc.nextLine().split(" ")).forEach(s -> nonTerminals.add(s.charAt(0)));

            while (sc.hasNextLine()) {

                tmp.addAll(Arrays.asList(toArray(sc.nextLine())));
                String leftSide = tmp.get(0);
                tmp.remove(0);
                rules.put(leftSide, new ArrayList<>());
                rules.get(leftSide).addAll(tmp);
                tmp.clear();

            }

            grammar.setStartSymbol(startSymbol);
            grammar.setTerminals(terminals);
            grammar.setNonTerminals(nonTerminals);
            grammar.setRules(rules);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return grammar;
    }

    public String[] toArray(String input){
        return input.split("\\s");
    }
}
