package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.KaleidoConverter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancer;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@KaleidoConverter
@TypeRecognition(sourceClass = Number.class, targetClass = long.class)
public class UnBoxLongConverter extends AbstractConverter<Number, Long> {
    @Override
    public Long convert(Number source) {
        if (source == null) {
            Instancer<Long> instancer = kaleidoscope.getInstancer(long.class);
            if (instancer == null) {
                throw new KaleidoException("没有找到[long]基本数据类型的Instancer");
            }
            return instancer.getDefault();
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
