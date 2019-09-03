package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Number.class, targetClass = Double.class)
public class BoxingDoubleConverter extends AbstractConverter<Number, Double> {
    @Override
    public Double convert(Number source) {
        return source == null ? null : source.doubleValue();
    }

    @Override
    public void copyProperties(Number source, Double target) {
        throw new KaleidoException("[Double]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Double newTarget() {
        throw new KaleidoException("[Double]基本数据类型不支持newTarget操作");
    }
}
