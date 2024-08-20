package mouse.hoi.main.bookmark.service;

import lombok.Data;
import mouse.hoi.main.states.data.State;

import java.util.HashSet;
import java.util.Set;
@Data
public class StateHistoricalData {
    public StateHistoricalData() {
        coastal = false;
        cores = new HashSet<>();
        claims = new HashSet<>();
    }
    private int stateId;
    private boolean coastal;
    private String owner;
    private Set<String> cores;
    private Set<String> claims;
    private State origin;
}
