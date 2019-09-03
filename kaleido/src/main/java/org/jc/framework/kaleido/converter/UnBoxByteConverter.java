package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancer;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Byte.class, targetClass = byte.class)
public class UnBoxByteConverter extends AbstractConverter<Number, Byte> {
    @Override
    public Byte convert(Number source) {
        if (source == null) {
            Instancer<Byte> instancer = kaleidoscope.getInstancer(byte.class);
            if (instancer == null) {
                throw new KaleidoException("没有找到[byte]基本数据类型的Instancer");
            }
            return instancer.getDefault();
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
    protected Byte newTarget() {
        throw new KaleidoException("[byte]基本数据类型不支持newTarget操作");
    }
}
