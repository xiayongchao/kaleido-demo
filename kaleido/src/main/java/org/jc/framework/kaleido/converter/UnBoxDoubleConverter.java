package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancers;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@Converter
@TypeRecognition(sourceClass = Number.class, targetClass = double.class)
public class UnBoxDoubleConverter extends AbstractConverter<Number, Double> {
    @Override
    public Double convert(Number source) {
        if (source == null) {
            Instancers<Double> instancers = kaleidoscope.getInstancer(double.class);
            if (instancers == null) {
                throw new KaleidoException("没有找到[double]基本数据类型的Instancer");
            }
            return instancers.getDefault();
        }
        return source.doubleValue();
    }

    @Override
    public void copyProperties(Number source, Double target) {
        throw new KaleidoException("[double]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Double newTarget() {
        throw new KaleidoException("[double]基本数据类型不支持newTarget操作");
    }
}
