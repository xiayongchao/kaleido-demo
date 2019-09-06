package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@Converter
@TypeRecognition(sourceClass = Number.class, targetClass = Integer.class)
public class BoxingIntegerConverter extends AbstractConverter<Number, Integer> {
    @Override
    public Integer convert(Number source) {
        return source == null ? null : source.intValue();
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
    protected Integer newTarget(Number source) {
        throw new KaleidoException("[Integer]基本数据类型不支持newTarget操作");
    }
}
