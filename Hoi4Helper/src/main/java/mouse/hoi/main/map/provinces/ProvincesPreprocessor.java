package mouse.hoi.main.map.provinces;

import mouse.hoi.exception.ProvinceException;
import mouse.hoi.main.map.Pos;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvincesPreprocessor {
    public ProvinceCollection preprocess(List<ProvinceInfo> provinceInfoList, BufferedImage provinceMap) {
        Map<ProvinceColor, ProvinceInfo> colorMap = new HashMap<>();
        Map<Integer, ProvinceInfo> idMap = new HashMap<>();
        for (ProvinceInfo info : provinceInfoList) {
            colorMap.put(info.color(), info);
            idMap.put(info.getId(), info);
        }
        Map<Pos, ProvinceInfo> posMap = new HashMap<>();
        Map<Integer, Pos> allPositions = new HashMap<>();
        for (int y = 0; y < provinceMap.getHeight(); y++) {
            for (int x = 0; x < provinceMap.getWidth(); x++) {
                int rgb = provinceMap.getRGB(x, y);
                Color color = new Color(rgb);
                ProvinceColor provinceColor = new ProvinceColor(color);
                ProvinceInfo provinceInfo = colorMap.get(provinceColor);
                if (provinceInfo == null) {
                    throw new ProvinceException("Unexpected province color: " + provinceColor);
                }
                Pos pos = Pos.of(x, y);
                posMap.put(pos, provinceInfo);
                allPositions.computeIfAbsent(provinceInfo.getId(), k -> pos);

            }
        }
        return new ProvinceCollection(colorMap, posMap, allPositions, idMap);
    }

    public ProvinceDefinitions preprocess(List<ProvinceInfo> provinceInfoList) {
        Map<ProvinceColor, ProvinceInfo> colorMap = new HashMap<>();
        Map<Integer, ProvinceInfo> idMap = new HashMap<>();
        for (ProvinceInfo info : provinceInfoList) {
            colorMap.put(info.color(), info);
            idMap.put(info.getId(), info);
        }
        return new ProvinceDefinitions(colorMap, idMap);
    }
}
