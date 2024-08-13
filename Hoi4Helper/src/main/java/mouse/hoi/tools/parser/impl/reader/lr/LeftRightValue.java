package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.Data;

@Data
public class LeftRightValue {
    private LeftValue leftValue;
    private RightValue rightValue;

    public LeftRightValue(LeftValue leftValue, RightValue rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }
}
