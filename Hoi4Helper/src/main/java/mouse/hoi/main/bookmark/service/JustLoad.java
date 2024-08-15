package mouse.hoi.main.bookmark.service;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.BookmarksWrapper;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.database.DatabaseManager;
import mouse.hoi.tools.parser.impl.reader.ReaderService;
import mouse.hoi.tools.parser.impl.writer.WriterService;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JustLoad implements AppService {

    private final FileProperties fileProperties;
    private final ReaderService readerService;
    private final WriterService writerService;
    private final DatabaseManager databaseManager;
    @Override
    public void start() {
        databaseManager.loadCountries();
        PropertyMap map = fileProperties.readProperties("src/main/resources/bookmark/init.input");
        String filename = map.expectedProperty("file");
        BookmarksWrapper bookmarksWrapper = readerService.readFromFile(filename, BookmarksWrapper.class);
        writerService.writeToFile(filename, bookmarksWrapper);
    }
}
