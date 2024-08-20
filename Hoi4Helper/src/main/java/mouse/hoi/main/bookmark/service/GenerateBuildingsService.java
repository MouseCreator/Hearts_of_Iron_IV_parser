package mouse.hoi.main.bookmark.service;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmark;
import mouse.hoi.main.bookmark.data.BookmarksWrapper;
import mouse.hoi.main.common.data.effect.Effect;
import mouse.hoi.main.common.data.effect.effects.AddBuildingConstructionEffect;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.data.scope.StateScope;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.database.DatabaseManager;
import mouse.hoi.tools.parser.impl.reader.ReaderService;
import mouse.hoi.tools.parser.impl.writer.WriterService;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GenerateBuildingsService implements AppService {

    private final BookmarkGenerateBuildings generateBuildings;
    private final OwnerMapFactory ownerMapFactory;
    private final DatabaseManager databaseManager;
    private final FileProperties fileProperties;
    private final ReaderService readerService;
    private final WriterService writerService;
    private final BuildPropertiesReader reader;
    @Override
    public void start() {
        databaseManager.loadCountries();
        PropertyMap map = fileProperties.readProperties("src/main/resources/bookmark/init.input");
        String filename = map.expectedProperty("file");
        BookmarksWrapper bookmarksWrapper = readerService.readFromFile(filename, BookmarksWrapper.class);
        Bookmark bookmark = bookmarksWrapper.getBookmarksList().getFirst().getList().getFirst();
        Effects effects = bookmark.getEffects();
        StateOwnersMap owners = ownerMapFactory.owners(effects);
        BuildingInputs inputs = reader.read();
        Map<Integer, PreparedBuildings> buildingsMap = generateBuildings.generateBuildings(owners, inputs);
        addToEffects(buildingsMap, effects);
        writerService.writeToFile(filename, bookmarksWrapper);
    }

    private void addToEffects(Map<Integer, PreparedBuildings> map, Effects effects) {
        Set<Integer> integers = map.keySet();
        for (int i : integers) {
            Optional<Effects> subEffect = effects.getSubEffect(String.valueOf(i));
            Effects subs;
            if (subEffect.isEmpty()) {
                subs = new Effects(new StateScope());
                effects.addSubEffects(i, subs);
            } else {
                subs = subEffect.get();
            }
            List<Effect> effectsIn = createEffect(map.get(i));
            for (Effect effect : effectsIn) {
                subs.simpleEffects().putEffect(effect);
            }
        }
    }

    private List<Effect> createEffect(PreparedBuildings preparedBuildings) {
        Set<String> keys = preparedBuildings.keys();
        List<Effect> effects = new ArrayList<>();
        for (String type : keys) {
            int level = preparedBuildings.level(type);
            AddBuildingConstructionEffect effect = AddBuildingConstructionEffect.build(type, level);
            effects.add(effect);
        }
        return effects;
    }
}
