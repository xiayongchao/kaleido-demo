package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@TypeRecognition(targetClass = short.class)
public class UnBoxShortInstancer extends AbstractInstancer<Short> {
    public UnBoxShortInstancer() {
        super((short) 0);
    }

    @Override
    public Short newInstance() {
        throw new KaleidoException("[short]基本数据类型不支持newInstance操作");
    }
}
