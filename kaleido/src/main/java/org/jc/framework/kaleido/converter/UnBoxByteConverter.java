package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Byte.class, targetClass = byte.class)
public class UnBoxByteConverter extends AbstractConverter<Byte, Byte> {
    @Override
    public Byte convert(Byte source) {
        return source;
    }

    @Override
    public void copyProperties(Byte source, Byte target) {
        throw new KaleidoException("[Byte/byte]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Byte newTarget() {
        throw new KaleidoException("[Byte/byte]基本数据类型不支持newTarget操作");
    }
}
