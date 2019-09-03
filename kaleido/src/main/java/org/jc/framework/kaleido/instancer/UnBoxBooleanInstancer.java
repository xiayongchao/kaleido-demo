package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@TypeRecognition(targetClass = boolean.class)
public class UnBoxBooleanInstancer extends AbstractInstancer<Boolean> {
    public UnBoxBooleanInstancer() {
        super(false);
    }

    @Override
    public Boolean newInstance() {
        throw new KaleidoException("[boolean]基本数据类型不支持newInstance操作");
    }
}
