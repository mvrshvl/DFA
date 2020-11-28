package lab4;

public class PDARunState {

    public int matchedSoFar;
    public String stack;
    public int state;
    public String input;
    public boolean failure;

    public PDARunState(String input, int matchedSoFar, String stack,
                       int state) {
        this.input = input;
        this.matchedSoFar = matchedSoFar;
        this.stack = stack;
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("Строка: %s, Уровень: %d, Баланс: %s, Состояние: %d /",
                input, matchedSoFar, stack, state);
    }

}
