package mouse.hoi.main.gfx.service;

import mouse.hoi.exception.InvalidInputException;
import mouse.hoi.main.gfx.service.properties.GfxProperties;
import mouse.hoi.main.gfx.service.properties.GFXType;
import mouse.hoi.main.gfx.service.synch.Synchronizer;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertiesHelper;
import mouse.hoi.tools.properties.PropertyFiller;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpriteTypesService implements AppService {
    private final Map<GFXType, Synchronizer> synchronizerMap;
    private final FileProperties fileProperties;
    private final PropertyFiller propertyFiller;
    private final PropertiesHelper propertiesHelper;

    public SpriteTypesService(List<Synchronizer> synchronizerList,
                              FileProperties fileProperties,
                              PropertyFiller propertyFiller,
                              PropertiesHelper propertiesHelper) {
        this.synchronizerMap = initMap(synchronizerList);
        this.fileProperties = fileProperties;
        this.propertyFiller = propertyFiller;
        this.propertiesHelper = propertiesHelper;
    }

    private Map<GFXType,Synchronizer> initMap(List<Synchronizer> synchronizerList) {
        Map<GFXType, Synchronizer> map = new HashMap<>();
        for (Synchronizer s : synchronizerList) {
            map.put(s.forType(), s);
        }
        return map;
    }

    public void start() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/gfx/gfx.input");
        GfxProperties gfxProperties = propertyFiller.fillObject(map, GfxProperties::new);
        List<String> toSynchronize = getToSynchronize(gfxProperties);
        List<GFXType> types = mapToTypes(toSynchronize);
        for (GFXType type : types) {
            Synchronizer synchronizer = synchronizerMap.get(type);
            if (synchronizer == null) {
                throw new RuntimeException("No synchronizer defined for type: " + type);
            }
            synchronizer.sync(gfxProperties);
        }
    }

    private List<GFXType> mapToTypes(List<String> toSynchronize) {
        List<GFXType> types = new ArrayList<>();
        for (String s : toSynchronize) {
            Collection<GFXType> mappedTypes = mapType(s);
            types.addAll(mappedTypes);
        }
        return types;
    }

    private Collection<GFXType> mapType(String s) {
        return switch (s) {
            case "goals" -> List.of(GFXType.GOAL, GFXType.GOAL_SHINE);
            case "ideas" -> List.of(GFXType.IDEA);
            case "events" -> List.of(GFXType.EVENT);
            case "characters" -> List.of(GFXType.CHARACTER);
            default -> throw new InvalidInputException("Invalid synchronizations type: " + s);
        };
    }

    private List<String> getToSynchronize(GfxProperties gfxProperties) {
        String active = gfxProperties.getActive();
        return propertiesHelper.listedProperties(active);
    }
}
