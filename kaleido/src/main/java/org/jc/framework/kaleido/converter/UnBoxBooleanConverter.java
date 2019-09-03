package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancer;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = Boolean.class, targetClass = boolean.class)
public class UnBoxBooleanConverter extends AbstractConverter<Boolean, Boolean> {
    @Override
    public Boolean convert(Boolean source) {
        if (source == null) {
            Instancer<Boolean> instancer = kaleidoscope.getInstancer(boolean.class);
            if (instancer == null) {
                throw new KaleidoException("没有找到[boolean]基本数据类型的Instancer");
            }
            return instancer.getDefault();
        }
        return source;
    }

    @Override
    public void copyProperties(Boolean source, Boolean target) {
        throw new KaleidoException("[boolean]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Boolean newTarget() {
        throw new KaleidoException("[boolean]基本数据类型不支持newTarget操作");
    }
}
