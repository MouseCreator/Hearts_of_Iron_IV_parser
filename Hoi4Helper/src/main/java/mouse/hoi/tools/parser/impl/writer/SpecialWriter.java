package mouse.hoi.tools.parser.impl.writer;

import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.*;

public class SpecialWriter {
    private final StringBuilder stringBuilder;
    private int tabsLevel;
    private boolean afterLn;
    public SpecialWriter() {
        stringBuilder = new StringBuilder();
        tabsLevel = 0;
        afterLn = false;
    }

    private void onBegin() {
        if (afterLn) {
            afterLn = false;
            tabs();
        }
    }

    public SpecialWriter incrementTabs() {
        tabsLevel++;
        return this;
    }
    public SpecialWriter decrementTabs() {
        tabsLevel--;
        return this;
    }
    public SpecialWriter tabs() {
        String t = "\t".repeat(tabsLevel);
        write(t);
        return this;
    }

    public String get() {
        return stringBuilder.toString();
    }

    public SpecialWriter write(int i) {
        onBegin();
        stringBuilder.append(i);
        return this;
    }

    public SpecialWriter eq() {
        onBegin();
        stringBuilder.append(" = ");
        return this;
    }

    public SpecialWriter comment(String comment) {
        return write(" # " + comment);
    }

    public SpecialWriter lineComment(String lineComment) {
        onBegin();
        return comment(lineComment);
    }

    public SpecialWriter write(String val, StringStyle style) {
        return switch (style) {
            case DEFAULT ->write(val);
            case QUOTED -> write("\""+val+"\"");
        };
    }

    public SpecialWriter write(DWData dwData) {
        dwData.write(this);
        return this;
    }
    public SpecialWriter ln() {
        stringBuilder.append("\n");
        afterLn = true;
        return this;
    }
    public SpecialWriter space() {
        stringBuilder.append(" ");
        return this;
    }

    public SpecialWriter write(String string) {
        onBegin();
        stringBuilder.append(string);
        return this;
    }
}
