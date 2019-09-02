package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancer;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Number.class, targetClass = Float.class)
public class BoxingFloatConverter extends AbstractConverter<Number, Float> {
    @Override
    public Float convert(Number source) {
        if (source == null) {
            Instancer<Float> instancer = kaleidoscope.getInstancer(Float.class);
            if (instancer == null) {
                throw new KaleidoException("没有找到[Float]基本数据类型的Instancer");
            }
            return instancer.getDefault();
        }
        return source.floatValue();
    }

    @Override
    public void copyProperties(Number source, Float target) {
        throw new KaleidoException("[Float]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Float newTarget() {
        throw new KaleidoException("[Float]基本数据类型不支持newTarget操作");
    }
}
