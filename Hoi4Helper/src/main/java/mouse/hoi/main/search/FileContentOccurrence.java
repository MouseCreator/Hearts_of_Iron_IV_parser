package mouse.hoi.main.search;

import lombok.Data;

@Data
public class FileContentOccurrence implements SearchOccurrence{

    private final String filename;
    private final int row;
    private final int position;

    public FileContentOccurrence(String path, int row, int pos) {
        this.filename = path;
        this.row = row;
        this.position = pos;
    }

    @Override
    public String print() {
        return filename + ":" + row + ":" + position;
    }
}
