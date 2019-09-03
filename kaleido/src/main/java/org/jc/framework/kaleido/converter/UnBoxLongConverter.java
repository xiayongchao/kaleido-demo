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
@TypeRecognition(sourceClass = Number.class, targetClass = long.class)
public class UnBoxLongConverter extends AbstractConverter<Number, Long> {
    @Override
    public Long convert(Number source) {
        if (source == null) {
            Instancers<Long> instancers = kaleidoscope.getInstancer(long.class);
            if (instancers == null) {
                throw new KaleidoException("没有找到[long]基本数据类型的Instancer");
            }
            return instancers.getDefault();
        }
        return source.longValue();
    }

    @Override
    public void copyProperties(Number source, Long target) {
        throw new KaleidoException("[long]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Long newTarget() {
        throw new KaleidoException("[long]基本数据类型不支持newTarget操作");
    }
}
