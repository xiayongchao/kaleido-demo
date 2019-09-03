package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@TypeRecognition(targetClass = byte.class)
public class UnBoxByteInstancer extends AbstractInstancer<Byte> {
    public UnBoxByteInstancer() {
        super((byte) 0);
    }

    @Override
    public Byte newInstance() {
        throw new KaleidoException("[byte]基本数据类型不支持newInstance操作");
    }
}
