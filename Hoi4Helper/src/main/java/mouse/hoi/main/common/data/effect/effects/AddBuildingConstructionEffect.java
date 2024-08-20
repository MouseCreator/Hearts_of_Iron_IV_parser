package mouse.hoi.main.common.data.effect.effects;

import lombok.Data;
import mouse.hoi.main.common.data.effect.UseEffect;
import mouse.hoi.main.common.data.effect.SpecialEffect;
import mouse.hoi.main.common.data.scope.ScopeEnum;
import mouse.hoi.tools.parser.impl.dom.active.ActiveObject;
import mouse.hoi.tools.parser.impl.dom.active.ActiveWriter;
import mouse.hoi.tools.parser.impl.dom.active.ObjectActiveWriter;

import java.util.Optional;
@UseEffect(key = "add_building_construction", scope=ScopeEnum.STATE)
public class AddBuildingConstructionEffect extends SpecialEffect {
    private String type;
    private int level;
    private boolean instant;
    private Integer provinceSimple = null;
    private ProvinceComplex provinceComplex = null;
    @Data
    private static class ProvinceComplex {
        private boolean allProvinces;
        private boolean limitToBorder;
        private boolean limitToCoastal;
        private boolean limitToNavalBase;
        private String limitToBorderCountry;
        private boolean limitToVictoryPoint;

        public ProvinceComplex() {
            allProvinces = false;
            limitToBorder = false;
            limitToCoastal = false;
            limitToNavalBase = false;
            limitToBorderCountry = null;
            limitToVictoryPoint = false;
        }
    }
    @Override
    public void read(ActiveObject activeObject) {
        type = activeObject.getString("bunker");
        level = activeObject.getInteger("level");
        instant = activeObject.getBoolean("instant_build");
        Optional<ActiveObject> province = activeObject.optionalData("province");
        if (province.isPresent()) {
            ActiveObject provinceData = province.get();
            if (provinceData.isSimple()) {
                provinceSimple = provinceData.simple().intValue();
            } else {
                provinceComplex = new ProvinceComplex();
                provinceData.optionalBoolean("all_provinces").ifPresent(provinceComplex::setAllProvinces);
                provinceData.optionalBoolean("limit_to_coastal").ifPresent(provinceComplex::setLimitToCoastal);
                provinceData.optionalBoolean("limit_to_naval_base").ifPresent(provinceComplex::setLimitToNavalBase);
                provinceData.optionalBoolean("limit_to_border").ifPresent(provinceComplex::setLimitToBorder);
                provinceData.optionalString("limit_to_border_country").ifPresent(provinceComplex::setLimitToBorderCountry);
                provinceData.optionalBoolean("limit_to_victory_point").ifPresent(provinceComplex::setLimitToVictoryPoint);
            }
        }
    }

    @Override
    public ActiveWriter write() {
        ObjectActiveWriter effectData = ObjectActiveWriter.object();
        effectData.put("type", type);
        effectData.put("level", level);
        if (instant)
            effectData.put("instant_build", true);
        if (provinceSimple != null) {
            effectData.put("province", provinceSimple);
        }
        else if (provinceComplex != null) {
            ObjectActiveWriter provinceData = ObjectActiveWriter.object();
            if (provinceComplex.allProvinces) {
                provinceData.put("all_provinces", true);
            }
            if (provinceComplex.limitToBorder) {
                provinceData.put("limit_to_border", true);
            }
            if (provinceComplex.limitToCoastal) {
                provinceData.put("limit_to_coastal", true);
            }
            if (provinceComplex.limitToNavalBase) {
                provinceData.put("limit_to_naval_base", true);
            }
            if (provinceComplex.limitToBorderCountry != null) {
                provinceData.put("limit_to_border_country", provinceComplex.limitToBorderCountry);
            }
            if (provinceComplex.limitToVictoryPoint) {
                provinceData.put("limit_to_victory_point", true);
            }
            effectData.put("province", provinceData);
        }
        return effectData;
    }
}
