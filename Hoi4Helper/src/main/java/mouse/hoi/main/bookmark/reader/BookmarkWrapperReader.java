package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmarks;
import mouse.hoi.main.bookmark.data.BookmarksWrapper;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkWrapperReader implements DataReader<BookmarksWrapper> {

    private final Readers readers;
    @Override
    public Class<BookmarksWrapper> forType() {
        return BookmarksWrapper.class;
    }

    @Override
    public void onKeyValue(BookmarksWrapper object, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("bookmarks").mapBlock(b -> readers.interpreters().read(Bookmarks.class, b)).push(object::getBookmarksList)
                .orElseThrow();
    }
}
