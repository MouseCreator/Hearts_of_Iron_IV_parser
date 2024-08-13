package mouse.hoi.main.map.provinces;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.csv.CSVData;
import mouse.hoi.tools.csv.CSVReader;
import mouse.hoi.tools.csv.CSVRow;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProvinceLoader {

    private final CSVReader csvReader;
    private final FileProperties fileProperties;

    public List<ProvinceInfo> loadProvinces() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/provinces/init.input");
        String filename = map.expectedProperty("definition");
        CSVData csvData = csvReader.readCSV(filename, ";", false);
        return toProvinces(csvData);
    }
    public List<ProvinceInfo> loadProvinces(String filename) {
        CSVData csvData = csvReader.readCSV(filename, ";", false);
        return toProvinces(csvData);
    }

    private List<ProvinceInfo> toProvinces(CSVData csvData) {
        List<ProvinceInfo> result = new ArrayList<>();
        for (CSVRow row : csvData) {
            ProvinceInfo mappedRow = mapRow(row);
            result.add(mappedRow);
        }
        return result;
    }

    private ProvinceInfo mapRow(CSVRow row) {
        ProvinceInfo provinceInfo = new ProvinceInfo();
        row.fill()
                .push(s -> provinceInfo.setId(toInteger(s)))
                .push(s -> provinceInfo.setRed(toInteger(s)))
                .push(s -> provinceInfo.setGreen(toInteger(s)))
                .push(s -> provinceInfo.setBlue(toInteger(s)))
                .push(provinceInfo::setType)
                .push(s -> provinceInfo.setCoastal(toBoolean(s)))
                .push(provinceInfo::setTerrain)
                .push(s -> provinceInfo.setContinent(toInteger(s)));
        return provinceInfo;
    }

    private boolean toBoolean(String s) {
        if (s.equalsIgnoreCase("true")) {
            return true;
        }
        if (s.equalsIgnoreCase("false")) {
            return false;
        }
        throw new IllegalArgumentException("Expected true or false: " + s);
    }

    private static int toInteger(String s) {
        return Integer.parseInt(s);
    }
}
