package mouse.hoi.tools.csv;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVData implements Iterable<CSVRow> {

    public CSVData() {
        this.rows = new ArrayList<>();
    }

    private final List<CSVRow> rows;

    public void addRow(CSVRow csvRow) {
        rows.add(csvRow);
    }

    @Override
    @NonNull
    public Iterator<CSVRow> iterator() {
        return rows.iterator();
    }
}
