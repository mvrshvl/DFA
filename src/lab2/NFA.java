package lab2;

import java.util.*;

/**
 * Мякишев С.Г. МОСм-201
 * Язык {aa, aab}*{b}
 */
public class NFA {

    private enum States {

        Q0(false), Q1(false), Q2(false), Q3(false), Q4(true);

        final boolean accept;

        Set<States> a;
        Set<States> b;

        // transition table
        static {
            Q0.a = new HashSet(Arrays.asList(Q1, Q2));
            Q0.b = new HashSet(Arrays.asList(Q4));

            Q1.a = new HashSet<>(Arrays.asList(Q0));
            Q1.b = Collections.EMPTY_SET;

            Q2.a = new HashSet<>(Arrays.asList(Q3));
            Q2.b = Collections.EMPTY_SET;

            Q3.a = Collections.EMPTY_SET;
            Q3.b = new HashSet<>(Arrays.asList(Q0));

            Q4.a = Collections.EMPTY_SET;
            Q4.b = Collections.EMPTY_SET;
        }

        States(boolean accept) {this.accept = accept;}

        Set<States> transition(char ch) {

            switch (ch) {
                case 'a':
                    return this.a;
                case 'b':
                    return this.b;
                default:
                    return Collections.EMPTY_SET;
            }

        }
    }

    public static boolean accept(String string) {
        Set<States> currStates = new HashSet<>();
        currStates.add(States.Q0);

        for (int i = 0; i < string.length(); i++)
            currStates = union(currStates, string.charAt(i));

        return currStates.stream().anyMatch(s -> s.accept);
    }

    private static Set<States> union(Set<States> currStates, char ch) {
        Set<States> nextStates = new HashSet<>();
        for (States cState : currStates)
            nextStates.addAll(cState.transition(ch));
        return nextStates;
    }

    public static void main(String[] args) {
        String[] tests = {"aaaaaaaab","aab","aaab","b",};

        for (String test:tests){
            boolean state = accept(test);
            System.out.println(state);
        }
    }
}