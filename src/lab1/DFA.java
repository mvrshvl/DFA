package lab1;

/**
 Мякишев С.Г. МОСм-201
 алфавит ДКА - ":", "), "(, "_"
 Необходимо передавать смайлы где
 : - глаза
 ) - улыбка
 ( - грусть
 _ - разделитель лиц
 ДКА принимает смайлы разделенные пробелом - "_"
 */


public class DFA {


    private enum States {

        Q0(false), Q1(false), Q2(true), Q3(true), Q4(false);
        // Таблица переходов
        static {
            Q0.eyes = Q1;  Q0.space = Q0;
            Q1.smile = Q2; Q1.sad = Q2;
            Q2.space = Q3;
            Q3.eyes = Q1; Q3.space = Q3;
        }

        States eyes;
        States smile;
        States sad;
        States space;

        final boolean accept;

        States(boolean accept) {this.accept = accept;}
        // * Представляет функцию перехода δ : Q × Σ → Q.
        States transition(char ch) {
            switch (ch) {
                case ':':
                    return this.eyes == null? Q4 : this.eyes;
                case ')':
                    return this.smile == null? Q4: this.smile;
                case '(':
                    return this.sad == null? Q4: this.sad;
                case '_':
                    return this.space == null? Q4: this.space;
                default:
                    return Q4;
            }
        }

    }

    public static boolean accept(String string) {
        States state = States.Q0;
        for (int i = 0; i < string.length(); i++) {
            state = state.transition(string.charAt(i));
        }
        return state.accept;
    }

    public static void main(String[] args) {
        String[] tests = {":)_:(:)",":)_:(_:)",")_)_(",":)_:)_:)",};

        for (String test:tests){
            boolean state = accept(test);
            System.out.println(state);
        }
    }
}