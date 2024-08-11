package mouse.hoi.tools.csv;

public interface CSVReader {
    CSVData readCSV(String file, String separator, boolean hasTitle);
}
