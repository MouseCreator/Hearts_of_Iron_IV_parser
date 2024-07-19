package mouse.hoi.tools.parser.impl.token;

import org.springframework.stereotype.Service;

@Service
public class TokenCollectionBuilder {
    public TCBuild start(String filename) {
        return new TCBuild(filename);
    }
    public static class TCBuild {

        private final String filename;
        public TCBuild(String file) {
            this.tokenCollection = new TokenCollectionImpl();
            this.filename = file;
        }

        private final TokenCollection tokenCollection;
        public TokenCollection get() {
            return tokenCollection;
        }
        public TCBuild id(int line, int pos, String id) {
            add(new IdToken(loc(line, pos), id));
            return this;
        }

        private Location loc(int line, int pos) {
            return new Location(filename, line, pos);
        }

        public TCBuild special(int line, int pos, String val) {
            add(new StringToken(loc(line, pos), val));
            return this;
        }
        public TCBuild num(int line, int pos, int num) {
            add(new IntegerToken(loc(line, pos), num));
            return this;
        }
        public TCBuild num(int line, int pos, double num) {
            add(new DoubleToken(loc(line, pos), num));
            return this;
        }
        public TCBuild string(int line, int pos, String str) {
            add(new StringToken(loc(line, pos), "\"" + str + "\""));
            return this;
        }
        private void add(Token token) {
            tokenCollection.put(token);
        }
    }
}
