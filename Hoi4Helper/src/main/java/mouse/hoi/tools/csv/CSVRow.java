package mouse.hoi.tools.csv;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.function.Consumer;

public class CSVRow {
    private final List<String> values;

    private CSVRow() {
        values = new ArrayList<>();
    }

    public static CSVRow fromArray(String[] split) {
        CSVRow csvRow = new CSVRow();
        Collections.addAll(csvRow.values, split);
        return csvRow;
    }
    public class CSVFiller {
        private int current = 0;
        public CSVFiller push(Consumer<String> consumer) {
            if (current >= values.size()) {
                throw new IllegalStateException("Out of values to push: " + values);
            }
            consumer.accept(values.get(current));
            current++;
            return this;
        }
    }
    public CSVFiller fill() {
        return new CSVFiller();
    }
}
