package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancer;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Number.class, targetClass = Byte.class)
public class BoxingByteConverter extends AbstractConverter<Number, Byte> {
    @Override
    public Byte convert(Number source) {
        if (source == null) {
            Instancer<Byte> instancer = kaleidoscope.getInstancer(Byte.class);
            if (instancer == null) {
                throw new KaleidoException("没有找到[Byte]基本数据类型的Instancer");
            }
            return instancer.getDefault();
        }
        return source.byteValue();
    }

    @Override
    public void copyProperties(Number source, Byte target) {
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
