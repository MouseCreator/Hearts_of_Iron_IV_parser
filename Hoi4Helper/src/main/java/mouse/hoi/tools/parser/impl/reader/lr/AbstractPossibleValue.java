package mouse.hoi.tools.parser.impl.reader.lr;


import lombok.EqualsAndHashCode;
import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.reader.subs.SubscriptObject;
@EqualsAndHashCode
public abstract class AbstractPossibleValue implements LeftValue, SimpleValue, RightValue{
    @Override
    public boolean isSubscript() {
        return false;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isDate() {
        return false;
    }

    @Override
    public SubscriptObject subscriptValue() {
        throw PossibleValues.notSubscript(this);
    }

    @Override
    public int intValue() {
        throw PossibleValues.notInt(this);
    }

    @Override
    public boolean boolValue() {
        throw PossibleValues.notBoolean(this);
    }

    @Override
    public double doubleValue() {
        throw PossibleValues.notDouble(this);
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    @Override
    public BlockNode blockValue() {
        throw PossibleValues.notBlock(this);
    }

    @Override
    public GameDate dateValue() {
        throw PossibleValues.notDate(this);
    }
}
