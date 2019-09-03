package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.KaleidoInstancer;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@KaleidoInstancer
@TypeRecognition(targetClass = float.class)
public class UnBoxFloatInstancer extends AbstractInstancer<Float> {
    public UnBoxFloatInstancer() {
        super((float) 0);
    }

    @Override
    public Float newInstance() {
        throw new KaleidoException("[float]基本数据类型不支持newInstance操作");
    }
}
