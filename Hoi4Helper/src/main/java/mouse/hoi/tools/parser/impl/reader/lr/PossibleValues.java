package mouse.hoi.tools.parser.impl.reader.lr;

import mouse.hoi.exception.PossibleValueException;

public class PossibleValues {
    public static PossibleValueException notInt(PossibleValue simpleValue) {
        return new PossibleValueException("Not an integer: " + simpleValue);
    }

    public static PossibleValueException notDouble(PossibleValue simpleValue) {
        return new PossibleValueException("Not a double: " + simpleValue);
    }
    public static PossibleValueException notSubscript(PossibleValue simpleValue) {
        return new PossibleValueException("Not a subscript object: " + simpleValue);
    }

    public static PossibleValueException notBoolean(PossibleValue simpleValue) {
        return new PossibleValueException("Not a boolean: " + simpleValue);
    }

    public static PossibleValueException notBlock(PossibleValue simpleValue) {
        return new PossibleValueException("Not a block value: " + simpleValue);
    }

    public static PossibleValueException notDate(PossibleValue possibleValue) {
        return new PossibleValueException("Not a date value: " + possibleValue);
    }
}
