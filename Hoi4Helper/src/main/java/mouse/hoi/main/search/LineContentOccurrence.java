package mouse.hoi.main.search;

import lombok.Data;

@Data
public class LineContentOccurrence implements SearchOccurrence{
    private final String filename;
    private final int row;
    private final int position;
    private final String line;

    public LineContentOccurrence(String path, String line, int row, int pos) {
        this.filename = path;
        this.row = row;
        this.position = pos;
        this.line = line;
    }

    @Override
    public String print() {
        return filename + ":" + row + ":" + position + " - " + line;
    }
}
