package mouse.hoi.main.bookmark.data;

import lombok.Data;
import mouse.hoi.tools.parser.impl.reader.Inits;

import java.util.ArrayList;
import java.util.List;
@Data
public class BookmarksWrapper implements Inits {
    private List<Bookmarks> bookmarksList;

    @Override
    public void initialize() {
        bookmarksList = new ArrayList<>();
    }
}
