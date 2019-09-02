package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@TypeRecognition(targetClass = Byte.class)
public class ByteInstancer extends AbstractInstancer<Byte> {

    public ByteInstancer() {
        super((byte) 0);
    }

    @Override
    public Byte newInstance() {
        throw new KaleidoException("[Byte/byte]基本数据类型不支持newInstance操作");
    }
}
