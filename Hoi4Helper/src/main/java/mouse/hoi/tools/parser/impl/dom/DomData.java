package mouse.hoi.tools.parser.impl.dom;

import mouse.hoi.exception.DomException;

public interface DomData {
    default boolean isSimple() {
        return false;
    }
    default boolean isObject() {
        return false;
    }
    default boolean isList() {
        return false;
    }
    default boolean isComplex() {
        return false;
    }

    default DomSimple simple() {
        throw new DomException("Not a simple DOM data: " + this);
    }

    default DomList list() {
        throw new DomException("Not a list DOM data: " + this);
    }

    default DomObject object() {
        throw new DomException("Not an object DOM data: " + this);
    }
    default DomComplex complex() {
        throw new DomException("Not a complex DOM data: " + this);
    }


}