package mouse.hoi.tools.parser.generator.help;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.generator.YyResult;
import mouse.hoi.tools.parser.generator.Yytoken;

import java.util.ArrayList;
import java.util.List;

public class YyResultBuilder {
    public static Builder build(String filename) {
        return new Builder(filename);
    }
    public static Builder build() {
        return new Builder("");
    }
    public static class Builder {
        private final String filename;
        private final List<Yytoken> list;
        private int line = 0;
        private int pos = 0;
        public Builder(String filename) {
            this.filename = filename;
            list = new ArrayList<>();
        }
        public Builder id(String val) {
            add(TokenType.ID, val);
            shift(val);
            return this;
        }
        public Builder intVal(int val) {
            String target = String.valueOf(val);
            add(TokenType.INT, target);
            shift(target);
            return this;
        }

        public Builder doubleVal(double val) {
            String target = String.valueOf(val);
            add(TokenType.DOUBLE, target);
            shift(target);
            return this;
        }

        public Builder string(String val) {
            String target = '"' + val + '"';
            add(TokenType.STRING, target);
            shift(target);
            return this;
        }
        public Builder special(String val) {
            add(TokenType.SPECIAL, val);
            shift(val);
            return this;
        }
        public Builder space() {
            pos++;
            return this;
        }
        public Builder space(int num) {
            pos+=num;
            return this;
        }
        public Builder ln() {
            pos=0;
            line++;
            return this;
        }
        public Builder ln(int num) {
            pos=0;
            line+=num;
            return this;
        }

        private void shift(String val) {
            String[] split = val.split("\n");
            int length = split.length;
            int last = length - 1;
            line += last;
            if (split.length > 1) {
                pos = 0;
            }
            pos += split[last].length();
        }

        private void add(TokenType type, String t) {
            list.add(new Yytoken(type, t, line, pos));
        }

        public YyResult get() {
            return new YyResult(filename, list);
        }
    }
}
