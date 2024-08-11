package mouse.hoi.tools.csv;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.files.FileManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVReaderImpl implements CSVReader {

    private final FileManager fileManager;
    @Override
    public CSVData readCSV(String file, String separator, boolean hasTitle) {
        CSVData csvData = new CSVData();
        List<String> lines = fileManager.readLines(file);
        List<String> activeLines = filterLines(lines);
        int start = hasTitle ? 1 : 0;
        for (int i = start; i < activeLines.size(); i++) {
            String currentLine = lines.get(i);
            String[] split = currentLine.split(separator);
            CSVRow csvRow = CSVRow.fromArray(split);
            csvData.addRow(csvRow);
        }
        return csvData;
    }

    private static List<String> filterLines(List<String> lines) {
        List<String> activeLines = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            activeLines.add(line);
        }
        return activeLines;
    }
}
