package lab4;

//Segundo teste: PDA de parênteses

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Test {
    public static void main(String [] args) {
        List<Character> alphabet = new ArrayList<Character>();
        alphabet.add('(');
        alphabet.add(')');

        List<Character> stackAlphabet = new ArrayList<Character>();
        stackAlphabet.add('(');

        HashSet<Integer> states = new HashSet<Integer>();
        states.add(1);

        List<PDATransition> transitions = new ArrayList<PDATransition>();
        transitions.add(new PDATransition('(', '_', 1, 1, "("));
        transitions.add(new PDATransition('(', '(', 1, 1, "(("));
        transitions.add(new PDATransition(')', '(', 1, 1, ""));

        PDA pda = new PDA(alphabet, stackAlphabet, 1, states, transitions);

        String []tests = {"","(())","(()","((())()(()))"};

        for (String test:tests){
            System.out.println(pda.doesMatch(test));
            System.out.println(PDATree.GetTree(test,'(',')'));
        }
    }
}
