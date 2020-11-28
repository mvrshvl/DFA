package lab4;

public class PDATransition {

    public Character inputChar;
    public Character stackHead;
    public String stackReplace;
    public int oldState;
    public int newState;

    public PDATransition(Character inputChar, Character stackHead, int oldState,
                         int newState, String stackReplace)
    {
        this.inputChar = inputChar;
        this.stackHead = stackHead;
        this.stackReplace = stackReplace;
        this.oldState = oldState;
        this.newState = newState;
    }

    public boolean matchesConfiguration(PDARunState state) {
        return inputChar == state.input.charAt(0) &&
                oldState == state.state &&
                ((stackHead == '_' && state.stack.length() == 0) ||
                        (state.stack.length() > 0 && stackHead == state.stack.charAt(0)));
    }
}
