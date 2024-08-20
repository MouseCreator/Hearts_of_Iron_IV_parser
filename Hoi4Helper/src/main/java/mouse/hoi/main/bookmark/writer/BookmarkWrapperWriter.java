package mouse.hoi.main.bookmark.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.BookmarksWrapper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkWrapperWriter implements DataWriter<BookmarksWrapper> {

    private final WriterSupport writerSupport;
    @Override
    public Class<BookmarksWrapper> forType() {
        return BookmarksWrapper.class;
    }
    @Override
    public DWData write(BookmarksWrapper object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.listAll("bookmarks").objects(object::getBookmarksList);
        return b.get();
    }
}
