package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancer;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Number.class, targetClass = Short.class)
public class BoxingShortConverter extends AbstractConverter<Number, Short> {
    @Override
    public Short convert(Number source) {
        if (source == null) {
            Instancer<Short> instancer = kaleidoscope.getInstancer(Short.class);
            if (instancer == null) {
                throw new KaleidoException("没有找到[Short]基本数据类型的Instancer");
            }
            return instancer.getDefault();
        }
        return source.shortValue();
    }

    @Override
    public void copyProperties(Number source, Short target) {
        throw new KaleidoException("[Short]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Short newTarget() {
        throw new KaleidoException("[Short]基本数据类型不支持newTarget操作");
    }
}
