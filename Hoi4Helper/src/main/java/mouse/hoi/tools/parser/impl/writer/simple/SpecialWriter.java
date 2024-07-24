package mouse.hoi.tools.parser.impl.writer.simple;

import lombok.Getter;
import lombok.Setter;
import mouse.hoi.tools.parser.impl.writer.WriteStyle;

public class SpecialWriter {
    @Setter
    @Getter
    private WriteStyle writeStyle = WriteStyle.DEFAULT;
    public SpecialWriter() {
        stringBuilder = new StringBuilder();
        tabLevel = 0;
    }

    private final StringBuilder stringBuilder;
    private int tabLevel;
    public SpecialWriter incrementTab() {
        tabLevel++;
        return this;
    }
    public SpecialWriter decrementTab() {
        tabLevel--;
        return this;
    }
    public SpecialWriter resetTab() {
        tabLevel = 0;
        return this;
    }
    public SpecialWriter tln() {
        if (writeStyle == WriteStyle.DEFAULT) {
            stringBuilder.append("\n");
            return tabs();
        } else if (writeStyle == WriteStyle.SIMPLE) {
            stringBuilder.append(" ");
            return this;
        }
        throw new RuntimeException("Unknown style: " + writeStyle);
    }
    public SpecialWriter ln() {
        stringBuilder.append("\n");
        return this;
    }
    public SpecialWriter append(String string) {
        stringBuilder.append(string);
        return this;
    }
    public SpecialWriter tabs() {
        if (tabLevel > 0) {
            stringBuilder.append("\t".repeat(tabLevel));
        }
        return this;
    }

    public String get() {
        return stringBuilder.toString();
    }

    public boolean isEmpty() {
        return stringBuilder.isEmpty();
    }


}
