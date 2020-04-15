package hu.unideb.cyk;

import java.util.*;

public class Grammar {

    private Character startSymbol = 'S';
    private List<Character> terminals = new LinkedList<>();
    private List<Character> nonTerminals = new LinkedList<>();
    private  TreeMap<String, ArrayList<String>> rules = new TreeMap<>();

    public Grammar() {}

    public Character getStartSymbol() {
        return startSymbol;
    }

    public void setStartSymbol(Character startSymbol) {
        this.startSymbol = startSymbol;
    }

    public List<Character> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Character> terminals) {
        this.terminals = terminals;
    }

    public List<Character> getNonTerminals() {
        return nonTerminals;
    }

    public void setNonTerminals(List<Character> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public  TreeMap<String, ArrayList<String>> getRules() {
        return rules;
    }

    public  void setRules(TreeMap<String, ArrayList<String>> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "Grammar:" +
                "\nstartSymbol= " + startSymbol +
                ",\nterminals= " + terminals +
                ",\nnonTerminals= " + nonTerminals +
                ",\nrules=" + rules.toString();
    }
}
