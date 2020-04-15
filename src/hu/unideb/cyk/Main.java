package hu.unideb.cyk;

public class Main {

    public static void main(String[] args) {

        GrammarParser gp = new GrammarParser();
        Grammar grammar = gp.readFromFile("E:\\Fordítóprogramok\\CYK\\resources\\example.txt");

        String word = "baaba";

        WordGenerator wg = new WordGenerator(grammar);
        if(wg.isGeneratable(word)) {
            System.out.println("The given word can be generated using the given grammar");
        } else {
            System.out.println("The given word cannot be generated using the given grammar");
        }
    }
}