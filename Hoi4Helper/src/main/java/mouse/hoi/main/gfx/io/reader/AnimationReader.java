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
        query.onToken("animationmaskfile").string().setOrSkip(animation::setAnimationMaskFile);
        query.onToken("animationtexturefile").string().setOrSkip(animation::setAnimationTextureFile);
        query.onToken("animationrotation").doublev().setOrSkip(animation::setAnimationRotation);
        query.onToken("animationtime").doublev().setOrSkip(animation::setAnimationTime);
        query.onToken("animationdelay").doublev().setOrSkip(animation::setDelaySeconds);
        query.onToken("animationblendmode").string().setOrSkip(animation::setBlendMode);
        query.onToken("animationtype").string().setOrSkip(animation::setType);
        query.onToken("animationlooping").bool().setOrSkip(animation::setLooping);
        query.onToken("animationrotationoffset").object(DPos.class).setOrDefault(animation::setRotationOffset, new DPos(0, 0));
        query.onToken("animationtexturescale").object(DPos.class).setOrDefault(animation::setTextureScale, new DPos(1, 1));
        return animation;
    }
}
