package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancers;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@Converter
@TypeRecognition(sourceClass = Boolean.class, targetClass = boolean.class)
public class UnBoxBooleanConverter extends AbstractConverter<Boolean, Boolean> {
    @Override
    public Boolean convert(Boolean source) {
        if (source == null) {
            Instancers<Boolean> instancers = kaleidoscope.getInstancer(boolean.class);
            if (instancers == null) {
                throw new KaleidoException("没有找到[boolean]基本数据类型的Instancer");
            }
            return instancers.getDefault();
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
    protected Boolean newTarget(Boolean source) {
        throw new KaleidoException("[boolean]基本数据类型不支持newTarget操作");
    }
}
