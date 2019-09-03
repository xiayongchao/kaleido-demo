package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.KaleidoInstancer;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@KaleidoInstancer
@TypeRecognition(targetClass = double.class)
public class UnBoxDoubleInstancer extends AbstractInstancer<Double> {
    public UnBoxDoubleInstancer() {
        super((double) 0);
    }

    @Override
    public Double newInstance() {
        throw new KaleidoException("[double]基本数据类型不支持newInstance操作");
    }
}
