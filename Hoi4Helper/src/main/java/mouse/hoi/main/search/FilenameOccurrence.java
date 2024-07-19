package mouse.hoi.main.search;

import lombok.Data;

@Data
public class FilenameOccurrence implements SearchOccurrence {
    private final String filename;

    public FilenameOccurrence(String filename) {
        this.filename = filename;
    }

    @Override
    public String print() {
        return filename;
    }
}
