package mouse.hoi.main.gfx.service;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.files.PathsLoader;
import mouse.hoi.tools.parser.impl.reader.ReaderService;
import mouse.hoi.tools.parser.impl.writer.WriterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SpriteTypesSynchronizer {

    private final DistinctSpriteTypesManager distinctSpriteTypesManager;
    private final PathsLoader pathsLoader;
    private final ReaderService readerService;
    private final WriterService writerService;

    public void synchronizeAndWrite(String file, String baseDirectory, Function<String, SpriteType> factory) {
        SpriteTypesWrapper spriteTypesWrapper = readerService.readFromFile(file, SpriteTypesWrapper.class);
        SpriteTypesWrapper fixed = synchronize(spriteTypesWrapper, baseDirectory, factory);
        writerService.writeToFile(file, fixed);
    }

    public SpriteTypesWrapper synchronize(String file, String baseDirectory, Function<String, SpriteType> factory) {
        SpriteTypesWrapper spriteTypesWrapper = readerService.readFromFile(file, SpriteTypesWrapper.class);
        return synchronize(spriteTypesWrapper, baseDirectory, factory);
    }
    public SpriteTypesWrapper synchronize(SpriteTypesWrapper spriteTypesWrapper, String baseDirectory, Function<String, SpriteType> factory) {
        List<String> strings = pathsLoader.allFiles(baseDirectory, false, true);
        List<SpriteType> spriteTypeList = spriteTypesWrapper.merged().getSpriteTypeList();
        DistinctTypesResult distinct = distinctSpriteTypesManager.onDistinct(spriteTypeList, strings);
        return createWrapper(factory, spriteTypeList, distinct);
    }

    private SpriteTypesWrapper createWrapper(Function<String, SpriteType> supplier, List<SpriteType> spriteTypeList, DistinctTypesResult distinct) {
        SpriteTypesWrapper wrapper = new SpriteTypesWrapper();
        SpriteTypes spriteTypes = new SpriteTypes();
        List<SpriteType> resultSpriteTypes = new ArrayList<>(spriteTypeList);
        resultSpriteTypes.removeAll(distinct.getNoLongerInUse());
        List<SpriteType> missing = createSpriteTypesFromFilenames(supplier, distinct.getMissing());
        resultSpriteTypes.addAll(missing);
        spriteTypes.setSpriteTypeList(resultSpriteTypes);
        wrapper.setSpriteTypes(List.of(spriteTypes));
        return wrapper;
    }

    private List<SpriteType> createSpriteTypesFromFilenames(Function<String, SpriteType> supplier, List<String> missing) {
        return missing.stream().map(supplier).toList();
    }

}
