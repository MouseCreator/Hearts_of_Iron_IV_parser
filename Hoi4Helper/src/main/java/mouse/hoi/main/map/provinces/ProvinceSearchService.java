package mouse.hoi.main.map.provinces;

import lombok.RequiredArgsConstructor;

import mouse.hoi.tools.image.ImagesIO;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyFiller;
import mouse.hoi.tools.properties.PropertyMap;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceSearchService {
    private final FileProperties fileProperties;
    private final PropertyFiller propertyFiller;
    private final ProvinceFinder provinceFinder;
    private final ProvincesPreprocessor provincesPreprocessor;
    private final ProvinceLoader loader;
    private final ImagesIO imagesIO;
    public void search() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/provinces/provinces.input");
        ProvinceProperties provinceProperties = new ProvinceProperties();
        propertyFiller.fillObject(map, provinceProperties);
        List<Integer> idsToFind = idsToFind(provinceProperties);
        String definitionsFile = provinceProperties.getDefinitionsFile();
        List<ProvinceInfo> provinceInfos = loader.loadProvinces(definitionsFile);
        String provinceMap = provinceProperties.getProvinceMap();
        BufferedImage image = imagesIO.loadImage(provinceMap);
        ProvinceCollection collection = provincesPreprocessor.preprocess(provinceInfos, image);
        for (int i : idsToFind) {
            ProvinceFindResult prov = provinceFinder.findProvinceById(collection, i);
            System.out.println(prov.print());
        }
    }

    private List<Integer> idsToFind(ProvinceProperties provinceProperties) {
        String toFind = provinceProperties.getToFind();
        String[] split = toFind.split(" +");
        List<Integer> result = new ArrayList<>();
        for (String s : split) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }
}
