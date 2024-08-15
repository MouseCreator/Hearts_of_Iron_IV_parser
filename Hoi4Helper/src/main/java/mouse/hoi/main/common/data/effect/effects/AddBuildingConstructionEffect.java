package mouse.hoi.main.common.data.effect.effects;

import lombok.Data;
import mouse.hoi.main.common.data.effect.store.EffectData;
import mouse.hoi.main.common.data.effect.SpecialEffect;
import mouse.hoi.main.common.data.effect.store.EffectDataObj;
import mouse.hoi.main.common.data.effect.store.EffectDataSimple;
import mouse.hoi.main.common.data.scope.ScopeEnum;

import java.util.List;
import java.util.Optional;
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
    public List<ScopeEnum> scopes() {
        return List.of(ScopeEnum.STATE);
    }

    @Override
    public String getEffectName() {
        return "add_building_construction";
    }

    @Override
    public void read(EffectData effectDataInput) {
        EffectDataObj effectData = effectDataInput.object();
        type = effectData.getString("bunker");
        level = effectData.getInteger("level");
        instant = effectData.getBoolean("instant_build");
        Optional<EffectData> province = effectData.optionalData("province");
        if (province.isPresent()) {
            EffectData provinceData = province.get();
            if (provinceData.isSimple()) {
                provinceSimple = provinceData.simple().intValue();
            } else {
                EffectDataObj object = provinceData.object();
                provinceComplex = new ProvinceComplex();
                object.optionalBoolean("all_provinces").ifPresent(provinceComplex::setAllProvinces);
                object.optionalBoolean("limit_to_coastal").ifPresent(provinceComplex::setLimitToCoastal);
                object.optionalBoolean("limit_to_naval_base").ifPresent(provinceComplex::setLimitToNavalBase);
                object.optionalBoolean("limit_to_border").ifPresent(provinceComplex::setLimitToBorder);
                object.optionalString("limit_to_border_country").ifPresent(provinceComplex::setLimitToBorderCountry);
                object.optionalBoolean("limit_to_victory_point").ifPresent(provinceComplex::setLimitToVictoryPoint);
            }
        }
    }

    @Override
    public EffectData write() {
        EffectDataObj effectData = new EffectDataObj();
        effectData.put("type", type);
        effectData.put("level", level);
        if (instant)
            effectData.put("instant_build", true);
        if (provinceSimple != null) {
            EffectDataSimple simple = new EffectDataSimple();
            simple.putSimple(provinceSimple);
            effectData.put("province", simple);
        }
        else if (provinceComplex != null) {
            EffectDataObj provinceData = new EffectDataObj();
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
