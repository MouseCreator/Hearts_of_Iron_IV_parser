package mouse.hoi.main.bookmark.writer;

import mouse.hoi.main.bookmark.data.BookmarksWrapper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class BookmarkWrapperWriter implements DataWriter<BookmarksWrapper> {
    @Override
    public Class<BookmarksWrapper> forType() {
        return BookmarksWrapper.class;
    }

    @Override
    public void write(SpecialWriter writer, BookmarksWrapper object) {
        writer.list().block("bookmarks", object::getBookmarksList);
    }
}
