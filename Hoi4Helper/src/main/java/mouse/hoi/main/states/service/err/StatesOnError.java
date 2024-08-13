package mouse.hoi.main.states.service.err;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import mouse.hoi.main.states.service.assign.StateAssigner;
import mouse.hoi.tools.files.FileManager;
import mouse.hoi.tools.files.PathsLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StatesOnError {
    private final PathsLoader pathsLoader;
    private final FileManager fileManager;
    public void fixErrors(String folder, Function<String, String> fixing) {
        List<String> stateFiles = pathsLoader.allFiles(folder, true, true);
        for (String file : stateFiles) {
            String content = fileManager.read(file);
            String applid = fixing.apply(content);
            fileManager.write(file, applid);
        }
    }
}
