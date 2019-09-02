package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancer;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Number.class, targetClass = Integer.class)
public class BoxingIntegerConverter extends AbstractConverter<Number, Integer> {
    @Override
    public Integer convert(Number source) {
        if (source == null) {
            Instancer<Integer> instancer = kaleidoscope.getInstancer(Integer.class);
            if (instancer == null) {
                throw new KaleidoException("没有找到[Integer]基本数据类型的Instancer");
            }
            return instancer.getDefault();
        }
        return source.intValue();
    }

    @Override
    public void copyProperties(Number source, Integer target) {
        throw new KaleidoException("[Integer]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Integer newTarget() {
        throw new KaleidoException("[Integer]基本数据类型不支持newTarget操作");
    }
}
