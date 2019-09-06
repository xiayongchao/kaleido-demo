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
@TypeRecognition(sourceClass = Byte.class, targetClass = byte.class)
public class UnBoxByteConverter extends AbstractConverter<Number, Byte> {
    @Override
    public Byte convert(Number source) {
        if (source == null) {
            Instancers<Byte> instancers = kaleidoscope.getInstancer(byte.class);
            if (instancers == null) {
                throw new KaleidoException("没有找到[byte]基本数据类型的Instancer");
            }
            return instancers.getDefault();
        }
        return source.byteValue();
    }

    @Override
    public void copyProperties(Number source, Byte target) {
        throw new KaleidoException("[byte]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Byte newTarget(Number source) {
        throw new KaleidoException("[byte]基本数据类型不支持newTarget操作");
    }
}
