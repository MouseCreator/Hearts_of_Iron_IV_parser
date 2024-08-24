package mouse.hoi.main.character.data;

import lombok.Data;

@Data
public class AiWillDoOperator {
    private String operation;
    private double value;

    public AiWillDoOperator(String operation, double value) {
        this.operation = operation;
        this.value = value;
    }
}
