package mouse.hoi.tools.parser.impl.writer.style;

import lombok.Getter;
import mouse.hoi.tools.utils.Numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        BigDecimal bd = BigDecimal.valueOf(d).setScale(maxAfter, RoundingMode.DOWN);
        String[] parts = bd.toPlainString().split("\\.");
        String integerPart = parts[0];
        StringBuilder decimalPart;
        if (Numbers.dWhole(d)) {
            decimalPart = new StringBuilder();
            while (decimalPart.length() < minAfter) {
                decimalPart.append("0");
            }
         } else {
            decimalPart = new StringBuilder();
            if (parts.length == 1) {
                return integerPart;
            }
            String t = parts[1];
            int c = 0;
            while (decimalPart.length() < minAfter) {
                if (c < t.length()) {
                    decimalPart.append(t.charAt(c++));
                } else {
                    decimalPart.append("0");
                }
            }
            StringBuilder reverseBuilder = new StringBuilder();
            boolean nonZeroFound = false;
            for (int i = maxAfter - 1; i >= minAfter; i--) {
                char c1 = t.charAt(i);
                if (nonZeroFound) {
                    reverseBuilder.append(c1);
                    continue;
                }
                if (c1 != '0') {
                    nonZeroFound = true;
                    reverseBuilder.append(c1);
                }
            }
            decimalPart.append(reverseBuilder.reverse());
        }

        return (decimalPart.isEmpty()) ? integerPart : integerPart + "." + decimalPart;
    }

}
