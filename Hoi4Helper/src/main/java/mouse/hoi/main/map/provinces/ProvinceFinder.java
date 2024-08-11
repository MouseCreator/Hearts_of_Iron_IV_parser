package mouse.hoi.main.map.provinces;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.Pos;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceFinder {
    public ProvinceFindResult findProvinceById(ProvinceCollection collection, int id) {
        ProvinceInfo provinceById = collection.getProvinceById(id);
        Pos pos = collection.provincePosition(provinceById);
        return SimpleFindResult.of(provinceById, pos);
    }

}
