package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.InstanceSupport;

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
            InstanceSupport<Double> instanceSupport = kaleidoscope.getInstancer(double.class);
            if (instanceSupport == null) {
                throw new KaleidoException("没有找到[double]基本数据类型的Instancer");
            }
            return instanceSupport.getDefault();
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
    protected Double newTarget(Number source) {
        throw new KaleidoException("[double]基本数据类型不支持newTarget操作");
    }
}
