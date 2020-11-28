package lab2;

import java.util.*;

/**
 Язык - (ab ∪ a)*.
 NFA с эпсилон переходом
 */
public class eNFA {

    private enum States {

        Q0(true), Q1(false), Q2(false), Q3(false), Q4(false), Q5(true), Q6(false), Q7(true);

        final boolean accept;

        Set<States> a;
        Set<States> b;
        Set<States> epsilon;

        static {
            Q0.a = Collections.EMPTY_SET;
            Q0.b = Collections.EMPTY_SET;
            Q0.epsilon = new HashSet<>(Arrays.asList(Q1));

            Q1.a = Collections.EMPTY_SET;
            Q1.b = Collections.EMPTY_SET;
            Q1.epsilon = new HashSet<>(Arrays.asList(Q2, Q6));

            Q2.a = new HashSet<>(Arrays.asList(Q3));
            Q2.b = Collections.EMPTY_SET;
            Q2.epsilon = Collections.EMPTY_SET;

            Q3.a = Collections.EMPTY_SET;
            Q3.b = Collections.EMPTY_SET;
            Q3.epsilon = new HashSet<>(Arrays.asList(Q4));

            Q4.a = Collections.EMPTY_SET;
            Q4.b = new HashSet<>(Arrays.asList(Q5));
            Q4.epsilon = Collections.EMPTY_SET;

            Q5.a = Collections.EMPTY_SET;
            Q5.b = Collections.EMPTY_SET;
            Q5.epsilon = new HashSet<>(Arrays.asList(Q1));

            Q6.a = new HashSet<>(Arrays.asList(Q7));
            Q6.b = Collections.EMPTY_SET;
            Q6.epsilon = Collections.EMPTY_SET;

            Q7.a = Collections.EMPTY_SET;
            Q7.b = Collections.EMPTY_SET;
            Q7.epsilon = new HashSet<>(Arrays.asList(Q1));
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

        Set<States> eClose() {
            Set<States> states = new HashSet<>();
            states.add(this);

            for(States e: this.epsilon)
                states.addAll(e.eClose());

            return states;
        }

    }

    public static boolean accept(String string) {
        Set<States> currStates = new HashSet<>(States.Q0.eClose());

        for (int i = 0; i < string.length(); i++)
            currStates = union(currStates, string.charAt(i));

        return currStates.stream().anyMatch(s -> s.accept);
    }

    private static Set<States> union(Set<States> currStates, char ch) {
        Set<States> nextStates = new HashSet<>();

        for (States cState : currStates)
            for(States s: cState.transition(ch))
                nextStates.addAll(s.eClose());
        return nextStates;
    }

    public static void main(String[] args) {
        String[] tests = {"abaabaaa","aab","aaab","abab",};

        for (String test:tests){
            boolean state = accept(test);
            System.out.println(state);
        }
    }
}