package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@Converter
@TypeRecognition(sourceClass = Number.class, targetClass = Byte.class)
public class BoxingByteConverter extends AbstractConverter<Number, Byte> {
    @Override
    public Byte convert(Number source) {
        return source == null ? null : source.byteValue();
    }

    @Override
    public void copyProperties(Number source, Byte target) {
        throw new KaleidoException("[Byte]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Byte newTarget(Number source) {
        throw new KaleidoException("[Byte]基本数据类型不支持newTarget操作");
    }
}
