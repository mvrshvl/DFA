package lab4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PDA {
    public List<Character> alphabet;
    public List<Character> stackAlphabet;
    public int initialState;
    public HashSet<Integer> states;
    public List<PDATransition> transitions;

    public PDA (List<Character> alphabet, List<Character> stackAlphabet,
                int initialState, HashSet<Integer> states, List<PDATransition> transitions) {
        this.alphabet = alphabet;
        this.stackAlphabet = stackAlphabet;
        this.initialState = initialState;
        this.states = states;
        this.transitions = transitions;
    }

    public boolean doesMatch(String input) {
        List<PDAResult> results = matchResults(input);

        for(PDAResult result : results)
            if(result.success)
                return true;

        return false;
    }

    public List<PDAResult> matchResults(String input) {

        if (input.equals("")) {
            return new ArrayList<PDAResult>(Arrays.asList(new PDAResult(0,0,true)));
        }

        boolean initial_transition = false;
        boolean transition_that_pops = false;
        for(PDATransition transition: transitions) {
            if(transition.stackHead == '_' && transition.oldState == initialState && transition.inputChar == input.charAt(0)) {
                initial_transition = true;
            }
            if(transition.stackReplace.length() == 0) {
                transition_that_pops = true;
            }
        }
        if(!initial_transition || !transition_that_pops) {
            return new ArrayList<PDAResult>(Arrays.asList(new PDAResult(0,0,false)));
        }

        Set<PDAResult> resultList = new HashSet<PDAResult>();

        List<PDARunState> stateList = new ArrayList<PDARunState>(Arrays.asList(new PDARunState(input,0,"",initialState)));

        while (!stateList.isEmpty()) {
            System.out.println(stateList);

            Set<PDARunState> newStateList = new HashSet<PDARunState>();

            for (PDARunState state : stateList) {
                List<PDARunState> nextStates = findNextStates(state);
                if(nextStates.isEmpty()) {
                    resultList.add(new PDAResult(state.matchedSoFar,
                            state.stack.length(), false));
                    continue;
                }

                for(PDARunState nextState : nextStates) {

                    if(nextState.failure || nextState.input.equals("")
                            && nextState.stack.length() == 0) {
                        resultList.add(new PDAResult(nextState.matchedSoFar,
                                nextState.stack.length(), !nextState.failure));
                    }
                    else {
                        newStateList.add(nextState);
                    }
                }
            }
            stateList = new ArrayList<PDARunState>(newStateList);
        }
        
        return new ArrayList<PDAResult>(resultList);

    }

    public List<PDARunState> findNextStates(PDARunState state) {
        List<PDARunState> followTransition = new ArrayList<PDARunState>();
        for(PDATransition transition : transitions) {
            if(transition.matchesConfiguration(state)) {
                followTransition.add(applyTransition(transition,state));
            }
        }
        return followTransition;
    }

    private static PDARunState applyTransition(PDATransition transition,
                                               PDARunState state) {
        int newState = transition.newState;
        String newStack = state.stack;
        String newInput = state.input.substring(1);
        int newMatchedSoFar = state.matchedSoFar + 1;
        boolean isFailure = false;

        if(transition.stackHead != '_') {
            if(newStack.length() > 0) {
                newStack = newStack.substring(1);
            }
            else {
                isFailure = true;
            }
        }
        else if(!newStack.equals("")) {
            isFailure = true;
        }

        if(!isFailure) {
            newStack = transition.stackReplace + newStack;
        }

        PDARunState returnState = new PDARunState(newInput,newMatchedSoFar,newStack,newState);

        if(isFailure || newInput.equals("") && newStack.length() > 0 ||
                newInput.length() < newStack.length()) {
            returnState.failure = true;
        }

        return returnState;
    }
}