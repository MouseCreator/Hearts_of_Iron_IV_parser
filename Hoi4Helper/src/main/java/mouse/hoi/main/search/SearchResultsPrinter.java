package mouse.hoi.main.search;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchResultsPrinter {
    public void print(List<SearchOccurrence> occurrenceList) {
        for (SearchOccurrence occurrence : occurrenceList) {
            System.out.println(occurrence.print());
        }
        System.out.println("Total: " + occurrenceList.size());
    }
}
