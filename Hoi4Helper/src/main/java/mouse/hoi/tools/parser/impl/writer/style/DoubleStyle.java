package mouse.hoi.tools.parser.impl.writer.style;

import lombok.Getter;

@Getter
public class DoubleStyle {
    private final int minAfter;
    private final int maxAfter;

    private final static int MAX_DEFAULT = 3;
    private final static int MIN_DEFAULT = 0;

    private DoubleStyle(int min, int max) {
        this.maxAfter = max;
        this.minAfter = min;
    }
    public static DoubleStyle of(int min, int max) {
        return new DoubleStyle(min, max);
    }
    public static DoubleStyle max(int max) {
        return new DoubleStyle(MIN_DEFAULT, max);
    }
    public static DoubleStyle min(int min) {
        return new DoubleStyle(min, MAX_DEFAULT);
    }
    public static DoubleStyle defaultStyle() {
        return new DoubleStyle(MIN_DEFAULT, MAX_DEFAULT);
    }

    public String styled(double d) {
        return null;
    }

}
