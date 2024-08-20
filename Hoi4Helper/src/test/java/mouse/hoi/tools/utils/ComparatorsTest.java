package mouse.hoi.tools.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorsTest {

    @Test
    void testNumberAware() {
        int compare = Comparators.numberAwareComparator.compare("2", "100");
        assertTrue(compare < 0);
    }
}