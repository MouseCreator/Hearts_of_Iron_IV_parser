package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmark;
import mouse.hoi.main.bookmark.data.Bookmarks;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarksReader implements DataReader<Bookmarks> {
    private final DomQueryService domQueryService;
    @Override
    public Class<Bookmarks> forType() {
        return Bookmarks.class;
    }

    @Override
    public Bookmarks read(DomData domData) {
        Bookmarks bookmarks = new Bookmarks();
        DomObjectQuery query = domQueryService.validateAndQueryObject(domData);
        query.onToken("bookmark").object(Bookmark.class).push(bookmarks::getList);
        return bookmarks;
    }
}
