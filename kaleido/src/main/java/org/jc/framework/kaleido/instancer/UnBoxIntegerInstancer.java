package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@TypeRecognition(targetClass = int.class)
public class UnBoxIntegerInstancer extends AbstractInstancer<Integer> {
    public UnBoxIntegerInstancer() {
        super((int) 0);
    }

    @Override
    public Integer newInstance() {
        throw new KaleidoException("[int]基本数据类型不支持newInstance操作");
    }
}
