package mouse.hoi.main.units;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.base.ModBasic;
import mouse.hoi.main.bookmark.data.BookmarksWrapper;
import mouse.hoi.main.bookmark.service.BookmarkLoader;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import mouse.hoi.tools.context.AppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitsGeneratorService implements AppService {

    private final CountryVictoryPointsFactory factory;
    private final BookmarkLoader bookmarkLoader;
    private final StateSaverLoader stateSaverLoader;
    private final UnitsGenerator unitsGenerator;
    private final ModBasic modBasic;
    private final UnitInputLoader unitInputLoader;
    @Override
    public void start() {
        BookmarksWrapper bookmark = bookmarkLoader.load();
        Effects effects = bookmark.getBookmarksList().getFirst().getList().getFirst().getEffects();
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates();
        CountryVictoryPoints countryVictoryPoints = factory.forCountry(effects, loadedStates);
        String dir = modBasic.modDirectory();
        UnitsInput unitInput = unitInputLoader.load();
        unitsGenerator.generateUnits(countryVictoryPoints, unitInput, dir);
    }
}
