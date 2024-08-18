package mouse.hoi.main.gfx.io.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.Animation;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimationReader implements DataReader<Animation> {

    private final DomQueryService queryService;
    @Override
    public Class<Animation> forType() {
        return Animation.class;
    }
    @Override
    public Animation read(DomData domData) {
        Animation animation = new Animation();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("animationmaskfile").string().set(animation::setAnimationMaskFile);
        query.onToken("animationtexturefile").simple().setString(animation::setAnimationTextureFile);
        query.onToken("animationrotation").doublev().set(animation::setAnimationRotation);
        query.onToken("animationtime").doublev().set(animation::setAnimationTime);
        query.onToken("animationdelay").doublev().set(animation::setDelaySeconds);
        query.onToken("animationblendmode").string().set(animation::setBlendMode);
        query.onToken("animationtype").string().set(animation::setType);
        query.onToken("animationlooping").bool().set(animation::setLooping);
        query.onToken("animationrotationoffset").object(DPos.class).set(animation::setRotationOffset);
        query.onToken("animationtexturescale").object(DPos.class).set(animation::setTextureScale);
        return animation;
    }
}
