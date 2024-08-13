package mouse.hoi.main.states.loader;

import mouse.hoi.main.states.data.State;

public record LoadedState(State state, String shortName, String longName) {
    private LoadedState updated(State state) {
        return new LoadedState(state, shortName, longName);
    }
}
