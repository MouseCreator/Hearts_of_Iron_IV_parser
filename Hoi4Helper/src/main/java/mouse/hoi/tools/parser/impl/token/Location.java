package mouse.hoi.tools.parser.impl.token;


public record Location(String filename, int line, int position) {

    public static Location of(String filename, int line, int pos) {
        return new Location(filename, line, pos);
    }

    public Location move(int toLine, int toPos) {
        return new Location(filename, toLine, toPos);
    }

    @Override
    public String toString() {
        return filename + ":" + line + ":" + position + ":";
    }
}
