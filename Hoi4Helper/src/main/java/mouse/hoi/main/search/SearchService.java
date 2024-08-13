package mouse.hoi.main.search;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyFiller;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {

    private final PropertyFiller propertyFiller;
    private final FileProperties fileProperties;
    private final InFilesSearch inFilesSearch;
    private final SearchResultsPrinter printer;
    public void search() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/search/init.input");
        SearchParams searchParams = propertyFiller.fillObject(map, SearchParams::new);
        List<SearchOccurrence> searchOccurrences = inFilesSearch.find(searchParams);
        printer.print(searchOccurrences);
    }
}
