package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmark;
import mouse.hoi.main.bookmark.data.Bookmarks;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarksReader implements DataReader<Bookmarks> {
    private final Readers readers;
    @Override
    public Class<Bookmarks> forType() {
        return Bookmarks.class;
    }

    @Override
    public void onKeyValue(Bookmarks object, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("bookmark").mapBlock(b -> readers.interpreters().read(Bookmark.class, b)).push(object::getList)
                .orElseThrow();
    }
}
