package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@TypeRecognition(targetClass = long.class)
public class UnBoxLongInstancer extends AbstractInstancer<Long> {
    public UnBoxLongInstancer() {
        super((long) 0);
    }

    @Override
    public Long newInstance() {
        throw new KaleidoException("[long]基本数据类型不支持newInstance操作");
    }
}
