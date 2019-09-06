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
@TypeRecognition(sourceClass = Number.class, targetClass = short.class)
public class UnBoxShortConverter extends AbstractConverter<Number, Short> {
    @Override
    public Short convert(Number source) {
        if (source == null) {
            Instancers<Short> instancers = kaleidoscope.getInstancer(short.class);
            if (instancers == null) {
                throw new KaleidoException("没有找到[short]基本数据类型的Instancer");
            }
            return instancers.getDefault();
        }
        return source.shortValue();
    }

    @Override
    public void copyProperties(Number source, Short target) {
        throw new KaleidoException("[short]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Short newTarget(Number source) {
        throw new KaleidoException("[short]基本数据类型不支持newTarget操作");
    }
}
