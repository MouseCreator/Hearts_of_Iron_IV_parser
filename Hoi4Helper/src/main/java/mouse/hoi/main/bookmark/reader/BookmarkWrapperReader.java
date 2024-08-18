package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmarks;
import mouse.hoi.main.bookmark.data.BookmarksWrapper;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkWrapperReader implements DataReader<BookmarksWrapper> {

    private final DomQueryService service;
    @Override
    public Class<BookmarksWrapper> forType() {
        return BookmarksWrapper.class;
    }

    @Override
    public BookmarksWrapper read(DomData domData) {
        BookmarksWrapper bookmarksWrapper = new BookmarksWrapper();
        DomObjectQuery query = service.validateAndQueryObject(domData);
        query.onToken("bookmarks").object(Bookmarks.class).push(bookmarksWrapper::getBookmarksList);
        return bookmarksWrapper;
    }
}
