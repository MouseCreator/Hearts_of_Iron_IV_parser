package mouse.hoi.tools.utils;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OptionalCastTest {
    private static class A {
    }
    private static class B extends A {
    }
    @Test
    void cast_Valid() {
        Object object = new B();
        Optional<A> a = OptionalCast.cast(object, A.class);
        assertTrue(a.isPresent());
        Optional<B> b = OptionalCast.cast(a.get(), B.class);
        assertTrue(b.isPresent());
    }
    @Test
    void cast_InValid() {
        A a = new A();
        Optional<B> b = OptionalCast.cast(a, B.class);
        assertTrue(b.isEmpty());
    }
}