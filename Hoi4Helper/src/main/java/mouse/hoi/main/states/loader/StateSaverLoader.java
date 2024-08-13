package mouse.hoi.main.states.loader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateWrapper;
import mouse.hoi.tools.files.PathManager;
import mouse.hoi.tools.files.PathsLoader;
import mouse.hoi.tools.parser.impl.reader.ReaderService;
import mouse.hoi.tools.parser.impl.writer.WriterService;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StateSaverLoader {
    private final FileProperties fileProperties;
    private final PathsLoader pathsLoader;
    private final PathManager pathManager;
    private final ReaderService readerService;
    private final WriterService writerService;

    public List<LoadedState> loadAllStates() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/states/init.input");
        String directory = map.expectedProperty("directory");
        return loadAllStates(directory);
    }
    public List<LoadedState> loadAllStates(String stateDirectory) {
        List<String> files = pathsLoader.allFiles(stateDirectory, true, true);
        List<LoadedState> loadedStates = new ArrayList<>();
        for (String file : files) {
            StateWrapper stateWrapper = readerService.readFromFile(file, StateWrapper.class);
            State state = stateWrapper.getState();
            String shortName = pathManager.getFileNameWithoutExtension(file);
            LoadedState loadedState = new LoadedState(state, shortName, file);
            loadedStates.add(loadedState);
        }
        return loadedStates;
    }

    public void saveState(LoadedState loadedState) {
        StateWrapper wrapper = new StateWrapper();
        wrapper.setState(loadedState.state());
        writerService.writeToFile(loadedState.longName(), wrapper);
    }
    public void saveStates(Collection<LoadedState> states) {
        states.forEach(this::saveState);
    }
}
