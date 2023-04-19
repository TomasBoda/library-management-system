package main.command;

import main.state.State;

public interface Action {
    State getState(State state);
}
