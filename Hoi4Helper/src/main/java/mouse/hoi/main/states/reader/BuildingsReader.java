package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.main.states.data.StateEnums;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftRightValue;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingsReader implements DataReader<Buildings> {

    private final Readers readers;
    @Override
    public Class<Buildings> forType() {
        return Buildings.class;
    }

    @Override
    public void onKeyValue(Buildings buildings, LeftValue leftValue, RightValue rightValue) {
        String[] buildConst = StateEnums.BUILDINGS;

        String type = leftValue.stringValue();
        for(String v : buildConst) {
            if (type.equalsIgnoreCase(v)) {
                int level = rightValue.intValue();
                buildings.addBuilding(type, level);
                return;
            }
        }
        if (leftValue.isInteger()) {
            createProvinceBuildings(buildings, leftValue, rightValue);
            return;
        }
        throw new ReaderException("Unknown building type: " + leftValue);
    }

    private void createProvinceBuildings(Buildings buildings, LeftValue leftValue, RightValue rightValue) {
        String[] provConst = StateEnums.PROVINCE_BUILDINGS;
        int province = leftValue.intValue();
        BlockNode blockNode = rightValue.blockValue();
        List<LeftRightValue> leftRightValues = readers.interpreters().getLeftRightValues(blockNode);
        for (LeftRightValue lrValues : leftRightValues) {
            LeftValue leftSub = lrValues.getLeftValue();
            String typeS = leftSub.stringValue();
            boolean match = false;
            for (String str : provConst) {
                if (str.equals(typeS)) {
                    int level = lrValues.getRightValue().intValue();
                    buildings.addProvinceBuilding(province, typeS, level);
                    match = true;
                    break;
                }
            }
            if (!match) {
                throw new ReaderException("Unexpected province building: " + typeS);
            }
        }
    }
}
