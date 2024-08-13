package mouse.hoi.main.states.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Building {
    private String type;
    private int level;


    public Building(String type, int level) {
        this.type = type;
        this.level = level;
    }
}
